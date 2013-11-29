/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty;

import com.virtualfactory.nifty.screens.controllers.ForgotYourPasswordController;
import com.virtualfactory.nifty.screens.controllers.ForgotYourPasswordController;
import com.virtualfactory.nifty.CommonBuilders;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
/**
 *
 * @author David
 */
public class ForgotYourPasswordDisplay {
    public static final String NAME = "ForgotYourPasswordDisplay";
    private static CommonBuilders builders = new CommonBuilders();
    
    public static void register(final Nifty nifty) {
        new ControlDefinitionBuilder(NAME) {{
            controller(new ForgotYourPasswordController());
            control(new ControlBuilder(DialogPanelControlDefinition.NAME) {{
                panel(builders.vspacer("20%"));
                panel(new PanelBuilder() {{
                  childLayoutVertical();
                  control(new LabelBuilder("forgotPasswordLabel", "Forgot your password"){{ alignCenter(); textHAlignCenter(); width("100%"); }});
                }});
                panel(builders.vspacer());
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutVertical();
                  control(new LabelBuilder("messageLabel_FYP", "Please type your email to recover your password"){{ textHAlignCenter(); width("100%"); }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("email_FYP", "Email:"){{ textHAlignRight(); width("30%"); }}); panel(builders.hspacer("5px"));
                  control(new TextFieldBuilder("passRecovered_FYP") {{ width("40%"); }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("errorMessage_FYP"){{ alignCenter(); width("*"); }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  panel(builders.hspacer("200px"));
                  control(new ButtonBuilder("sendEmail_FYP", "Send Email"));
                  panel(builders.hspacer("20px"));
                  control(new ButtonBuilder("cancel_FYP", "Cancel"));
                }});
            }});
        }}.registerControlDefintion(nifty);
    }
}
