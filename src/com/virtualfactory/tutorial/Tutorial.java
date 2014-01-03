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
                stepMessage = "To start the game, you will need to complete the checklist.";
                break;
            case 3:
                stepMessage = "Now you are ready!!!\nPress the start button.";
                break;
            case 4:
                stepMessage = "Now lets hire employees!\nNot all the Operators are the same. An employee can be Versatile, "
                        + "Material Handler or Operator.\nGo to Uilities.";
                break;
            case 5:
                stepMessage = "Go to Operators and hire Operator 2.\nRemember to always press the \"Update\" button if "
                        + "you want to save the changes.";
                break;
            case 6:
                stepMessage = "Step 6 reached.";
                break;
            default:
                stepMessage = 
                        "Step " + this.stepNumber + " message is missing.";
                
        }
        
        return stepMessage;
        
    }
    
    public int getCurrentStep() {
        return this.stepNumber;
    }
    
}
