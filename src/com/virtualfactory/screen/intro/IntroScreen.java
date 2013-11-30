package com.virtualfactory.screen.intro;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.EffectBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.screen.DefaultScreenController;

final public class IntroScreen {
    private static boolean isScreenAlreadyBuilt = false;
    
    private IntroScreen() {}
    
    public static void build(final Nifty niftyGUI) {
        if (isScreenAlreadyBuilt)
            throw new UnsupportedOperationException("Intro Screen has already been built.");
        
        new ScreenBuilder("start") {
            {
                controller(new DefaultScreenController() {
                    @Override
                    public void onStartScreen() {
                        niftyGUI.gotoScreen("initialMenu");
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
        }.build(niftyGUI);
        
        isScreenAlreadyBuilt = true;
    }
}