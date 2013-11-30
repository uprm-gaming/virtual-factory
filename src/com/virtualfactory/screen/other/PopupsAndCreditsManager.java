package com.virtualfactory.screen.other;

import com.virtualfactory.screen.other.ProgressBarController;
import com.virtualfactory.utils.CommonBuilders;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.EffectBuilder;
import de.lessvoid.nifty.builder.HoverEffectBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.builder.StyleBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.tools.Color;

final public class PopupsAndCreditsManager {
    private static String popupBackgroundImage = "Interface/panelpopup2.png";//"Textures/background_gray.png";

    private PopupsAndCreditsManager() {}
    
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