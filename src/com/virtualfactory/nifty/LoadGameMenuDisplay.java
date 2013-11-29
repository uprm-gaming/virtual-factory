/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty;

import com.virtualfactory.nifty.screens.controllers.LoadGameMenuController;
import com.virtualfactory.nifty.DialogPanelControlDefinition;
import com.virtualfactory.nifty.CommonBuilders;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
/**
 *
 * @author David
 */
public class LoadGameMenuDisplay {
    public static final String NAME = "LoadGameMenuDisplay";
    private static CommonBuilders builders = new CommonBuilders();
    
    public static void register(final Nifty nifty) {
        new ControlDefinitionBuilder(NAME) {{
            controller(new LoadGameMenuController());
            control(new ControlBuilder(DialogPanelControlDefinition.NAME) {{
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("loadGame", "Load Game"){{
                      alignCenter();
                      width("*");
                  }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(builders.createLabel("List of Games Saved:","*"));
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new ListBoxBuilder("listBox_LGMD") {{
                        displayItems(10);
                        selectionModeSingle();
                        showVerticalScrollbar();
                        showHorizontalScrollbar();
                        width("*");                        
                  }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new ButtonBuilder("continuePlayingButton_LGMD", "Continue playing.."));
                  panel(builders.hspacer("20px"));
                  control(new ButtonBuilder("cancelButton_LGMD", "Cancel"));
                }});
            }});
        }}.registerControlDefintion(nifty);
    }
}
