package com.virtualfactory.nifty.screens;

import com.virtualfactory.nifty.screens.controllers.MenuScreenController;
import com.virtualfactory.nifty.ControlsDisplay;
import com.virtualfactory.nifty.ForgotYourPasswordDisplay;
import com.virtualfactory.nifty.InitialMenuDisplay;
import com.virtualfactory.nifty.LoadGameMenuDisplay;
import com.virtualfactory.nifty.MainMenuDisplay;
import com.virtualfactory.nifty.NewGame1MenuDisplay;
import com.virtualfactory.nifty.NewUserMenuDisplay;
import com.virtualfactory.nifty.OptionsMenuDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.screen.Screen;

final public class MenuScreen {
    private static boolean isScreenAlreadyBuilt = false;
    
    private MenuScreen() {}
    
    public static Screen build(final Nifty nifty) {
        if (isScreenAlreadyBuilt)
            throw new UnsupportedOperationException("Menu Screen has already been built.");
        
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
        
        isScreenAlreadyBuilt = true;
        return screen;
    }
}