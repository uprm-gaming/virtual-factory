package com.virtualfactory.tutorial;

import com.virtualfactory.narrator.Narrator;
import java.util.ArrayList;

/**
 * Objective-based level.
 * @author José Martinez
 * @author Amilcar Torres
 */
public class Objective {
    
    private Narrator narrator;
    private String message; 
    private int stepNumber;
    private boolean isHidden;
    private boolean isObjectiveLevelCompleted, isTimeLimitedStep;
    private long startTime = 0;
    private int secondsToWait = 13;
    private boolean firstRun = true;
    public ArrayList<Integer> actionRequiredSteps;

    public Objective(Narrator narrator) {
        
        this.narrator = narrator;
        this.stepNumber = 0;
        this.isHidden = false;
        this.isObjectiveLevelCompleted = false;
        this.isTimeLimitedStep = false;
        this.message = getMessage();
        this.actionRequiredSteps = new ArrayList<>();
        this.actionRequiredSteps.add(2);
        this.actionRequiredSteps.add(4);
        this.actionRequiredSteps.add(5);
        this.actionRequiredSteps.add(8);
        
        
//        this.actionRequiredSteps.add(9);
//        this.actionRequiredSteps.add(10);
//        this.actionRequiredSteps.add(11);
//        this.actionRequiredSteps.add(12);

    }
    
    public void update () {
//        System.out.println("Step #" + this.stepNumber);
        if (this.isObjectiveLevelCompleted);
        else if (this.isHidden)  
                hide();
        else if (this.narrator.hasStoppedTalking()) {
            if (this.isTimeLimitedStep && System.currentTimeMillis() - this.startTime > this.secondsToWait*1000)
                nextStep();
            
            talk();
            
        }   
    }
    
    public void talk() {

        this.narrator.talk(this.message, 13, "boss");
        
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
        if (!firstRun)
            this.stepNumber++;
        else
            this.firstRun = false;
        
        this.message = getMessage();
        
        startTime = System.currentTimeMillis();

        if (isObjectiveLevelCompleted) {
            talk();
        }
    }

    private String getMessage() {
        String stepMessage;
        switch (this.stepNumber) {
            case 0:
                stepMessage = "Ah, you are here. It's good that you are early. "
                        + "We are excited to have you as part of the Virtual Factory team. "
                        + "An Hurricane has just hit our country, and thousands of pieces of "
                        + "furniture are needed to rebuild the damaged homes. We need you to rebuild this country! "
                        + "\n(Press SPACE to continue)";
                break;
            case 1:
                stepMessage = "Let's give you a basic rundown of what you will be doing. "
                        + "The finished product will be a combination of two initial parts. "
                        + "These parts will have to be shipped before the given time frame "
                        + "or we will start losing money.\n(Press SPACE to continue)";
                break;
            case 2:
                stepMessage = "Alright lets get started. I hope you completed our training(see 'Tutorial Level'). "
                        + "We need to get the raw material for the production of part one. For that we "
                        + "need to hire and assign the appropiate amount of workers for the corresponding transportation "
                        + "activity.\n";
                break;
            case 3:
                stepMessage = "I hope you assigned the correct number. Well you "
                        + "know what you are doing so I'll trust on your skill. "
                        + "Remember to assign workers for all the other tasks.\n(Press SPACE to continue)";
                break;
            case 4:
                stepMessage = "Let's work on the priorities for each of the tasks. "
                        + "You've had years of training in this. Work your magic. ";
                break;
            case 5:
                stepMessage = "You must not forget to buy machines and equipment! "
                        + "\nHow many will you buy? ";
                break;
            case 6:
                stepMessage = "Explore all the available settings and choose wisely. We need "
                        + "to ship as much furniture as posible in a very short amount of time. "
                        + "\n(Press SPACE to continue)";
                break;
            case 7:
                stepMessage = "Alright, that should do it. It's time to open the factory and start working. "
                        + "Let's see how much furniture we can supply to Virtual 'Muricans today!";
                break;
            case 8:
                //A message to be displayed if the player is losing.
                stepMessage = "";
                break;
//            case 9:
//                //A message to be displayed if the player is losing.
//                stepMessage = "Oh no, we are losing money! Fix it quick or we will "
//                        + "be forced to fire you!";
//                this.isTimeLimitedStep = true;
//                break;
//            case 10:
//                //A message to be displayed if the player is losing.
//                stepMessage = "";
//                this.isTimeLimitedStep = false;
//            case 11:
//                //A message to be displayed at random moments.
//                stepMessage = "Remember you can change the supplier for your raw "
//                        + "material at any time.";
//                break;
            case 9:
                //The message to be displayed when the game is completed.
                this.isObjectiveLevelCompleted = true;
                stepMessage = "Congratulations! We supplied furniture to all the "
                        + "Virtual 'Muricans that suffered the effects of the storm! ";
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
        return this.isObjectiveLevelCompleted;
    }

    public void setCurrentStep(int num) {
        this.stepNumber = num;
    }
     
}