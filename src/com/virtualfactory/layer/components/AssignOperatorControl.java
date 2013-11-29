/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.layer.components;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.CheckBox;
import de.lessvoid.nifty.controls.CheckBoxStateChangedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
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
import com.virtualfactory.entity.E_Operator;
import com.virtualfactory.strategy.EventStrategy;
import com.virtualfactory.utils.CommonBuilders;
import com.virtualfactory.utils.ObjectState;
import com.virtualfactory.utils.OperatorCategory;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.TypeActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 *
 * @author David
 */
public class AssignOperatorControl implements Controller {
    private Nifty nifty;
    private Screen screen;
    private WindowControl winControls;
    private boolean isVisible;
    private GameEngine gameEngine;
    final CommonBuilders common = new CommonBuilders();
    private ArrayList<E_Activity> arrActivities;
    private ArrayList<Integer> arrPriorities;
    private ListBox<String> listBoxAssignOpe;
    private Map<Integer, E_Activity> mapActivities;
    private ArrayList<E_Operator> arrOpeCarrier;
    private ArrayList<E_Operator> arrOpeAssembler;
    private ArrayList<E_Operator> arrOpeVersatile;
    private boolean requireUpdate = false;
    
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winAssignOperatorControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        isVisible = false;
        listBoxAssignOpe = ((ListBox<String>)screen.findNiftyControl("activitiesList_WAsOpC", ListBox.class));
//        listBoxAssignOpe.setListBoxViewConverter(new MessagesViewConverter_AssignOpe());
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
            listBoxAssignOpe.clear();
            ((Label)screen.findNiftyControl("messageResult_WAsOpC", Label.class)).setText("");
        }else{
            requireUpdate = false;
            loadOperators();
            arrActivities = gameEngine.getGameData().getActivities_OrderByPriority();
            mapActivities = new HashMap<Integer, E_Activity>();
            getActivityList();
            listBoxAssignOpe.selectItemByIndex(0);
        }        
    }
    
    private void getActivityList(){
        listBoxAssignOpe.clear();
        mapActivities.clear();
        int i=0;
        listBoxAssignOpe.addItem("All");
        mapActivities.put(i, null);
        i++;
        for (E_Activity tempActivity : arrActivities){
            if (tempActivity.getTypeActivity().equals(TypeActivity.Operation) || tempActivity.getTypeActivity().equals(TypeActivity.Transport)){
                listBoxAssignOpe.addItem(tempActivity.getTypeActivity() + " " + String.valueOf(i) + " (" + tempActivity.getArrAssignedOperators().size() + ")");
                mapActivities.put(i, tempActivity);
                i++;
            }
        }
    }
    
    @NiftyEventSubscriber(id="activitiesList_WAsOpC")
    public void onSelectionListBox(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getSelectionIndices().size() > 0){
            requireUpdate = false;
            ((Label)screen.findNiftyControl("messageResult_WAsOpC", Label.class)).setText("");
            if (Integer.parseInt(String.valueOf(event.getSelectionIndices().get(0))) > 0){
                ((Label)screen.findNiftyControl("description_WAsOpC", Label.class)).setText("Activity: " + mapActivities.get(Integer.parseInt(String.valueOf(event.getSelectionIndices().get(0)))).getActivityDescription());
                updateValues(Integer.parseInt(String.valueOf(event.getSelectionIndices().get(0))));
            }else{
                ((Label)screen.findNiftyControl("description_WAsOpC", Label.class)).setText("");
                updateValues(-1);
            }
        }
    }
    
    private void updateValues(int idIndex){
        for (int i=0; i<arrOpeCarrier.size(); i++){
            ((CheckBox)screen.findNiftyControl("opeCheckBox_MH_AsOpC_" + arrOpeCarrier.get(i).getIdOperator(), CheckBox.class)).uncheck();
            ((CheckBox)screen.findNiftyControl("opeCheckBox_MH_AsOpC_" + arrOpeCarrier.get(i).getIdOperator(), CheckBox.class)).setEnabled(idIndex<0 ? false : (arrOpeCarrier.get(i).getState().equals(ObjectState.Active)));
        }
        for (int i=0; i<arrOpeAssembler.size(); i++){
            ((CheckBox)screen.findNiftyControl("opeCheckBox_LO_AsOpC_" + arrOpeAssembler.get(i).getIdOperator(), CheckBox.class)).uncheck();
            ((CheckBox)screen.findNiftyControl("opeCheckBox_LO_AsOpC_" + arrOpeAssembler.get(i).getIdOperator(), CheckBox.class)).setEnabled(idIndex<0 ? false : (arrOpeAssembler.get(i).getState().equals(ObjectState.Active)));
        }
        for (int i=0; i<arrOpeVersatile.size(); i++){
            ((CheckBox)screen.findNiftyControl("opeCheckBox_V_AsOpC_" + arrOpeVersatile.get(i).getIdOperator(), CheckBox.class)).uncheck();
            ((CheckBox)screen.findNiftyControl("opeCheckBox_V_AsOpC_" + arrOpeVersatile.get(i).getIdOperator(), CheckBox.class)).setEnabled(idIndex<0 ? false : (arrOpeVersatile.get(i).getState().equals(ObjectState.Active)));
        }
        if (idIndex >= 0){
            if (mapActivities.get(idIndex).getTypeActivity().equals(TypeActivity.Operation)){
                for (int i=0; i<arrOpeCarrier.size(); i++)
                    ((CheckBox)screen.findNiftyControl("opeCheckBox_MH_AsOpC_" + arrOpeCarrier.get(i).getIdOperator(), CheckBox.class)).setEnabled(false);
            }else
            if (mapActivities.get(idIndex).getTypeActivity().equals(TypeActivity.Transport)){
                for (int i=0; i<arrOpeAssembler.size(); i++)
                    ((CheckBox)screen.findNiftyControl("opeCheckBox_LO_AsOpC_" + arrOpeAssembler.get(i).getIdOperator(), CheckBox.class)).setEnabled(false);
            }
            if (mapActivities.get(idIndex).getArrAssignedOperators() != null){
                ArrayList<E_Operator> tempArrOpe = new ArrayList<E_Operator>(mapActivities.get(idIndex).getArrAssignedOperators().values());
                for (int i=0; i<tempArrOpe.size(); i++){
                    if (tempArrOpe.get(i).getCategory().equals(OperatorCategory.Carrier)){
                        ((CheckBox)screen.findNiftyControl("opeCheckBox_MH_AsOpC_" + tempArrOpe.get(i).getIdOperator(), CheckBox.class)).check();
                    }else
                    if (tempArrOpe.get(i).getCategory().equals(OperatorCategory.Assembler)){
                        ((CheckBox)screen.findNiftyControl("opeCheckBox_LO_AsOpC_" + tempArrOpe.get(i).getIdOperator(), CheckBox.class)).check();
                    }else
                    if (tempArrOpe.get(i).getCategory().equals(OperatorCategory.Versatile)){
                        ((CheckBox)screen.findNiftyControl("opeCheckBox_V_AsOpC_" + tempArrOpe.get(i).getIdOperator(), CheckBox.class)).check();
                    }
                }
            }
        }
    }
    
    private void loadOperators(){
        int currentHeight = 230;
        gameEngine.getGameData().updateUserOperatorCategories();
        arrOpeCarrier = new ArrayList<E_Operator>(gameEngine.getGameData().getMapUserOperatorCarrier().values());
        arrOpeAssembler = new ArrayList<E_Operator>(gameEngine.getGameData().getMapUserOperatorAssembler().values());
        arrOpeVersatile = new ArrayList<E_Operator>(gameEngine.getGameData().getMapUserOperatorVersatile().values());
        int idNoExist = 100;
        for (int i=0; i<arrOpeCarrier.size(); i+=3){
            String idOpe1 = "", nameOpe1 = "", idOpe2 = "", nameOpe2 = "", idOpe3 = "", nameOpe3 = "";
            boolean statusOpe1 = false, statusOpe2 = false, statusOpe3 = false;
            if (arrOpeCarrier.size() >= i+1){
                idOpe1 = String.valueOf(arrOpeCarrier.get(i).getIdOperator());
                nameOpe1 = arrOpeCarrier.get(i).getNameOperator() + " (" + arrOpeCarrier.get(i).getArrActivitiesAssigned().size() + ")";
                statusOpe1 = arrOpeCarrier.get(i).getState().equals(ObjectState.Active);
            }else{
                idOpe1 = String.valueOf(idNoExist);
                idNoExist++;
            }
            if (arrOpeCarrier.size() >= i+2){
                idOpe2 = String.valueOf(arrOpeCarrier.get(i+1).getIdOperator());
                nameOpe2 = arrOpeCarrier.get(i+1).getNameOperator() + " (" + arrOpeCarrier.get(i+1).getArrActivitiesAssigned().size() + ")";
                statusOpe2 = arrOpeCarrier.get(i+1).getState().equals(ObjectState.Active);
            }else{
                idOpe2 = String.valueOf(idNoExist);
                idNoExist++;
            }
            if (arrOpeCarrier.size() >= i+3){
                idOpe3 = String.valueOf(arrOpeCarrier.get(i+2).getIdOperator());
                nameOpe3 = arrOpeCarrier.get(i+2).getNameOperator() + " (" + arrOpeCarrier.get(i+2).getArrActivitiesAssigned().size() + ")";
                statusOpe3 = arrOpeCarrier.get(i+2).getState().equals(ObjectState.Active);
            }else{
                idOpe3 = String.valueOf(idNoExist);
                idNoExist++;
            }
            createNewOperator("MH_AsOpC","winMH_AsOpC_Parent", idOpe1, nameOpe1, statusOpe1, idOpe2, nameOpe2, statusOpe2, idOpe3, nameOpe3, statusOpe3);
            currentHeight += 25;
        }
        for (int i=0; i<arrOpeAssembler.size(); i+=3){
            String idOpe1 = "", nameOpe1 = "", idOpe2 = "", nameOpe2 = "", idOpe3 = "", nameOpe3 = "";
            boolean statusOpe1 = false, statusOpe2 = false, statusOpe3 = false;
            if (arrOpeAssembler.size() >= i+1){
                idOpe1 = String.valueOf(arrOpeAssembler.get(i).getIdOperator());
                nameOpe1 = arrOpeAssembler.get(i).getNameOperator() + " (" + arrOpeAssembler.get(i).getArrActivitiesAssigned().size() + ")";
                statusOpe1 = arrOpeAssembler.get(i).getState().equals(ObjectState.Active);
            }else{
                idOpe1 = String.valueOf(idNoExist);
                idNoExist++;
            }
            if (arrOpeAssembler.size() >= i+2){
                idOpe2 = String.valueOf(arrOpeAssembler.get(i+1).getIdOperator());
                nameOpe2 = arrOpeAssembler.get(i+1).getNameOperator() + " (" + arrOpeAssembler.get(i+1).getArrActivitiesAssigned().size() + ")";
                statusOpe2 = arrOpeAssembler.get(i+1).getState().equals(ObjectState.Active);
            }else{
                idOpe2 = String.valueOf(idNoExist);
                idNoExist++;
            }
            if (arrOpeAssembler.size() >= i+3){
                idOpe3 = String.valueOf(arrOpeAssembler.get(i+2).getIdOperator());
                nameOpe3 = arrOpeAssembler.get(i+2).getNameOperator() + " (" + arrOpeAssembler.get(i+2).getArrActivitiesAssigned().size() + ")";
                statusOpe3 = arrOpeAssembler.get(i+2).getState().equals(ObjectState.Active);
            }else{
                idOpe3 = String.valueOf(idNoExist);
                idNoExist++;
            }
            createNewOperator("LO_AsOpC","winLO_AsOpC_Parent", idOpe1, nameOpe1, statusOpe1, idOpe2, nameOpe2, statusOpe2, idOpe3, nameOpe3, statusOpe3);
            currentHeight += 25;
        }
        for (int i=0; i<arrOpeVersatile.size(); i+=3){
            String idOpe1 = "", nameOpe1 = "", idOpe2 = "", nameOpe2 = "", idOpe3 = "", nameOpe3 = "";
            boolean statusOpe1 = false, statusOpe2 = false, statusOpe3 = false;
            if (arrOpeVersatile.size() >= i+1){
                idOpe1 = String.valueOf(arrOpeVersatile.get(i).getIdOperator());
                nameOpe1 = arrOpeVersatile.get(i).getNameOperator() + " (" + arrOpeVersatile.get(i).getArrActivitiesAssigned().size() + ")";
                statusOpe1 = arrOpeVersatile.get(i).getState().equals(ObjectState.Active);
            }else{
                idOpe1 = String.valueOf(idNoExist);
                idNoExist++;
            }
            if (arrOpeVersatile.size() >= i+2){
                idOpe2 = String.valueOf(arrOpeVersatile.get(i+1).getIdOperator());
                nameOpe2 = arrOpeVersatile.get(i+1).getNameOperator() + " (" + arrOpeVersatile.get(i+1).getArrActivitiesAssigned().size() + ")";
                statusOpe2 = arrOpeVersatile.get(i+1).getState().equals(ObjectState.Active);
            }else{
                idOpe2 = String.valueOf(idNoExist);
                idNoExist++;
            }
            if (arrOpeVersatile.size() >= i+3){
                idOpe3 = String.valueOf(arrOpeVersatile.get(i+2).getIdOperator());
                nameOpe3 = arrOpeVersatile.get(i+2).getNameOperator() + " (" + arrOpeVersatile.get(i+2).getArrActivitiesAssigned().size() + ")";
                statusOpe3 = arrOpeVersatile.get(i+2).getState().equals(ObjectState.Active);
            }else{
                idOpe3 = String.valueOf(idNoExist);
                idNoExist++;
            }
            createNewOperator("V_AsOpC", "winV_AsOpC_Parent", idOpe1, nameOpe1, statusOpe1, idOpe2, nameOpe2, statusOpe2, idOpe3, nameOpe3, statusOpe3);
            currentHeight += 25;
        }
        winControls.setHeight(new SizeValue(String.valueOf(currentHeight)));
        screen.findElementByName("winAssignOperatorControl").getParent().layoutElements();
    }
    
    private void createNewOperator(final String parentPanel, final String parentOperator, final String idOperator1, final String nameOperator1, boolean ope1Status, final String idOperator2, final String nameOperator2, boolean ope2Status, final String idOperator3, final String nameOperator3, boolean ope3Status){
        new PanelBuilder("panel_WAsOpC_" + idOperator1 + "_" + idOperator2 + "_" + idOperator3){{
            childLayoutHorizontal();
            control(new ControlBuilder("opeCheckBox_" + parentPanel + "_" + idOperator1, "checkbox") {{ set("checked", "false"); }});  panel(common.hspacer("5px"));
            control(new LabelBuilder("opeLabel_" + parentPanel + "_" + idOperator1, nameOperator1){{   width("90px");  height("20px");  textHAlignLeft();  }});  panel(common.hspacer("5px"));
            control(new ControlBuilder("opeCheckBox_" + parentPanel + "_" + idOperator2, "checkbox") {{ set("checked", "false"); }});  panel(common.hspacer("5px"));
            control(new LabelBuilder("opeLabel_" + parentPanel + "_" + idOperator2, nameOperator2){{   width("90px");  height("20px");  textHAlignLeft();  }});  panel(common.hspacer("5px"));
            control(new ControlBuilder("opeCheckBox_" + parentPanel + "_" + idOperator3, "checkbox") {{ set("checked", "false"); }});  panel(common.hspacer("5px"));
            control(new LabelBuilder("opeLabel_" + parentPanel + "_" + idOperator3, nameOperator3){{   width("90px");  height("20px");  textHAlignLeft();  }});  
        }}.build(nifty, screen, screen.findElementByName(parentOperator));
        if (!ope1Status) ((CheckBox)screen.findNiftyControl("opeCheckBox_" + parentPanel + "_" + idOperator1, CheckBox.class)).disable();
        if (!ope2Status) ((CheckBox)screen.findNiftyControl("opeCheckBox_" + parentPanel + "_" + idOperator2, CheckBox.class)).disable();
        if (!ope3Status) ((CheckBox)screen.findNiftyControl("opeCheckBox_" + parentPanel + "_" + idOperator3, CheckBox.class)).disable();
        if (nameOperator1.isEmpty()) ((CheckBox)screen.findNiftyControl("opeCheckBox_" + parentPanel + "_" + idOperator1, CheckBox.class)).getElement().setVisible(false);
        if (nameOperator2.isEmpty()) ((CheckBox)screen.findNiftyControl("opeCheckBox_" + parentPanel + "_" + idOperator2, CheckBox.class)).getElement().setVisible(false);
        if (nameOperator3.isEmpty()) ((CheckBox)screen.findNiftyControl("opeCheckBox_" + parentPanel + "_" + idOperator3, CheckBox.class)).getElement().setVisible(false);
    }
    
    @NiftyEventSubscriber(pattern="opeCheckBox_.*")
    public void onCheckedBoxChanged(final String id, final CheckBoxStateChangedEvent event) {
        ((Label)screen.findNiftyControl("messageResult_WAsOpC", Label.class)).setText("Requires to be updated");
        requireUpdate = true;
    }
    
    private void cleanActivities(){
        for(Element tempElement : screen.findElementByName("winMH_AsOpC_Parent").getElements()){
            nifty.removeElement(screen, tempElement);
        }
        for(Element tempElement : screen.findElementByName("winLO_AsOpC_Parent").getElements()){
            nifty.removeElement(screen, tempElement);
        }
        for(Element tempElement : screen.findElementByName("winV_AsOpC_Parent").getElements()){
            nifty.removeElement(screen, tempElement);
        }
        nifty.executeEndOfFrameElementActions();
    }
    
//    public void listBoxItemClicked_AO(){
//        System.out.println(listBoxAssignOpe.getFocusItem().getId().toString());
//        
//    }
    
    @NiftyEventSubscriber(id="assingOperatorUpdate")
    public void onUpdateButtonClicked(final String id, final ButtonClickedEvent event) {
        if (!requireUpdate) return;
        requireUpdate = false;
        int focusItemIndex = listBoxAssignOpe.getFocusItemIndex();
        E_Activity tempActivity = mapActivities.get(focusItemIndex);
//        tempActivity.getArrAssignedOperators().clear();
        if (tempActivity.getTypeActivity().equals(TypeActivity.Transport))
            for (int i=0; i<arrOpeCarrier.size(); i++){
                if (((CheckBox)screen.findNiftyControl("opeCheckBox_MH_AsOpC_" + arrOpeCarrier.get(i).getIdOperator(), CheckBox.class)).isChecked()){
                    if (!arrOpeCarrier.get(i).getArrActivitiesAssigned().containsKey(tempActivity.getIdActivity())){
                        arrOpeCarrier.get(i).getArrActivitiesAssigned().put(tempActivity.getIdActivity(), tempActivity);
                        tempActivity.getArrAssignedOperators().put(arrOpeCarrier.get(i).getIdOperator(), arrOpeCarrier.get(i));
                    }
                }else{
                    if (arrOpeCarrier.get(i).getArrActivitiesAssigned().containsKey(tempActivity.getIdActivity())){
                        arrOpeCarrier.get(i).getArrActivitiesAssigned().remove(tempActivity.getIdActivity());
                        tempActivity.getArrAssignedOperators().remove(arrOpeCarrier.get(i).getIdOperator());
                    }
                }
                ((Label)screen.findNiftyControl("opeLabel_MH_AsOpC_" + arrOpeCarrier.get(i).getIdOperator(), Label.class)).setText(arrOpeCarrier.get(i).getNameOperator() + " (" + arrOpeCarrier.get(i).getArrActivitiesAssigned().size() + ")");
            }
        if (tempActivity.getTypeActivity().equals(TypeActivity.Operation))
            for (int i=0; i<arrOpeAssembler.size(); i++){
                if (((CheckBox)screen.findNiftyControl("opeCheckBox_LO_AsOpC_" + arrOpeAssembler.get(i).getIdOperator(), CheckBox.class)).isChecked()){
                    if (!arrOpeAssembler.get(i).getArrActivitiesAssigned().containsKey(tempActivity.getIdActivity())){
                        arrOpeAssembler.get(i).getArrActivitiesAssigned().put(tempActivity.getIdActivity(), tempActivity);
                        tempActivity.getArrAssignedOperators().put(arrOpeAssembler.get(i).getIdOperator(), arrOpeAssembler.get(i));
                    }
                }else{
                    if (arrOpeAssembler.get(i).getArrActivitiesAssigned().containsKey(tempActivity.getIdActivity())){
                        arrOpeAssembler.get(i).getArrActivitiesAssigned().remove(tempActivity.getIdActivity());
                        tempActivity.getArrAssignedOperators().remove(arrOpeAssembler.get(i).getIdOperator());
                    }
                }
                ((Label)screen.findNiftyControl("opeLabel_LO_AsOpC_" + arrOpeAssembler.get(i).getIdOperator(), Label.class)).setText(arrOpeAssembler.get(i).getNameOperator() + " (" + arrOpeAssembler.get(i).getArrActivitiesAssigned().size() + ")");
            }
        for (int i=0; i<arrOpeVersatile.size(); i++){
            if (((CheckBox)screen.findNiftyControl("opeCheckBox_V_AsOpC_" + arrOpeVersatile.get(i).getIdOperator(), CheckBox.class)).isChecked()){
                if (!arrOpeVersatile.get(i).getArrActivitiesAssigned().containsKey(tempActivity.getIdActivity())){
                    arrOpeVersatile.get(i).getArrActivitiesAssigned().put(tempActivity.getIdActivity(), tempActivity);
                    tempActivity.getArrAssignedOperators().put(arrOpeVersatile.get(i).getIdOperator(), arrOpeVersatile.get(i));
                }
            }else{
                if (arrOpeVersatile.get(i).getArrActivitiesAssigned().containsKey(tempActivity.getIdActivity())){
                    arrOpeVersatile.get(i).getArrActivitiesAssigned().remove(tempActivity.getIdActivity());
                    tempActivity.getArrAssignedOperators().remove(arrOpeVersatile.get(i).getIdOperator());
                }
            }
            ((Label)screen.findNiftyControl("opeLabel_V_AsOpC_" + arrOpeVersatile.get(i).getIdOperator(), Label.class)).setText(arrOpeVersatile.get(i).getNameOperator() + " (" + arrOpeVersatile.get(i).getArrActivitiesAssigned().size() + ")");
        }
        getActivityList();
        listBoxAssignOpe.setFocusItemByIndex(focusItemIndex);
        listBoxAssignOpe.selectItemByIndex(focusItemIndex);
        for (EventStrategy temp : gameEngine.getManageEvents().getArrEvents()){
            if (temp.getType().equals(TypeActivity.Operation.toString()) || temp.getType().equals(TypeActivity.Transport.toString()) || temp.getType().equals(TypeActivity.Store.toString()))
                temp.updateStrategy();
        }
        ((Label)screen.findNiftyControl("messageResult_WAsOpC", Label.class)).setText("Updated successfully");
        nifty.getScreen("layerScreen").findElementByName("winGSC_Element").getControl(GameSetupControl.class).setupOperatorsDone();
//        gameEngine.updateLastActivitySystemTime();
//        
//        int numOpeSelected = 0;
//        for (E_Operator ope : gameEngine.getGameData().getMapUserOperator().values()){
//            ope.setAssignedActivityNumber(0);
//        }            
//        for (E_Activity activity : arrActivities){
//            if (activity.getTypeActivity().equals(TypeActivity.Operation) || activity.getTypeActivity().equals(TypeActivity.Transport) || activity.getTypeActivity().equals(TypeActivity.Store)){
//                activity.getArrAssignedOperators().clear();
//                numOpeSelected = (int)((Slider) screen.findNiftyControl("sliderActivity_WAsOpC_" + activity.getIdActivity(), Slider.class)).getValue();
//                if (activity.getTypeActivity().equals(TypeActivity.Transport) || activity.getTypeActivity().equals(TypeActivity.Store)){
//                    int levelNumber = 0;                    
//                    while (numOpeSelected > 0){
//                        //FIRST ATTEMPT: AVAILABLE CARRIER OPERATORS
//                        for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorCarrier().values()){
//                            if (numOpeSelected == 0) break;
//                            if (ope.getAssignedActivityNumber() == levelNumber && !activity.getArrAssignedOperators().containsKey(ope.getIdOperator())){
//                                ope.setAssignedActivityNumber(ope.getAssignedActivityNumber() + 1);
//                                activity.getArrAssignedOperators().put(ope.getIdOperator(), ope);
//                                numOpeSelected--;
//                            }
//                        }
//                        //SECOND ATTEMPT: AVAILABLE VERSATILE OPERATORS
//                        for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorVersatile().values()){
//                            if (numOpeSelected == 0) break;
//                            if (ope.getAssignedActivityNumber() == levelNumber && !activity.getArrAssignedOperators().containsKey(ope.getIdOperator())){
//                                ope.setAssignedActivityNumber(ope.getAssignedActivityNumber() + 1);
//                                activity.getArrAssignedOperators().put(ope.getIdOperator(), ope);
//                                numOpeSelected--;
//                            }
//                        }
//                        levelNumber++;
//                    }
//                }else
//                if (activity.getTypeActivity().equals(TypeActivity.Operation)){
//                    int levelNumber = 0;                    
//                    while (numOpeSelected > 0){
//                        //FIRST ATTEMPT: AVAILABLE ASSEMBLER OPERATORS
//                        for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorAssembler().values()){
//                            if (numOpeSelected == 0) break;
//                            if (ope.getAssignedActivityNumber() == levelNumber && !activity.getArrAssignedOperators().containsKey(ope.getIdOperator())){
//                                ope.setAssignedActivityNumber(ope.getAssignedActivityNumber() + 1);
//                                activity.getArrAssignedOperators().put(ope.getIdOperator(), ope);
//                                numOpeSelected--;
//                            }
//                        }
//                        //SECOND ATTEMPT: AVAILABLE VERSATILE OPERATORS
//                        for (E_Operator ope : gameEngine.getGameData().getMapUserOperatorVersatile().values()){
//                            if (numOpeSelected == 0) break;
//                            if (ope.getAssignedActivityNumber() == levelNumber && !activity.getArrAssignedOperators().containsKey(ope.getIdOperator())){
//                                ope.setAssignedActivityNumber(ope.getAssignedActivityNumber() + 1);
//                                activity.getArrAssignedOperators().put(ope.getIdOperator(), ope);
//                                numOpeSelected--;
//                            }
//                        }
//                        levelNumber++;
//                    }
//                }
//            }
//        }
    }
}
