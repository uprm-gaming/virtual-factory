/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.threads;

import de.lessvoid.nifty.controls.Label;
import com.virtualfactory.data.GameData;
import com.virtualfactory.data.D_Game;
import com.virtualfactory.data.D_Player;
import com.virtualfactory.entity.E_Game;
import com.virtualfactory.entity.E_Player;
import com.virtualfactory.entity.E_PlayerLog;
import com.virtualfactory.screen.menu.components.InitialMenuController;
import com.virtualfactory.screen.other.ProgressBarController;
import com.virtualfactory.utils.Params;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author David
 */
public class UserDataLoading extends Thread {
    private String email;
    private String password;
    private E_Player currentPlayer;
    private InitialMenuController imc;
    private ProgressBarController pbc;
    private Label updatingLabel;
    private GameData gameData;
    
    @Override
    public void run(){
        boolean result = true;
        D_Player dPlayer = new D_Player();
        Params.percentageLoading = 0.0f;
        Params.percentageQuote = 0.0f;
        pbc.setProgress(0.0f);
        //SYNCHRONIZING user data
        updatingLabel.setText("Synchronizing User Data..");
        E_Player localPlayer = dPlayer.SelectSQLite_PlayerByEmail(email);
        pbc.setProgress(0.15f);
        E_Player remotePlayer = dPlayer.SelectPlayerByEmail(email);
        pbc.setProgress(0.25f);
        if (localPlayer != null && remotePlayer != null){
            if (localPlayer.getLastLoginTime() > remotePlayer.getLastLoginTime()){ //update REMOTE
                dPlayer.Update(localPlayer);
                currentPlayer = localPlayer;
            }else
            if (localPlayer.getLastLoginTime() < remotePlayer.getLastLoginTime()){ //update LOCAL
                dPlayer.UpdateSQLite(remotePlayer);
                currentPlayer = remotePlayer;
            }else{
                currentPlayer = localPlayer;//or remotePlayer
            }
        }else
        if (localPlayer == null && remotePlayer != null){
            currentPlayer = remotePlayer;
            updatingLabel.setText("Updating User Data..");
            dPlayer.InsertSQLite(currentPlayer);
            Params.percentageLoading = Params.percentageLoading + 0.1f;
//            pbc.setProgress(Params.percentageLoading);
//            updatingLabel.setText("Updating User Games..");
//            Params.percentageQuote = 0.2f;
//            new D_Game().updateLocalDBByPlayer(currentPlayer.getIdPlayer(), pbc);
        }else
        if (localPlayer != null && remotePlayer == null){
            currentPlayer = localPlayer;
            //it should never happen because when the user is created, it will be stored into remote and local database
        }
        pbc.setProgress(0.55f);
        //validate PASSWORD
        if (currentPlayer != null){
            synchronizeGame_LocalRemoteDB();
            synchronizeLog_LocalRemoteDB();
            if (!currentPlayer.getPassword().equals(password)){
                updatingLabel.setText("Wrong Password.");
                result = false;
            }                
        }else{
            updatingLabel.setText("Not user found.");
            result = false;
        }
        pbc.setProgress(1f);
        gameData.setPlayer(currentPlayer);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("ERROR:" + ex.getMessage());
        }
        imc.LoginResponse(result);
    }
    
    private void synchronizeGame_LocalRemoteDB(){
        D_Game dGame = new D_Game();
        Map<Integer, E_Game> remoteGames = dGame.SelectGames_OfPlayer(currentPlayer.getIdPlayer());
        Map<Integer, E_Game> localGames = dGame.SQLite_SelectGames_OfAPlayer(currentPlayer.getIdPlayer());
        if (remoteGames.size() > localGames.size()){ //update games in local database
            for (E_Game remoteGame : remoteGames.values()){
                if (localGames.containsKey(remoteGame.getIdGame())){//update depending time_date
                    if (localGames.get(remoteGame.getIdGame()).getDateTime() >  remoteGame.getDateTime()){
                        dGame.Update(localGames.get(remoteGame.getIdGame()));
                    }else
                    if (localGames.get(remoteGame.getIdGame()).getDateTime() <  remoteGame.getDateTime()){
                        dGame.SQLite_Update(remoteGame);
                    }
                }else{
                    dGame.SQLite_Insert(remoteGame);
                }
            }
        }else
        if (remoteGames.size() < localGames.size()){ //update games in remote database
            for (E_Game localGame : localGames.values()){
                if (remoteGames.containsKey(localGame.getIdGame())){
                    if (remoteGames.get(localGame.getIdGame()).getDateTime() >  localGame.getDateTime()){
                        dGame.SQLite_Update(remoteGames.get(localGame.getIdGame()));
                    }else
                    if (remoteGames.get(localGame.getIdGame()).getDateTime() <  localGame.getDateTime()){
                        dGame.Update(localGame);
                    }
                }else{
                    dGame.Insert(localGame);
                }
            }
        }else{
            for (E_Game remoteGame : remoteGames.values()){
                if (localGames.containsKey(remoteGame.getIdGame())){ //check last game update
                    if (localGames.get(remoteGame.getIdGame()).getDateTime() >  remoteGame.getDateTime()){
                        dGame.Update(localGames.get(remoteGame.getIdGame()));
                    }else
                    if (localGames.get(remoteGame.getIdGame()).getDateTime() <  remoteGame.getDateTime()){
                        dGame.SQLite_Update(remoteGame);
                    }
                }
            }
        }
    }
    
    private void synchronizeLog_LocalRemoteDB(){
        D_Player dPlayer = new D_Player();
        Map<Integer, E_PlayerLog> logMissing = dPlayer.PlayerLogSQLite_Select(currentPlayer.getIdPlayer(), pbc);
        if (logMissing.size() > 0){
            int i=0;
            Params.percentageQuote = 1.0f - Params.percentageLoading;
            for (E_PlayerLog tempPlayerLog : logMissing.values()){
                if (tempPlayerLog.getLoginTime() != -1 && tempPlayerLog.getLogoutTime() != -1 
                    && tempPlayerLog.getMinutesTime()>=0 && !tempPlayerLog.getGameLevel().isEmpty()){
                    if (tempPlayerLog.getIdPlayerLogRemote() == -1){
                        if (dPlayer.PlayerLog_Insert(tempPlayerLog, false)){//insert Remote DB
                            dPlayer.PlayerLogSQLite_Delete(tempPlayerLog);//drop Local DB
                        }
                    }else{
                        if (dPlayer.PlayerLog_Update(tempPlayerLog, false)){//update Remote DB
                            dPlayer.PlayerLogSQLite_Delete(tempPlayerLog);//drop Local DB
                        }
                    }
                }else{
                    dPlayer.PlayerLogSQLite_Delete(tempPlayerLog);//drop Local DB
                }
                i++;
                pbc.setProgress(Params.percentageLoading + i*Params.percentageQuote/(logMissing.size()*1.0f));
            }
            Params.percentageLoading = Params.percentageLoading + Params.percentageQuote;
            pbc.setProgress(Params.percentageLoading);
        }
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public E_Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(E_Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public InitialMenuController getImc() {
        return imc;
    }

    public void setImc(InitialMenuController imc) {
        this.imc = imc;
    }
    
    public Label getUpdatingLabel() {
        return updatingLabel;
    }

    public void setUpdatingLabel(Label updatingLabel) {
        this.updatingLabel = updatingLabel;
    }

    public ProgressBarController getPbc() {
        return pbc;
    }

    public void setPbc(ProgressBarController pbc) {
        this.pbc = pbc;
    }
}
