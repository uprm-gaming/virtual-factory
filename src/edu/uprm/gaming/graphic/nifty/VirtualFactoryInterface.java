package edu.uprm.gaming.graphic.nifty;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.EffectBuilder;
import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.builder.HoverEffectBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.StyleBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioButtonBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioGroupBuilder;
import de.lessvoid.nifty.controls.slider.builder.SliderBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.controls.window.builder.WindowBuilder;
import de.lessvoid.nifty.screen.DefaultScreenController;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.Color;
import edu.uprm.gaming.graphic.nifty.controls.ActivityControl;
import edu.uprm.gaming.graphic.nifty.controls.AssignOperatorControl;
import edu.uprm.gaming.graphic.nifty.controls.CharactersControl;
import edu.uprm.gaming.graphic.nifty.controls.DashboardControl;
import edu.uprm.gaming.graphic.nifty.controls.FlowChartControl;
import edu.uprm.gaming.graphic.nifty.controls.GameLogControl;
import edu.uprm.gaming.graphic.nifty.controls.GameSetupControl;
import edu.uprm.gaming.graphic.nifty.controls.MachineControl;
import edu.uprm.gaming.graphic.nifty.controls.MainMultipleControls;
import edu.uprm.gaming.graphic.nifty.controls.OperatorControl;
import edu.uprm.gaming.graphic.nifty.controls.OrderControl;
import edu.uprm.gaming.graphic.nifty.controls.OverallControl;
import edu.uprm.gaming.graphic.nifty.controls.PartControl;
import edu.uprm.gaming.graphic.nifty.controls.PriorityControl;
import edu.uprm.gaming.graphic.nifty.controls.StorageCostControl;
import edu.uprm.gaming.graphic.nifty.controls.StorageStationControl;
import edu.uprm.gaming.graphic.nifty.controls.SupplierControl;
import edu.uprm.gaming.graphic.nifty.controls.UnitLoadControl;
import edu.uprm.gaming.utils.Params;

final public class VirtualFactoryInterface {
    private static String panelBackgroundImage = "Interface/panelBack3.png";
    private static String buttonBackgroundImage = "Textures/button-green.png";
    private static String popupBackgroundImage = "Interface/panelpopup2.png";//"Textures/background_gray.png";
    
    private static ElementBuilder.Align operator_label = ElementBuilder.Align.Right;
    private static ElementBuilder.Align operator_value = ElementBuilder.Align.Left;
    private static ElementBuilder.Align machine_label = ElementBuilder.Align.Right;
    private static ElementBuilder.Align machine_value = ElementBuilder.Align.Left;
    private static ElementBuilder.Align station_label = ElementBuilder.Align.Right;
    private static ElementBuilder.Align station_value = ElementBuilder.Align.Left;
    private static ElementBuilder.Align part_label = ElementBuilder.Align.Right;
    private static ElementBuilder.Align part_value = ElementBuilder.Align.Left;
    private static ElementBuilder.Align activity_label = ElementBuilder.Align.Right;
    private static ElementBuilder.Align activity_value = ElementBuilder.Align.Left;
    private static ElementBuilder.Align overall_label = ElementBuilder.Align.Left;
    private static ElementBuilder.Align overall_value = ElementBuilder.Align.Right;
    private static ElementBuilder.Align supplier_label = ElementBuilder.Align.Right;
    private static ElementBuilder.Align supplier_value = ElementBuilder.Align.Left;
    private static ElementBuilder.Align order_label = ElementBuilder.Align.Left;
    private static ElementBuilder.Align order_value = ElementBuilder.Align.Left;
    
    private static int minDashboardPositionX = 1260;
    private static int minDashboardPositionY = 30;
    private static int dashboardWidth = 535;
    private static int dashboardHeight = 430;
    
    private VirtualFactoryInterface() {}
    
    public static Screen createIntroScreen(final Nifty nifty) {
        Screen screen = new ScreenBuilder("start") {
            {
                controller(new DefaultScreenController() {
                    @Override
                    public void onStartScreen() {
                        nifty.gotoScreen("initialMenu");
                    }
                });
                layer(new LayerBuilder("layer") {
                    {
                        childLayoutCenter();
                        onStartScreenEffect(new EffectBuilder("fade") {
                            {
                                length(3000);
                                effectParameter("start", "#0");
                                effectParameter("end", "#f");
                            }
                        });
                        onStartScreenEffect(new EffectBuilder("playSound") {
                            {
                                startDelay(1400);
                                effectParameter("sound", "intro");
                            }
                        });
                        onActiveEffect(new EffectBuilder("gradient") {
                            {
                                effectValue("offset", "0%", "color", "#c6c6c6");
                                effectValue("offset", "85%", "color", "#c6c6c6");
                                effectValue("offset", "100%", "color", "#c6c6c6");
                            }
                        });
                        panel(new PanelBuilder() {
                            {
                                alignCenter();
                                valignCenter();
                                childLayoutHorizontal();      
                                panel(new PanelBuilder() {
                                    {
                                        alignCenter();
                                        valignCenter();
                                        childLayoutOverlay();
                                        width("800px");
                                        height("200px");
                                        onStartScreenEffect(new EffectBuilder("shake") {
                                            {
                                                length(250);
                                                startDelay(1300);
                                                inherit();
                                                effectParameter("global", "false");
                                                effectParameter("distance", "10.");
                                            }
                                        });
                                        onStartScreenEffect(new EffectBuilder("imageSize") {
                                            {
                                                length(600);
                                                startDelay(4500);
                                                effectParameter("startSize", "1.0");
                                                effectParameter("endSize", "2.0");
                                                inherit();
                                                neverStopRendering(true);
                                            }
                                        });
                                        onStartScreenEffect(new EffectBuilder("fade") {
                                            {
                                                length(600);
                                                startDelay(4500);
                                                effectParameter("start", "#f");
                                                effectParameter("end", "#0");
                                                inherit();
                                                neverStopRendering(true);
                                            }
                                        });
                                        image(new ImageBuilder() {
                                            {
                                                filename("Interface/virtual2.png");
                                                onStartScreenEffect(new EffectBuilder("move") {
                                                    {
                                                        length(1000);
                                                        startDelay(300);
                                                        timeType("exp");
                                                        effectParameter("factor", "1.f");
                                                        effectParameter("mode", "in");
                                                        effectParameter("direction", "left");
                                                    }
                                                });
                                            }
                                        });
                                        image(new ImageBuilder() {
                                            {
                                                filename("Interface/factory2.png");
                                                onStartScreenEffect(new EffectBuilder("move") {
                                                    {
                                                        length(1000);
                                                        startDelay(300);
                                                        timeType("exp");
                                                        effectParameter("factor", "1.f");
                                                        effectParameter("mode", "in");
                                                        effectParameter("direction", "right");
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
                layer(new LayerBuilder() {
                    {
                        backgroundColor("#f");
                        onStartScreenEffect(new EffectBuilder("fade") {
                            {
                                length(1000);
                                startDelay(4500);
                                effectParameter("start", "#0");
                                effectParameter("end", "#f");
                            }
                        });
                    }
                });
            }
        }.build(nifty);
        return screen;
    }
    
    public static Screen createMenuScreen(final Nifty nifty) {
        Screen screen = new ScreenBuilder("initialMenu") {
            {
                controller(new MenuScreenController());
                inputMapping("de.lessvoid.nifty.input.mapping.DefaultInputMapping");
                layer(new LayerBuilder("layer") {
                    {
                        backgroundImage("Interface/intro3.png");
                        childLayoutVertical();
                        panel(new PanelBuilder("dialogParent") {
                            {
                                childLayoutOverlay();
                                width("100%");
                                height("100%");
                                alignCenter();
                                valignCenter();
                                control(new ControlBuilder("dialogInitialMenu", InitialMenuDisplay.NAME));
                                control(new ControlBuilder("dialogMainMenu", MainMenuDisplay.NAME));
                                control(new ControlBuilder("dialogForgotYourPasswordMenu", ForgotYourPasswordDisplay.NAME));
                                control(new ControlBuilder("dialogNewUserMenu", NewUserMenuDisplay.NAME));
                                control(new ControlBuilder("dialogNewGameStage1Menu", NewGame1MenuDisplay.NAME));
                                control(new ControlBuilder("dialogLoadGameMenu", LoadGameMenuDisplay.NAME));
                                control(new ControlBuilder("dialogOptionsMenu", OptionsMenuDisplay.NAME));
                                control(new ControlBuilder("dialogControlsMenu", ControlsDisplay.NAME));
                            }
                        });
                    }
                });
            }
        }.build(nifty);
        return screen;
    }
    
    public static Screen createLayerScreen(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();
        new ControlDefinitionBuilder("customListBox_Line") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        image(new ImageBuilder("#icon") {
                            {
                                width("25px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#message") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                height("30px");
                                width("*");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);

        new ControlDefinitionBuilder("customListBoxOperator_Line") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        image(new ImageBuilder("#icon") {
                            {
                                width("25px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#message") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                height("100px");
                                width("*");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);

        new ControlDefinitionBuilder("customListBox_AssemblyDetail") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#part") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("140px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#value") {
                            {
                                visibleToMouse();
                                alignCenter();
                                textHAlignCenter();
                                width("60px");
                                height("25px");
                            }
                        });
                        visibleToMouse();
                    }
                });
            }
        }.registerControlDefintion(nifty);

        new ControlDefinitionBuilder("customListBox_Orders") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#index") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("20px");
                                height("25px");
                            }
                        });
                        image(new ImageBuilder("#icon") {
                            {
                                width("25px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#part") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("120px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#quantity") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("30px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#timeOver") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignRight();
                                width("80px");
                                height("25px");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);

        new ControlDefinitionBuilder("customListBox_Slots") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");

                        control(new LabelBuilder("#part") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("75px");
                                height("25px");
                            }
                        });
                        image(new ImageBuilder("#icon") {
                            {
                                width("22px");
                                height("22px");
                            }
                        });
                        control(new LabelBuilder("#quantity") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignCenter();
                                width("60px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#partsNumber") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignCenter();
                                width("60px");
                                height("25px");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
        new ControlDefinitionBuilder("customListBox_Buckets") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#index") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignCenter();
                                width("20px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#unit") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("60px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#sizeCapacity") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("50px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#partAssigned") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignLeft();
                                width("80px");
                                height("25px");
                            }
                        });
                        control(new LabelBuilder("#unitsToArriveRemove") {
                            {
                                visibleToMouse();
                                alignLeft();
                                textHAlignCenter();
                                width("50px");
                                height("25px");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
        new ControlDefinitionBuilder("customListBox_GameLog") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#time") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("25px");
                                width("130px");
                            }
                        });
                        image(new ImageBuilder("#icon") {
                            {
                                width("20px");
                                height("20px");
                            }
                        });
                        control(new LabelBuilder("#message") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("25px");
                                width("480");
                                
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
        new ControlDefinitionBuilder("customListBox_StationsList_DB") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#station") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("100px");
                            }
                        });
                        control(new LabelBuilder("#part") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("65px");
                            }
                        });
                        control(new LabelBuilder("#quantity") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("60");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
        new ControlDefinitionBuilder("customListBox_TransportList_DB") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#fromTo") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("180px");
                            }
                        });
                        control(new LabelBuilder("#part") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("60px");
                            }
                        });
                        control(new LabelBuilder("#required") {
                            {
                                visibleToMouse();
                                textHAlignLeft();
                                height("20px");
                                width("40");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
        new ControlDefinitionBuilder("customListBox_AssingOperators") {
            {
                panel(new PanelBuilder() {
                    {
                        childLayoutHorizontal();
                        width("100%");
                        control(new LabelBuilder("#id") {
                            {
                                alignLeft();
                                textHAlignLeft();
                                width("10px");
                                height("25px");
                                wrap(true);
                            }
                        });
                        control(new LabelBuilder("#type") {
                            {
                                alignLeft();
                                textHAlignLeft();
                                width("80px");
                                height("25px");
                                wrap(true);
                            }
                        });
                        control(new LabelBuilder("#name") {
                            {
                                alignLeft();
                                textHAlignLeft();
                                width("220px");
                                height("25px");
                                wrap(true);
                            }
                        });
                        inputMapping("de.lessvoid.nifty.input.mapping.MenuInputMapping");
                        onHoverEffect(new HoverEffectBuilder("colorBar") {
                            {
                                effectParameter("color", "#006400");//verde
                                post(true);
                                inset("1px");
                                neverStopRendering(true);
                                effectParameter("timeType", "infinite");
                            }
                        });
                        onCustomEffect(new EffectBuilder("colorBar") {
                            {
                                customKey("focus");
                                post(false);
                                effectParameter("color", "#FFA200");//amarillo
                                neverStopRendering(true);
                                effectParameter("timeType", "infinite");
                            }
                        });
                        onCustomEffect(new EffectBuilder("colorBar") {
                            {
                                customKey("select");
                                post(false);
                                effectParameter("color", "#FFA200");// "#f00f");
                                neverStopRendering(true);
                                effectParameter("timeType", "infinite");
                            }
                        });
                        onCustomEffect(new EffectBuilder("textColor") {
                            {
                                customKey("select");
                                post(false);
                                effectParameter("color", "#000000");// "#000f");
                                neverStopRendering(true);
                                effectParameter("timeType", "infinite");
                            }
                        });
                        onClickEffect(new EffectBuilder("focus") {
                            {
                                effectParameter("targetElement", "#parent#parent");
                            }
                        });
                        interactOnClick("listBoxItemClicked_AO()");
                    }
                });
            }
        }.registerControlDefintion(nifty);

        Screen screen = new ScreenBuilder("layerScreen") {
            {
                controller(new GeneralScreenController());
                inputMapping("de.lessvoid.nifty.input.mapping.DefaultInputMapping");
                layer(new LayerBuilder("layer") {
                    {
                        
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                
                                width("100%");
                                alignCenter();
                                childLayoutHorizontal();
                                backgroundImage("Interface/panelBack3.png");
                                control(new LabelBuilder("gameNamePrincipal", "  Game: ...") {
                                    {
                                        width("90px");
                                        textHAlignLeft();
                                    }
                                });
                                control(new LabelBuilder("gamePrincipalStatus", "_") {
                                    {
                                        width("60px");
                                        textHAlignLeft();
                                    }
                                });
                                image(new ImageBuilder("imagePlay") {
                                    {
                                        filename("Interface/button_play_red.png");
                                        width("25px");
                                        height("25px");
                                        focusable(true);
                                        interactOnClick("playGameValidated()");
                                    }
                                });
                                panel(common.hspacer("20px"));
                                control(new SliderBuilder("sliderTime", false) {
                                    {
                                        width("125px");
                                    }
                                });
                                panel(common.hspacer("3px"));
                                control(new LabelBuilder("labelSliderTime") {
                                    {
                                        width("30px");
                                    }
                                });
                                panel(common.hspacer("25px"));
                                image(new ImageBuilder("imageCurrentTime") {
                                    {
                                        filename("Interface/clock-blue.png");
                                        width("25px");
                                        height("25px");
                                    }
                                });
                                control(new LabelBuilder("labelCurrentGameTime") {
                                    {
                                        width("130px");
                                        textHAlignLeft();
                                    }
                                });
                                panel(common.hspacer("10px"));
                                image(new ImageBuilder("imageDueDate") {
                                    {
                                        filename("Interface/clock-red.png");
                                        width("25px");
                                        height("25px");
                                    }
                                });
                                control(new LabelBuilder("labelDueDateNextOrder") {
                                    {
                                        width("130px");
                                        textHAlignLeft();
                                    }
                                });
                                panel(common.hspacer("10px"));
                                image(new ImageBuilder("imagePurchaseDueDate") {
                                    {
                                        filename("Interface/clock-green.png");
                                        width("25px");
                                        height("25px");
                                    }
                                });
                                control(new LabelBuilder("labelPurchaseDueDate") {
                                    {
                                        width("120px");
                                        textHAlignLeft();
                                    }
                                });
                                panel(common.hspacer("85px"));

                                image(new ImageBuilder("imageSpeaker") {
                                    {
                                        filename("Interface/speaker_blue2.png");
                                        width("25px");
                                        height("25px");
                                        focusable(true);
                                        interactOnClick("manageGameVolume()");
                                    }
                                });
                                panel(common.hspacer("10px"));
                                control(new ButtonBuilder("buttonStaticOptionFlowChart", "Flow Chart") {
                                    {
                                        width("100px");
                                    }
                                });
                                panel(common.hspacer("3px"));
                                control(new ButtonBuilder("buttonStaticOptionGameSetup", "Setup") {
                                    {
                                        width("100px");
                                    }
                                });
                                panel(common.hspacer("3px"));
                                control(new ButtonBuilder("buttonStaticOptionReturnToMenu", "Return to Menu") {
                                    {
                                        width("100px");
                                    }
                                });
                            }
                        });
                        panel(new PanelBuilder() {
                            {
                                childLayoutHorizontal();
                                height("*");
                                panel(new PanelBuilder() {
                                    {
                                        childLayoutVertical();
                                        width("10%");
                                        panel(common.vspacer("2px"));

                                        panel(new PanelBuilder() {
                                            {
                                                childLayoutHorizontal();
                                                image(new ImageBuilder("imageControls") {
                                                    {
                                                        filename("Interface/Principal/controls2.png");
                                                        width("25px");
                                                        height("25px");
                                                        focusable(true);
                                                    }
                                                });
                                                control(new ButtonBuilder("buttonOptionControls", "Controls"));
                                            }
                                        });
                                        panel(new PanelBuilder() {
                                            {
                                                childLayoutHorizontal();
                                                image(new ImageBuilder("imageActivities") {
                                                    {
                                                        filename("Interface/Principal/activity.png");
                                                        width("25px");
                                                        height("25px");
                                                        focusable(true);
                                                    }
                                                });
                                                control(new ButtonBuilder("buttonOptionActivities", "Activities"));
                                            }
                                        });
                                        panel(new PanelBuilder() {
                                            {
                                                childLayoutHorizontal();
                                                image(new ImageBuilder("imageUtilities") {
                                                    {
                                                        filename("Interface/Principal/utilities.png");
                                                        width("25px");
                                                        height("25px");
                                                        focusable(true);
                                                    }
                                                });
                                                control(new ButtonBuilder("buttonOptionUtilities", "Utilities"));
                                            }
                                        });
                                    }
                                });
                                panel(new PanelBuilder("dynamicButtons") {
                                    {
                                        childLayoutVertical();
                                        width("10%");
                                        control(new ButtonBuilder("dynBut0", "test0") {
                                            {
                                                width("98%");
                                                textHAlignCenter();
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut1", "test1") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut2", "test2") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut3", "test3") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut4", "test4") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut5", "test5") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut6", "test6") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut7", "test7") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut8", "test8") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut9", "test9") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut10", "test10") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut11", "test11") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut12", "test12") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut13", "test13") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut14", "test14") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut15", "test15") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut16", "test16") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut17", "test17") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut18", "test18") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut19", "test19") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut20", "test20") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut21", "test21") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut22", "test22") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut23", "test23") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynBut24", "test24") {
                                            {
                                                width("98%");
                                            }
                                        });
                                    }
                                });
                                panel(new PanelBuilder("dynamicSubLevelButtons") {
                                    {
                                        childLayoutVertical();
                                        width("10%");
                                        control(new ButtonBuilder("dynSubLevelBut0", "test0") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut1", "test1") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut2", "test2") {

                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut3", "test3") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut4", "test4") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut5", "test5") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut6", "test6") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut7", "test7") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut8", "test8") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut9", "test9") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut10", "test10") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut11", "test11") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut12", "test12") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut13", "test13") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut14", "test14") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut15", "test15") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut16", "test16") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut17", "test17") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut18", "test18") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut19", "test19") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut20", "test20") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut21", "test21") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut22", "test22") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut23", "test23") {
                                            {
                                                width("98%");
                                            }
                                        });
                                        panel(common.vspacer("2px"));
                                        control(new ButtonBuilder("dynSubLevelBut24", "test24") {
                                            {
                                                width("98%");
                                            }
                                        });
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                       // style(null);
                                        childLayoutAbsolute();
                                        width("70%");
                                        height("*");

                                        panel(new PanelBuilder("multipleControls_SMC") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new MainMultipleControls());
                                                panel(new PanelBuilder("parent_SMC") {
                                                    {
                                                        childLayoutVertical();
//                                backgroundImage(panelBackgroundImage);
                                                        //add elements....

                                                        //end elements...
                                                        x("0px");
                                                        y("25px");
                                                        width("240");
                                                        height("450");
                                                    }
                                                });
                                            }
                                        });

                                        panel(new PanelBuilder("manageVolume_MGV") {
                                            {
                                                
                                                childLayoutAbsolute();
                                                panel(new PanelBuilder("parent_MGV") {
                                                    {
                                                        childLayoutVertical();
//                                backgroundImage(panelBackgroundImage);
                                                        //add elements....

                                                        //end elements...
                                                        x("938px");
                                                        y("25px");
                                                        width("150");
                                                        height("150");
                                                    }
                                                });
                                            }
                                        });

                                        panel(new PanelBuilder() {
                                            {
                                                childLayoutAbsolute();
                                                image(new ImageBuilder("imageTimeFactor") {
                                                    {
                                                        filename("Interface/Principal/square_minus.png");
                                                        width("24px");
                                                        height("24px");
                                                        x("195px");
                                                    }
                                                });
                                                image(new ImageBuilder("imageTimeFactor") {
                                                    {
                                                        filename("Interface/Principal/square_plus.png");
                                                        width("24px");
                                                        height("24px");
                                                        x("297px");
                                                    }
                                                });
                                            }
                                        });
                                        //******************************************************************
                                        panel(new PanelBuilder("winOC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new OperatorControl());
                                                control(new WindowBuilder("winOperatorControl", "Operator") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("150px");
                                                                        control(new LabelBuilder("text_WOC", "Operators list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("operatorsList_WOC") {
                                                                            {
                                                                                displayItems(12);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("id_WOC", "Operator ID:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("idValue_WOC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("name_WOC", "Name:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("nameValue_WOC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new RadioGroupBuilder("RadioGroup_Activate_WOC"));
                                                                                control(new LabelBuilder("activateValue_WOC", "Hire") {
                                                                                    {
                                                                                        width("60px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                control(new RadioButtonBuilder("activate_WOC_True") {
                                                                                    {
                                                                                        group("RadioGroup_Activate_WOC");
                                                                                        width("40px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("deactivateValue_WOC", "Fire") {
                                                                                    {
                                                                                        width("60px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                control(new RadioButtonBuilder("activate_WOC_False") {
                                                                                    {
                                                                                        group("RadioGroup_Activate_WOC");
                                                                                        width("40px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("hireFire_WOC", "Hire/Fire Cost:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("hireFireValue_WOC", "_") {
                                                                                    {
                                                                                        width("130px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("status_WOC", "Status:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("statusValue_WOC", "_") {
                                                                                    {
                                                                                        width("100px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                height("25px");
                                                                                control(new LabelBuilder("category_WOC", "Category:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new DropDownBuilder("categoryValue_WOC") {
                                                                                    {
                                                                                        width("130px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                        visibleToMouse(true);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("location_WOC", "Current Location:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("locationValue_WOC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                height("22px");
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("speed_WOC", "Speed:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("speedValue_WOC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                height("22px");
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("salaryPerHourCarrier_WOC", "Material Handler Salary/Hour:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("salaryPerHourCarrierValue_WOC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                height("22px");
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("salaryPerHourAssembler_WOC", "Operator Salary/Hour:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("salaryPerHourAssemblerValue_WOC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                height("22px");
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("salaryPerHourVersatile_WOC", "Versatile Salary/Hour:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("salaryPerHourVersatileValue_WOC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                height("22px");
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("salaryPerHour_WOC", "Current Salary/Hour:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("salaryPerHourValue_WOC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("earnedMoney_WOC", "Earned Money:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("earnedMoneyValue_WOC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("percentageUsage_WOC", "% Usage:") {
                                                                                    {
                                                                                        width("170px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("percentageUsageValue_WOC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(operator_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
//                     
                                                                        panel(common.vspacer("5px"));
                                                                        control(new LabelBuilder("messageResult_WOC", "") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                        control(new ButtonBuilder("operatorUpdate", "Update") {
                                                                            {
                                                                                width("95%");
                                                                                alignCenter();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        visible(false);
                                                        x("312px");
                                                        y("28px");
                                                        width("490px");
                                                        height("385px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winMC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new MachineControl());
                                                control(new WindowBuilder("winMachineControl", "Machine") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("120px");
                                                                        control(new LabelBuilder("text_WMC", "Machines list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("machinesList_WMC") {
                                                                            {
                                                                                displayItems(12);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("id_WMC", "Machine ID:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("idValue_WMC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("description_WMC", "Description:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("descriptionValue_WMC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new RadioGroupBuilder("RadioGroup_Activate_WMC"));
                                                                                        panel(common.hspacer("20px"));
                                                                                        control(new LabelBuilder("activateValue_WMC", "Buy") {
                                                                                            {
                                                                                                width("60px");
                                                                                                height("20px");
                                                                                                textHAlign(operator_label);
                                                                                            }
                                                                                        });
                                                                                        control(new RadioButtonBuilder("activate_WMC_True") {
                                                                                            {
                                                                                                group("RadioGroup_Activate_WMC");
                                                                                                width("40px");
                                                                                                height("20px");
                                                                                                textHAlign(operator_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("deactivateValue_WMC", "Sell") {
                                                                                            {
                                                                                                width("60px");
                                                                                                height("20px");
                                                                                                textHAlign(operator_label);
                                                                                            }
                                                                                        });
                                                                                        control(new RadioButtonBuilder("activate_WMC_False") {
                                                                                            {
                                                                                                group("RadioGroup_Activate_WMC");
                                                                                                width("40px");
                                                                                                height("20px");
                                                                                                textHAlign(operator_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("buySell_WMC", "Purchase/Sale Price:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("buySellValue_WMC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("percentageDepreciation_WMC", "% Depreciation:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("percentageDepreciationValue_WMC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("percentageDepreciationAccumulated_WMC", "% Accumulated Depreciation:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("percentageDepreciationAccumulatedValue_WMC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("status_WMC", "Status:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("statusValue_WMC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("currentLocation_WMC", "Current Location:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("currentLocationValue_WMC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                //                                        panel(new PanelBuilder(){{
                                                                                //                                            childLayoutHorizontal();    height("22px");
                                                                                //                                            control(new LabelBuilder("priceForPurchase_WMC","Price For Purchase:"){{   width("170px");  height("20px"); textHAlign(machine_label); }});  panel(common.hspacer("5px"));
                                                                                //                                            control(new LabelBuilder("priceForPurchaseValue_WMC"){{   width("70px");  height("20px"); textHAlign(machine_value); }});
                                                                                //                                        }});
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("costPerHour_WMC", "Cost Per Hour:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("costPerHourValue_WMC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("totalCost_WMC", "Total Cost:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("totalCostValue_WMC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(common.vspacer("10px"));
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("percentageAvailability_WMC", "% Availability:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("percentageAvailabilityValue_WMC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("percentageUsage_WMC", "% Usage:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("percentageUsageValue_WMC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder("partsProduced_Parent") {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        //ADD DETAILS

                                                                                        //END DETAILS
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("activityAssigned_WMC", "Activity Assigned:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("activityAssignedValue_WMC", "_") {
                                                                                            {
                                                                                                width("140px");
                                                                                                height("40px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("percentageProbabilityFailure_WMC", "% Probability of Failure:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("percentageProbabilityFailureValue_WMC", "_") {
                                                                                            {
                                                                                                width("100px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("performPreventiveMaintenance_WMC", "Preventive Maintenance:") {
                                                                                            {
                                                                                                width("170px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("performPreventiveMaintenanceValue_WMC", "_") {
                                                                                            {
                                                                                                width("60px");
                                                                                                height("20px");
                                                                                                textHAlign(machine_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new ButtonBuilder("performPreventiveMaintenanceButton", "Perform") {
                                                                                            {
                                                                                                width("60px");
                                                                                                alignCenter();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                        control(new LabelBuilder("messageResult_WMC", "") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        control(new ButtonBuilder("machineUpdate", "Update") {
                                                                            {
                                                                                width("100%");
                                                                                alignCenter();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        visible(false);
                                                        x("163px");
                                                        y("28px");
                                                        width("450px");
                                                        height("415px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winSSC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new StorageStationControl());
                                                control(new WindowBuilder("winStorageStationControl", "Station") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        valignTop();
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("90px");
                                                                        control(new LabelBuilder("text_WSSC", "Stations list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("stationsList_WSSC") {
                                                                            {
                                                                                displayItems(8);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder("stationParent_WSSC") {
                                                                    {
                                                                        childLayoutVertical();
                                                                        visibleToMouse(true);
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("400px");
                                                        height("390px");
                                                    }
                                                });
                                            }
                                        });

                                        panel(new PanelBuilder("winPC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new PartControl());
                                                control(new WindowBuilder("winPartControl", "Part") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("90px");
                                                                        control(new LabelBuilder("text_WPC", "Parts list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("partsList_WPC") {
                                                                            {
                                                                                displayItems(10);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("id_WPC", "Part ID:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("idValue_WPC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("name_WPC", "Name:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("nameValue_WPC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("unit_WPC", "Unit:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("unitValue_WPC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("currentStock_WPC", "Current Stock:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("currentStockValue_WPC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("priceForSale_WPC", "Price For Sale:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("priceForSaleValue_WPC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new LabelBuilder("outputQuantity_WPC", "Output Quantity:") {
                                                                                    {
                                                                                        width("120px");
                                                                                        height("20px");
                                                                                        textHAlign(part_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("outputQuantityValue_WPC") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(part_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("10px"));
                                                                        control(new LabelBuilder("assemblySection_WPC", "Assembly Section") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlign(ElementBuilder.Align.Center);
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                control(new TextFieldBuilder("assemblyPartRequired", "  Part required") {
                                                                                    {
                                                                                        width("140px");
                                                                                    }
                                                                                });
                                                                                control(new TextFieldBuilder("assemblyQuantity", "  Quantity") {
                                                                                    {
                                                                                        width("84px");
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("assemblySectionValue_WPC") {
                                                                            {
                                                                                displayItems(3);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("*");
                                                                                control(new ControlBuilder("customListBox_AssemblyDetail") {
                                                                                    {
                                                                                        controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("340px");
                                                        height("310px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winAC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new ActivityControl());
                                                control(new WindowBuilder("winActivityControl", "Activity") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("110px");
                                                                        control(new LabelBuilder("text_WAC", "Activities list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("activitiesList_WAC") {
                                                                            {
                                                                                displayItems(10);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(new PanelBuilder("winAC_Parent") {
                                                                            {
                                                                                childLayoutVertical();
                                                                                visibleToMouse(true);
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("id_WAC", "Activity ID:") {
                                                                                            {
                                                                                                width("110px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("idValue_WAC", "_") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("description_WAC", "Description:") {
                                                                                            {
                                                                                                width("110px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("descriptionValue_WAC", "_") {
                                                                                            {
                                                                                                width("130px");
                                                                                                height("37px");
                                                                                                textHAlign(activity_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("typeActivity_WAC", "Type Activity:") {
                                                                                            {
                                                                                                width("110px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("typeActivityValue_WAC", "_") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("part_WAC", "Part:") {
                                                                                            {
                                                                                                width("110px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("partValue_WAC", "_") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(activity_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(common.vspacer("10px"));
                                                                                //ADD TYPE_ACTIVITY

                                                                                //END TYPE_ACTIVITY
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                        control(new LabelBuilder("messageResult_WAC", "") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("10px"));
                                                                        control(new ButtonBuilder("activityRefresh", "Refresh") {
                                                                            {
                                                                                width("100%");
                                                                                alignCenter();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("490px");
                                                        y("28px");
                                                        visible(false);
                                                        width("380px");
                                                        height("455px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winOvC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new OverallControl());
                                                control(new LabelBuilder("OverallLabel", "Overall") {
                                                    {
//                                                        this.backgroundColor(de.lessvoid.nifty.tools.Color.BLACK);
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
                                                        backgroundImage("Interface/panelBack3.png");
                                                        x("948px");
                                                        int h = Params.screenHeight - (720 - 700);
                                                        y(Integer.toString(h) + "px");//"700px");
                                                        width("330px");
                                                        interactOnClick("ShowWindow()");
                                                    }
                                                });
                                                control(new WindowBuilder("winOverallControl", "Overall") {
                                                    {
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
                                                        interactOnClick("HideWindow()");
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();

                                                                        control(new LabelBuilder("currentMoney_WOvC", "Available Cash:") {
                                                                            {
                                                                                width("195px");
                                                                                height("20px");
                                                                                textHAlign(overall_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("currentMoneyValueSign_WOvC", Params.moneySign) {
                                                                            {
                                                                                width(Params.moneySignSizeField);
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("currentMoneyValue_WOvC", "_") {
                                                                            {
                                                                                width("70px");
                                                                                height("20px");
                                                                                textHAlign(overall_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                //TOTAL EXPENDITURES
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("totalExpenditures_WOvC", "Total Expenditures:") {
                                                                            {
                                                                                width("195px");
                                                                                height("20px");
                                                                                textHAlign(overall_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("totalExpendituresValueSign_WOvC", Params.moneySign) {
                                                                            {
                                                                                width(Params.moneySignSizeField);
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("totalExpendituresValue_WOvC", "_") {
                                                                            {
                                                                                width("70px");
                                                                                height("20px");
                                                                                textHAlign(overall_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        //ADD DETAILS
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("20px"));
                                                                                image(new ImageBuilder("imageOperatorCosts") {
                                                                                    {
                                                                                        filename("Interface/Principal/add_gray.png");
                                                                                        width("16px");
                                                                                        height("16px");
                                                                                        focusable(true);
                                                                                        interactOnClick("clickToOperatorCosts()");
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("operatorCosts_WOvC", "Operator Costs:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("operatorCostsValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("operatorCostsValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder("operatorCosts_parent") {
                                                                            {
                                                                                childLayoutVertical();

                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("20px"));
                                                                                image(new ImageBuilder("imageMachineEquipmentCosts") {
                                                                                    {
                                                                                        filename("Interface/Principal/add_gray.png");
                                                                                        width("16px");
                                                                                        height("16px");
                                                                                        focusable(true);
                                                                                        interactOnClick("clickToMachineEquipmentCosts()");
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("machineEquipmentCosts_WOvC", "Machine & Equipment Costs:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("machineEquipmentCostsValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("machineEquipmentCostsValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder("machineEquipmentCosts_parent") {
                                                                            {
                                                                                childLayoutVertical();

                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("20px"));
                                                                                image(new ImageBuilder("imageOtherCosts") {
                                                                                    {
                                                                                        filename("Interface/Principal/add_gray.png");
                                                                                        width("16px");
                                                                                        height("16px");
                                                                                        focusable(true);
                                                                                        interactOnClick("clickToOtherCosts()");
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("otherCosts_WOvC", "Other Costs:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("otherCostsValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("otherCostsValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder("otherCosts_parent") {
                                                                            {
                                                                                childLayoutVertical();

                                                                            }
                                                                        });
                                                                        //END DETAILS
                                                                    }
                                                                });
                                                                //TOTAL INCOMES
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("totalIncomes_WOvC", "Total Incomes:") {
                                                                            {
                                                                                width("195px");
                                                                                height("20px");
                                                                                textHAlign(overall_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("totalIncomesValueSign_WOvC", Params.moneySign) {
                                                                            {
                                                                                width(Params.moneySignSizeField);
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("totalIncomesValue_WOvC", "_") {
                                                                            {
                                                                                width("70px");
                                                                                height("20px");
                                                                                textHAlign(overall_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("35px"));
                                                                                control(new LabelBuilder("saleMachine_WOvC", "Machine Sale:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("saleMachineValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("saleMachineValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("35px"));
                                                                                control(new LabelBuilder("saleEquipment_WOvC", "Equipment Sale:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("saleEquipmentValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("saleEquipmentValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(common.hspacer("35px"));
                                                                                control(new LabelBuilder("incomeForSales_WOvC", "Part Sale:") {
                                                                                    {
                                                                                        width("160px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_label);
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                                control(new LabelBuilder("incomeForSalesValueSign_WOvC", Params.moneySign) {
                                                                                    {
                                                                                        width(Params.moneySignSizeField);
                                                                                        height("20px");
                                                                                        textHAlignCenter();
                                                                                    }
                                                                                });
                                                                                control(new LabelBuilder("incomeForSalesValue_WOvC", "_") {
                                                                                    {
                                                                                        width("70px");
                                                                                        height("20px");
                                                                                        textHAlign(overall_value);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                //TOTAL PROFIT
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();

                                                                        control(new LabelBuilder("totalProfit_WOvC", "Total Profit:") {
                                                                            {
                                                                                width("195px");
                                                                                height("20px");
                                                                                textHAlign(overall_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("totalProfitValueSign_WOvC", Params.moneySign) {
                                                                            {
                                                                                width(Params.moneySignSizeField);
                                                                                height("20px");
                                                                                textHAlignCenter();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("totalProfitValue_WOvC", "_") {
                                                                            {
                                                                                width("70px");
                                                                                height("20px");
                                                                                textHAlign(overall_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("948px");
                                                        int h = Params.screenHeight - (720 - 488);
                                                        y(Integer.toString(h) + "px");//"488px");
                                                        visible(false);
                                                        width("330px");
                                                        height("230px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winSuC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new SupplierControl());
                                                control(new WindowBuilder("winSupplierControl", "Supplier") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutHorizontal();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        width("90px");
                                                                        control(new LabelBuilder("text_WSuC", "Supplier list:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new ListBoxBuilder("suppliersList_WSuC") {
                                                                            {
                                                                                displayItems(10);
                                                                                selectionModeSingle();
                                                                                optionalVerticalScrollbar();
                                                                                hideHorizontalScrollbar();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.hspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("id_WSuC", "Supplier ID:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("idValue_WSuC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("name_WSuC", "Name:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("nameValue_WSuC", "_") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(common.vspacer("10px"));
                                                                                control(new LabelBuilder("catalogSection_WSuc", "Catalog Section") {
                                                                                    {
                                                                                        width("100%");
                                                                                        height("20px");
                                                                                        textHAlign(ElementBuilder.Align.Center);
                                                                                    }
                                                                                });
                                                                                panel(common.vspacer("5px"));
                                                                                control(new ListBoxBuilder("catalogSectionValue_WSuC") {
                                                                                    {
                                                                                        displayItems(8);
                                                                                        selectionModeSingle();
                                                                                        optionalVerticalScrollbar();
                                                                                        hideHorizontalScrollbar();
                                                                                        width("90%");
                                                                                    }
                                                                                });
                                                                                width("50%");
                                                                            }
                                                                        });
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                control(new LabelBuilder("catalogSelected_WSuc", "Catalog Selected") {
                                                                                    {
                                                                                        width("100%");
                                                                                        height("20px");
                                                                                        textHAlign(ElementBuilder.Align.Center);
                                                                                    }
                                                                                });
                                                                                panel(common.vspacer("5px"));
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("part_WSuC", "Part:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("partValue_WSuC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("25px");
                                                                                        control(new LabelBuilder("productionDistn_WSuC", "Production Distn:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("productionDistnValue_WSuC") {
                                                                                            {
                                                                                                width("100px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("productionParam1_WSuC", "Production Param1:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("productionParam1Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("productionParam2_WSuC", "Production Param2:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("productionParam2Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("productionCalculated_WSuC", "Production Calc.:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("productionCalculatedValue_WSuC", "_") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceLimit1_WSuC", "Price Limit 1:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceLimit1Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceCharge1_WSuC", "Price Charge 1:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceCharge1Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceLimit2_WSuC", "Price Limit 2:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceLimit2Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceCharge2_WSuC", "Price Charge 2:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceCharge2Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceLimit3_WSuC", "Price Limit 3:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceLimit3Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        height("22px");
                                                                                        control(new LabelBuilder("priceCharge3_WSuC", "Price Charge 3:") {
                                                                                            {
                                                                                                width("120px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_label);
                                                                                            }
                                                                                        });
                                                                                        panel(common.hspacer("5px"));
                                                                                        control(new LabelBuilder("priceCharge3Value_WSuC") {
                                                                                            {
                                                                                                width("70px");
                                                                                                height("20px");
                                                                                                textHAlign(supplier_value);
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                width("50%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("500px");
                                                        height("310px");
                                                    }
                                                });
                                            }
                                        });

                                        panel(new PanelBuilder("winOrC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new OrderControl());
                                                control(new LabelBuilder("OrderLabel", "Order") {
                                                    {
                                                        
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
//                                                        this.backgroundColor(de.lessvoid.nifty.tools.Color.BLACK);
                                                        backgroundImage("Interface/panelBack3.png");
                                                        x("2px");
                                                        int h = Params.screenHeight - (720 - 700);
                                                        y(Integer.toString(h) + "px");//"700px");
                                                        width("330px");
                                                        interactOnClick("ShowWindow()");

                                                        //interactOnClick("HideLabel()");

                                                    }
                                                });
                                                control(new WindowBuilder("winOrderControl", "Order") {
                                                    {
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
                                                        interactOnClick("HideWindow()");
                                                        //interactOnClick();
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("objective_WOrC", "Objective:") {
                                                                            {
                                                                                width("55px");
                                                                                height("20px");
                                                                                textHAlign(order_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("objectiveValue_WOrC", "_") {
                                                                            {
                                                                                width("265px");
                                                                                height("20px");
                                                                                textHAlign(order_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("totalOrders_WOrC", "Successful / Total Orders:") {
                                                                            {
                                                                                width("180px");
                                                                                height("20px");
                                                                                textHAlign(order_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("totalOrdersValue_WOrC", "_") {
                                                                            {
                                                                                width("140px");
                                                                                height("20px");
                                                                                textHAlign(order_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("maxFailedOrders_WOrC", "Failed / Max.Failed Orders:") {
                                                                            {
                                                                                width("180px");
                                                                                height("20px");
                                                                                textHAlign(order_label);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("maxFailedOrdersValue_WOrC", "_") {
                                                                            {
                                                                                width("140px");
                                                                                height("20px");
                                                                                textHAlign(order_value);
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new TextFieldBuilder("indexOrder_WOrC", "No") {
                                                                            {
                                                                                width("20px");
                                                                            }
                                                                        });
                                                                        control(new TextFieldBuilder("partRequired_WOrC", "     Part Required") {
                                                                            {
                                                                                width("120px");
                                                                            }
                                                                        });
                                                                        control(new TextFieldBuilder("quantity_WOrC", " Quantity") {
                                                                            {
                                                                                width("60px");
                                                                            }
                                                                        });
                                                                        control(new TextFieldBuilder("dueDate_WOrC", "       Due Date") {
                                                                            {
                                                                                width("115px");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                control(new ListBoxBuilder("ordersValue_WOrC") {
                                                                    {
                                                                        displayItems(4);
                                                                        selectionModeSingle();
                                                                        optionalVerticalScrollbar();
                                                                        hideHorizontalScrollbar();
                                                                        width("*");
                                                                        control(new ControlBuilder("customListBox_Orders") {
                                                                            {
                                                                                controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("2px");
                                                        int h = Params.screenHeight - (720 - 488);
                                                        y(Integer.toString(h) + "px");//"488px");
                                                        visible(false);
                                                        width("330px");
                                                        height("230px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winPrC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new PriorityControl());
                                                control(new WindowBuilder("winPriorityControl", "Priority Activity") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        control(new LabelBuilder("text_WPrC", "Set up the priority for each activity:\n(between: 1=most & 10=less priority)") {
                                                                            {
                                                                                width("100%");
                                                                                height("25px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        panel(common.vspacer("10px"));
                                                                        panel(new PanelBuilder("winPrC_Parent") {
                                                                            {
                                                                                childLayoutVertical();
                                                                                //ADD ACTIVITY

                                                                                //END ACTIVITY
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                control(new LabelBuilder("messageResult_WPrC", "") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignCenter();
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                control(new ButtonBuilder("priorityUpdate", "Update") {
                                                                    {
                                                                        width("90%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("415px");
                                                        height("100px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winULC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new UnitLoadControl());
                                                control(new WindowBuilder("winUnitLoadControl", "Unit Load (Parts per Trip)") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        control(new LabelBuilder("text_WULC", "Set unit load (parts per trip) for each activity:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("10px"));
                                                                        panel(new PanelBuilder("winULC_Parent") {
                                                                            {
                                                                                childLayoutVertical();
                                                                                //ADD ACTIVITY

                                                                                //END ACTIVITY
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                control(new LabelBuilder("messageResult_WULC", "") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignCenter();
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                control(new ButtonBuilder("unitLoadUpdate", "Update") {
                                                                    {
                                                                        width("90%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("330px");
                                                        height("0px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winAsOpC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new AssignOperatorControl());
                                                control(new WindowBuilder("winAssignOperatorControl", "Assign Operators") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        control(new LabelBuilder("text_WAsOpC", "Assign operators for each activity:") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutVertical();
                                                                                        width("120px");
                                                                                        control(new LabelBuilder("text2_WAsOpC", "Activities list:") {
                                                                                            {
                                                                                                width("100%");
                                                                                                height("20px");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new ListBoxBuilder("activitiesList_WAsOpC") {
                                                                                            {
                                                                                                displayItems(10);
                                                                                                selectionModeSingle();
                                                                                                optionalVerticalScrollbar();
                                                                                                hideHorizontalScrollbar();
                                                                                                width("100%");
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("10px"));
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutVertical();
                                                                                        width("360px");
                                                                                        control(new LabelBuilder("description_WAsOpC") {
                                                                                            {
                                                                                                width("100%");
                                                                                                height("20px");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        panel(common.vspacer("3px"));
                                                                                        control(new LabelBuilder("text3_WAsOpC", "Material handler:") {
                                                                                            {
                                                                                                width("100%");
                                                                                                height("20px");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        panel(new PanelBuilder("winMH_AsOpC_Parent") {
                                                                                            {
                                                                                                childLayoutVertical();
                                                                                                //ADD ACTIVITY
                                                                                            }
                                                                                        });
                                                                                        panel(common.vspacer("5px"));
                                                                                        control(new LabelBuilder("text4_WAsOpC", "Line operator:") {
                                                                                            {
                                                                                                width("100%");
                                                                                                height("20px");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        panel(new PanelBuilder("winLO_AsOpC_Parent") {
                                                                                            {
                                                                                                childLayoutVertical();
                                                                                                //ADD ACTIVITY
                                                                                            }
                                                                                        });
                                                                                        panel(common.vspacer("5px"));
                                                                                        control(new LabelBuilder("text5_WAsOpC", "Versatile:") {
                                                                                            {
                                                                                                width("100%");
                                                                                                height("20px");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        panel(new PanelBuilder("winV_AsOpC_Parent") {
                                                                                            {
                                                                                                childLayoutVertical();
                                                                                                //ADD ACTIVITY
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("text11_WAsOpC", "(?) : Number of operators/activities assigned") {
                                                                            {
                                                                                width("100%");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                control(new LabelBuilder("messageResult_WAsOpC", "") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignCenter();
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                control(new ButtonBuilder("assingOperatorUpdate", "Update") {
                                                                    {
                                                                        width("90%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("500px");
                                                        height("500px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winFCC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new FlowChartControl());
                                                control(new WindowBuilder("winFlowChartControl", "Process Flow Chart") {
                                                    {                                             
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                image(new ImageBuilder("imageFlowOfActivities") {
                                                                    {
                                                                        filename("Models/Flows/none.png");
                                                                        width("473px");
                                                                        height("383px");
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                control(new ButtonBuilder("closeFlowChart", "Close") {
                                                                    {
                                                                        width("100%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("245px");
                                                        y("30px");
                                                        visible(false);
                                                        width("493px");
                                                        height("448px");
                                                    }
                                                });
                                            }
                                        });




                                        panel(new PanelBuilder("winGLC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new GameLogControl());

                                                control(new LabelBuilder("LogLabel", "Game Log") {
                                                    {
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
                                                        //this.backgroundColor(de.lessvoid.nifty.tools.Color.BLACK);//.NONE);//
                                                        backgroundImage("Interface/panelBack3.png");
                                                        x("334px");
                                                        int h = Params.screenHeight - (720 - 700);
                                                        y(Integer.toString(h) + "px");//"700px");
                                                        width("612px");
                                                        interactOnClick("HideWindow()");
                                                        
                                                    }
                                                });


                                                control(new WindowBuilder("winGameLogControl", "Game Log") {
                                                    {
                                                        onShowEffect(common.createMoveEffect("in", "bottom", 600));
                                                        onHideEffect(common.createMoveEffect("out", "bottom", 600));
                                                        interactOnClickRepeat("showHide()");
                                                        closeable(false);
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new TextFieldBuilder("time_WGLC", " Date Time") {
                                                                            {
                                                                                width("130px");
                                                                            }
                                                                        });
                                                                        control(new TextFieldBuilder("message_WGLC", " Message") {
                                                                            {
                                                                                width("467px");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                control(new ListBoxBuilder("gameLog_WGLC") {
                                                                    {
                                                                        displayItems(7);
                                                                        selectionModeSingle();
                                                                        optionalVerticalScrollbar();
                                                                        hideHorizontalScrollbar();
                                                                        width("*");
                                                                        control(new ControlBuilder("customListBox_GameLog") {
                                                                            {
                                                                                controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("334px");
                                                        int h = Params.screenHeight - (720 - 488);
                                                        y(Integer.toString(h) + "px");//"488px");
                                                        visible(false);
                                                        width("612px");
                                                        height("230px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winGSC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new GameSetupControl());
                                                control(new WindowBuilder("winGameSetupControl", "Game Setup") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        control(new LabelBuilder("setupDescription", Params.setupDescription) {
                                                                            {
                                                                                textHAlignLeft();
                                                                                width("100%");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupResources") {
                                                                            {
                                                                                filename("Interface/Principal/resources2.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupResources()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupResources", Params.setupResources) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupResourcesStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupResources()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupStorage") {
                                                                            {
                                                                                filename("Interface/Principal/storage.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupStorage()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupStorage", Params.setupStorage) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupStorageStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupStorage()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupUnitLoad") {
                                                                            {
                                                                                filename("Interface/Principal/unit_load.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupUnitLoad()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupUnitLoad", Params.setupUnitLoad) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupUnitLoadStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupUnitLoad()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupPurchase") {
                                                                            {
                                                                                filename("Interface/Principal/purchase.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupPurchase()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupPurchase", Params.setupPurchase) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupPurchaseStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupPurchase()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupOperators") {
                                                                            {
                                                                                filename("Interface/Principal/assign_operators.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupOperators()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupOperators", Params.setupOperators) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupOperatorsStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupOperators()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        image(new ImageBuilder("imageSetupPriority") {
                                                                            {
                                                                                filename("Interface/Principal/priority.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupPriority()");
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("2px"));
                                                                        control(new LabelBuilder("textSetupPriority", Params.setupPriority) {
                                                                            {
                                                                                width("340px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        image(new ImageBuilder("imageSetupPriorityStatus") {
                                                                            {
                                                                                filename("Interface/Principal/wait_red.png");
                                                                                width("24px");
                                                                                height("24px");
                                                                                focusable(true);
                                                                                interactOnClick("clickSetupPriority()");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(common.hspacer("5%"));
                                                                        control(new ButtonBuilder("setupDefaultGame", "Default Setup") {
                                                                            {
                                                                                width("40%");
                                                                                alignCenter();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("10%"));
                                                                        control(new ButtonBuilder("setupStartGame", "Start Game") {
                                                                            {
                                                                                width("40%");
                                                                                alignCenter();
                                                                                backgroundImage(buttonBackgroundImage);
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5%"));
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("743px");
                                                        y("30px");
                                                        visible(false);
                                                        width("405px");
                                                        height("280px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winChC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new CharactersControl());
                                                control(new WindowBuilder("winCharactersControl", "Resources Management") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("operator_WChC", "Operators:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("operatorsText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("operatorsValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("10px"));
                                                                        control(new LabelBuilder("hire_WChC", "Hire x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("hireValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("carrier_WChC", " -Mat.Handler:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("carrierText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("carrierValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("10px"));
                                                                        control(new LabelBuilder("fire_WChC", "Fire x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("fireValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });

                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("assembler_WChC", " -Operator:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("assemblerText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("assemblerValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("versatile_WChC", " -Versatile:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("versatileText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("versatileValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("machines_WChC", "Machines:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("machinesText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("machinesValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("10px"));
                                                                        control(new LabelBuilder("buyMachine_WChC", "Buy x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("buyMachineValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(common.hspacer("245px"));
                                                                        control(new LabelBuilder("sellMachine_WChC", "Sell x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("sellMachineValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        height("25px");
                                                                        control(new LabelBuilder("equipment_WChC", "Equipment:") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("equipmentText_WChC", "") {
                                                                            {
                                                                                width("30px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new SliderBuilder("equipmentValue_WChC", false) {
                                                                            {
                                                                                width("120px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("10px"));
                                                                        control(new LabelBuilder("buyEquipment_WChC", "Buy x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("buyEquipmentValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(common.hspacer("245px"));
                                                                        control(new LabelBuilder("sellEquipment_WChC", "Sell x 0:") {
                                                                            {
                                                                                width("50px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("sellEquipmentValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("75px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(common.hspacer("245px"));
                                                                        control(new LabelBuilder("total_WChC", "Total :") {
                                                                            {
                                                                                width("45px");
                                                                                height("20px");
                                                                                textHAlignLeft();
                                                                            }
                                                                        });
                                                                        control(new LabelBuilder("totalValue_WChC", "$ 0.00") {
                                                                            {
                                                                                width("80px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                control(new LabelBuilder("messageResult_WChC", "") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignCenter();
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                control(new ButtonBuilder("charactersUpdate", "Update") {
                                                                    {
                                                                        width("90%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("390px");
                                                        height("300px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winASCC_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new StorageCostControl());
                                                control(new WindowBuilder("winStorageCostControl", "Assign Storage Costs") {
                                                    {
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                control(new LabelBuilder("description_WASCC", "Select the number of slots available for each storage") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignLeft();
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                panel(new PanelBuilder("storageCostParent") {
                                                                    {
                                                                        childLayoutVertical();
                                                                        //add storages

                                                                        //end storages
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        panel(common.hspacer("295px"));
                                                                        control(new LabelBuilder("totalCost_WASCC", "Total Cost/Hour:") {
                                                                            {
                                                                                width("100px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                        panel(common.hspacer("5px"));
                                                                        control(new LabelBuilder("totalCostValue_WASCC", "") {
                                                                            {
                                                                                width("70px");
                                                                                height("20px");
                                                                                textHAlignRight();
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                panel(common.vspacer("5px"));
                                                                control(new LabelBuilder("messageResult_WASCC", "") {
                                                                    {
                                                                        width("100%");
                                                                        height("20px");
                                                                        textHAlignCenter();
                                                                    }
                                                                });
                                                                panel(common.vspacer("10px"));
                                                                control(new ButtonBuilder("assingStorageCostsUpdate", "Update") {
                                                                    {
                                                                        width("90%");
                                                                        alignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        x("258px");
                                                        y("28px");
                                                        visible(false);
                                                        width("485px");
                                                        height("320px");
                                                    }
                                                });
                                            }
                                        });
                                        panel(new PanelBuilder("winDashboard_Element") {
                                            {
                                                childLayoutAbsolute();
                                                controller(new DashboardControl());
                                                control(new WindowBuilder("winDashboardControl", "Dashboard") {
                                                    {
                                                        onShowEffect(common.createMoveEffect("in", "right", 600));
                                                        onHideEffect(common.createMoveEffect("out", "right", 600));
                                                        backgroundImage(panelBackgroundImage);
                                                        panel(new PanelBuilder() {
                                                            {
                                                                childLayoutVertical();
                                                                onShowEffect(common.createMoveEffect("in", "right", 600));
                                                                onHideEffect(common.createMoveEffect("out", "right", 600));
                                                                //operators & equipment
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        //operators
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                control(new LabelBuilder("operator_DB", "Operators :") {
                                                                                    {
                                                                                        width("*");
                                                                                        textHAlignLeft();
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("hiredTotal_DB", "  - Hired / Total :") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("hiredTotalValue_DB", "-") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("busyIdle_DB", "  - Busy / Idle :") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("busyIdleValue_DB", "-") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("materialHandler_DB", "  - Material Handler :") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("materialHandlerValue_DB", "-") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("lineOperator_DB", "  - Line Operator :") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("lineOperatorValue_DB", "-") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("versatile_DB", "  - Versatile :") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("versatileValue_DB", "-") {
                                                                                            {
                                                                                                width("50%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                width("230px");
                                                                                height("120px");
                                                                            }
                                                                        });
                                                                        //line
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                image(new ImageBuilder("imageLineVertical") {
                                                                                    {
                                                                                        filename("Interface/Principal/line_vertical.png");
                                                                                        width("5px");
                                                                                        height("140px");
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                            }
                                                                        });
                                                                        //equipment
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                control(new LabelBuilder("machineEquipment_DB", "Machines / Equipment :") {
                                                                                    {
                                                                                        width("*");
                                                                                        textHAlignLeft();
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("machinePurchasedTotal_DB", "  - Machine Purchased / Total :") {
                                                                                            {
                                                                                                width("75%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("machinePurchasedTotalValue_DB", "-") {
                                                                                            {
                                                                                                width("25%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("equipmentPurchasedTotal_DB", "  - Equipment Purchased / Total :") {
                                                                                            {
                                                                                                width("75%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("equipmentPurchasedTotalValue_DB", "-") {
                                                                                            {
                                                                                                width("25%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("machineBrokenAvailable_DB", "  - Machine Busy / Idle :") {
                                                                                            {
                                                                                                width("75%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("machineBrokenAvailableValue_DB", "-") {
                                                                                            {
                                                                                                width("25%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new LabelBuilder("equipmentBrokenAvailable_DB", "  - Equipment Busy / Idle :") {
                                                                                            {
                                                                                                width("75%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                        control(new LabelBuilder("equipmentBrokenAvailableValue_DB", "-") {
                                                                                            {
                                                                                                width("25%");
                                                                                                textHAlignLeft();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                width("250px");
                                                                                height("120px");
                                                                            }
                                                                        });
                                                                        height("140px");
                                                                    }
                                                                });
                                                                //line
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutVertical();
                                                                        panel(common.vspacer("5px"));
                                                                        image(new ImageBuilder("imageLineHorizontal") {
                                                                            {
                                                                                filename("Interface/Principal/line_horizontal.png");
                                                                                width("*");
                                                                                height("5px");
                                                                            }
                                                                        });
                                                                        panel(common.vspacer("5px"));
                                                                    }
                                                                });
                                                                panel(new PanelBuilder() {
                                                                    {
                                                                        childLayoutHorizontal();
                                                                        //stations
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                control(new LabelBuilder("stations_DB", "Stations :") {
                                                                                    {
                                                                                        width("*");
                                                                                        textHAlignLeft();
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new TextFieldBuilder("stationName_DB", " Station") {
                                                                                            {
                                                                                                width("100px");
                                                                                            }
                                                                                        });
                                                                                        control(new TextFieldBuilder("stationPart_DB", " Part") {
                                                                                            {
                                                                                                width("60px");
                                                                                            }
                                                                                        });
                                                                                        control(new TextFieldBuilder("stationQuantity_DB", " Quantity") {
                                                                                            {
                                                                                                width("65px");
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                control(new ListBoxBuilder("stationsList_DB") {
                                                                                    {
                                                                                        displayItems(9);
                                                                                        selectionModeSingle();
                                                                                        optionalVerticalScrollbar();
                                                                                        hideHorizontalScrollbar();
                                                                                        width("225px");
                                                                                        control(new ControlBuilder("customListBox_StationsList_DB") {
                                                                                            {
                                                                                                controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                width("230px");
                                                                                height("320px");
                                                                            }
                                                                        });
                                                                        //line
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutHorizontal();
                                                                                image(new ImageBuilder("imageLineVertical2") {
                                                                                    {
                                                                                        filename("Interface/Principal/line_vertical.png");
                                                                                        width("5px");
                                                                                        height("*");
                                                                                    }
                                                                                });
                                                                                panel(common.hspacer("5px"));
                                                                            }
                                                                        });
                                                                        //transport
                                                                        panel(new PanelBuilder() {
                                                                            {
                                                                                childLayoutVertical();
                                                                                control(new LabelBuilder("transport_DB", "Transport :") {
                                                                                    {
                                                                                        width("*");
                                                                                        textHAlignLeft();
                                                                                    }
                                                                                });
                                                                                panel(new PanelBuilder() {
                                                                                    {
                                                                                        childLayoutHorizontal();
                                                                                        control(new TextFieldBuilder("transportName_DB", " Transport") {
                                                                                            {
                                                                                                width("180px");
                                                                                            }
                                                                                        });
                                                                                        control(new TextFieldBuilder("transportPart_DB", " Part") {
                                                                                            {
                                                                                                width("60px");
                                                                                            }
                                                                                        });
                                                                                        control(new TextFieldBuilder("transportRequired_DB", " No") {
                                                                                            {
                                                                                                width("40px");
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                control(new ListBoxBuilder("transportList_DB") {
                                                                                    {
                                                                                        displayItems(9);
                                                                                        selectionModeSingle();
                                                                                        optionalVerticalScrollbar();
                                                                                        hideHorizontalScrollbar();
                                                                                        width("280px");
                                                                                        control(new ControlBuilder("customListBox_TransportList_DB") {
                                                                                            {
                                                                                                controller("de.lessvoid.nifty.controls.listbox.ListBoxItemController");
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                                width("280px");
                                                                                height("280px");
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                                //line
                                                            }
                                                        });
                                                        x(1278 - dashboardWidth + "px");
                                                        y(minDashboardPositionY + "px");
                                                        visible(false);
                                                        width(dashboardWidth + "px");
                                                        height(dashboardHeight + "px");
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.build(nifty);
        return screen;
    }
    
    private static void registerWarningNewGamePopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();

        new PopupBuilder("warningNewGamePopup") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("280px");
                        height("200px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("fadeSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });

                        onActiveEffect(new EffectBuilder("playSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("40px");
                                childLayoutHorizontal();
                                control(new LabelBuilder("warningMessage", "You currently have a game in progress,\nare you sure you want to start a new game?") {
                                    {
                                        alignCenter();
                                        width("*");
                                    }
                                });
                            }
                        });
                        panel(new PanelBuilder() {
                            {
                                width("*");
                                paddingTop("60px");
                                alignCenter();
                                childLayoutHorizontal();
                                panel(common.hspacer("10px"));
                                control(new ButtonBuilder("warningPopupYes") {
                                    {
                                        label("Yes");
                                        alignCenter();
                                        valignCenter();
                                    }
                                });
                                panel(common.hspacer("30px"));
                                control(new ButtonBuilder("warningPopupNo") {
                                    {
                                        label("No");
                                        alignCenter();
                                        valignCenter();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerQuitPopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();

        new PopupBuilder("quitPopup") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("240px");
                        height("200px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("fadeSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });

                        onActiveEffect(new EffectBuilder("playSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("40px");
                                childLayoutHorizontal();
                                control(new LabelBuilder("login", "Are you sure you want to quit game?") {
                                    {
                                        alignCenter();
                                        width("*");
                                    }
                                });
                            }
                        });
                        panel(new PanelBuilder() {
                            {
                                width("*");
                                paddingTop("60px");
                                alignCenter();
                                childLayoutHorizontal();
                                control(new ButtonBuilder("quitPopupYes") {
                                    {
                                        label("Quit");
                                        alignCenter();
                                        valignCenter();
                                    }
                                });
                                panel(common.hspacer("20px"));
                                control(new ButtonBuilder("quitPopupNo") {
                                    {
                                        label("Cancel");
                                        alignCenter();
                                        valignCenter();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerGameUpdatingPopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();

        new ControlDefinitionBuilder("progressBarUtility") {
            {
                controller(new ProgressBarController());
                image(new ImageBuilder("imageBorder") {
                    {
                        childLayoutAbsolute();
                        filename("Interface/Principal/border_bar.png");
                        alignCenter();
                        imageMode("resize:15,2,15,15,15,2,15,2,15,2,15,15");
                        image(new ImageBuilder("imageInner") {
                            {
                                filename("Interface/Principal/inner_bar.png");
                                width("32px");
                                height("100%");
                                alignCenter();
                                x("0");
                                y("0");
                                imageMode("resize:15,2,15,15,15,2,15,2,15,2,15,15");
                            }
                        });
                        text(new TextBuilder("textInner") {
                            {
                                text("0%");
                                textHAlignCenter();
                                textVAlignCenter();
                                width("100%");
                                height("100%");
                                x("0");
                                y("0");
                                style("base-font-link");
                                color("#f00f");
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);

        new PopupBuilder("gameUpdating") {
            {
                childLayoutCenter();
                backgroundColor("#000a");
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("240px");
                        height("300px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("fadeSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });

                        onActiveEffect(new EffectBuilder("playSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("15px");
                                paddingLeft("10px");
                                childLayoutVertical();
                                control(new LabelBuilder("updatingHeader", "Updating...") {
                                    {
                                        width("200px");
                                        height("20px");
                                        textHAlignCenter();
                                    }
                                });
                                image(new ImageBuilder("updatingImage") {
                                    {
                                        filename("Interface/Principal/update.png");
                                        width("128px");
                                        height("128px");
                                        alignCenter();
                                    }
                                });
                                panel(common.vspacer("10px"));
                                control(new ControlBuilder("progressBarUtility") {
                                    {
                                        alignCenter();
                                        valignCenter();
                                        width("200px");
                                        height("32px");
                                    }
                                });
                                control(new LabelBuilder("updatingElement", "---") {
                                    {
                                        width("200px");
                                        height("20px");
                                        textHAlignCenter();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerGameClosingPopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();

        new PopupBuilder("gameClosing") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("300px");
                        height("240px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("fadeSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
//                        onActiveEffect(new EffectBuilder("gradient") {
//                            {
//                                effectValue("offset", "0%", "color", "#00bffecc");
//                                effectValue("offset", "75%", "color", "#00213cff");
//                                effectValue("offset", "100%", "color", "#880000cc");
//                            }
//                        });
                        onActiveEffect(new EffectBuilder("playSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("15px");
                                childLayoutVertical();
                                image(new ImageBuilder("gameClosingImage") {
                                    {
                                        filename("Interface/Principal/game_closing.png");
                                        width("128px");
                                        height("128px");
                                        alignCenter();
                                    }
                                });
                                control(new LabelBuilder("gameClosingMessage", "Your session will be closed in: 0 seconds") {
                                    {
                                        width("300px");
                                        height("20px");
                                        textHAlignCenter();
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                        width("*");
                                        paddingTop("10px");
                                        alignCenter();
                                        childLayoutHorizontal();
                                        panel(common.hspacer("30px"));
                                        control(new ButtonBuilder("gameClosingContinue", "Continue") {
                                            {
                                                alignCenter();
                                                valignCenter();
                                            }
                                        });
                                        panel(common.hspacer("20px"));
                                        control(new ButtonBuilder("gameClosingExit", "Exit") {
                                            {
                                                alignCenter();
                                                valignCenter();
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerGameOverPopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();

        new PopupBuilder("gameOverByLostOrders") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("240px");
                        height("300px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });

                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("15px");
                                childLayoutVertical();
                                image(new ImageBuilder("gameOverImage") {
                                    {
                                        filename("Interface/Principal/gameover.png");
                                        width("200px");
                                        height("200px");
                                        alignCenter();
                                    }
                                });
                                control(new LabelBuilder("gameOverMessage", "You missed too many orders!!!") {
                                    {
                                        width("200px");
                                        height("20px");
                                        textHAlignCenter();
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                        paddingTop("10px");
                                        width("*");
                                        alignCenter();
                                        childLayoutHorizontal();
                                        control(new ButtonBuilder("gameOverRestartO", "Restart"));
                                        panel(common.hspacer("20px"));
                                        control(new ButtonBuilder("gameOverQuitO", "Quit"));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);

        new PopupBuilder("gameOverByBankruptcy") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("240px");
                        height("300px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });

                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("15px");
                                childLayoutVertical();
                                image(new ImageBuilder("gameOverImage") {
                                    {
                                        filename("Interface/Principal/gameover.png");
                                        width("200px");
                                        height("200px");
                                        alignCenter();
                                    }
                                });
                                control(new LabelBuilder("gameOverMessage", "You fell into bankruptcy!!!") {
                                    {
                                        width("200px");
                                        height("20px");
                                        textHAlignCenter();
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                        paddingTop("10px");
                                        width("*");
                                        alignCenter();
                                        childLayoutHorizontal();
                                        control(new ButtonBuilder("gameOverRestartB", "Restart"));
                                        panel(common.hspacer("20px"));
                                        control(new ButtonBuilder("gameOverQuitB", "Quit"));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);

        new PopupBuilder("youWon") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        backgroundImage(popupBackgroundImage);
                        width("360px");
                        height("280px");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(10);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });

                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                paddingTop("15px");
                                childLayoutVertical();
                                image(new ImageBuilder("gameOverImage") {
                                    {
                                        filename("Interface/Principal/youwon.png");
                                        width("200px");
                                        height("200px");
                                        alignCenter();
                                    }
                                });
                                panel(new PanelBuilder() {
                                    {
                                        width("*");
                                        paddingTop("10px");
                                        alignCenter();
                                        childLayoutHorizontal();
                                        control(new ButtonBuilder("gameWonRestart", "Restart"));
                                        panel(common.hspacer("10px"));
                                        control(new ButtonBuilder("gameWonNextGame", "Next Game"));
                                        panel(common.hspacer("10px"));
                                        control(new ButtonBuilder("gameWonQuit", "Quit"));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    private static void registerCreditsPopup(final Nifty nifty) {
        final CommonBuilders common = new CommonBuilders();
        new PopupBuilder("creditsPopup") {
            {
                childLayoutCenter();
                panel(new PanelBuilder() {
                    {
                        width("80%");
                        height("80%");
                        alignCenter();
                        valignCenter();
                        onStartScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                effectParameter("mode", "in");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("move") {
                            {
                                length(400);
                                inherit();
                                neverStopRendering(true);
                                effectParameter("mode", "out");
                                effectParameter("direction", "top");
                            }
                        });
                        onEndScreenEffect(new EffectBuilder("fadeSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        onActiveEffect(new EffectBuilder("gradient") {
                            {
                                effectValue("offset", "0%", "color", "#00bffecc");
                                effectValue("offset", "75%", "color", "#00213cff");
                                effectValue("offset", "100%", "color", "#880000cc");
                            }
                        });
                        onActiveEffect(new EffectBuilder("playSound") {
                            {
                                effectParameter("sound", "credits");
                            }
                        });
                        padding("10px");
                        childLayoutVertical();
                        panel(new PanelBuilder() {
                            {
                                width("100%");
                                height("*");
                                childLayoutOverlay();
                                childClip(true);
                                panel(new PanelBuilder() {
                                    {
                                        width("100%");
                                        childLayoutVertical();
                                        onActiveEffect(new EffectBuilder("autoScroll") {
                                            {
                                                length(20000);//100000
                                                effectParameter("start", "0");
                                                effectParameter("end", "-3200");
                                                inherit(true);
                                            }
                                        });
                                        panel(common.vspacer("800px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Nifty 1.3");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text("Standard Controls Demonstration using JavaBuilder pattern");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("30px"));
                                        text(new TextBuilder() {
                                            {
                                                text("\"Look ma, No XML!\" :)");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("70px"));
                                        panel(new PanelBuilder() {
                                            {
                                                width("100%");
                                                height("256px");
                                                childLayoutCenter();
                                                panel(new PanelBuilder() {
                                                    {
                                                        alignCenter();
                                                        valignCenter();
                                                        childLayoutHorizontal();
                                                        width("656px");
                                                        panel(new PanelBuilder() {
                                                            {
                                                                width("200px");
                                                                height("256px");
                                                                childLayoutCenter();
                                                                text(new TextBuilder() {
                                                                    {
                                                                        text("Nifty 1.3 Core");
                                                                        style("base-font");
                                                                        alignCenter();
                                                                        valignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        panel(new PanelBuilder() {
                                                            {
                                                                width("256px");
                                                                height("256px");
                                                                alignCenter();
                                                                valignCenter();
                                                                childLayoutOverlay();
                                                                image(new ImageBuilder() {
                                                                    {
                                                                        filename("Interface/yin.png");
                                                                    }
                                                                });
                                                                image(new ImageBuilder() {
                                                                    {
                                                                        filename("Interface/yang.png");
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        panel(new PanelBuilder() {
                                                            {
                                                                width("200px");
                                                                height("256px");
                                                                childLayoutCenter();
                                                                text(new TextBuilder() {
                                                                    {
                                                                        text("Nifty 1.3 Standard Controls");
                                                                        style("base-font");
                                                                        alignCenter();
                                                                        valignCenter();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                        panel(common.vspacer("70px"));
                                        text(new TextBuilder() {
                                            {
                                                text("written and performed\nby void");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("100px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Sound Credits");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text("This demonstration uses creative commons licenced sound samples\nand music from the following sources");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("30px"));
                                        image(new ImageBuilder() {
                                            {
                                                style("creditsImage");
                                                filename("Interface/freesound.png");
                                            }
                                        });
                                        panel(common.vspacer("25px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Interface/19546__tobi123__Gong_mf2.wav");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("50px"));
                                        image(new ImageBuilder() {
                                            {
                                                style("creditsImage");
                                                filename("Interface/cc-mixter-logo.png");
                                                set("action", "openLink(http://ccmixter.org/)");
                                            }
                                        });
                                        panel(common.vspacer("25px"));
                                        text(new TextBuilder() {
                                            {
                                                text("\"Almost Given Up\" by Loveshadow");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("100px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Additional Credits");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text("ueber awesome Yin/Yang graphic by Dori\n(http://www.nadori.de)\n\nThanks! :)");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("100px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Special thanks go to");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text(
                                                        "The following people helped creating Nifty with valuable feedback,\nfixing bugs or sending patches.\n(in no particular order)\n\n"
                                                        + "chaz0x0\n"
                                                        + "Tumaini\n"
                                                        + "arielsan\n"
                                                        + "gaba1978\n"
                                                        + "ractoc\n"
                                                        + "bonechilla\n"
                                                        + "mdeletrain\n"
                                                        + "mulov\n"
                                                        + "gouessej\n");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("75px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Greetings and kudos go out to");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text(
                                                        "Ariel Coppes and Ruben Garat of Gemserk\n(http://blog.gemserk.com/)\n\n\n"
                                                        + "Erlend, Kirill, Normen, Skye and Ruth of jMonkeyEngine\n(http://www.jmonkeyengine.com/home/)\n\n\n"
                                                        + "Brian Matzon, Elias Naur, Caspian Rychlik-Prince for lwjgl\n(http://www.lwjgl.org/\n\n\n"
                                                        + "KappaOne, MatthiasM, aho, Dragonene, darkprophet, appel, woogley, Riven, NoobFukaire\nfor valuable input and discussions at #lwjgl IRC on the freenode network\n\n\n"
                                                        + "... and Kevin Glass\n(http://slick.cokeandcode.com/)\n\n\n\n\n\n\n\n"
                                                        + "As well as everybody that has not yet given up on Nifty =)\n\n"
                                                        + "And again sorry to all of you that I've forgotten. You rock too!\n\n\n");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("350px"));
                                        image(new ImageBuilder() {
                                            {
                                                style("creditsImage");
                                                filename("Interface/nifty-logo.png");
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        panel(new PanelBuilder() {
                            {
                                width("100%");
                                paddingTop("10px");
                                childLayoutCenter();
                                control(new ButtonBuilder("creditsBack") {
                                    {
                                        label("Back");
                                        alignRight();
                                        valignCenter();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerPopup(nifty);
    }

    public static void registerStyles(final Nifty nifty) {
        new StyleBuilder() {
            {
                id("base-font-link");
                base("base-font");
                color("#8fff");
                interactOnRelease("$action");
                onHoverEffect(new HoverEffectBuilder("changeMouseCursor") {
                    {
                        effectParameter("id", "hand");
                        color(Color.WHITE);
                    }
                });
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id("creditsImage");
                alignCenter();
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id("creditsCaption");
                font("Interface/verdana-48-regular.fnt");
                width("100%");
                textHAlignCenter();
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id("creditsCenter");
                base("base-font");
                width("100%");
                textHAlignCenter();
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id("nifty-panel-red");
            }
        }.build(nifty);
    }
    
    public static void registerPopups(final Nifty nifty) {
        registerCreditsPopup(nifty);
        registerQuitPopup(nifty);
        registerGameOverPopup(nifty);
        registerGameUpdatingPopup(nifty);
        registerGameClosingPopup(nifty);
        registerWarningNewGamePopup(nifty);
    }
}