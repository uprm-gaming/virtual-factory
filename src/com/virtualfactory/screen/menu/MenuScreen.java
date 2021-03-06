package com.virtualfactory.screen.menu;

import com.virtualfactory.screen.menu.components.InitialMenuDisplay;
import com.virtualfactory.screen.menu.components.NewGame1MenuDisplay;
import com.virtualfactory.screen.menu.components.NewUserMenuDisplay;
import com.virtualfactory.screen.menu.components.ControlsDisplay;
import com.virtualfactory.screen.menu.components.DialogPanelControlDefinition;
import com.virtualfactory.screen.menu.components.MainMenuDisplay;
import com.virtualfactory.screen.menu.components.ForgotYourPasswordDisplay;
import com.virtualfactory.screen.menu.components.LoadGameMenuDisplay;
import com.virtualfactory.screen.menu.components.OptionsMenuDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;

final public class MenuScreen {
    private static boolean isScreenAlreadyBuilt = false;
    
    private MenuScreen() {}
    
    public static void build(final Nifty niftyGUI) {
        if (niftyGUI == null || isScreenAlreadyBuilt) {
            String values = "niftyGUI = " + niftyGUI + "\nisScreenAlreadyBuilt = " + isScreenAlreadyBuilt;
            throw new IllegalStateException("Either Nifty GUI is not initialized or Menu Screen already exists.\n" + values);
        }
        
        registerMenuScreenComponents(niftyGUI);
        
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
        }.build(niftyGUI);
        
        isScreenAlreadyBuilt = true;
    }
    
    private static void registerMenuScreenComponents(Nifty niftyGUI) {
        DialogPanelControlDefinition.register(niftyGUI);
        InitialMenuDisplay.register(niftyGUI);
        ForgotYourPasswordDisplay.register(niftyGUI);
        MainMenuDisplay.register(niftyGUI);
        ControlsDisplay.register(niftyGUI);
        NewUserMenuDisplay.register(niftyGUI);
        NewGame1MenuDisplay.register(niftyGUI);
        LoadGameMenuDisplay.register(niftyGUI);
        OptionsMenuDisplay.register(niftyGUI);
    }
}