/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.EffectBuilder;
import de.lessvoid.nifty.builder.HoverEffectBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.tools.Color;
/**
 *
 * @author David
 */
public class NewGame1MenuDisplay {
    public static final String NAME = "NewGameStage1MenuDisplay";
    private static CommonBuilders builders = new CommonBuilders();
    //private static final NewGame1MenuController newGameMenuController = new NewGame1MenuController();
    
    public static void register(final Nifty nifty) {
        new ControlDefinitionBuilder("customListBox_NewGameStage1") {{
          panel(new PanelBuilder() {{
            childLayoutHorizontal();
            width("100%");
            control(new LabelBuilder("#index"){{
                alignCenter();
                textHAlignCenter();
                width("30px");
                height("25px");                
            }});
            control(new LabelBuilder("#name"){{
                alignLeft();
                textHAlignLeft();
                width("80px");
                height("25px");                
            }});
            image(new ImageBuilder("#icon"){{
                width("25px");
                height("25px");
            }});
            control(new LabelBuilder("#category"){{
                alignLeft();
                textHAlignLeft();
                width("60px");
                height("25px");                
            }});
            image(new ImageBuilder("#iconStatus"){{
                width("25px");
                height("25px");
            }});
            image(new ImageBuilder("#iconStatus2"){{
                width("25px");
                height("25px");
            }});
            control(new LabelBuilder("#status"){{
                alignLeft();
                textHAlignLeft();
                width("70px");
                height("25px");                
            }});
            control(new LabelBuilder("#attemptsNumber"){{
                textHAlignCenter();
                width("40px");
                height("25px");                
            }});
            control(new LabelBuilder("#yourBestScore"){{
                textHAlignRight();
                width("110px");
                height("25px");                
            }});
            control(new LabelBuilder("#gameBestScore"){{
                textHAlignRight();
                width("110px");
                height("25px");                
            }});
            //visibleToMouse();
            inputMapping("de.lessvoid.nifty.input.mapping.MenuInputMapping");
            onHoverEffect(new HoverEffectBuilder("colorBar") {{
              effectParameter("color", "#006400");
              post(true);
              inset("1px");
              neverStopRendering(true);
              effectParameter("timeType", "infinite");
            }});
            onCustomEffect(new EffectBuilder("colorBar") {{
              customKey("focus");
              post(false);
              effectParameter("color", "#FFA200");//#f00f");
              neverStopRendering(true);
              effectParameter("timeType", "infinite");
            }});
            onCustomEffect(new EffectBuilder("colorBar") {{
              customKey("select");
              post(false);
              effectParameter("color","#FFA200");// "#f00f");
              neverStopRendering(true);
              effectParameter("timeType", "infinite");
            }});
            onCustomEffect(new EffectBuilder("textColor") {{
              customKey("select");
              post(false);
              effectParameter("color","#000000");// "#000f");
              neverStopRendering(true);
              effectParameter("timeType", "infinite");
            }});
            onClickEffect(new EffectBuilder("focus") {{
              effectParameter("targetElement", "#parent#parent");
            }});
            interactOnClick("listBoxItemClicked()");
          }});
        }}.registerControlDefintion(nifty);
        
        new ControlDefinitionBuilder(NAME) {{
            controller(new NewGame1MenuController());
            control(new ControlBuilder(DialogPanelControlDefinition.NAME) {{
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new LabelBuilder("newGame", "New Game"){{
                      alignCenter();
                      width("*");
                  }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(builders.createLabel("Select a game:","*"));
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new TextFieldBuilder("NoGame","  #") {{
                    width("25px");
                  }});
                  control(new TextFieldBuilder("NameGame","  Name") {{
                    width("80px");
                  }});
                  control(new TextFieldBuilder("LevelGame","  Category") {{
                    width("85px");
                  }});
                  control(new TextFieldBuilder("StatusGame","  Status") {{
                    width("120px");
                  }});
                  control(new TextFieldBuilder("AttempstNumber","# Attempts") {{
                    width("67px");
                  }});
                  control(new TextFieldBuilder("YourBestScore"," Your Best Score") {{
                    width("103px");
                  }});
                  control(new TextFieldBuilder("GameBestScore"," Game Best Score") {{
                    width("108px");
                  }});
                }});
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  control(new ListBoxBuilder("listBox_NG1MD") {{
                        displayItems(6);
                        selectionModeSingle();
                        hideHorizontalScrollbar();
                        width("600px");
                        control(new ControlBuilder("customListBox_NewGameStage1"){{
                            controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                        }});
                        //interactOnClick("listBox_NG1MD_ItemClicked()");
                  }});
//                  control(new ListBoxBuilder("listBox_NG1MD2") {{
//                        displayItems(4);
//                        selectionModeSingle();
//                        showVerticalScrollbar();
//                        showHorizontalScrollbar();
//                        width("600px");
//                  }});
                }});
                control(new LabelBuilder("gameSelectedStatus"){{
                    visibleToMouse();
                    alignCenter();
                    textHAlignCenter();
                    width("100%");
                    height("25px");                
                }});
                panel(new PanelBuilder() {{
                  childLayoutVertical();
                  control(new LabelBuilder("descriptionTextField") {{
                    backgroundImage("Interface/Principal/nifty-panel-simple.png");
                    width("100%");
                    textHAlignLeft();
                    height("100px");
                    textVAlignCenter();
                  }});
                }});
                panel(builders.vspacer());
                panel(new PanelBuilder() {{
                  childLayoutHorizontal();
                  panel(builders.hspacer("190px"));
                  control(new ButtonBuilder("cancelButton_NG1MD", "Cancel"));
                  panel(builders.hspacer("20px"));
                  control(new ButtonBuilder("nextButton_NG1MD", "Play"));
                }});
            }});
        }}.registerControlDefintion(nifty);
    }
}
