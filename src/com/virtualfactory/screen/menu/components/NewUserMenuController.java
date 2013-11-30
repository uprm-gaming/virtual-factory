/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.screen.menu.components;

import com.virtualfactory.screen.menu.MenuScreenController;
import com.virtualfactory.utils.CommonBuilders;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.RadioButton;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import com.virtualfactory.app.GameEngine;
import com.virtualfactory.entity.E_Player;
import com.virtualfactory.utils.Messages;
import com.virtualfactory.utils.Params;
import com.virtualfactory.utils.PasswordGenerator;
import com.virtualfactory.utils.SendEmail;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author David
 */
public class NewUserMenuController implements Controller {
    private Screen screen;
    private Button createUserButton;
    private Button cancelButton;
    private TextField nameTextField;
    private TextField lastNameTextField;
    private DropDown statusDropDown;
    private TextField statusDescriptionTextField;
//    private TextField genderTextField;
    private DropDown degreeDropDown;
    private TextField degreeDescriptionTextField;
    private TextField countryTextField;
    private TextField oldPassTextField;
    private TextField passTextField;
    private TextField rePassTextField;
    private TextField emailTextField;
    private Label titleWindow;
    private Label errorMessage;
    private GameEngine gameEngine;
    private boolean isNewUser = false;
    private E_Player playerToUpdate = null;
    private PasswordGenerator pg = null;
    private static CommonBuilders builders = new CommonBuilders();
    private Nifty nifty;
    private String newGeneratedPassword;
    private ArrayList<String> arrStatus;
    private ArrayList<String> arrDegree;
    
    @Override
    public void bind(
        final Nifty nifty,
        final Screen screen,
        final Element element,
        final Properties parameter,
        final Attributes controlDefinitionAttributes) {
        this.nifty = nifty;
        this.screen = screen;
        this.nameTextField = screen.findNiftyControl("nameTextField_NUMD", TextField.class);
        this.emailTextField = screen.findNiftyControl("emailTextField_NUMD", TextField.class);
        this.createUserButton = screen.findNiftyControl("createUserButton_NUMD", Button.class);
        this.cancelButton = screen.findNiftyControl("cancelButton_NUMD", Button.class);
        this.titleWindow = screen.findNiftyControl("newUser_NUMD", Label.class);
        this.errorMessage = screen.findNiftyControl("errorMessage_NUMD", Label.class);
        
        this.lastNameTextField = screen.findNiftyControl("lastNameTextField_NUMD", TextField.class);
//        this.genderTextField = screen.findNiftyControl("genderTextField_NUMD", TextField.class);
        this.statusDropDown = screen.findNiftyControl("statusUserDropDown_NUMD", DropDown.class);
        this.statusDescriptionTextField = screen.findNiftyControl("statusDescriptionUserTextField_NUMD", TextField.class);
        this.degreeDropDown = screen.findNiftyControl("degreeUserDropDown_NUMD", DropDown.class);
        this.degreeDescriptionTextField = screen.findNiftyControl("degreeDescriptionUserTextField_NUMD", TextField.class);
        this.countryTextField = screen.findNiftyControl("countryTextField_NUMD", TextField.class);
        this.gameEngine = ((MenuScreenController)screen.getScreenController()).getGameEngine();
    }
    
    @Override
    public void init(final Properties parameter, final Attributes controlDefinitionAttributes) {
        //updateControls();
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onFocus(final boolean getFocus) {
    }

    @Override
    public boolean inputEvent(final NiftyInputEvent inputEvent) {
        return false;
    }
    
    private ArrayList<String> getStatusList(){
        if (arrStatus != null) return arrStatus;
        arrStatus = new ArrayList<String>();
        arrStatus.add("- Select One -");
        arrStatus.add("K-12 Student");
        arrStatus.add("Undergraduate/College Student");
        arrStatus.add("Graduate Student");
        arrStatus.add("Corporate/Industry Affiliate");
        arrStatus.add("Other");
        return arrStatus;
    }
    
    private ArrayList<String> getDegreeList(){
        if (arrDegree != null) return arrDegree;
        arrDegree = new ArrayList<String>();
        arrDegree.add("- Select One -");
        arrDegree.add("Engineering");
        arrDegree.add("Business Administration");
        arrDegree.add("Other");
        return arrDegree;
    }
    
    protected void updateControls(boolean isNewUser, E_Player currentUser){
        this.isNewUser = isNewUser;
        createUserButton.enable();
        nameTextField.enable();
        nameTextField.setText("");
        lastNameTextField.enable();
        lastNameTextField.setText("");
        statusDescriptionTextField.enable();
        statusDescriptionTextField.setText("");
//        ((RadioButtonGroup)screen.findNiftyControl("RadioGroup_Gender_NUMD", RadioButtonGroup.class)).allowDeselection(true);
        ((RadioButton)screen.findNiftyControl("female_NUMD", RadioButton.class)).select();
        ((RadioButton)screen.findNiftyControl("male_NUMD", RadioButton.class)).select();
        degreeDescriptionTextField.enable();
        degreeDescriptionTextField.setText("");
        countryTextField.enable();
        countryTextField.setText("");
        statusDropDown.enable();
        statusDropDown.clear();
        statusDropDown.addAllItems(getStatusList());
        degreeDropDown.enable();
        degreeDropDown.clear();
        degreeDropDown.addAllItems(getDegreeList());
        emailTextField.setText("");
        emailTextField.setMaxLength(100);
        cancelButton.setText("Cancel");
        if (screen.findElementByName("panel_EditProfile_NUMD") != null)    screen.findElementByName("panel_EditProfile_NUMD").markForRemoval();
        if (screen.findElementByName("panel_NewUser_NUMD") != null)   screen.findElementByName("panel_NewUser_NUMD").markForRemoval();
        screen.findElementByName("newUser_Parent").layoutElements();
        this.playerToUpdate = currentUser;
        if (isNewUser){
            createPanelNewUser();
            emailTextField.enable();
            createUserButton.setText("Create Account");
            titleWindow.setText("New User Registration");
        }else{
            createPanelEditProfile();
            this.oldPassTextField = screen.findNiftyControl("oldPassTextField_NUMD", TextField.class);
            this.passTextField = screen.findNiftyControl("passTextField_NUMD", TextField.class);
            this.rePassTextField = screen.findNiftyControl("rePassTextField_NUMD", TextField.class);
            oldPassTextField.setText("");
            oldPassTextField.setMaxLength(20);
            passTextField.setText("");
            passTextField.setMaxLength(20);
            rePassTextField.setText("");
            rePassTextField.setMaxLength(20);
            nameTextField.setText(currentUser.getName());
            lastNameTextField.setText(currentUser.getLastName());
            statusDropDown.selectItem(currentUser.getStatus());
            statusDescriptionTextField.setText(currentUser.getStatusDescription());
            if (currentUser.getGender().equals("Male"))
                ((RadioButton)screen.findNiftyControl("male_NUMD", RadioButton.class)).select();
            else
                ((RadioButton)screen.findNiftyControl("female_NUMD", RadioButton.class)).select();
            degreeDropDown.selectItem(currentUser.getDegree());
            degreeDescriptionTextField.setText(currentUser.getDegreeDescription());
            countryTextField.setText(currentUser.getCountry());
            emailTextField.setText(currentUser.getEmail());
            emailTextField.disable();
            oldPassTextField.enable();
            passTextField.enable();
            rePassTextField.enable();
            createUserButton.setText("Update Account");
            titleWindow.setText("Update Profile");
        }
        screen.findElementByName("newUser_Parent").layoutElements();
        
//        screen.findElementByName("principalPanel_NUMD").layoutElements();
//        screen.processAddAndRemoveLayerElements();
        errorMessage.setText("");
        nameTextField.setFocus();
//        nifty.resetMouseInputEvents();
        if (Params.BUILD_FOR_TESTING_SESSION) {
            ((Button)screen.findNiftyControl("createUserButton_NUMD", Button.class)).disable();
        }

    }
    
    private void createPanelNewUser(){
        new PanelBuilder("panel_NewUser_NUMD") {{
            childLayoutVertical();
            panel(new PanelBuilder() {{
                childLayoutHorizontal();
                panel(builders.hspacer("30%")); panel(builders.hspacer("5px"));
                control(new LabelBuilder("requiredField_2", "*  Indicates required field"){{ textHAlignLeft(); width("40%"); }});
            }});
            panel(new PanelBuilder() {{
                childLayoutHorizontal();
                panel(builders.hspacer("30%")); panel(builders.hspacer("5px"));
                control(new LabelBuilder("newUserRequirement", "** Require Internet connection to create a new user"){{ textHAlignLeft(); width("60%"); }});
            }});
//            width("100%");
        }}.build(nifty, screen, screen.findElementByName("newUser_Parent"));
    }
    
    private void createPanelEditProfile(){
        new PanelBuilder("panel_EditProfile_NUMD") {{
            childLayoutVertical();
//            panel(builders.vspacer());
            panel(new PanelBuilder() {{
              childLayoutHorizontal();
              control(new LabelBuilder("oldPasswordUser", "* Old Password:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
              control(new TextFieldBuilder("oldPassTextField_NUMD") {{ width("35%"); passwordChar("*".charAt(0)); }});
            }});
//            panel(builders.vspacer());
            panel(new PanelBuilder() {{
              childLayoutHorizontal();
              control(new LabelBuilder("passwordUser", "* New Password:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
              control(new TextFieldBuilder("passTextField_NUMD") {{ width("35%"); passwordChar("*".charAt(0)); }});
            }});
//            panel(builders.vspacer());
            panel(new PanelBuilder() {{
              childLayoutHorizontal();
              control(new LabelBuilder("rePasswordUser", "* Confirm New Password:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
              control(new TextFieldBuilder("rePassTextField_NUMD") {{ width("35%"); passwordChar("*".charAt(0)); }});
            }});
            panel(new PanelBuilder() {{
              childLayoutHorizontal();
              panel(builders.hspacer("30%")); panel(builders.hspacer("5px"));
              control(new LabelBuilder("passRequirements", "Password between 6-12 characters"){{ textHAlignLeft(); width("200px"); }});
            }});
            panel(new PanelBuilder() {{
              childLayoutHorizontal();
              panel(builders.hspacer("30%")); panel(builders.hspacer("5px"));
              control(new LabelBuilder("requiredField", "* Indicates required field"){{ textHAlignLeft(); width("50%"); }});
            }});
//            width("100%");
        }}.build(nifty, screen, screen.findElementByName("newUser_Parent"));
    }
    
    @NiftyEventSubscriber(id="createUserButton_NUMD")
    public void onCreateUserButtonClicked(final String id, final ButtonClickedEvent event) {
        errorMessage.setText("");
        if (!Params.existsInternetConnection){
            errorMessage.setText("Error: There is no Internet connection!");
            return;
        }
        if (isNewUser)
            generateNewPassword();
        if (!validateControls(isNewUser)){
            errorMessage.setText("Error: Please fill the fields correctly!");
            return;
        }
        if (isNewUser && !gameEngine.getGameData().validateEmailNewPlayer(emailTextField.getText())){
            errorMessage.setText("Error: The email account is already registered!");
            return;
        }
        if (isNewUser && !validateEduEmail()){
            errorMessage.setText("Error: Please enter an 'edu' email account!");
            return;
        }
        if (!isNewUser && !validateOldPassword()){
            errorMessage.setText("Error: Please enter your old password correctly");
            return;
        }
        if (!isNewUser && !validateOldNewPassword()){
            errorMessage.setText("Error: Please enter a different new password");
            return;
        }
        if (isNewUser){
            if (!gameEngine.getGameData().createPlayer(createInstancePlayer())){
                errorMessage.setText("Error: Could not create the new user!");
                return;
            }else{
                gameEngine.updateCursorIcon(1);
                String result = new SendEmail().send(emailTextField.getRealText(), nameTextField.getRealText() + " " + lastNameTextField.getRealText(), Messages.newPassword
                        .replace(Messages.wildCard, nameTextField.getRealText() + lastNameTextField.getRealText()).replace(Messages.wildCard2, newGeneratedPassword));
                gameEngine.updateCursorIcon(0);
                if (result.isEmpty()){
                    errorMessage.setText("You will receive a generated password to your email in a short time.\nWe suggest you change it to the next time.");
                    cancelButton.setText("Continue");
                }else{
                    errorMessage.setText(result);
                }
            }
        }else{
            if (!gameEngine.getGameData().updatePlayerProfile(updatePlayerInstance())) {
                errorMessage.setText("Error: Could not update the current user!");
                return;
            }else{
                errorMessage.setText("Your profile has been updated successfully");
                cancelButton.setText("Continue");
            }
        }
        createUserButton.disable();
    }
    
    private E_Player updatePlayerInstance(){
        playerToUpdate.setName(nameTextField.getRealText());
        playerToUpdate.setLastName(lastNameTextField.getRealText());
        playerToUpdate.setStatus(statusDropDown.getSelection().toString());
        playerToUpdate.setStatusDescription(statusDescriptionTextField.getRealText());
        playerToUpdate.setGender(((RadioButton)screen.findNiftyControl("male_NUMD", RadioButton.class)).isActivated() ? "Male" : "Female");
        playerToUpdate.setDegree(degreeDropDown.getSelection().toString());
        playerToUpdate.setDegreeDescription(degreeDescriptionTextField.getRealText());
        playerToUpdate.setCountry(countryTextField.getRealText());
        playerToUpdate.setPassword(passTextField.getRealText());
        return playerToUpdate;
    }
    
    @NiftyEventSubscriber(id="cancelButton_NUMD")
    public void onCancelButtonClicked(final String id, final ButtonClickedEvent event) {
        Element currentElement = screen.findElementByName("dialogNewUserMenu");
        currentElement.hide();
        screen.findElementByName("dialogNewUserMenu").stopEffect(EffectEventId.onCustom);
        if (playerToUpdate == null){
            Element nextElement = screen.findElementByName("dialogInitialMenu");
            InitialMenuController initialMenu = nextElement.getControl(InitialMenuController.class);
            initialMenu.updateControls();
            nextElement.show();
            screen.findElementByName("dialogInitialMenu").startEffect(EffectEventId.onCustom, null, "selected");
        }else{
            Element nextElement = screen.findElementByName("dialogMainMenu");
            MainMenuController mainMenu = nextElement.getControl(MainMenuController.class);
            mainMenu.updateControls();
            nextElement.show();
            screen.findElementByName("dialogMainMenu").startEffect(EffectEventId.onCustom, null, "selected");
        }
    }
    
    private boolean validateOldPassword(){
        if (!oldPassTextField.getRealText().equals(playerToUpdate.getPassword()))
            return false;
        return true;
    }
    
    private boolean validateOldNewPassword(){
//        if (oldPassTextField.getRealText().equals(passTextField.getRealText()))
//            return false;
        return true;
    }
    
    private boolean validateEduEmail(){
//        String eduEmail = emailTextField.getDisplayedText().split("\\.")[emailTextField.getDisplayedText().split("\\.").length-1];
//        if (!eduEmail.toUpperCase().equals("EDU"))
//            return false;
        return true;
    }
    
    private boolean validateControls(boolean isNewUser){
        if (nameTextField.getRealText().isEmpty() || lastNameTextField.getRealText().isEmpty() || statusDescriptionTextField.getRealText().isEmpty() ||
            degreeDescriptionTextField.getRealText().isEmpty() || countryTextField.getRealText().isEmpty() ||
            statusDropDown.getSelectedIndex() == 0 || degreeDropDown.getSelectedIndex() == 0)
            return false;
        if (!isNewUser){
            if (rePassTextField.getText().length() == 0 || 
                passTextField.getText().length() == 0 || emailTextField.getText().length() == 0)
                return false;
            if (!passTextField.getText().equals(rePassTextField.getText()))
                return false;
            if (!(passTextField.getText().length() >= 6 && passTextField.getText().length() <= 12))
                return false;
            if (!emailTextField.getText().contains("@"))
                return false;
            if (!emailTextField.getText().contains("."))
                return false;
        }else{
            if (emailTextField.getText().length() == 0)
                return false;
            if (!emailTextField.getText().contains("@"))
                return false;
            if (!emailTextField.getText().contains("."))
                return false;
        }        
        return true;
    }
    
    private E_Player createInstancePlayer(){
        E_Player player = new E_Player();
        player.setName(nameTextField.getRealText());
        player.setLastName(lastNameTextField.getRealText());
        player.setStatus(statusDropDown.getSelection().toString());
        player.setStatusDescription(statusDescriptionTextField.getRealText());
        player.setGender(((RadioButton)screen.findNiftyControl("male_NUMD", RadioButton.class)).isActivated() ? "Male" : "Female");
        player.setDegree(degreeDropDown.getSelection().toString());
        player.setDegreeDescription(degreeDescriptionTextField.getRealText());
        player.setCountry(countryTextField.getRealText());
        player.setEmail(emailTextField.getText());
        player.setPassword(newGeneratedPassword);
        player.setGameTime(0);
        return player;
    }
    
    private void generateNewPassword(){
        if (pg == null) pg = new PasswordGenerator(12);
        newGeneratedPassword = pg.nextString();
//        passTextField.setText(newPassword);
//        rePassTextField.setText(newPassword);
    }
}
