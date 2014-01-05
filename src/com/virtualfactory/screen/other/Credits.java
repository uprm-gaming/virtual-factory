package com.virtualfactory.screen.other;

import com.virtualfactory.utils.CommonBuilders;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.EffectBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;

final public class Credits {
    private Credits() {}
    
    public static void register(final Nifty nifty) {
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
                                effectValue("offset", "0%", "color", "#00000f");
                                effectValue("offset", "75%", "color", "#00213cff");
                                effectValue("offset", "100%", "color", "#cccccc");
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
                                                effectParameter("end", "-2350");
                                                inherit(true);
                                            }
                                        });
                                        panel(common.vspacer("800px"));
                                        text(new TextBuilder() {
                                            {
                                                text("Virtual Factory 2.0 (Beta Release)");
                                                style("creditsCaption");
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
                                                                        text("");
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
                                                                        filename("Interface/Logo/VF-icon.png");
                                                                        width("256px");
                                                                        height("256px");
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
                                                                        text("");
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
                                                text("");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("100px"));
                                        text(new TextBuilder() {
                                            {
                                                text("UPRM Gaming team");
                                                style("creditsCaption");
                                            }
                                        });
                                        text(new TextBuilder() {
                                            {
                                                text("Video game developed by the UPRM gaming team");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("30px"));
                                        image(new ImageBuilder() {
                                            {
                                                style("creditsImage");
                                                filename("Interface/Logo/GamingLogo.png");
                                                width("256px");
                                                height("256px");
                                            }
                                        });
                                        panel(common.vspacer("25px"));
                                        text(new TextBuilder() {
                                            {
                                                text("http://ingenieria.uprm.edu/gaming/");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("100px"));
                                        image(new ImageBuilder() {
                                            {
                                                style("creditsImage");
                                                filename("Interface/Logo/NSF_logo.png");
                                                width("256px");
                                                height("256px");

                                            }
                                        });
                                        panel(common.vspacer("25px"));
                                        text(new TextBuilder() {
                                            {
                                                text("National Sciene Foundation\n"
                                                        + "#0835990");
                                                style("creditsCenter");
                                            }
                                        });
                                        panel(common.vspacer("100px"));
//                                        text(new TextBuilder() {
//                                            {
//                                                text("Current Team");
//                                                style("creditsCaption");
//                                            }
//                                        });
//                                        text(new TextBuilder() {
//                                            {
//                                                text("ueber awesome Yin/Yang graphic by Dori\n(http://www.nadori.de)\n\nThanks! :)");
//                                                style("creditsCenter");
//                                            }
//                                        });
//                                        panel(common.vspacer("100px"));
//                                        text(new TextBuilder() {
//                                            {
//                                                text("Special thanks go to");
//                                                style("creditsCaption");
//                                            }
//                                        });
//                                        text(new TextBuilder() {
//                                            {
//                                                text(
//                                                        "The following people helped creating Nifty with valuable feedback,\nfixing bugs or sending patches.\n(in no particular order)\n\n"
//                                                        + "chaz0x0\n"
//                                                        + "Tumaini\n"
//                                                        + "arielsan\n"
//                                                        + "gaba1978\n"
//                                                        + "ractoc\n"
//                                                        + "bonechilla\n"
//                                                        + "mdeletrain\n"
//                                                        + "mulov\n"
//                                                        + "gouessej\n");
//                                                style("creditsCenter");
//                                            }
//                                        });
//                                        panel(common.vspacer("75px"));
//                                        text(new TextBuilder() {
//                                            {
//                                                text("Greetings and kudos go out to");
//                                                style("creditsCaption");
//                                            }
//                                        });
//                                        text(new TextBuilder() {
//                                            {
//                                                text(
//                                                        "Ariel Coppes and Ruben Garat of Gemserk\n(http://blog.gemserk.com/)\n\n\n"
//                                                        + "Erlend, Kirill, Normen, Skye and Ruth of jMonkeyEngine\n(http://www.jmonkeyengine.com/home/)\n\n\n"
//                                                        + "Brian Matzon, Elias Naur, Caspian Rychlik-Prince for lwjgl\n(http://www.lwjgl.org/\n\n\n"
//                                                        + "KappaOne, MatthiasM, aho, Dragonene, darkprophet, appel, woogley, Riven, NoobFukaire\nfor valuable input and discussions at #lwjgl IRC on the freenode network\n\n\n"
//                                                        + "... and Kevin Glass\n(http://slick.cokeandcode.com/)\n\n\n\n\n\n\n\n"
//                                                        + "As well as everybody that has not yet given up on Nifty =)\n\n"
//                                                        + "And again sorry to all of you that I've forgotten. You rock too!\n\n\n");
//                                                style("creditsCenter");
//                                            }
                                        //                                        });
                                        panel(common.vspacer("350px"));
                                        image(new ImageBuilder() {
                                            {
                                                style("creditsImage");
                                                filename("Interface/VF-Letters-Orange.png");
                                                width("600px");
                                                height("146px");

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
}
