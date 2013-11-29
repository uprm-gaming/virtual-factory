/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.gui.layer.resources;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.slider.builder.SliderBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Activity;
import com.virtualfactory.gui.CommonBuilders;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Utils;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author David
 */
public class PriorityControl implements Controller {
    private Nifty nifty;
    private Screen screen;
    private WindowControl winControls;
    private boolean isVisible;
    private GameEngine gameEngine;
    final CommonBuilders common = new CommonBuilders();
    private ArrayList<E_Activity> arrActivities;
    private ArrayList<Integer> arrPriorities;

    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winPriorityControl", WindowControl.class);
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
            ((Label)screen.findNiftyControl("messageResult_WPrC", Label.class)).setText("");
        }else{
            updateValues();
            ((Label)screen.findNiftyControl("text_WPrC", Label.class)).setText("Set up the priority for each activity: (between: 1=most & " + arrActivities.size() + "=less priority)");
        }
    }
    
    private void updateValues(){
        arrActivities = gameEngine.getGameData().getActivities_OrderByPriority();
        int currentHeight = 100;
        cleanActivities();
        for (E_Activity tempActivity : arrActivities){
            createNewActivity(String.valueOf(tempActivity.getIdActivity()), tempActivity.getTypeActivity().toString(), tempActivity.getActivityDescription(), String.valueOf(tempActivity.getPriorityQueue()));
            currentHeight += 25;
        }
        winControls.setHeight(new SizeValue(String.valueOf(currentHeight)));
        screen.findElementByName("winPrC_Parent").layoutElements();
        screen.findElementByName("winPriorityControl").getParent().layoutElements();
        nifty.executeEndOfFrameElementActions();
    }
    
    private void createNewActivity(final String idActivity, final String typeActivity, final String nameActivity, final String priorityActivity){
        new PanelBuilder("panel_WPrC_" + idActivity){{
            childLayoutHorizontal();
            control(new LabelBuilder("typeActivity_WPrC_" + idActivity, typeActivity){{   width("60px");  height("20px");  textHAlignLeft();  }});  panel(common.hspacer("5px"));
            control(new LabelBuilder("nameActivity_WPrC_" + idActivity, nameActivity){{   width("200px");  height("20px");  textHAlignLeft();  }});  panel(common.hspacer("5px"));
//            control(new TextFieldBuilder("priorityActivity_WPrC_" + idActivity, priorityActivity){{   width("30px");  height("20px"); textHAlignRight(); }});
            control(new LabelBuilder("priorityValue_WPrC_" + idActivity, priorityActivity){{   width("20px");  height("20px");  textHAlignCenter();  }}); panel(common.hspacer("5px"));
            control(new SliderBuilder("sliderTime_WPrC_" + idActivity, false) {{  width("100px"); }}); 
        }}.build(nifty, screen, screen.findElementByName("winPrC_Parent"));
        ((Slider) screen.findNiftyControl("sliderTime_WPrC_" + idActivity, Slider.class)).setup(1.f, arrActivities.size(), Float.parseFloat(priorityActivity), 1.f, 1.f);
    }
    
    @NiftyEventSubscriber(pattern="sliderTime_WPrC_.*")
    public void onCarrierSliderChange(final String id, final SliderChangedEvent event) {
        ((Label)screen.findNiftyControl("priorityValue_WPrC_" + id.replace("sliderTime_WPrC_", ""), Label.class)).setText(String.valueOf((int)event.getValue()));
    }
    
    private void cleanActivities(){
        for(Element tempElement : screen.findElementByName("winPrC_Parent").getElements()){
            nifty.removeElement(screen, tempElement);
        }
        nifty.executeEndOfFrameElementActions();
    }
    
    @NiftyEventSubscriber(id="priorityUpdate")
    public void onRefreshButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        for (E_Activity tempActivity : arrActivities){
            tempActivity.setPriorityQueue(Integer.parseInt(((Label)screen.findNiftyControl("priorityValue_WPrC_" + tempActivity.getIdActivity(), Label.class)).getText()));
        }
//        for (E_Activity tempActivity : arrActivities){
//            if (Utils.isParsableToInt(((TextField)screen.findNiftyControl("priorityActivity_WPrC_" + tempActivity.getIdActivity(), TextField.class)).getText())){
//                int newValue = Integer.parseInt(((TextField)screen.findNiftyControl("priorityActivity_WPrC_" + tempActivity.getIdActivity(), TextField.class)).getText());
//                if (newValue < 1)
//                    newValue = 1;
//                else
//                    if (newValue > 10)
//                        newValue = 10;
//                tempActivity.setPriorityQueue(newValue);
//            }else{
//                ((Label)screen.findNiftyControl("messageResult_WPrC", Label.class)).setText("Error in activity priority: " + tempActivity.getTypeActivity().toString());
//                return;
//            }
//        }
        gameEngine.getManageEvents().updateEvents_OrderByPriority();
        arrActivities = gameEngine.getGameData().getActivities_OrderByPriority();
        for (int i=0; i<arrActivities.size(); i++){
            arrActivities.get(i).setPriorityQueue(i+1);
        }
        ((Label)screen.findNiftyControl("messageResult_WPrC", Label.class)).setText("Updated successfully");
        updateValues();
        nifty.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupControl.class).setupPriorityDone();
    }
}
