/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.tools.Color;
/**
 *
 * @author David
 */
public class InitialMenuDisplay {
    public static final String NAME = "InitialMenuDisplay";
    private static CommonBuilders builders = new CommonBuilders();
    
    public static void register(final Nifty nifty) {
        new ControlDefinitionBuilder(NAME) {{
            controller(new InitialMenuController());
            control(new ControlBuilder(DialogPanelControlDefinition.NAME) {{
                panel(builders.vspacer("20%"));
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("login", "Login"){{ alignCenter(); width("*");}});
                }});
                panel(builders.vspacer());
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("email_IMD", "Email:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px")); 
                  control(new TextFieldBuilder("userTextField_IMD") {{ width("40%"); }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("pass_IMD", "Password:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
                  control(new TextFieldBuilder("passTextField_IMD") {{ width("40%"); passwordChar("*".charAt(0)); }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutVertical(); alignCenter();
                  //panel(builders.hspacer("40%")); panel(builders.hspacer("5px"));
                  text(new TextBuilder("forgorPassword_IMD"){{   
                    text("Forgotten Password?");
                    style("base-font-link"); 
                    color("#eeaa06"); 
                    textHAlignCenter(); 
                    set("action", "clickToForgotPassword()");
                 }});
                }});
                panel(builders.vspacer());
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  panel(builders.hspacer("130px"));
                  control(new ButtonBuilder("loginButton_IMD", "Login"));//{{ backgroundImage("Textures/button-red.png"); }});
                  panel(builders.hspacer("20px"));
                  control(new ButtonBuilder("newUserButton_IMD", "New User"));
                  panel(builders.hspacer("20px"));
                  control(new ButtonBuilder("quitButton_IMD", "Quit"));//{{ backgroundImage("Textures/button-green.png"); }});
                }});
                panel(builders.vspacer());
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("errorMessage_IMD"){{
                      alignCenter();
                      width("*");
                  }});
                }});
            }});
        }}.registerControlDefintion(nifty);
    }
}
