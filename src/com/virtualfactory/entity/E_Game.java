package com.virtualfactory.entity;

import com.virtualfactory.utils.GameCategory;
import com.virtualfactory.utils.GameStatus;
import com.virtualfactory.utils.GameType;
import com.virtualfactory.utils.Params;

/**
 *
 * @author David
 */
public class E_Game {
    private int idGame;
    private int dateTime;
    private int idPlayer;
    private double currentMoney;
    private double initialMoney;
    private int currentMinute;
    private int currentHour;
    private int currentDay;
    private int currentMonth;
    private double currentTime;
    private double timeFactor;
    private GameType gameType;
    private GameCategory gameCategory;
    private String description;
    private double percentageToWin;
    private String gameName;
    private GameStatus gameStatus;
    private int idPhase;
    private int nextIdPhase;
    private int idTerrain;
    private String objective;
    private String flowImage;
    private int attemptNumbers;
    private String yourBestScore;
    private String gameBestScore;
    private double overhead;
    
//
//    public E_Game(int idGame, int idPlayer, double currentMoney, int currentMinute, int currentHour, int currentDay, int currentMonth, double currentTime) {
//        this.idGame = idGame;
//        this.idPlayer = idPlayer;
//        this.currentMoney = currentMoney;
//        this.currentMinute = currentMinute;
//        this.currentHour = currentHour;
//        this.currentDay = currentDay;
//        this.currentMonth = currentMonth;
//        this.currentTime = currentTime;
//    }

    public E_Game() {
    }
    
    /**
     * The variable should have an older time
     * than the current game
     */
    public int getDifferenceTimes_Minutes(E_Game olderGame){
        int initialTime = this.getCurrentMinute() + this.getCurrentHour()*60 + 
                this.getCurrentDay()*Params.numberWorkHoursPerDay*60 + 
                this.getCurrentMonth()*Params.numberWorkDaysPerMonth*Params.numberWorkHoursPerDay*60;
        int finalTime = olderGame.getCurrentMinute() + olderGame.getCurrentHour()*60 + 
                olderGame.getCurrentDay()*Params.numberWorkHoursPerDay*60 + 
                olderGame.getCurrentMonth()*Params.numberWorkDaysPerMonth*Params.numberWorkHoursPerDay*60;
        
        return finalTime - initialTime;
    }

    public double getOverhead() {
        return overhead;
    }

    public void setOverhead(double overhead) {
        this.overhead = overhead;
    }

    public String getGameBestScore() {
        return gameBestScore;
    }

    public void setGameBestScore(String gameBestScore) {
        this.gameBestScore = gameBestScore;
    }

    public String getYourBestScore() {
        return yourBestScore;
    }

    public void setYourBestScore(String yourBestScore) {
        this.yourBestScore = yourBestScore;
    }

    public int getAttemptNumbers() {
        return attemptNumbers;
    }

    public void setAttemptNumbers(int attemptNumbers) {
        this.attemptNumbers = attemptNumbers;
    }

    public String getFlowImage() {
        return flowImage;
    }

    public void setFlowImage(String flowImage) {
        this.flowImage = flowImage;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public int getIdPhase() {
        return idPhase;
    }

    public void setIdPhase(int idPhase) {
        this.idPhase = idPhase;
    }

    public int getNextIdPhase() {
        return nextIdPhase;
    }

    public void setNextIdPhase(int nextIdPhase) {
        this.nextIdPhase = nextIdPhase;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public double getPercentageToWin() {
        return percentageToWin;
    }

    public void setPercentageToWin(double percentageToWin) {
        this.percentageToWin = percentageToWin;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public int getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(int currentHour) {
        this.currentHour = currentHour;
    }

    public int getCurrentMinute() {
        return currentMinute;
    }

    public void setCurrentMinute(int currentMinute) {
        this.currentMinute = currentMinute;
    }

    public double getInitialMoney() {
        return initialMoney;
    }

    public void setInitialMoney(double initialMoney) {
        this.initialMoney = initialMoney;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getDateTime() {
        return dateTime;
    }

    public void setDateTime(int dataTime) {
        this.dateTime = dataTime;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public double getTimeFactor() {
        return timeFactor;
    }

    public void setTimeFactor(double timeFactor) {
        this.timeFactor = timeFactor;
    }

    public GameCategory getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(GameCategory gameCategory) {
        this.gameCategory = gameCategory;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
