/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.tutorial;

import com.virtualfactory.narrator.Narrator;
import com.virtualfactory.utils.Params;

/**
 *
 * @author José Martinez
 */
public class Tutorial {
    
    private Narrator narrator;
    private String message; 
    private int stepNumber;
    private boolean isLevelStarted;
    
    public Tutorial(Narrator narrator) {
        
        this.narrator = narrator;
        this.message = "Tutorial Level Started!!!\n"
                + "Go to the first floor to start the tutorial.";
        this.stepNumber = 0;
        this.isLevelStarted = false;
    }
    
    public void update () {
//        System.out.println("Step #" + this.stepNumber);
        if (!Params.isLevelStarted && !this.narrator.hasStoppedTalking()) 
            this.narrator.hide();
        else if (this.narrator.hasStoppedTalking())
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
            case 11:
                stepMessage = "The unit load(parts per trip) can be different "
                        + "depending on the transportation activity.\nGo to Utilities-Supplier.";
                break;
            case 12:
                stepMessage = "In this window you can manage the suppliers. "
                        + "You will need to analize the cost and the properties "
                        + "of each supplier and use the supplier that you need the most."
                        + "\nGo to Utilities-Part.";
                break;
            case 13:
                stepMessage = "Different parts are required to create your product. "
                        + "Bla, bla, bla...  \nGo to Utilities-Station.";
                break;
            case 14:
                stepMessage = "La cantidad de cada pieza que tienen en cada estación?"
                        + "\nPress the Right-Shift button.";
                break;
            case 15:
                stepMessage = "The dashboard is very useful to see what's going on in your factory. "
                        + "Press the Right-Shift button again to close the dashboard."
                        + "\nClick the Order tab at the button left corner.";
                break;
            case 16:
                stepMessage = "This window show the orders received by your "
                        + "factory and wether it has been completed or not. You "
                        + "need to complete a certain amount of orders to win the game."
                        + " Check out the other two tabs too. They contain very"
                        + " important information that you will need to use during the game."
                        + "\nClick the Overall tab at the button right corner.";
                break;
            case 17:
                stepMessage = "The overall window tells you everything you need to know about money."
                        + "\nClick the Overall tab again to close it.";
                break;
            case 18:
                stepMessage = "Very good!  We are almost done. Do you see the "
                        + "three clocks at the top of the screen?  They represent "
                        + "something. You can see the information about a specific "
                        + "object by getting near it and clicking on it or pressing"
                        + " the Left-Shift button. Click something to see its information.";
                break;
            case 19:
                stepMessage = "You are done!! To end the tutorial resume the game "
                        + "and complete the received order. You can see more detailed information "
                        + "in the user manual. Just go back to the main menu "
                        + "and click the User Manual button.";
                break;
            default:
                stepMessage = "Step " + this.stepNumber + " message is missing.";
                
        }
        
        return stepMessage;
        
    }
    
    public int getCurrentStep() {
        return this.stepNumber;
    }
 
    public void isLevelStarted(boolean started) {
        this.isLevelStarted = started;
    }
    
}
