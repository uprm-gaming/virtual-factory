package com.virtualfactory.gui.menu;

import com.virtualfactory.gui.menu.resources.MenuScreenController;
import com.virtualfactory.gui.menu.resources.InitialMenuDisplay;
import com.virtualfactory.gui.menu.resources.NewGame1MenuDisplay;
import com.virtualfactory.gui.menu.resources.NewUserMenuDisplay;
import com.virtualfactory.gui.menu.resources.ControlsDisplay;
import com.virtualfactory.gui.menu.resources.MainMenuDisplay;
import com.virtualfactory.gui.menu.resources.ForgotYourPasswordDisplay;
import com.virtualfactory.gui.menu.resources.LoadGameMenuDisplay;
import com.virtualfactory.gui.menu.resources.OptionsMenuDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;

final public class MenuScreen {
    private static boolean isScreenAlreadyBuilt = false;
    
    private MenuScreen() {}
    
    public static void build(final Nifty nifty) {
        if (isScreenAlreadyBuilt)
            throw new UnsupportedOperationException("Menu Screen has already been built.");
        
        new ScreenBuilder("initialMenu") {
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
    }
}