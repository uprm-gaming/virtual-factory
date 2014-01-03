package com.virtualfactory.screen.layer.components;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.DropDownSelectionChangedEvent;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.controls.RadioButton;
import de.lessvoid.nifty.controls.RadioButtonStateChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.controls.WindowClosedEvent;
import de.lessvoid.nifty.tools.SizeValue;
import com.virtualfactory.engine.GameEngine;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.strategy.EventStrategy;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.OperatorCategory;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Status;
import com.virtualfactory.utils.TypeActivity;
import com.virtualfactory.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author David
 */
public class OperatorScreenController implements Controller {
    private Nifty nifty;
    private Screen screen;
    private Button continuePlayingButton;
    private Button cancelButton;
    private TextField gameDescription;
    private ListBox<String> listBoxGames;
    private GameEngine gameEngine;
    private ArrayList<E_Game> arrGames;
    private WindowControl winControls;
    private Element element;
    private E_Operator operator;
    private boolean isVisible;
    private String assemblerValue = "Operator";
    private String carrierValue = "Material Handler";
    private String versatileValue = "Versatile";
    private String idButton = "";
    private ListBox<String> listBoxOperator;
    private Map<Integer, E_Operator> mapOperators;
    private Map<Integer, E_Operator> tempOperators;
    private boolean cleanMessage = true;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.element = element;
        this.winControls = screen.findNiftyControl("winOperatorControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);  
        isVisible = false;
        listBoxOperator = ((ListBox<String>)screen.findNiftyControl("operatorsList_WOC", ListBox.class));
    }
    
    @NiftyEventSubscriber(id="winOperatorControl")
    public void onCloseButtonClicked(final String id, final WindowClosedEvent event) {
        if (operator != null) operator.showHideSpot(false);
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
        ((DropDown)screen.findNiftyControl("categoryValue_WOC", DropDown.class)).clear();
        ((DropDown)screen.findNiftyControl("categoryValue_WOC", DropDown.class)).addItem(assemblerValue);
        ((DropDown)screen.findNiftyControl("categoryValue_WOC", DropDown.class)).addItem(carrierValue);
        ((DropDown)screen.findNiftyControl("categoryValue_WOC", DropDown.class)).addItem(versatileValue);
    }

    @Override
    public void onFocus(final boolean getFocus) {
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
    
    public void loadWindowControl(GameEngine game, int index, Pair<Integer,Integer> position){
        this.gameEngine = game;
        idButton = "";
        if (index == -1){
            winControls.getElement().setVisible(false);
            winControls.getContent().hide();       
            isVisible = false;
        }else{
            winControls.getElement().setVisible(true);
            winControls.getContent().show();
            isVisible = true;
            if (position != null){
                if (winControls.getWidth() + position.getFirst() > gameEngine.jmonkeyApp.getGuiViewPort().getCamera().getWidth())
                    position.setFirst(gameEngine.jmonkeyApp.getGuiViewPort().getCamera().getWidth() - winControls.getWidth());
                if (winControls.getHeight() + position.getSecond() > gameEngine.jmonkeyApp.getGuiViewPort().getCamera().getHeight())
                    position.setSecond(gameEngine.jmonkeyApp.getGuiViewPort().getCamera().getHeight() - winControls.getHeight());
                winControls.getElement().setConstraintX(new SizeValue(position.getFirst() + "px"));
                winControls.getElement().setConstraintY(new SizeValue(position.getSecond() + "px"));
                winControls.getElement().getParent().layoutElements();
            }else{
                winControls.getElement().setConstraintX(new SizeValue("258px"));
                winControls.getElement().getParent().layoutElements();
            }
            winControls.getElement().setConstraintX(null);
            winControls.getElement().setConstraintY(null);
        }
        loadValues(index);
    }
        
    private void loadValues(int index){
        if (index == -1){
            ((Label)screen.findNiftyControl("idValue_WOC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("nameValue_WOC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("statusValue_WOC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("locationValue_WOC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("speedValue_WOC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("hireFireValue_WOC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("salaryPerHourCarrierValue_WOC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("salaryPerHourAssemblerValue_WOC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("salaryPerHourVersatileValue_WOC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("salaryPerHourValue_WOC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("percentageUsageValue_WOC", Label.class)).setText("_");
//            ((Label)screen.findNiftyControl("skill1_WOC", Label.class)).setText("");
//            ((Label)screen.findNiftyControl("skill1Value_WOC", Label.class)).setText("");
//            ((Label)screen.findNiftyControl("skill2_WOC", Label.class)).setText("");
//            ((Label)screen.findNiftyControl("skill2Value_WOC", Label.class)).setText("");
//            ((Label)screen.findNiftyControl("skill3_WOC", Label.class)).setText("");
//            ((Label)screen.findNiftyControl("skill3Value_WOC", Label.class)).setText("");
//            ((Label)screen.findNiftyControl("skill4_WOC", Label.class)).setText("");
//            ((Label)screen.findNiftyControl("skill4Value_WOC", Label.class)).setText("");
//            ((Label)screen.findNiftyControl("skill5_WOC", Label.class)).setText("");
//            ((Label)screen.findNiftyControl("skill5Value_WOC", Label.class)).setText("");
//            ((Label)screen.findNiftyControl("activityAssignedValue_WOC", Label.class)).setText("");
            winControls.setTitle("Operator");
            if (operator != null) operator.showHideSpot(false);
            listBoxOperator.clear();
        }else{
            listBoxOperator.clear();
            mapOperators = new HashMap<Integer, E_Operator>();
            operator = null;
            if (index != Params.operatorList) operator = gameEngine.getGameData().getMapUserOperator().get(index);
            tempOperators = gameEngine.getGameData().getMapUserOperator();
            index = getOperatorList();
            listBoxOperator.selectItemByIndex(index);
        }
    }
    
    private int getOperatorList(){
        listBoxOperator.clear();
        mapOperators.clear();
        int i=0;
        int newIndex = 0;
        for (E_Operator tOperator : tempOperators.values()){
            listBoxOperator.addItem("Ope " + String.valueOf(i+1) + " (" + (tOperator.getState().equals(ObjectState.Active) ? Params.opeActive : Params.opeInactive) + ") " + (tOperator.getCategory().equals(OperatorCategory.Assembler) ? "Operator" : (tOperator.getCategory().equals(OperatorCategory.Carrier) ? "Mat.Handler" : "Versatile")));
            mapOperators.put(i, tOperator);
            if (operator != null)
                if (operator.equals(tOperator))
                    newIndex = i;
            i++;
        }
        return newIndex;
    }
    
    private void updateValues(int listBoxIndex){
        operator = mapOperators.get(listBoxIndex);
        operator.showHideSpot(true);
        if (operator.getState().equals(ObjectState.Active))
            winControls.setTitle("Operator " + String.valueOf(listBoxIndex+1) + " (Hired)");
        else
            winControls.setTitle("Operator " + String.valueOf(listBoxIndex+1) + " (Fired)");
        ((Label)screen.findNiftyControl("idValue_WOC", Label.class)).setText(String.valueOf(operator.getIdOperator()));
        ((Label)screen.findNiftyControl("nameValue_WOC", Label.class)).setText(operator.getNameOperator());
        ((Label)screen.findNiftyControl("speedValue_WOC", Label.class)).setText(String.valueOf(operator.getSpeed()));
        ((Label)screen.findNiftyControl("hireFireValue_WOC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(operator.getPriceForHire()) + " / " + Utils.formatValue2DecToString(operator.getPriceForFire()));
        ((Label)screen.findNiftyControl("salaryPerHourCarrierValue_WOC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(operator.getSalaryPerHourCarrier()));
        ((Label)screen.findNiftyControl("salaryPerHourAssemblerValue_WOC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(operator.getSalaryPerHourAssembler()));
        ((Label)screen.findNiftyControl("salaryPerHourVersatileValue_WOC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(operator.getSalaryPerHourVersatile()));
        if (operator.getState() == ObjectState.Active)
            ((RadioButton)screen.findNiftyControl("activate_WOC_True", RadioButton.class)).select();
        else
            ((RadioButton)screen.findNiftyControl("activate_WOC_False", RadioButton.class)).select();
//        for (E_Skill skill : gameEngine.getGameData().getMapGameSkill().values()){
//            switch (skill.getIdSkill()){
//                case 0:
//                    ((Label)screen.findNiftyControl("skill1_WOC", Label.class)).setText(skill.getSkillDescription());
//                    break;
//                case 1:
//                    ((Label)screen.findNiftyControl("skill2_WOC", Label.class)).setText(skill.getSkillDescription());
//                    break;
//                case 2:
//                    ((Label)screen.findNiftyControl("skill3_WOC", Label.class)).setText(skill.getSkillDescription());
//                    break;
//                case 3:
//                    ((Label)screen.findNiftyControl("skill4_WOC", Label.class)).setText(skill.getSkillDescription());
//                    break;
//                case 4:
//                    ((Label)screen.findNiftyControl("skill5_WOC", Label.class)).setText(skill.getSkillDescription());
//                    break;
//            }
//        }
//        for (Pair<Integer,Double> skillOperator : operator.getArrSkills()){
//            switch (skillOperator.getFirst()){
//                case 0:
//                    ((Label)screen.findNiftyControl("skill1Value_WOC", Label.class)).setText(String.valueOf(skillOperator.getSecond()));
//                    break;
//                case 1:
//                    ((Label)screen.findNiftyControl("skill2Value_WOC", Label.class)).setText(String.valueOf(skillOperator.getSecond()));
//                    break;
//                case 2:
//                    ((Label)screen.findNiftyControl("skill3Value_WOC", Label.class)).setText(String.valueOf(skillOperator.getSecond()));
//                    break;
//                case 3:
//                    ((Label)screen.findNiftyControl("skill4Value_WOC", Label.class)).setText(String.valueOf(skillOperator.getSecond()));
//                    break;
//                case 4:
//                    ((Label)screen.findNiftyControl("skill5Value_WOC", Label.class)).setText(String.valueOf(skillOperator.getSecond()));
//                    break;
//            }
//        }
        if (operator.getCategory().equals(OperatorCategory.Assembler))
            ((DropDown)screen.findNiftyControl("categoryValue_WOC", DropDown.class)).selectItem(assemblerValue);
        else
        if (operator.getCategory().equals(OperatorCategory.Carrier))
            ((DropDown)screen.findNiftyControl("categoryValue_WOC", DropDown.class)).selectItem(carrierValue);
        else
            ((DropDown)screen.findNiftyControl("categoryValue_WOC", DropDown.class)).selectItem(versatileValue);
        updateData();
    }
    
    @NiftyEventSubscriber(id="operatorsList_WOC")
    public void onSelectionListBox(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getSelectionIndices().size() > 0){
            if (operator != null) operator.showHideSpot(false);
            if (cleanMessage) ((Label)screen.findNiftyControl("messageResult_WOC", Label.class)).setText("");
            updateValues(Integer.parseInt(String.valueOf(event.getSelectionIndices().get(0))));
        }
    }
    
    @NiftyEventSubscriber(id="categoryValue_WOC")
    public void onSelectionListBox(final String id, final DropDownSelectionChangedEvent event) {
        if (cleanMessage) ((Label)screen.findNiftyControl("messageResult_WOC", Label.class)).setText("");
    }
    
    @NiftyEventSubscriber(pattern="activate_WOC_.*")
    public void onSelectionRadioButton(final String id, final RadioButtonStateChangedEvent event) {
        ((Label)screen.findNiftyControl("messageResult_WOC", Label.class)).setText("");
    }

    public String getIdButton() {
        return idButton;
    }

    public void setIdButton(String idButton) {
        this.idButton = idButton;
    }
    
    public void updateData(){
        if (operator.activateLaterDeactivation)
            ((Label)screen.findNiftyControl("statusValue_WOC", Label.class)).setText(operator.getStatus().toString() + " (Later Deactivation)");
        else
            ((Label)screen.findNiftyControl("statusValue_WOC", Label.class)).setText(operator.getStatus().toString());
        ((Label)screen.findNiftyControl("locationValue_WOC", Label.class)).setText("(" + operator.getCurrentLocationX() + "," + operator.getCurrentLocationZ() + ")");
        ((Label)screen.findNiftyControl("salaryPerHourValue_WOC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(operator.getSalaryPerHour()));
        ((Label)screen.findNiftyControl("earnedMoneyValue_WOC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(operator.updateAndGetEarnedMoney()));
        ((Label)screen.findNiftyControl("percentageUsageValue_WOC", Label.class)).setText(Utils.formatValue2DecToString(operator.getPercentageUsage()*100.0) + "%");
//        ((Label)screen.findNiftyControl("activityAssignedValue_WOC", Label.class)).setText(operator.getActivityDescriptionAssigned());
//        screen.findElementByName("activityAssignedValue_WOC").getRenderer(TextRenderer.class).setLineWrapping(true);
//        screen.findElementByName("activityAssignedValue_WOC").getParent().layoutElements();
        screen.findElementByName("winOperatorControl").layoutElements();
        screen.findElementByName("winOperatorControl").getParent().layoutElements();
    }
    
    @NiftyEventSubscriber(id="operatorUpdate")
    public void onUpdateButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        cleanMessage = false;
        ObjectState currentStateOperator = ((RadioButton)screen.findNiftyControl("activate_WOC_True", RadioButton.class)).isActivated() == true ? ObjectState.Active : ObjectState.Inactive;
        if (operator.getState() != currentStateOperator){
            if (operator.getStatus().equals(Status.Idle)){
                operator.setState(currentStateOperator);
                ((Label)screen.findNiftyControl("messageResult_WOC", Label.class)).setText("Updated successfully");
                if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 5)
                    Params.tutorial.nextStep();
            }else{
                operator.activateLaterDeactivation = true;
                ((Label)screen.findNiftyControl("messageResult_WOC", Label.class)).setText("Updated to a later deactivation");
            }
//            gameEngine.getGeneralScreenController().updateOptionButtonClicked();    //update buttons
            updateOptionButtonClicked();
            if (currentStateOperator.equals(ObjectState.Active)){
                winControls.setTitle("Operator " + operator.getIdOperator() + "(Hired)");
                gameEngine.getGameData().setTotalOperatorsHire(gameEngine.getGameData().getTotalOperatorsHire() + operator.getPriceForHire());
            }else{
                winControls.setTitle("Operator " + operator.getIdOperator() + "(Fired)");
                gameEngine.getGameData().setTotalOperatorsFire(gameEngine.getGameData().getTotalOperatorsFire() + operator.getPriceForFire());
            }
        }
        OperatorCategory newCategory;
        if (((DropDown)screen.findNiftyControl("categoryValue_WOC", DropDown.class)).getSelection().toString().equals(assemblerValue))
            newCategory = OperatorCategory.Assembler;
        else
        if (((DropDown)screen.findNiftyControl("categoryValue_WOC", DropDown.class)).getSelection().toString().equals(carrierValue))
            newCategory = OperatorCategory.Carrier;
        else
            newCategory = OperatorCategory.Versatile;
        if (!operator.getCategory().equals(newCategory)){
            operator.setCategory(newCategory);
            operator.updateSkillsPerCategory();
            for (EventStrategy temp : gameEngine.getManageEvents().getArrEvents()){
                if (temp.getType().equals(TypeActivity.Operation.toString()) || 
                        temp.getType().equals(TypeActivity.Transport.toString()) || 
                        temp.getType().equals(TypeActivity.Store.toString()))
                    temp.updateStrategy();
            }
//            gameEngine.getGeneralScreenController().updateOptionButtonClicked();    //update buttons
            updateOptionButtonClicked();
            ((Label)screen.findNiftyControl("messageResult_WOC", Label.class)).setText("Updated successfully");
        }
        updateData();
        cleanMessage = true;
    }
    
    private void updateOptionButtonClicked(){
        if (operator != null){
            int newIndex = getOperatorList();
            listBoxOperator.selectItemByIndex(newIndex);
//            gameEngine.getGeneralScreenController().updateSubLevelButtonText(idButton, "Ope" + operator.getIdOperator() + " ( " + (operator.getState().equals(ObjectState.Inactive) ? Params.opeInactive : Params.opeActive) + " ) " + (operator.getCategory().equals(OperatorCategory.Assembler) ? "Operator" : (operator.getCategory().equals(OperatorCategory.Carrier) ? "Material Handler" : OperatorCategory.Versatile.toString())));
        }            
    }
}
