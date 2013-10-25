/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty.controls;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.TextFieldChangedEvent;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.slider.builder.SliderBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import edu.uprm.gaming.GameEngine;
import edu.uprm.gaming.entity.E_Activity;
import edu.uprm.gaming.graphic.nifty.CommonBuilders;
import edu.uprm.gaming.utils.Pair;
import edu.uprm.gaming.utils.TypeActivity;
import edu.uprm.gaming.utils.Utils;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author David
 */
public class UnitLoadControl   implements Controller {
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
        this.winControls = screen.findNiftyControl("winUnitLoadControl", WindowControl.class);
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
            ((Label)screen.findNiftyControl("messageResult_WULC", Label.class)).setText("");
        }else{
            updateValues();
        }        
    }
    
    private void updateValues(){
        arrActivities = gameEngine.getGameData().getActivities_OrderByPriority();
        int currentHeight = 40;
        cleanActivities();
        gameEngine.getGameData().updateUserOperatorCategories();
        int unitLoad = 0;
        for (E_Activity tempActivity : arrActivities){
            if (tempActivity.getTypeActivity().equals(TypeActivity.Transport))
                unitLoad = gameEngine.getGameData().getMapTransport().get(tempActivity.getIdActivity()).getUnitLoad();
            else
                unitLoad = -1;
            createNewActivity(String.valueOf(tempActivity.getIdActivity()), tempActivity.getTypeActivity().toString(), tempActivity.getActivityDescription(), unitLoad);
            currentHeight += 25;
        }
        winControls.setHeight(new SizeValue(String.valueOf(currentHeight)));
        screen.findElementByName("winULC_Parent").layoutElements();
        screen.findElementByName("winUnitLoadControl").getParent().layoutElements();
        nifty.executeEndOfFrameElementActions();
    }
    
    private void createNewActivity(final String idActivity, final String typeActivity, final String nameActivity, final int unitLoad){
        if (typeActivity.equals("Transport"))
        new PanelBuilder("panel_WULC_" + idActivity){{
            childLayoutHorizontal();
            control(new LabelBuilder("typeActivity_WULC_" + idActivity, typeActivity){{   width("60px");  height("20px");  textHAlignLeft();  }});  panel(common.hspacer("5px"));
            control(new LabelBuilder("nameActivity_WULC_" + idActivity, nameActivity){{   width("200px");  height("20px");  textHAlignLeft();  }});  panel(common.hspacer("5px"));
            control(new TextFieldBuilder("unitLoad_WULC_" + idActivity, String.valueOf(unitLoad)){{   width("30px");  height("20px"); textHAlignRight();}});
        }}.build(nifty, screen, screen.findElementByName("winULC_Parent"));
        if (unitLoad < 0){
            //((Label) screen.findNiftyControl("unitLoad_WULC_" + idActivity, Label.class)).setText("");
            ((TextField) screen.findNiftyControl("unitLoad_WULC_" + idActivity, TextField.class)).setText("0");
        }else{
            ((Slider) screen.findNiftyControl("sliderTime_WULC_" + idActivity, Slider.class)).setup(0.f, 100.f, (float)unitLoad, 1.f, 1.f);
        }
    }
    
    private void cleanActivities(){
        for(Element tempElement : screen.findElementByName("winULC_Parent").getElements()){
            nifty.removeElement(screen, tempElement);
        }
        nifty.executeEndOfFrameElementActions();
    }
    
//    @NiftyEventSubscriber(pattern="unitLoad_WULC_.*")
//    public void onCarrierChange(final String id, final TextFieldChangedEvent event) {
//        ((Label)screen.findNiftyControl("unitLoad_WULC_" + id.replace("sliderTime_WULC_", ""), Label.class)).setText(String.valueOf((int)event.getValue()));
//        ((Label)screen.findNiftyControl("messageResult_WULC", Label.class)).setText("");
//    }
    
    @NiftyEventSubscriber(id="unitLoadUpdate")
    public void onUpdateButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.updateLastActivitySystemTime();
        for (E_Activity tempActivity : arrActivities){
            if (tempActivity.getTypeActivity().equals(TypeActivity.Transport)){
                int temp;
                String textUnitLoad = ((TextField) screen.findNiftyControl("unitLoad_WULC_" + tempActivity.getIdActivity(), TextField.class)).getText();
                try{
                    temp= Integer.parseInt(textUnitLoad);
                    if (temp<0)
                    {
                    temp=0;
                    ((TextField) screen.findNiftyControl("unitLoad_WULC_" + tempActivity.getIdActivity(), TextField.class)).setText("0");
                    }
                }
                catch(Exception e)
                {
                    temp=0;
                    ((TextField) screen.findNiftyControl("unitLoad_WULC_" + tempActivity.getIdActivity(), TextField.class)).setText("0");

                }
                gameEngine.getGameData().getMapTransport().get(tempActivity.getIdActivity()).setUnitLoad(temp);
            }
        }
        ((Label)screen.findNiftyControl("messageResult_WULC", Label.class)).setText("Updated successfully");
        nifty.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupControl.class).setupUnitLoadDone();
        nifty.getScreen("layerScreen").findElementByName("winDashboard_Element").getControl(DashboardControl.class).updateTransportList();
    }
}
