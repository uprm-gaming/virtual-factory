/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.entity;

import com.virtualfactory.utils.Actions;
import com.virtualfactory.utils.GameTables;

/**
 *
 * @author David
 */
public class E_ToUpdate {
    int idToUpdate;
    int idGame;
    int gameTableKey;
    GameTables gameTable;
    Actions actionToDo;

    public int getIdToUpdate() {
        return idToUpdate;
    }

    public void setIdToUpdate(int idToUpdate) {
        this.idToUpdate = idToUpdate;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getGameTableKey() {
        return gameTableKey;
    }

    public void setGameTableKey(int gameTableKey) {
        this.gameTableKey = gameTableKey;
    }

    public GameTables getGameTable() {
        return gameTable;
    }

    public void setGameTable(GameTables gameTable) {
        this.gameTable = gameTable;
    }

    public Actions getActionToDo() {
        return actionToDo;
    }

    public void setActionToDo(Actions actionToDo) {
        this.actionToDo = actionToDo;
    }
    
}
