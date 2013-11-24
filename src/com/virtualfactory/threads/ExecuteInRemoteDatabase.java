/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.threads;

import com.virtualfactory.data.D_Game;
import com.virtualfactory.entity.E_Game;

/**
 *
 * @author David
 */
public class ExecuteInRemoteDatabase extends Thread {
    private E_Game game;
    private String action;
    
    @Override
    public void run(){
        if (action.equals("Game_Insert"))
            gameInsert();
    }
    
    private void gameInsert(){
        new D_Game().Insert(game);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public E_Game getGame() {
        return game;
    }

    public void setGame(E_Game game) {
        this.game = game;
    }
    
    
}
