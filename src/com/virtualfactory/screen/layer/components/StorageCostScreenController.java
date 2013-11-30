package com.virtualfactory.screen.layer.components;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.slider.builder.SliderBuilder;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Activity;
import com.virtualfactory.entity.E_Station;
import com.virtualfactory.utils.CommonBuilders;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Utils;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author David
 */
public class StorageCostScreenController implements Controller {
    private Nifty nifty;
    private Screen screen;
    private WindowControl winControls;
    private boolean isVisible;
    private GameEngine gameEngine;
    final CommonBuilders common = new CommonBuilders();
    private ArrayList<E_Activity> arrActivities;
    private ArrayList<Integer> arrPriorities;
    private boolean finishedToCreate = false;

    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winStorageCostControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        isVisible = false;
    }
    
    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public void init(Properties parameter, Attributes controlDefinitionAttributes) {
        
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onFocus(boolean getFocus) {
        
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
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
            winControls.getElement().setConstraintX(null);
            winControls.getElement().setConstraintY(null);
        }
        loadValues(index);
    }
    
    private void loadValues(int index){
        if (index == -1){
            cleanActivities();
        }else{
            updateValues();
        }
        ((Label)screen.findNiftyControl("messageResult_WASCC", Label.class)).setText("");
    }
    
    private void cleanActivities(){
        for(Element tempElement : screen.findElementByName("storageCostParent").getElements()){
            tempElement.markForRemoval();
        }
        screen.findElementByName("storageCostParent").layoutElements();
    }
    
    private void updateValues(){
        int currentHeight = 135;
        cleanActivities();
        finishedToCreate = false;
        for (E_Station tempStation : gameEngine.getGameData().getMapUserStorageStation().values()){
            tempStation.setTempSelectedSlots(tempStation.getSelectedSlots());
            createNewStorage(String.valueOf(tempStation.getIdStation()), tempStation.getStationDescription(), tempStation.getTotalSlots(), tempStation.getSelectedSlots(), tempStation.getCostPerHour());
            currentHeight += 22;
        }
        winControls.setHeight(new SizeValue(String.valueOf(currentHeight)));
        screen.findElementByName("storageCostParent").layoutElements();
        screen.findElementByName("winStorageCostControl").getParent().layoutElements();
        finishedToCreate = true;
        updateTotalCost();
    }
    
    private void updateTotalCost(){
        double totalCostValue = 0;
        for (E_Station tempStation : gameEngine.getGameData().getMapUserStorageStation().values()){
            totalCostValue += tempStation.getTempSelectedSlots()*tempStation.getCostPerHour();
        }
        ((Label)screen.findNiftyControl("totalCostValue_WASCC", Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(totalCostValue));
    }
    
    private void createNewStorage(final String idStation, final String description, final int totalSlots, final int selectedSlots, final double costPerHour){
        new PanelBuilder("panel_WASCC_" + idStation){{
            childLayoutHorizontal();
            control(new LabelBuilder("description_WASCC_" + idStation, description + ":"){{   width("140px");  height("20px");  textHAlignLeft();  }});  panel(common.hspacer("5px"));
            control(new LabelBuilder("slots_WASCC_" + idStation, selectedSlots + "/" + totalSlots){{   width("50px");  height("20px");  textHAlignCenter();  }});  panel(common.hspacer("5px"));
            control(new SliderBuilder("selectedSlots_WASCC_" + idStation, false){{ width("120px"); height("20px"); }}); panel(common.hspacer("5px"));
            control(new LabelBuilder("quantity_WASCC_" + idStation, selectedSlots + " x " + Utils.formatValue2DecToString(costPerHour) + " ="){{   width("70px");  height("20px");  textHAlignLeft();  }});  panel(common.hspacer("5px"));
            control(new LabelBuilder("total_WASCC_" + idStation, Params.moneySign + " " + Utils.formatValue2DecToString(selectedSlots*costPerHour)){{   width("70px");  height("20px");  textHAlignRight();  }});
        }}.build(nifty, screen, screen.findElementByName("storageCostParent"));
        ((Slider) screen.findNiftyControl("selectedSlots_WASCC_" + idStation, Slider.class)).setup(0f, totalSlots, selectedSlots, 1.f, 1.f);
    }
    
    @NiftyEventSubscriber(pattern="selectedSlots_WASCC_.*")
    public void onQuantitySliderChange(final String id, final SliderChangedEvent event) {
        if (!finishedToCreate) return;
        int valueSelected = (int)((Slider) screen.findNiftyControl(id, Slider.class)).getValue();
        int valueMax = (int)((Slider) screen.findNiftyControl(id, Slider.class)).getMax();
        double costPerHour = gameEngine.getGameData().getMapUserStorageStation().get(Integer.parseInt(id.replace("selectedSlots_WASCC_", ""))).getCostPerHour();
        gameEngine.getGameData().getMapUserStorageStation().get(Integer.parseInt(id.replace("selectedSlots_WASCC_", ""))).setTempSelectedSlots(valueSelected);
        ((Label)screen.findNiftyControl("slots_WASCC_" + id.replace("selectedSlots_WASCC_", ""), Label.class)).setText(valueSelected + "/" + valueMax);
        ((Label)screen.findNiftyControl("quantity_WASCC_" + id.replace("selectedSlots_WASCC_", ""), Label.class)).setText(valueSelected + " x " + Utils.formatValue2DecToString(costPerHour));
        ((Label)screen.findNiftyControl("total_WASCC_" + id.replace("selectedSlots_WASCC_", ""), Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(valueSelected*costPerHour));
        updateTotalCost();
        ((Label)screen.findNiftyControl("messageResult_WASCC", Label.class)).setText("");
    }
    
    @NiftyEventSubscriber(id="assingStorageCostsUpdate")
    public void onUpdateButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        for (E_Station tempStation : gameEngine.getGameData().getMapUserStorageStation().values()){
            if (tempStation.getSelectedSlots() != tempStation.getTempSelectedSlots()){
                tempStation.updateSlots();
                tempStation.setTempSelectedSlots(tempStation.getSelectedSlots());
                ((Slider) screen.findNiftyControl("selectedSlots_WASCC_" + tempStation.getIdStation(), Slider.class)).setValue(tempStation.getSelectedSlots());
                ((Label)screen.findNiftyControl("slots_WASCC_" + tempStation.getIdStation(), Label.class)).setText(tempStation.getSelectedSlots() + "/" + tempStation.getTotalSlots());
                ((Label)screen.findNiftyControl("quantity_WASCC_" + tempStation.getIdStation(), Label.class)).setText(tempStation.getSelectedSlots() + " x " + Utils.formatValue2DecToString(tempStation.getCostPerHour()));
                ((Label)screen.findNiftyControl("total_WASCC_" + tempStation.getIdStation(), Label.class)).setText(Params.moneySign + " " + Utils.formatValue2DecToString(tempStation.getSelectedSlots()*tempStation.getCostPerHour()));
            }
        }
        updateTotalCost();
        ((Label)screen.findNiftyControl("messageResult_WASCC", Label.class)).setText("Updated successfully");
        nifty.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupScreenController.class).setupStorageDone();
    }
}
