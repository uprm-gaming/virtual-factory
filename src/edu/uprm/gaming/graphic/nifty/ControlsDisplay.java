/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import edu.uprm.gaming.utils.Params;
/**
 *
 * @author David
 */
public class ControlsDisplay {
    public static final String NAME = "ControlsDisplay";
    private static CommonBuilders builders = new CommonBuilders();
    
    public static void register(final Nifty nifty) {
        new ControlDefinitionBuilder(NAME) {{
            controller(new ControlsController());
            control(new ControlBuilder(DialogPanelControlDefinition.NAME) {{
                panel(new PanelBuilder() {{
                    childLayoutVertical();
                    control(new LabelBuilder("controlsLabel", "Game Controls"){{ alignCenter(); textHAlignCenter(); width("100%"); }});
                }});
                control(new LabelBuilder("controlsDescription_FYP", Params.ControlsDescription){{
                    textHAlignCenter(); marginTop("-45px"); }});
                control(new ButtonBuilder("returnToMenu_FYP", "Return to menu") {{alignCenter(); marginTop("-45px");}} );
            }});
        }}.registerControlDefintion(nifty);
    }
}
