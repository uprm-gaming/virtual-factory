/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.entity;

/**
 *
 * @author David
 */
public class E_PlayerLog {
    private int idPlayerLog;
    private int idPlayer;
    private int loginTime = -1;
    private int logoutTime = -1;
    private int minutesTime;
    private String gameLevel;
    private int idPlayerLogRemote = -1;

    public int getIdPlayerLogRemote() {
        return idPlayerLogRemote;
    }

    public void setIdPlayerLogRemote(int idPlayerLogRemote) {
        this.idPlayerLogRemote = idPlayerLogRemote;
    }

    public int getIdPlayerLog() {
        return idPlayerLog;
    }

    public void setIdPlayerLog(int idPlayerLog) {
        this.idPlayerLog = idPlayerLog;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(int loginTime) {
        this.loginTime = loginTime;
    }

    public int getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(int logoutTime) {
        this.logoutTime = logoutTime;
    }

    public int getMinutesTime() {
        return minutesTime;
    }

    public void setMinutesTime(int minutesTime) {
        this.minutesTime = minutesTime;
    }

    public String getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(String gameLevel) {
        this.gameLevel = gameLevel;
    }
    
}
