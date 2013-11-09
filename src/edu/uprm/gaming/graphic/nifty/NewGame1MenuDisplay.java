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
                    effectParameter("color", "#eeaa06"); //Anaranjado
                    post(true);
                    inset("1px");
                    neverStopRendering(true);
                    effectParameter("timeType", "infinite");
                }});
                onHoverEffect(new HoverEffectBuilder("textColor") {{
                    effectParameter("color", "#353535");
                    post(true);
                    inset("1px");
                    neverStopRendering(true);
                    effectParameter("timeType", "infinite");
                }});
                onCustomEffect(new EffectBuilder("colorBar") {{
                    customKey("focus");
                    post(false);
                    effectParameter("color", "#20afc9");//#f00f");
                    neverStopRendering(true);
                    effectParameter("timeType", "infinite");
                }});
                onCustomEffect(new EffectBuilder("colorBar") {{
                    customKey("select");
                    post(false);
                    effectParameter("color","#20afc9");// "#f00f");
                    neverStopRendering(true);
                    effectParameter("timeType", "infinite");
                }});
                onCustomEffect(new EffectBuilder("textColor") {{
                    customKey("select");
                    post(false);
                    effectParameter("color","#353535");// "#000f");
                    neverStopRendering(true);
                    effectParameter("timeType", "infinite");
                }});
                onCustomEffect(new EffectBuilder("textColor") {{
                    customKey("focus");
                    post(false);
                    effectParameter("color","#353535");// "#000f");
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
                    panel(builders.vspacer());
                    panel(builders.vspacer());

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
                    final String bColor = "#bfbeb7";
                    final String fntColor = "#2f2f2f";
                    backgroundColor(bColor);
                    childLayoutHorizontal();
                    control(new LabelBuilder("NoGame"){{
                        text(" # ");
                        textHAlignCenter();
                        color(fntColor);
                        width("25px");
                    }});
                    control(new LabelBuilder("NameGame"){{
                        text(" Name");
                        textHAlignLeft();
                        color(fntColor);
                        width("80px");
                    }});
                    control(new LabelBuilder("LevelGame"){{
                        text("Category");
                        textHAlignCenter();
                        color(fntColor);
                        width("85px");
                    }});
                    control(new LabelBuilder("StatusGame"){{
                        text("Status");
                        textHAlignCenter();
                        color(fntColor);
                        width("120px");
                    }});
                    control(new LabelBuilder("AttempstNumber"){{
                        text("Attempts");
                        textHAlignCenter();
                        color(fntColor);
                        width("67px");
                    }});
                    control(new LabelBuilder("YourBestScore"){{
                        text("Your Best Score");
                        textHAlignCenter();
                        color(fntColor);
                        width("103px");
                    }});
                    control(new LabelBuilder("GameBestScore"){{
                        text(" Game Best Score");
                        textHAlignCenter();
                        color(fntColor);
                        width("121px");
                    }});
                }});
                panel(new PanelBuilder() {{
                    childLayoutHorizontal();
                    control(new ListBoxBuilder("listBox_NG1MD") {{
                        this.style("nifty-listbox2");
                        displayItems(4);
                        selectionModeSingle();
                        hideHorizontalScrollbar();
                        hideVerticalScrollbar();
                        width("600px");
                        control(new ControlBuilder("customListBox_NewGameStage1"){{
                            controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                        }});
                        //interactOnClick("listBox_NG1MD_ItemClicked()");
                    }});
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
                        //backgroundImage("Interface/Principal/nifty-panel-simple.png");
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
