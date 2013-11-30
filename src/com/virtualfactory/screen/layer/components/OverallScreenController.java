package com.virtualfactory.screen.layer.components;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.nifty.builder.ElementBuilder.Align;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Machine;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.entity.E_Purchase;
import com.virtualfactory.entity.E_Ship;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.utils.CommonBuilders;
import com.virtualfactory.utils.GameType;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Sounds;
import com.virtualfactory.utils.StationType;
import com.virtualfactory.utils.Utils;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author David
 */
public class OverallScreenController implements Controller {
    private Nifty nifty;
    private Screen screen;
    private GameEngine gameEngine;
    private WindowControl winControls;
    private boolean isVisible;
    private Element quitPopup;
    private Element gameOverPopup;
    private boolean isExpanded_OperatorCosts = false;
    private boolean isExpanded_MachineEquipmentCosts = false;
    private boolean isExpanded_OtherCosts = false;
    private NiftyImage imageAdd;
    private NiftyImage imageMinus;
    private Align overall_label = Align.Left;
    private Align overall_value = Align.Right;
    private final CommonBuilders common = new CommonBuilders();
    private String sizePreviousImage = "35px";
    private boolean isUpdating = false;
//    private int number = 0;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winOverallControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        Attributes y = new Attributes();
        y.set("closeable", "false");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, y);
        isVisible = false;
        quitPopup = nifty.createPopup("quitPopup");
        gameOverPopup = nifty.createPopup("gameOverByBankruptcy");
        imageAdd = nifty.createImage("Interface/Principal/add_gray.png", false);
        imageMinus = nifty.createImage("Interface/Principal/minus_gray.png", false);
    }
    
    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    @Override
    public void init(final Properties parameter, final Attributes controlDefinitionAttributes) {
        
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onFocus(final boolean getFocus) {
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
    
    @NiftyEventSubscriber(id="overallRefresh")
    public void onRefreshButtonClicked(final String id, final ButtonClickedEvent event) {
        updateData();
    }
    
    public void loadWindowControl(GameEngine game,int index, Pair<Integer,Integer> position){
        this.gameEngine = game;
        if (index == -1){
            winControls.getElement().setVisible(false);
            winControls.getContent().hide(); 
            isVisible = false;
        }else{
            winControls.getElement().setVisible(true);
            winControls.getContent().show();
            isVisible = true;
            if (position != null){
                if (winControls.getWidth() + position.getFirst() > gameEngine.app.getGuiViewPort().getCamera().getWidth())
                    position.setFirst(gameEngine.app.getGuiViewPort().getCamera().getWidth() - winControls.getWidth());
                if (winControls.getHeight() + position.getSecond() > gameEngine.app.getGuiViewPort().getCamera().getHeight())
                    position.setSecond(gameEngine.app.getGuiViewPort().getCamera().getHeight() - winControls.getHeight());
                winControls.getElement().setConstraintX(new SizeValue(position.getFirst() + "px"));
                winControls.getElement().setConstraintY(new SizeValue(position.getSecond() + "px"));
                winControls.getElement().getParent().layoutElements();
            }            
//            winControls.getElement().setConstraintX(null);
//            winControls.getElement().setConstraintY(null);
        }
        loadValues(index);
    }
    
    private void loadValues(int index){
        if (index == -1){
            ((Label)screen.findNiftyControl("currentMoneyValue_WOvC", Label.class)).setText(Utils.formatValue2DecToString(0));
            ((Label)screen.findNiftyControl("totalExpendituresValue_WOvC", Label.class)).setText(Utils.formatValue2DecToString(0));
            ((Label)screen.findNiftyControl("totalIncomesValue_WOvC", Label.class)).setText(Utils.formatValue2DecToString(0));
            ((Label)screen.findNiftyControl("totalProfitValue_WOvC", Label.class)).setText(Utils.formatValue2DecToString(0));
        }else{
            updateData();
        }        
    }

    public void updateData(){
        if (isUpdating) return;
        isUpdating = true;
        double currentMoney = 0;
        double initialMoney = 0;
        double totalOverHead = ((gameEngine.getGameData().getCurrentTimeWithFactor() - gameEngine.getGameData().getInitialTimeWithFactor())/Params.overheadRateInMinutes)*gameEngine.getGameData().getCurrentGame().getOverhead();
        double totalCostOperators = 0;
        double totalHireOperators = gameEngine.getGameData().getTotalOperatorsHire();
        double totalFireOperators = gameEngine.getGameData().getTotalOperatorsFire();
        double totalCostMachines = 0;
        double totalPurchaseMachines = gameEngine.getGameData().getTotalMachinesPurchase();
        double totalPreventiveMaintenanceMachine = gameEngine.getGameData().getTotalMachinePreventiveMaintenance();
        double totalSaleMachines = gameEngine.getGameData().getTotalMachinesSale();
        double totalCostEquipment = 0;
        double totalPurchaseEquipment = gameEngine.getGameData().getTotalEquipmentPurchase();
        double totalPreventiveMaintenanceEquipment = gameEngine.getGameData().getTotalEquipmentPreventiveMaintenance();
        double totalSaleEquipment = gameEngine.getGameData().getTotalEquipmentSale();
        double totalCostStations = 0;
        double totalCostPurchases = 0;
        double totalIncomeShips = 0;
        double totalExpenditures = 0;
        double totalIncomes = 0;
        double totalProfit = 0;
        initialMoney = gameEngine.getGameData().getCurrentGame().getInitialMoney();
//        ((Label)screen.findNiftyControl("initialMoneyValue_WOvC", Label.class)).setText(Utils.formatValue2DecToString(initialMoney));
        for (E_Operator operator : gameEngine.getGameData().getMapUserOperator().values())
            totalCostOperators += operator.updateAndGetEarnedMoney();
        for (E_Machine machine : gameEngine.getGameData().getMapUserMachineByOperation().values())
            totalCostMachines += machine.getAccumulatedCost();
        for (E_Machine machine : gameEngine.getGameData().getMapUserMachineByTransport().values())
            totalCostEquipment += machine.getAccumulatedCost();
        for (E_Station station : gameEngine.getGameData().getMapUserStation().values()){
            if (station.getStationType().equals(StationType.StorageRM) || station.getStationType().equals(StationType.StorageIG) || station.getStationType().equals(StationType.StorageFG)){
                totalCostStations += station.getTotalCostBySlots();
//                for (E_Bucket bucket : station.getArrBuckets())
//                    totalCostStations += bucket.updateAndGetTotalCost();
            }
        }
        for (E_Purchase purchase : gameEngine.getGameData().getMapPurchase().values())
            totalCostPurchases += purchase.getAccumulatedCosts();
        for (E_Ship ship : gameEngine.getGameData().getMapShip().values())
            totalIncomeShips += ship.getIncomeAccumulated();
        
        currentMoney = initialMoney - totalCostOperators - totalHireOperators - totalFireOperators - totalOverHead
                - totalCostMachines - totalPurchaseMachines - totalPreventiveMaintenanceMachine + totalSaleMachines 
                - totalCostEquipment - totalPurchaseEquipment - totalPreventiveMaintenanceEquipment + totalSaleEquipment
                - totalCostStations - totalCostPurchases + totalIncomeShips;
        gameEngine.getGameData().getCurrentGame().setCurrentMoney(Utils.formatValue2Dec(currentMoney));
        
        totalExpenditures = totalCostOperators + totalFireOperators + totalHireOperators + totalCostMachines + totalPurchaseMachines +
                totalPreventiveMaintenanceMachine + totalCostEquipment + totalPurchaseEquipment + totalPreventiveMaintenanceEquipment + 
                totalCostStations + totalCostPurchases + totalOverHead;
        totalIncomes = totalSaleMachines + totalSaleEquipment + totalIncomeShips;
        totalProfit = totalIncomes - totalExpenditures;
        if (isVisible){
            if (isExpanded_OperatorCosts){
                ((Label)screen.findNiftyControl("costOperatorValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalCostOperators) + ")");
                ((Label)screen.findNiftyControl("hireOperatorValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalHireOperators) + ")");
                ((Label)screen.findNiftyControl("fireOperatorValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalFireOperators) + ")");
            }
            if (isExpanded_MachineEquipmentCosts){
                ((Label)screen.findNiftyControl("costMachineValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalCostMachines) + ")");
                ((Label)screen.findNiftyControl("purchaseMachineValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalPurchaseMachines) + ")");
                ((Label)screen.findNiftyControl("preventiveMaintenanceMachineValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalPreventiveMaintenanceMachine) + ")");
                ((Label)screen.findNiftyControl("costEquipmentValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalCostEquipment) + ")");
                ((Label)screen.findNiftyControl("purchaseEquipmentValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalPurchaseEquipment) + ")");
                ((Label)screen.findNiftyControl("preventiveMaintenanceEquipmentValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalPreventiveMaintenanceEquipment) + ")");
            }
            if (isExpanded_OtherCosts){
                ((Label)screen.findNiftyControl("costOverheadValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalOverHead) + ")");
                ((Label)screen.findNiftyControl("costStationsValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalCostStations) + ")");
                ((Label)screen.findNiftyControl("costPurchasesValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalCostPurchases) + ")");
            }
            
            ((Label)screen.findNiftyControl("operatorCostsValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalCostOperators+totalHireOperators+totalFireOperators) + ")");
            ((Label)screen.findNiftyControl("machineEquipmentCostsValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalCostMachines+totalPurchaseMachines+totalPreventiveMaintenanceMachine+totalCostEquipment+totalPurchaseEquipment+totalPreventiveMaintenanceEquipment) + ")");
            ((Label)screen.findNiftyControl("otherCostsValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalOverHead+totalCostStations+totalCostPurchases) + ")");
            
            ((Label)screen.findNiftyControl("saleMachineValue_WOvC", Label.class)).setText(Utils.formatValue2DecToString(totalSaleMachines));
            ((Label)screen.findNiftyControl("saleEquipmentValue_WOvC", Label.class)).setText(Utils.formatValue2DecToString(totalSaleEquipment));
            ((Label)screen.findNiftyControl("incomeForSalesValue_WOvC", Label.class)).setText(Utils.formatValue2DecToString(totalIncomeShips));
            
            ((Label)screen.findNiftyControl("currentMoneyValue_WOvC", Label.class)).setText(Utils.formatValue2DecToString(currentMoney));
            ((Label)screen.findNiftyControl("totalExpendituresValue_WOvC", Label.class)).setText("(" + Utils.formatValue2DecToString(totalExpenditures) + ")");
            ((Label)screen.findNiftyControl("totalIncomesValue_WOvC", Label.class)).setText(Utils.formatValue2DecToString(totalIncomes));
            ((Label)screen.findNiftyControl("totalProfitValue_WOvC", Label.class)).setText(totalProfit<0 ? "(" + Utils.formatValue2DecToString(totalProfit) + ")" : Utils.formatValue2DecToString(totalProfit));
        }
        if (currentMoney <= 0){
            //GAME OVER!!!! YOU LOST!!
            gameEngine.getGameSounds().stopSound(Sounds.Background);
            gameEngine.getGameSounds().stopSound(Sounds.GameNoMoney);
            gameEngine.getGameSounds().playSound(Sounds.GameOver);
            gameEngine.getGeneralScreenController().pauseGame();
            gameEngine.getGameData().getCurrentGame().setAttemptNumbers(gameEngine.getGameData().getCurrentGame().getAttemptNumbers()+1);
            gameEngine.getGameData().updatePlayerLog();
            gameEngine.getGameData().updateFailedGame();
            gameEngine.setExecuteGame(false);
            nifty.showPopup(screen, gameOverPopup.getId(), null);
        }
        isUpdating = false;
    }
    
    @NiftyEventSubscriber(id="gameOverRestartB")
    public void onGameOverRestartButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.closePopup(gameOverPopup.getId());
        ArrayList<E_Game> games = gameEngine.getGameData().loadGamesByType(GameType.CPU);
        E_Game game = null;
        for (E_Game tempGame : games){
            if (gameEngine.getInitialGameId() == tempGame.getIdGame()){
                game = tempGame;
                break;
            }
        }        
        if (game != null){
            gameEngine.playGame(game,true);
//            gameEngine.getGeneralScreenController().playGame();
        }
    }
    
    @NiftyEventSubscriber(id="gameOverQuitB")
    public void onGameOverQuitButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.showPopup(screen, quitPopup.getId(), null);
    }
    
    @NiftyEventSubscriber(pattern="quitPopup.*")
    public void onAnswerPopupButtonClicked(final String id, final ButtonClickedEvent event) {
        if (id.equals("quitPopupYes")){
            gameEngine.getGameData().logoutPlayer();
            gameEngine.app.stop();
             System.exit(0);
        }else{
            nifty.closePopup(quitPopup.getId());
        }
    }
    
    public void clickToOperatorCosts(){
        if (isExpanded_OperatorCosts){
            screen.findElementByName("imageOperatorCosts").getRenderer(ImageRenderer.class).setImage(imageAdd);
        }else{
            screen.findElementByName("imageOperatorCosts").getRenderer(ImageRenderer.class).setImage(imageMinus);
        }
        loadOperatorCosts(!isExpanded_OperatorCosts);
        isExpanded_OperatorCosts = !isExpanded_OperatorCosts;
        updateLocation();
    }
    
    public void clickToMachineEquipmentCosts(){
        if (isExpanded_MachineEquipmentCosts){
            screen.findElementByName("imageMachineEquipmentCosts").getRenderer(ImageRenderer.class).setImage(imageAdd);
        }else{
            screen.findElementByName("imageMachineEquipmentCosts").getRenderer(ImageRenderer.class).setImage(imageMinus);
        }
        loadMachineEquipmentCosts(!isExpanded_MachineEquipmentCosts);
        isExpanded_MachineEquipmentCosts = !isExpanded_MachineEquipmentCosts;
        updateLocation();
    }
    
    public void clickToOtherCosts(){
        if (isExpanded_OtherCosts){
            screen.findElementByName("imageOtherCosts").getRenderer(ImageRenderer.class).setImage(imageAdd);
        }else{
            screen.findElementByName("imageOtherCosts").getRenderer(ImageRenderer.class).setImage(imageMinus);
        }
        loadOtherCosts(!isExpanded_OtherCosts);
        isExpanded_OtherCosts = !isExpanded_OtherCosts;
        updateLocation();
    }
        
    private void loadOperatorCosts(boolean isLoading){
        int newHeight;
        String panelName = "operatorCosts_parent";
        if (isLoading){
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("costOperator_WOvC","Operator Cost/Hour:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("costOperatorValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("costOperatorValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("hireOperator_WOvC","Operator Hire:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("hireOperatorSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("hireOperatorValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("fireOperator_WOvC","Operator Fire:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("fireOperatorValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("fireOperatorValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            newHeight = 60;
        }else{
            for(Element tempElement : screen.findElementByName(panelName).getElements())
                tempElement.markForRemoval();
            newHeight = -60;
        }
        winControls.setHeight(new SizeValue(String.valueOf(winControls.getHeight() + newHeight)));
        screen.findElementByName(panelName).layoutElements();
        screen.findElementByName("winOverallControl").getParent().layoutElements();
    }
    
    private void loadMachineEquipmentCosts(boolean isLoading){
        int newHeight;
        String panelName = "machineEquipmentCosts_parent";
        if (isLoading){
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("costMachine_WOvC","Machine Cost/Hour:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("costMachineValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("costMachineValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("purchaseMachine_WOvC","Machine Purchase:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("purchaseMachineValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("purchaseMachineValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("preventiveMaintenanceMachine_WOvC","Machine Prev.Maint.:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("preventiveMaintenanceMachineValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("preventiveMaintenanceMachineValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("costEquipment_WOvC","Equipment Cost/Hour:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("costEquipmentValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("costEquipmentValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("purchaseEquipment_WOvC","Equipment Purchase:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("purchaseEquipmentValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("purchaseEquipmentValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("preventiveMaintenanceEquipment_WOvC","Equipment Prev.Maint.:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("preventiveMaintenanceEquipmentValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("preventiveMaintenanceEquipmentValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            newHeight = 120;
        }else{
            for(Element tempElement : screen.findElementByName(panelName).getElements())
                tempElement.markForRemoval();
            newHeight = -120;
        }
        winControls.setHeight(new SizeValue(String.valueOf(winControls.getHeight() + newHeight)));
        screen.findElementByName(panelName).layoutElements();
        screen.findElementByName("winOverallControl").getParent().layoutElements();
    }
    
    private void loadOtherCosts(boolean isLoading){
        int newHeight;
        String panelName = "otherCosts_parent";
        if (isLoading){
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("costOverhead_WOvC","Overhead Cost/Hour:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("costOverheadValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("costOverheadValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("costStations_WOvC","Storage Cost/Hour:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("costStationsValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("costStationsValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            new PanelBuilder(){{
                childLayoutHorizontal();
                panel(common.hspacer(sizePreviousImage));
                control(new LabelBuilder("costPurchases_WOvC","Part Purchase:"){{   width("160px");  height("20px");  textHAlign(overall_label);  }});  panel(common.hspacer("5px"));
                control(new LabelBuilder("costPurchasesValueSign_WOvC",Params.moneySign){{   width(Params.moneySignSizeField);  height("20px"); textHAlignCenter(); }});
                control(new LabelBuilder("costPurchasesValue_WOvC","_"){{   width("70px");  height("20px"); textHAlign(overall_value); }});
            }}.build(nifty, screen, screen.findElementByName(panelName));
            newHeight = 60;
        }else{
            for(Element tempElement : screen.findElementByName(panelName).getElements())
                tempElement.markForRemoval();
            newHeight = -60;
        }
        winControls.setHeight(new SizeValue(String.valueOf(winControls.getHeight() + newHeight)));
        screen.findElementByName(panelName).layoutElements();
        screen.findElementByName("winOverallControl").getParent().layoutElements();
    }
    
    private void updateLocation(){
        int initialY = 488;
        int yOperators = 60;
        int yMachines = 120;
        int yOthers = 60;
        if (isExpanded_OperatorCosts)
            initialY -= yOperators;
        if (isExpanded_MachineEquipmentCosts)
            initialY -= yMachines;
        if (isExpanded_OtherCosts)
            initialY -= yOthers;
        winControls.getElement().setConstraintY(new SizeValue(initialY + "px"));
        winControls.getElement().getParent().layoutElements();
    }
    public void HideWindow()
    {
        nifty.getScreen("layerScreen").findElementByName("winOverallControl").hide();
        nifty.getScreen("layerScreen").findElementByName("OverallLabel").show();
        
    }
    public void ShowWindow(){
        nifty.getScreen("layerScreen").findElementByName("winOverallControl").show();
        nifty.getScreen("layerScreen").findElementByName("OverallLabel").hide();


    }
}