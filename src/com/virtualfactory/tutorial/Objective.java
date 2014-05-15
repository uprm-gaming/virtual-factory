package com.virtualfactory.tutorial;

import com.virtualfactory.narrator.Narrator;

/**
 * Custom tutorial to guide the beginning player.
 * @author José Martinez
 */
public class Objective {
    
    private Narrator narrator;
    private String message; 
    private int stepNumber;
    private boolean isHidden;
    private boolean isObjectiveCompleted;
    
    public Objective(Narrator narrator) {
        
        this.narrator = narrator;
        this.stepNumber = 0;
        this.isHidden = false;
        this.isObjectiveCompleted = false;
        this.message = getMessage();
    }
    
    public void update () {
//        System.out.println("Step #" + this.stepNumber);
        if (this.isObjectiveCompleted);
        else if (this.isHidden)  
                hide();
        else if (this.narrator.hasStoppedTalking())
            talk();
        
    }
    
    public void talk() {
        
        this.narrator.talk(this.message, 10, "boss");
        
    }
    
    public void hide() {
        
        this.narrator.hide();
        this.isHidden = true;
    }
    
    public void show() {
        
        this.isHidden = false;
    }
    
    public void nextStep () {
        this.narrator.hide();
        this.stepNumber++;
        this.message = getMessage();
        
        if (isObjectiveCompleted) {
            talk();
        }
    }

    private String getMessage() {
        String stepMessage = "";
        switch (this.stepNumber) {
            case 0:
                stepMessage = "Ah, you are here. It's good that you are early. "
                + "We are excited to have you as part of the Virtual Factory team. "
                + "Let's give you a basic rundown of what you will be doing.";
                break;
            case 1:
                stepMessage = "The finished product will be a combination of two initial parts. "
                + "These parts will have to be shipped before the given time frame "
                + "or we will start losing money.";
                break;
            case 2:
                stepMessage = "Alright lets get started. We need to get the raw"
                + "material for the production of part one. For that we"
                + "need to assign the appropiate amount of workers.";
                break;
            case 3:
                stepMessage = "I hope you assigned the correct number. Well you "
                        + "know what you are doing so I'll trust on your skill. "
                        + "Now it's time to assign workers for the other tasks. ";
                break;
            case 4:
                stepMessage = "Let's work on the priorities for each of the tasks. "
                        + "You've had years of training in this. Work your magic. ";
                break;
            case 5:
                stepMessage = "You must not forget to buy machines! Which model "
                        + "will you choose?";
                break;
            case 6:
                stepNumber++;
                stepMessage = getMessage();
                break;
            case 7:
                //A message to be displayed at random moments.
                stepMessage = "Remember you can change the supplier for your raw "
                        + "material at any time.";
                break;
            case 8:
                stepMessage = "Alright, that should do it. Let's see how much "
                        + "furniture we can supply to Virtual 'Muricans today.";
                break;
            case 9:
                //A message to be displayed if the player is losing.
                stepMessage = "Oh no, we are losing money! Fix it quick or we will"
                        + "be force to fire you!";
                break;
            case 10:
                //The message to be displayed when the game is completed.
                this.isObjectiveCompleted = true;
                stepMessage = "Congratulations! We supplied furniture to all the "
                        + "Virtual 'Muricans that suffered the effects of the storm!";
                break;
            default:
                stepMessage = "Step " + this.stepNumber + "'s message is missing.";
                
        }
        
        return stepMessage;
        
    }
    
    public int getCurrentStep() {
        return this.stepNumber;
    }

    public boolean isObjectiveCompleted() {
        return this.isObjectiveCompleted;
    }

    public void setCurrentStep(int num) {
        this.stepNumber = num;
    }
     
}