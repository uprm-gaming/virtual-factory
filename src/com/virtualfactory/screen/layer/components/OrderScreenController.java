package com.virtualfactory.screen.layer.components;

import de.lessvoid.nifty.EndNotify;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.window.WindowControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.engine.GameEngine;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Order;
import com.virtualfactory.screen.menu.MenuScreenController;
import com.virtualfactory.screen.menu.components.NewGame1MenuController;
import com.virtualfactory.utils.GameStatus;
import com.virtualfactory.utils.GameType;
import com.virtualfactory.utils.MessageType;
import com.virtualfactory.utils.Messages;
import com.virtualfactory.utils.OrderStates;
import com.virtualfactory.utils.Pair;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.Sounds;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author David
 */
public class OrderScreenController implements Controller {
    private Nifty nifty;
    private Screen screen;
    private WindowControl winControls;
    private boolean isVisible;
    private GameEngine gameEngine;
    private int noMaxOrdersFailed;
    private Element quitPopup;
    private Element gameOverPopup;
    private Element gameWonPopup;
    private int currentOrders = 0;
    private ArrayList<ListBoxMessages_Order> arrOrders;

    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.winControls = screen.findNiftyControl("winOrderControl", WindowControl.class);
        Attributes x = new Attributes();
        x.set("hideOnClose", "true");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, x);
        Attributes y = new Attributes();
        y.set("closeable", "false");
        this.winControls.bind(nifty, screen, winControls.getElement(), null, y);
        isVisible = false;
        ((ListBox<ListBoxMessages_Order>)screen.findNiftyControl("ordersValue_WOrC", ListBox.class)).setListBoxViewConverter(new MessagesViewConverter_Order());
        quitPopup = nifty.createPopup("quitPopup");
        gameOverPopup = nifty.createPopup("gameOverByLostOrders");
        gameWonPopup = nifty.createPopup("youWon");
        ((TextField)screen.findNiftyControl("indexOrder_WOrC", TextField.class)).setEnabled(false);
        ((TextField)screen.findNiftyControl("partRequired_WOrC", TextField.class)).setEnabled(false);
        ((TextField)screen.findNiftyControl("quantity_WOrC", TextField.class)).setEnabled(false);
        ((TextField)screen.findNiftyControl("dueDate_WOrC", TextField.class)).setEnabled(false);
        arrOrders = new ArrayList<ListBoxMessages_Order>();
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
        noMaxOrdersFailed = (int)(gameEngine.getGameData().getMapOrder().size()*(100 - gameEngine.getGameData().getCurrentGame().getPercentageToWin())/100.0);
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
            }            
//            winControls.getElement().setConstraintX(null);
//            winControls.getElement().setConstraintY(null);
        }
        loadValues(index);
    }
    
    private void loadValues(int index){
        if (index == -1){
            ((Label)screen.findNiftyControl("totalOrdersValue_WOrC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("maxFailedOrdersValue_WOrC", Label.class)).setText("_");
            ((Label)screen.findNiftyControl("objectiveValue_WOrC", Label.class)).setText("_");
            ((ListBox<ListBoxMessages_Order>)screen.findNiftyControl("ordersValue_WOrC", ListBox.class)).clear();
        }else{
            ((Label)screen.findNiftyControl("totalOrdersValue_WOrC", Label.class)).setText("0 / " + gameEngine.getGameData().getMapOrder().size());
            ((Label)screen.findNiftyControl("maxFailedOrdersValue_WOrC", Label.class)).setText("0 / " + noMaxOrdersFailed);
            ((Label)screen.findNiftyControl("objectiveValue_WOrC", Label.class)).setText(gameEngine.getGameData().getCurrentGame().getObjective());
            updateData();
            if (index == 1){
                nifty.closePopup(gameWonPopup.getId());
                screen.processAddAndRemoveLayerElements();
            }
        }
//        screen.findElementByName("objectiveValue_WOrC").getRenderer(TextRenderer.class).setLineWrapping(true);
//        screen.findElementByName("objectiveValue_WOrC").getParent().layoutElements();
    }
    
    public void updateData(){
        double currentTime = gameEngine.getGameData().getCurrentTimeWithFactor();
        int noFailedOrders = 0;
        int noSucessfulOrders = 0;
        boolean allProcessed = true;
        int orderIndex = 0;
        for (E_Order order : gameEngine.getGameData().getMapOrder().values()){
            if (currentTime >= order.getTime()){
                orderIndex++; 
                if (orderIndex <= currentOrders){//set
                    if (!((ListBoxMessages_Order)arrOrders.get(orderIndex-1)).getCurrentState().equals(order.getState())){
                        ((ListBoxMessages_Order)arrOrders.get(orderIndex-1)).setIcon(order.getState().toString());
                        if (order.getState().equals(OrderStates.Fail)){
                            GameLogScreenController.addMessage(MessageType.Error, Messages.orderFailed.replace(Messages.wildCard, String.valueOf(order.getIdOrder()+1)));
                            gameEngine.getGameSounds().playSound(Sounds.OrderFailed);
                        }else
                        if (order.getState().equals(OrderStates.Working))
                            GameLogScreenController.addMessage(MessageType.Info, Messages.orderWorking.replace(Messages.wildCard, String.valueOf(order.getIdOrder()+1)));
                        else
                        if (order.getState().equals(OrderStates.Done)){
                            GameLogScreenController.addMessage(MessageType.Notification, Messages.orderDone.replace(Messages.wildCard, String.valueOf(order.getIdOrder()+1)));
                            gameEngine.getGameSounds().playSound(Sounds.OrderSucceed);
                        }
                    }                    
                }else{//new
                    currentOrders++;
                    arrOrders.add(new ListBoxMessages_Order(nifty, 
                        String.valueOf(order.getIdOrder()+1), 
                        gameEngine.getGameData().getMapUserPart().get(order.getIdPart()).getPartDescription(), 
                        String.valueOf(order.getQuantity()), 
                        gameEngine.getGameData().convertTimeUnitsToString((int)order.getTime() + order.getTimeUnits()), 
                        order.getState().toString()));
                    GameLogScreenController.addMessage(MessageType.Info, Messages.orderArrived.replace(Messages.wildCard, String.valueOf(order.getIdOrder()+1)).replace(Messages.wildCard2, String.valueOf(order.getTimeUnits())));
                    gameEngine.getGameSounds().playSound(Sounds.OrderArrived);
                }
                if (order.getState() == OrderStates.Done)
                    noSucessfulOrders++;
                else
                if (order.getState() == OrderStates.Fail)
                    noFailedOrders++;
                else
                if (order.getState() != OrderStates.Done)
                    allProcessed = false;
            }
        }
        if (isVisible){
            ((ListBox<ListBoxMessages_Order>)screen.findNiftyControl("ordersValue_WOrC", ListBox.class)).clear();
            ((ListBox<ListBoxMessages_Order>)screen.findNiftyControl("ordersValue_WOrC", ListBox.class)).addAllItems(arrOrders);
            ((ListBox<ListBoxMessages_Order>)screen.findNiftyControl("ordersValue_WOrC", ListBox.class)).setFocusItemByIndex(arrOrders.size()-1);
            ((Label)screen.findNiftyControl("totalOrdersValue_WOrC", Label.class)).setText(noSucessfulOrders + " / " + gameEngine.getGameData().getMapOrder().size());
            ((Label)screen.findNiftyControl("maxFailedOrdersValue_WOrC", Label.class)).setText(noFailedOrders + " / " + noMaxOrdersFailed);
        }        
        if (noFailedOrders > noMaxOrdersFailed){
            //GAME OVER!!!! YOU LOST!!
            gameEngine.getGameSounds().stopSound(Sounds.Background);
            gameEngine.getGameSounds().stopSound(Sounds.GameNoMoney);
            gameEngine.getGameSounds().playSound(Sounds.GameOver);
            gameEngine.getLayerScreenController().pauseGame();
            gameEngine.getGameData().getCurrentGame().setAttemptNumbers(gameEngine.getGameData().getCurrentGame().getAttemptNumbers()+1);
            gameEngine.getGameData().updateFailedGame();
            gameEngine.getGameData().updatePlayerLog();
            gameEngine.setExecuteGame(false);
            nifty.showPopup(screen, gameOverPopup.getId(), null);//function in OverallScreenController
        }else        
        if (arrOrders.size() == gameEngine.getGameData().getMapOrder().size() && allProcessed){
            //YOU WON!!!!
            
            if (Params.isTutorialLevel && !Params.tutorial.isTutorialCompleted()){ //If the tutorial has not been completed, do not end the game.
                return;
            }
            else if (Params.isObjectiveLevel && !Params.objective.isObjectiveCompleted()){ //If the objective level has not been completed, do not end the game.
                if (Params.isObjectiveLevel && Params.objective.getCurrentStep() == 8)
                    Params.objective.nextStep();
                else
                    return;
            }
            
            gameEngine.getGameSounds().stopSound(Sounds.Background);
            gameEngine.getGameSounds().playSound(Sounds.GameWon);
            gameEngine.getNifty().getScreen("layerScreen").findElementByName("winOvC_Element").getControl(OverallScreenController.class).updateData();
            gameEngine.getLayerScreenController().pauseGame();
            gameEngine.getGameData().getCurrentGame().setGameStatus(GameStatus.Completed);
            gameEngine.getGameData().updateWonGame();
            gameEngine.getGameData().updatePlayerLog();
            gameEngine.setExecuteGame(false);
            nifty.showPopup(screen, gameWonPopup.getId(), null);
        }
    }
    
    public void cleanOrders(){
        ((ListBox<ListBoxMessages_Order>)screen.findNiftyControl("ordersValue_WOrC", ListBox.class)).clear();
        arrOrders.clear();
        currentOrders = 0;
    }
    
    private void gameOverRestart(Element tempPopup){
        nifty.closePopup(tempPopup.getId());
        screen.processAddAndRemoveLayerElements();
        ArrayList<E_Game> games = gameEngine.getGameData().loadGamesByType(GameType.CPU);
        E_Game game = null;
        for (E_Game tempGame : games){
            if (gameEngine.getInitialGameId() == tempGame.getIdGame()){
                game = tempGame;
                break;
            }
        }
        if (game != null){
            cleanOrders();
            gameEngine.playGame(game,true);
        }
    }
    
    @NiftyEventSubscriber(id="gameOverRestartO")
    public void onGameOverRestartButtonClicked(final String id, final ButtonClickedEvent event) {
        gameOverRestart(gameOverPopup);
    }
    
    @NiftyEventSubscriber(id="gameOverQuitO")
    public void onGameOverQuitButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.showPopup(screen, quitPopup.getId(), null);
    }
    
    class popupClosed implements EndNotify{
        @Override
        public void perform(){
            ((MenuScreenController)nifty.getScreen("initialMenu").getScreenController()).setDefaultStart(false);
            nifty.gotoScreen("initialMenu");
            Element nextElement = nifty.getScreen("initialMenu").findElementByName("dialogNewGameStage1Menu");
            NewGame1MenuController loadGameMenu = nextElement.getControl(NewGame1MenuController.class);
            loadGameMenu.updateControls_stage1();
        }
    }
    
    @NiftyEventSubscriber(id="gameWonNextGame")
    public void onGameWonNextGameButtonClicked(final String id, final ButtonClickedEvent event) {
        gameEngine.getLayerScreenController().pauseGame();
        cleanOrders();
      
        nifty.closePopup(gameWonPopup.getId(),new popupClosed());
        

//        nifty.gotoScreen("initialMenu");
        
        Element nextElement = nifty.getScreen("initialMenu").findElementByName("dialogNewGameStage1Menu");
        NewGame1MenuController loadGameMenu = nextElement.getControl(NewGame1MenuController.class);
        loadGameMenu.updateControls_stage1();
        nextElement.show();
//        nifty.getScreen("initialMenu").findElementByName("dialogMainMenu").hideWithoutEffect();
//        nifty.getScreen("initialMenu").findElementByName("dialogNewGameStage1Menu").showWithoutEffects();
    }
    
    @NiftyEventSubscriber(id="gameWonRestart")
    public void onGameWonRestartButtonClicked(final String id, final ButtonClickedEvent event) {
        gameOverRestart(gameWonPopup);
    }
    
    @NiftyEventSubscriber(id="gameWonQuit")
    public void onGameWonQuitButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.showPopup(screen, quitPopup.getId(), null);
    }
    
    @NiftyEventSubscriber(pattern="quitPopup.*")
    public void onAnswerPopupButtonClicked(final String id, final ButtonClickedEvent event) {
        if (id.equals("quitPopupYes")){
            gameEngine.getGameData().logoutPlayer();
            gameEngine.jmonkeyApp.stop();
             System.exit(0);
        }else{
            nifty.closePopup(quitPopup.getId());
        }
    }
    public void HideWindow()
    {
        nifty.getScreen("layerScreen").findElementByName("winOrderControl").hide();
        nifty.getScreen("layerScreen").findElementByName("OrderLabel").show();
    }
    public void ShowWindow()
    {
        nifty.getScreen("layerScreen").findElementByName("winOrderControl").show();  
        nifty.getScreen("layerScreen").findElementByName("OrderLabel").hide();
        if (Params.isTutorialLevel && Params.tutorial.getCurrentStep() == 17)
            Params.tutorial.nextStep();
        
    }
}
