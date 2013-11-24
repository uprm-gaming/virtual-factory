/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.utils;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.virtualfactory.GameEngine;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class GameSounds { 
    private final static String folder = "Sounds/";
    public final static String playPause = folder + "play_pause.wav";
    public final static String machineEquipmentBroken = folder + "machineEquipment_broken.wav";
    public final static String machineEquipmentFixed = folder + "machineEquipment_fixed_mono.wav";
    public final static String orderArrived = folder + "order_arrived.wav";
    public final static String orderNearToDueDate = folder + "orderNearToDueDate.wav";
    public final static String orderSucceed = folder + "order_succeed.wav";
    public final static String orderFailed = folder + "order_failed.wav";
    public final static String gameOver = folder + "game_over_mono.wav";
    public final static String gameWon = folder + "game_won_mono.wav";
    public final static String background = folder + "background_mono.wav";
    public final static String machineWorking = folder + "machineWorking.wav";
    public final static String gameWarningMoney = folder + "game_warning.wav";
    public final static String gameNoMoney = folder + "game_bankrupt.wav";
    public final static String purchaseArrived = folder + "purchase_arrived.wav";
    private float currentSFXVolume=100;
    private AudioNode audioPlayPause;
    private AudioNode audioMachineEquipmentBroken;
    private AudioNode audioMachineEquipmentFixed;
    private AudioNode audioOrderArrived;
    private AudioNode audioOrderNearToDueDate;
    private AudioNode audioOrderSucceed;
    private AudioNode audioOrderFailed;
    private AudioNode audioGameOver;
    private AudioNode audioGameWon;
    private AudioNode audioBackground;
    private AudioNode audioMachineWorking;
    private AudioNode audioGameWarningMoney;
    private AudioNode audioGameNoMoney;
    private AudioNode audioPurchaseArrived;
    
    //Chris fix
    private boolean isPlayingMachineWorking;
    
    public GameSounds(AssetManager assetManager){
        audioPlayPause = new AudioNode(assetManager, playPause, false);
        audioPlayPause.setLooping(false);
        audioMachineEquipmentBroken = new AudioNode(assetManager, machineEquipmentBroken, false);
        audioMachineEquipmentBroken.setLooping(false);
        audioMachineEquipmentFixed = new AudioNode(assetManager, machineEquipmentFixed, false);
        audioMachineEquipmentFixed.setLooping(false);
        audioOrderArrived = new AudioNode(assetManager, orderArrived, false);
        audioOrderArrived.setLooping(false);
        audioOrderNearToDueDate = new AudioNode(assetManager, orderNearToDueDate, false);
        audioOrderNearToDueDate.setLooping(true);
        audioOrderSucceed = new AudioNode(assetManager, orderSucceed, false);
        audioOrderSucceed.setLooping(false);
        audioOrderFailed = new AudioNode(assetManager, orderFailed, false);
        audioOrderFailed.setLooping(false);
        audioGameOver = new AudioNode(assetManager, gameOver, false);
        audioGameOver.setLooping(false);
        audioGameWon = new AudioNode(assetManager, gameWon, false);
        audioGameWon.setLooping(false);
        audioBackground = new AudioNode(assetManager, background, false);
        audioBackground.setLooping(true);
        audioMachineWorking = new AudioNode(assetManager, machineWorking, false);
        audioMachineWorking.setLooping(true);
        audioGameWarningMoney = new AudioNode(assetManager, gameWarningMoney, false);
        audioGameWarningMoney.setLooping(false);
        audioGameNoMoney = new AudioNode(assetManager, gameNoMoney, false);
        audioGameNoMoney.setLooping(false);
        audioPurchaseArrived = new AudioNode(assetManager, purchaseArrived, false);
        audioPurchaseArrived.setLooping(false);
    }
    
    public void playSound(Sounds sound){
        manageSounds(sound, 1);
    }
    
    public void stopSound(Sounds sound){
        manageSounds(sound, -1);
    }
    
    public void pauseSound(Sounds sound){
        manageSounds(sound, 0);
    }
    
    public void stopSounds(){
        
    }
    
    private void manageSounds(Sounds sound, int action){
        switch (sound){
            case PlayPause: 
                audioPlayPause.playInstance(); 
                break;
            case MachineEquipmentBroken: 
                audioMachineEquipmentBroken.playInstance(); 
                break;
            case MachineEquipmentFixed: 
                audioMachineEquipmentFixed.playInstance(); 
                break;
            case OrderArrived: 
                audioOrderArrived.playInstance(); 
                break;
            case OrderNearToDueDate: 
                switch (action){
                    case -1:    audioOrderNearToDueDate.stop(); break;
                    case 0:    audioOrderNearToDueDate.pause(); break;
                    case 1:    audioOrderNearToDueDate.play(); break;
                }
                break;
            case OrderSucceed: 
                audioOrderSucceed.playInstance();
                break;
            case OrderFailed: 
                audioOrderFailed.playInstance();
                break;
            case GameOver: 
                audioGameOver.playInstance();
                break;
            case GameWon: 
                audioGameWon.playInstance();
                break;
            case Background: 
                switch (action){
                    case -1:    audioBackground.stop(); break;
                    case 0:    audioBackground.pause(); break;
                    case 1:    audioBackground.play(); break;
                }
                break;
            case MachineWorking: 
                switch (action){
                    case -1:    
                    audioMachineWorking.stop();
                    isPlayingMachineWorking=false;
                    break;
                    case 0:    audioMachineWorking.pause(); 
                    isPlayingMachineWorking=false;
                    break;
                        
                    case 1:    audioMachineWorking.play();
                    isPlayingMachineWorking=true;
                    break;
                }
                break;
            case GameWarningMoney: 
                audioGameWarningMoney.playInstance();
                break;
            case GameNoMoney: 
                switch (action){
                    case -1:    audioGameNoMoney.stop(); break;
                    case 0:    audioGameNoMoney.pause(); break;
                    case 1:    audioGameNoMoney.play(); break;
                }
                break;
            case PurchaseArrived: 
                audioPurchaseArrived.playInstance();
                break;
        }
    }
    public boolean machineSoundPlaying ()
    {
        return isPlayingMachineWorking;
    }
    
    public void setVolumeMusic(float newVolume){
        audioBackground.setVolume(newVolume);
    }
    
    public void setVolumeSFX(float newVolume,GameEngine engine){
        this.currentSFXVolume=newVolume;
        audioPlayPause.setVolume(newVolume);
        audioMachineEquipmentBroken.setVolume(newVolume);
        audioMachineEquipmentFixed.setVolume(newVolume);
        audioOrderArrived.setVolume(newVolume);
        audioOrderNearToDueDate.setVolume(newVolume);
        audioOrderSucceed.setVolume(newVolume);
        audioOrderFailed.setVolume(newVolume);
        audioGameOver.setVolume(newVolume);
        audioGameWon.setVolume(newVolume);
        audioMachineWorking.setVolume(newVolume);
        audioGameWarningMoney.setVolume(newVolume);
        audioGameNoMoney.setVolume(newVolume);
        audioPurchaseArrived.setVolume(newVolume);
     
        for (Pair<GameSounds,Sounds> a : engine.getArrGameSounds())
        {
            a.getFirst().audioMachineWorking.setVolume(newVolume);
        }
     
       
    }

    public void notifyAudio(GameEngine engine)
    {
        engine.getGeneralScreenController().notifySound();
    }
}
