/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.tutorial;

import com.virtualfactory.narrator.Narrator;

/**
 *
 * @author José Martinez
 */
public class Tutorial {
    
    private Narrator narrator;
    private String message; 
    private int stepNumber;
    
    public Tutorial(Narrator narrator) {
        
        this.narrator = narrator;
        this.message = "Tutorial Level Started!!!\n"
                + "Go to the first floor to start the tutorial.";
        this.stepNumber = 0;
    }
    
    public void update () {
        System.out.println("Step #" + this.stepNumber);
        if (this.narrator.hasStoppedTalking())
            talk();
    }
    
    public void talk(){
        
        narrator.talk(this.message, 10);
        
    }
    
    public void nextStep () {
        this.narrator.hide();
        this.stepNumber++;
        this.message = getMessage();
    }

    private String getMessage() {
        String stepMessage = "";
        switch (this.stepNumber) {
            case 0:
                break;
            case 1:
                stepMessage = "Press the setup button.";
                break;
            case 2:
                stepMessage = "To start the game, you will need to complete "
                        + "the checklist.";
                break;
            case 3:
                stepMessage = "Now you are ready to start!\nPress the start "
                        + "button.";
                break;
            case 4:
                stepMessage = "Now lets hire employees! Not all the operators "
                        + "have the same job. An employee can be "
                        + "'Material Handler', 'Asembler' or 'Versatile'. Go to "
                        + "Uilities.";
                break;
            case 5:
                stepMessage = "Go to Operators and hire an operator. Remember to "
                        + "always press the \"Update\" button if "
                        + "you want to save the changes.";
                break;
            case 6:
                stepMessage = "Pausing the game can be really helpful!. \nPause "
                        + "the game using the button at the top left corner. ";
                break;
            case 7:
                stepMessage = "Good Job! Lets learn how to buy or sell machines "
                        + "and equipment.\nGo to Utilities-Machine.";
                break;
            case 8:
                stepMessage = "In this window you can buy or sell machines. "
                        + "You can also perform preventive maintenance in order "
                        + "to reduce the machine's probability of failure. \nGo to Utilities-Equipment.";
                break;
            case 9:
                stepMessage = "Using this window you can buy, sell and perform "
                        + "preventive maintenance on equipment.\nNow go to Activities-Purchase.";
                break;
            case 10:
                stepMessage = "In case you didn't know, you need material for "
                        + "the product that your factory creates! In this window "
                        + "you can decide how much material you want to buy (Order "
                        + "Quantity), how often you want to reorder material "
                        + "(Reorder Point) and who is going to be your supplier. "
                        + "Go to Activities-Transport.";
                break;
            default:
                stepMessage = "Step " + this.stepNumber + " message is missing.";
                
        }
        
        return stepMessage;
        
    }
    
    public int getCurrentStep() {
        return this.stepNumber;
    }
    
}
