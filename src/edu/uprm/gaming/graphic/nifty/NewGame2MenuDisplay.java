/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.EffectBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioButtonBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioGroupBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
/**
 *
 * @author David
 */
public class NewGame2MenuDisplay {
    public static final String NAME = "NewGameStage2MenuDisplay";
    private static CommonBuilders builders = new CommonBuilders();
    
    public static void register(final Nifty nifty) {
        new ControlDefinitionBuilder("customListBox_NewGameStage2") {{
          panel(new PanelBuilder() {{
            childLayoutHorizontal();
            width("100%");
            image(new ImageBuilder("#icon"){{
                width("25px");
                height("25px");
            }});
            control(new LabelBuilder("#item"){{
                visibleToMouse();
                alignLeft();
                textHAlignLeft();
                width("125px");
                height("25px");
            }});
            control(new TextFieldBuilder("#value") {{
                width("200px");
                height("22px");
            }});
          }});
        }}.registerControlDefintion(nifty);
        
        new ControlDefinitionBuilder(NAME) {{
            controller(new NewGame2MenuController());
            control(new ControlBuilder(DialogPanelControlDefinition.NAME) {{
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("newGame2", "New Game - Step 2"){{
                      alignCenter();
                      width("*");
                  }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                    childLayoutHorizontal();
                    control(builders.createLabel("Select a mission:","*"));
                }});
                panel(builders.vspacer());
                control(new RadioGroupBuilder("RadioGroup-1"));
                panel(new PanelBuilder() {{
                    childLayoutVertical();
                    backgroundColor("#8001");
                    paddingLeft("7px");
                    paddingRight("7px");
                    paddingTop("4px");
                    paddingBottom("4px");
                    width("600px");
                    onActiveEffect(new EffectBuilder("border") {{
                      effectParameter("color", "#0008");
                    }});
                    panel(new PanelBuilder() {{
                      childLayoutHorizontal();
                      control(new RadioButtonBuilder("option-1") {{
                        group("RadioGroup-1");
                      }});
                      control(builders.createLabel("Produce parts on time", "300px"));
                    }});
                    panel(new PanelBuilder() {{
                      childLayoutHorizontal();
                      control(new RadioButtonBuilder("option-2") {{
                        group("RadioGroup-1");
                      }});
                      control(builders.createLabel("Get profitability on time", "300px"));
                    }});
                    panel(new PanelBuilder() {{
                      childLayoutHorizontal();
                      control(new RadioButtonBuilder("option-3") {{
                        group("RadioGroup-1");
                      }});
                      control(builders.createLabel("Produce parts & get profitability on time", "300px"));
                    }});
                  }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                    childLayoutHorizontal();
                    control(builders.createLabel("Update objectives:","*"));
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new ListBoxBuilder("listBox_NG2MD") {{
                        displayItems(5);
                        selectionModeSingle();
                        showVerticalScrollbar();
                        showHorizontalScrollbar();
                        width("600px");
                        control(new ControlBuilder("customListBox_NewGameStage2"){{
                            controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                        }});
                  }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new ButtonBuilder("backButton_NG2MD", "Back"));
                  panel(builders.hspacer("20px"));
                  control(new ButtonBuilder("cancelButton_NG2MD", "Cancel"));
                  panel(builders.hspacer("20px"));
                  control(new ButtonBuilder("playButton_NG2MD", "Play"));
                }});
            }});
        }}.registerControlDefintion(nifty);
    }
}
