/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty;

import com.virtualfactory.nifty.screens.controllers.NewUserMenuController;
import com.virtualfactory.nifty.DialogPanelControlDefinition;
import com.virtualfactory.nifty.CommonBuilders;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioButtonBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioGroupBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
/**
 *
 * @author David
 */
public class NewUserMenuDisplay {
    public static final String NAME = "NewUserMenuDisplay";
    private static CommonBuilders builders = new CommonBuilders();
    
    public static void register(final Nifty nifty) {
        new ControlDefinitionBuilder(NAME) {{
            controller(new NewUserMenuController());
            control(new ControlBuilder(DialogPanelControlDefinition.NAME) {{
                panel(new PanelBuilder() {{
                  childLayoutHorizontal(); height("25px");
                  control(new LabelBuilder("newUser_NUMD", "New User Registration"){{ textHAlignCenter(); width("*"); }});
                }});                
                panel(builders.vspacer());
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal(); height("25px");
                  control(new LabelBuilder("nameUser", "* Name:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
                  control(new TextFieldBuilder("nameTextField_NUMD") {{ width("35%"); maxLength(100); }});
                }});
                panel(new PanelBuilder() {{
                  childLayoutHorizontal(); height("25px");
                  control(new LabelBuilder("lastNameUser", "* Last Name:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
                  control(new TextFieldBuilder("lastNameTextField_NUMD") {{ width("35%"); maxLength(100); }});
                }});
                panel(new PanelBuilder() {{
                  childLayoutHorizontal(); height("25px");
                  control(new LabelBuilder("genderUser", "* Gender:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
                  control(new RadioGroupBuilder("RadioGroup_Gender_NUMD"));
                  control(new RadioButtonBuilder("male_NUMD"){{ group("RadioGroup_Gender_NUMD"); width("20px");  height("20px"); }});  panel(builders.hspacer("5px"));
                  control(new LabelBuilder("maleValue_NUMD","Male"){{   width("60px");  height("20px"); textHAlignLeft(); }});
                  control(new RadioButtonBuilder("female_NUMD"){{ group("RadioGroup_Gender_NUMD"); width("20px");  height("20px"); }});
                  control(new LabelBuilder("femaleValue_NUMD","Female"){{   width("60px");  height("20px"); textHAlignLeft(); }});
                }});
                panel(new PanelBuilder() {{
                  childLayoutHorizontal(); height("25px");
                  control(new LabelBuilder("statusUser", "* Status:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
                  control(new DropDownBuilder("statusUserDropDown_NUMD") {{ width("35%"); }}); panel(builders.hspacer("5px"));
                  control(new TextFieldBuilder("statusDescriptionUserTextField_NUMD") {{ width("30%"); maxLength(100); }});
                }});
                panel(new PanelBuilder() {{
                  childLayoutHorizontal(); height("25px");
                  control(new LabelBuilder("degreeUser", "* Degree:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
                  control(new DropDownBuilder("degreeUserDropDown_NUMD") {{ width("35%"); }}); panel(builders.hspacer("5px"));
                  control(new TextFieldBuilder("degreeDescriptionUserTextField_NUMD") {{ width("30%"); maxLength(50); }});
                }});
                panel(new PanelBuilder() {{
                  childLayoutHorizontal(); height("25px");
                  control(new LabelBuilder("countryUser", "* Country:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
                  control(new TextFieldBuilder("countryTextField_NUMD") {{ width("35%"); maxLength(50); }});
                }});
                panel(new PanelBuilder() {{
                  childLayoutHorizontal(); height("25px");
                  control(new LabelBuilder("emailUser", "* E-mail:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
                  control(new TextFieldBuilder("emailTextField_NUMD") {{ width("35%"); }});
                }});
                panel(new PanelBuilder("newUser_Parent") {{
                    childLayoutVertical();
                }});
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("errorMessage_NUMD"){{
                      alignCenter();
                      width("*"); height("35px");
                  }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  panel(builders.hspacer("190px"));
                  control(new ButtonBuilder("createUserButton_NUMD", "Create Account"));
                  panel(builders.hspacer("20px"));
                  control(new ButtonBuilder("cancelButton_NUMD", "Cancel"));
                }});
            }});
        }}.registerControlDefintion(nifty);
    }
}
