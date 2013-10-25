/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty.controls;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.SizeValue;
import edu.uprm.gaming.GameEngine;
import edu.uprm.gaming.entity.E_Bucket;
import edu.uprm.gaming.entity.E_Game;
import edu.uprm.gaming.entity.E_Station;
import edu.uprm.gaming.utils.Pair;
import edu.uprm.gaming.utils.StationType;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author David
 */
public class StationControl implements Controller {
    private Nifty nifty;
    private Screen screen;
    private Button continuePlayingButton;
    private Button cancelButton;
    private TextField gameDescription;
    private ListBox<String> listBoxGames;
    private GameEngine gameEngine;
    private ArrayList<E_Game> arrGames;
    private WindowControl winControls;
    private E_Station station;
    private E_Bucket bucket;
    private int idActivity = -1;
    private int idPart = -1;
    private ArrayList<E_Bucket> arrBuckets = null;
    private boolean isVisible;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winStationControl", WindowControl.class);
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
            }else{
                winControls.getElement().setConstraintX(new SizeValue("413px"));
                winControls.getElement().getParent().layoutElements();
            }
            winControls.getElement().setConstraintX(null);
            winControls.getElement().setConstraintY(null);
        }
        loadValues(index);
    }
    
    private void loadValues(int index){
        if (index == -1){
            ((Label)screen.findNiftyControl("idValue_WSC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("descriptionValue_WSC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("sizeWidthValue_WSC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("sizeLengthValue_WSC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("currentLocationValue_WSC", Label.class)).setText("_");
            idActivity = -1;
            ((ListBox<String>)screen.findNiftyControl("bucketsListValue_WBC", ListBox.class)).clear();
            arrBuckets = null;
            loadBucketData("");
            winControls.setTitle("Station");
        }else{
            station = gameEngine.getGameData().getMapUserStation().get(index);
            winControls.setTitle("Station " + station.getIdStation() + " - " + station.getStationType().toString());
            ((Label)screen.findNiftyControl("idValue_WSC", Label.class)).setText(String.valueOf(station.getIdStation()));
            ((Label)screen.findNiftyControl("descriptionValue_WSC", Label.class)).setText(station.getStationDescription());
            ((Label)screen.findNiftyControl("sizeWidthValue_WSC", Label.class)).setText(String.valueOf(station.getSizeW()));
            ((Label)screen.findNiftyControl("sizeLengthValue_WSC", Label.class)).setText(String.valueOf(station.getSizeL()));
            ((ListBox<String>)screen.findNiftyControl("bucketsListValue_WBC", ListBox.class)).clear();
            ((Label)screen.findNiftyControl("currentLocationValue_WSC", Label.class)).setText("(" + station.getStationLocationX() + "," + station.getStationLocationY() + ")");
            arrBuckets = station.getArrBuckets();
            ((ListBox<String>)screen.findNiftyControl("bucketsListValue_WBC", ListBox.class)).addAllItems(getArrBucketsName());
            if (station.getStationType().equals(StationType.MachineZone) || station.getStationType().equals(StationType.StaffZone)){
                ((ListBox<String>)screen.findNiftyControl("bucketsListValue_WBC", ListBox.class)).setEnabled(false);
                bucket = null;
                loadBucketData("");
            }else{
                ((ListBox<String>)screen.findNiftyControl("bucketsListValue_WBC", ListBox.class)).setEnabled(true);
                if (((ListBox<String>)screen.findNiftyControl("bucketsListValue_WBC", ListBox.class)).itemCount() > 0)
                    ((ListBox<String>)screen.findNiftyControl("bucketsListValue_WBC", ListBox.class)).selectItemByIndex(0);
            }
            updateData();
        }        
    }
    
    private ArrayList<String> getArrBucketsName(){
        ArrayList<String> arrBucketsName = new ArrayList<String>();
        for (E_Bucket bucket : arrBuckets){
            arrBucketsName.add("Bucket " + bucket.getIdBucket());
        }
        return arrBucketsName;
    }
    
    @NiftyEventSubscriber(id="bucketsListValue_WBC")
    public void onBucketListBoxClicked(final String id, final ListBoxSelectionChangedEvent event) {
        if (event.getListBox().getFocusItem() != null){
            if (!event.getListBox().getFocusItem().equals("")){
                loadBucketData(event.getListBox().getFocusItem().toString().replace("Bucket ", ""));
            }
        }        
    }
    
    private void loadBucketData(String idBucket){
        if (idBucket.equals("")){
            idPart = -1;
            ((Label)screen.findNiftyControl("bucket_WBC", Label.class)).setText("BUCKET _");
            ((Label)screen.findNiftyControl("idBucketValue_WBC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("capacityValue_WBC", Label.class)).setText("");
            ((Label)screen.findNiftyControl("unitValue_WBC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("sizeValue_WBC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("partAssignedValue_WBC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("directionValue_WBC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("unitsToArriveValue_WBC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("unitsToRemoveValue_WBC", Label.class)).setText("_");
        }else{
            bucket = null;
            for (E_Bucket tempBucket : arrBuckets){
                if (tempBucket.getIdBucket() == Integer.parseInt(idBucket)){
                    bucket = tempBucket;
                    break;
                }
            }
            ((Label)screen.findNiftyControl("bucket_WBC", Label.class)).setText("BUCKET " + bucket.getIdBucket());
            ((Label)screen.findNiftyControl("idBucketValue_WBC", Label.class)).setText(String.valueOf(bucket.getIdBucket()));
            ((Label)screen.findNiftyControl("capacityValue_WBC", Label.class)).setText(String.valueOf(bucket.getCapacity()));
            ((Label)screen.findNiftyControl("unitValue_WBC", Label.class)).setText(bucket.getUnit().toString());
            ((Label)screen.findNiftyControl("directionValue_WBC", Label.class)).setText(bucket.getDirection().toString());
            updateDataBucket();
        }
    }
    
    public void updateData(){
        if (station == null) return;
        updateDataBucket();
    }
    
    public void updateDataBucket(){
        if (bucket == null) return;
        if (bucket.activateLaterDeactivation)
            ((Label)screen.findNiftyControl("bucket_WBC", Label.class)).setText("BUCKET " + bucket.getIdBucket() + " (Later Deactivation)");
        else
            ((Label)screen.findNiftyControl("bucket_WBC", Label.class)).setText("BUCKET " + bucket.getIdBucket());
        ((Label)screen.findNiftyControl("sizeValue_WBC", Label.class)).setText(String.valueOf(bucket.getSize()));
        ((Label)screen.findNiftyControl("partAssignedValue_WBC", Label.class)).setText(gameEngine.getGameData().getMapUserPart().get(bucket.getIdPart()).getPartDescription());
        ((Label)screen.findNiftyControl("unitsToArriveValue_WBC", Label.class)).setText(String.valueOf(bucket.getUnitsToArrive()));
        ((Label)screen.findNiftyControl("unitsToRemoveValue_WBC", Label.class)).setText(String.valueOf(bucket.getUnitsToRemove()));
        idPart = bucket.getIdPart();
    }
}