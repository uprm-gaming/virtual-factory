/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.gui.menu.resources;

import com.virtualfactory.gui.DialogPanelControlDefinition;
import com.virtualfactory.gui.CommonBuilders;
import com.virtualfactory.gui.CommonBuilders;
import com.virtualfactory.gui.DialogPanelControlDefinition;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
/**
 *
 * @author David
 */
public class OptionsMenuDisplay {
    public static final String NAME = "OptionsMenuDisplay";
    private static CommonBuilders builders = new CommonBuilders();
    
    public static void register(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();
        new ControlDefinitionBuilder(NAME) {{
            controller(new OptionsMenuController());
            control(new ControlBuilder(DialogPanelControlDefinition.NAME) {{
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("options", "Game Settings"){{ alignCenter(); width("*"); }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  panel(new PanelBuilder() {{
                      childLayoutVertical();
                      panel(new PanelBuilder(){{
                        childLayoutHorizontal();
                        image(new ImageBuilder("imageMachinesSettings"){{
                            filename("Interface/Principal/add_gray.png");
                            width("16px"); height("16px"); focusable(true);
                            interactOnClick("clickToMachines()");
                        }});
                        panel(common.hspacer("5px"));
                        control(new LabelBuilder("machineSettings_GSC","Machine"){{   height("20px");  textHAlignLeft(); }});
                      }});
                      panel(new PanelBuilder("optionsListParent_Machine"){{
                        childLayoutVertical();
                        
                      }});
                      panel(new PanelBuilder(){{
                        childLayoutHorizontal();
                        image(new ImageBuilder("imageEquipmentSettings"){{
                            filename("Interface/Principal/add_gray.png");
                            width("16px"); height("16px"); focusable(true);
                            interactOnClick("clickToEquipments()");
                        }});
                        panel(common.hspacer("5px"));
                        control(new LabelBuilder("equipmentSettings_GSC","Equipment"){{   height("20px");  textHAlignLeft(); }});
                      }});
                      panel(new PanelBuilder("optionsListParent_Equipment"){{
                        childLayoutVertical();
                        
                      }});
                      width("20%");
                  }});
                  panel(new PanelBuilder() {{
                      width("2%");
                  }});
                  panel(new PanelBuilder("optionsParent") {{
                      backgroundImage("Interface/Principal/nifty-panel-simple.png");
                      childLayoutVertical();
                      alignCenter();
                      panel(new PanelBuilder("detailedOptionsParent"){{
                        alignCenter();
                        childLayoutVertical();
                        
                      }});
                      width("78%");
                  }});
                  height("80%");
                }});
                //backgroundColor("#0008");
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutVertical();
                  alignCenter();
                  control(new LabelBuilder("messageResult_GSC",""){{   width("100%");  height("20px"); textHAlignCenter(); }});
                  control(new ButtonBuilder("cancelButton_GSC", "Cancel"){{ alignCenter(); }});
                }});
            }});
        }}.registerControlDefintion(nifty);
    }
}
