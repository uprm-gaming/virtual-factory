package com.virtualfactory.gui.menu.resources;

import com.virtualfactory.gui.DialogPanelControlDefinition;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import com.virtualfactory.utils.Params;

/**
 * 
 * @author Pepe
 */
public class ControlsDisplay {
    public static final String NAME = "ControlsDisplay";

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