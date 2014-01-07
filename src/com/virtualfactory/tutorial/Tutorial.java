package com.virtualfactory.tutorial;

import com.virtualfactory.narrator.Narrator;

/**
 * Custom tutorial to guide the beginning player.
 * @author José Martinez
 */
public class Tutorial {
    
    private Narrator narrator;
    private String message; 
    private int stepNumber;
    private boolean isHidden;
    private boolean isTutorialCompleted;
    
    public Tutorial(Narrator narrator) {
        
        this.narrator = narrator;
        this.stepNumber = 0;
        this.isHidden = false;
        this.isTutorialCompleted = false;
        this.message = getMessage();
    }
    
    public void update () {
//        System.out.println("Step #" + this.stepNumber);
        if (this.isTutorialCompleted);
        else if (this.isHidden)  
                hide();
        else if (this.narrator.hasStoppedTalking())
            talk();
        
    }
    
    public void talk() {
        
        this.narrator.talk(this.message, 20);
        
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
        
        if (isTutorialCompleted) {
            talk();
        }
    }

    private String getMessage() {
        String stepMessage = "";
        switch (this.stepNumber) {
            case 0:
                stepMessage = "Welcome to the tutorial Level!\n"
                + "Go to the first floor to start the tutorial. You can walk "
                + "using the A, W, D and S keys.";
                break;
            case 1:
                stepMessage = "Press the setup button.";
                break;
            case 2:
                stepMessage = "To start the game, you will need to complete "
                        + "the checklist. You can start completing the requirements "
                        + "by clicking the pause symbol beside each item of the list. "
                        + "\nClick the pause symbol beside the first item.";
                break;
            case 3:
                stepMessage = "In this window you must assign the number of operators, "
                        + "machines and equipment you are going to use. "
                        + "Select three operators (one Mat. Handler, one Operator and one Versatile),"
                        + " two machines and two equipment to continue. Remember "
                        + "to press the Update button after every change you want to save.";
                break;
            case 4:
                stepMessage = "Now it's time to hire an employee yourself. Not all the people "
                        + "can do the same job. An employee can be "
                        + "'Material Handler', 'Asembler' (operator) or 'Versatile'. Go to "
                        + "Uilities.";
                break;
            case 5:
                stepMessage = "Go to Operators and hire someone. Remember to "
                        + "always press the \"Update\" button if "
                        + "you want to save the changes.";
                break;
            case 6:
                stepNumber++;
                stepMessage = getMessage();
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
                stepMessage = "In case you didn't know, you need material to create "
                        + "the product! In this window "
                        + "you can decide how much material you want to buy (Order "
                        + "Quantity), how often you want to reorder material "
                        + "(Reorder Point) and who is going to be your supplier. "
                        + "Go to Activities-Transport.";
                break;
            case 11:
                stepMessage = "Different transportation activities take place in your factory. "
                        + "You can see the information about each different transportation activity in this window."
                        + "The unit load (parts per trip) can be different "
                        + "depending on the transportation activity."
                        + "\nGo to Utilities-Supplier.";
                break;
            case 12:
                stepMessage = "In this window you can manage the suppliers. "
                        + "You will need to analize the cost and the properties "
                        + "of each supplier and use the supplier that you need the most."
                        + "\nGo to Utilities-Part.";
                break;
            case 13:
                stepMessage = "Different parts are required to create your product. "
                        + "This window displays the price for sell, parts required "
                        + "and other useful information that you need to know."
                        + " \nGo to Utilities-Station.";
                break;
            case 14:
                stepMessage = "There are different stations in your factory. Using "
                        + "this window you can see how much material is"
                        + " currently in a specific station. "
                        + "\nPress the Flow Chart button.";
                break;
            case 15:
                stepMessage = "The Flow Chart explains the creation process of the product."
                        + "\nClose the Flow Chart and complete the whole list at the Game Setup window.";
                break;
            case 16:
                stepMessage = "You can now start the game.\nPress the start button to begin, then "
                        + "Press the Right-Shift button.";
                break;

            case 17:
                stepMessage = "The dashboard is very useful to see what's going on in "
                        + "your factory. Use the Right-Shift button again to close the dashboard."
                        + "\nClick the Order tab at the bottom left corner.";
                break;
            case 18:
                stepMessage = "This window show the orders received by your "
                        + "factory and wether it has been completed or not. You "
                        + "need to complete a certain amount of orders to win the game."
                        + " Check out the other two tabs too. They contain very"
                        + " important information that you will need to use during the game."
                        + "\nClick the Overall tab at the botton right corner.";
                break;
            case 19:
                stepMessage = "The overall window tells you everything you need to know about money."
                        + "\nClick the Overall tab again to close it.";
                break;
            case 20:
                stepMessage = "Do you see the three clocks at the top of the screen?  The one at the left"
                        + " is the game time. The Next Order Due clock specifies in how much time the next "
                        + "order has to be completed. and The Next Purchase indicator tells you the time "
                        + "that is left for a new purchase of raw material to arrive to the factory. "
                        + "\nClick some object to see its information.";
                break;
            case 21:
                stepMessage = "You can see an object's information by clicking it. You can click on "
                        + "workers, machines, equipment and stations."
                        + "\nPausing the game can be really helpful!. Pause "
                        + "the game using the button at the top left corner. ";
                break;
            case 22:
                this.isTutorialCompleted = true;
                stepMessage = "Well done! To end the tutorial, resume the game "
                        + "and complete the current order. You can see more detailed information "
                        + "about the game in the user manual. You can access the "
                        + "user manual by clicking the User"
                        + " Manual button in the main menu.\nHave fun!";
                break;
            default:
                stepMessage = "Step " + this.stepNumber + "'s message is missing.";
                
        }
        
        return stepMessage;
        
    }
    
    public int getCurrentStep() {
        return this.stepNumber;
    }

    public boolean isTutorialCompleted() {
        return this.isTutorialCompleted;
    }

    public void setCurrentStep(int num) {
        this.stepNumber = num;
    }

    public int getLastStep() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}