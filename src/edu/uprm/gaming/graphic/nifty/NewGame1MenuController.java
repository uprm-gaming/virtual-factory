/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty;

import de.lessvoid.nifty.EndNotify;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.render.TextRenderer;
import edu.uprm.gaming.GameEngine;
import edu.uprm.gaming.entity.E_Game;
import edu.uprm.gaming.utils.GameStatus;
import edu.uprm.gaming.utils.Params;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author David
 */
public class NewGame1MenuController implements Controller {
    private Nifty nifty;
    private Screen screen;
    private Button continuePlayingButton;
    private Button cancelButton;
    private ListBox<ListBoxMessages_NewGame1> listBoxGames;
    private GameEngine gameEngine;
    private ArrayList<E_Game> arrGames;
    private Element warningPopUp;
    private boolean popupAnswer = false;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.gameEngine = ((MenuScreenController)screen.getScreenController()).getGameEngine();
        warningPopUp = nifty.createPopup("warningNewGamePopup");
    }
    
    @SuppressWarnings("unchecked")
    private ListBox<ListBoxMessages_NewGame1> getListBox(final String name) {
        return (ListBox<ListBoxMessages_NewGame1>) screen.findNiftyControl(name, ListBox.class);
    }
    
    @Override
    public void init(final Properties parameter, final Attributes controlDefinitionAttributes) {
        
    }

    @Override
    public void onStartScreen() {
        ((Label)screen.findNiftyControl("gameSelectedStatus", Label.class)).setText("");
    }

    @Override
    public void onFocus(final boolean getFocus) {
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
    
    public void updateControls_stage1(){
        this.listBoxGames = getListBox("listBox_NG1MD");
        this.listBoxGames.setListBoxViewConverter(new MessagesViewConverter_NewGame1());
        arrGames = this.gameEngine.getGameData().loadGamesOfAPlayer(gameEngine.getGameData().getPlayer().getIdPlayer());
        E_Game game;
        listBoxGames.clear();
        for (int i=0; i<arrGames.size(); i++){
            game = arrGames.get(i);
            listBoxGames.addItem(new ListBoxMessages_NewGame1(nifty, String.valueOf(i+1), game.getGameName(), 
                    game.getGameCategory().toString(), game.getGameStatus().toString(), game.getYourBestScore(),
                    game.getGameBestScore(), game.getAttemptNumbers()));
        }
        if (listBoxGames.getItems().size() > 0) {
            listBoxGames.selectItemByIndex(0);
        }
        updateListBoxClicked();
        
        if (!gameEngine.isExecuteGame()) {
        }
    }


    public void listBoxItemClicked(){
        System.out.println(listBoxGames.getFocusItem().getTextCategory().toString());
        updateListBoxClicked();
    }
    
//    @NiftyEventSubscriber(id="listBox_NG1MD")
//    public void onListBoxClicked(final String id, final ListBoxSelectionChangedEvent event) {
//        updateListBoxClicked();
//    }
    
    private void updateListBoxClicked(){
        if (listBoxGames.getFocusItemIndex() != -1){
            ((Label)screen.findNiftyControl("descriptionTextField", Label.class)).setText(setWrapText(arrGames.get(listBoxGames.getFocusItemIndex()).getDescription()));
        }else{
            ((Label)screen.findNiftyControl("descriptionTextField", Label.class)).setText("_");
        }
        screen.findElementByName("descriptionTextField").getRenderer(TextRenderer.class).setLineWrapping(true);
//        screen.findElementByName("descriptionTextField").setHeight(80);
        screen.findElementByName("descriptionTextField").getParent().layoutElements();
    }
    
    private String setWrapText(String oldString){
//        int numChars = 100;
//        int i = 1;
//        String iniString = "";
//        String endString = "";
//        while (oldString.length() > (numChars*i)){
//            iniString = oldString.substring(0, (numChars*i));
//            endString = oldString.substring((numChars*i), oldString.length());
//            oldString = iniString + "\n" + endString ;
//            i++;
//        }
        return oldString;
    }
    
    @NiftyEventSubscriber(id="cancelButton_NG1MD")
    public void onCancelButtonNG1Clicked(final String id, final ButtonClickedEvent event) {
        Element nextElement = screen.findElementByName("dialogMainMenu");
        MainMenuController mainMenu = nextElement.getControl(MainMenuController.class);
        mainMenu.updateControls();
        nextElement.show();
        Element currentElement = screen.findElementByName("dialogNewGameStage1Menu");
        currentElement.hide();
        screen.findElementByName("dialogNewGameStage1Menu").stopEffect(EffectEventId.onCustom);
        screen.findElementByName("dialogMainMenu").startEffect(EffectEventId.onCustom, null, "selected");
    }
    
    @NiftyEventSubscriber(id="nextButton_NG1MD")
    public void onNextButtonNG1Clicked(final String id, final ButtonClickedEvent event) {
        if (!gameEngine.isExecuteGame()) {
            if (Params.DEBUG_ON) {
                System.out.println("---------play game button-------");
            }
        }

        if (listBoxGames.getFocusItemIndex() != -1){
            if (!listBoxGames.getFocusItem().getStatus().equals(GameStatus.Locked.toString())){
                if (gameEngine.isExecuteGame())
                    nifty.showPopup(screen, warningPopUp.getId(), null);
                else
                    playNewSelectedGame();
            }else{
                ((Label)screen.findNiftyControl("gameSelectedStatus", Label.class)).setText("Error: the game selected is locked");
            }
        }
    }
    
    class popupWarningClosed implements EndNotify{
        @Override
        public void perform(){
            if (popupAnswer)
                playNewSelectedGame();
        }
    }
    
    @NiftyEventSubscriber(pattern="warningPopup.*")
    public void onAnswerWarningPopupButtonClicked(final String id, final ButtonClickedEvent event) {
        popupAnswer = id.equals("warningPopupYes");
        nifty.closePopup(warningPopUp.getId(), new popupWarningClosed());
    }
    
    private void playNewSelectedGame(){
        ((Label)screen.findNiftyControl("gameSelectedStatus", Label.class)).setText("");
        Element currentElement = screen.findElementByName("dialogNewGameStage1Menu");
        currentElement.hideWithoutEffect();
//        if (nifty.getScreen("layerScreen").isRunning())
//            nifty.update();
        
        nifty.gotoScreen("layerScreen");
        nifty.executeEndOfFrameElementActions();
        this.gameEngine.playGame(arrGames.get(listBoxGames.getFocusItemIndex()),true);
    }
}
