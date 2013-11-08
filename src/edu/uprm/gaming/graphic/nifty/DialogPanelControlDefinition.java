package edu.uprm.gaming.graphic.nifty;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.EffectBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import java.awt.Color;

/**
 * This registers a special Panel control with Nifty we later use as the parent control/panel
 * for Dialogs. With it we can change the look and feel as well as the attached effect in
 * one place.
 * @author void
 */
public class DialogPanelControlDefinition {
  public static String NAME = "dialogPanel";
    public static void register(final Nifty nifty) {
        new ControlDefinitionBuilder(NAME) {
            {
                set("childRootId", "#effectPanel");
                panel(new PanelBuilder() {
                    {
                        visible(false);
                        childLayoutCenter();
                        
                        
                        panel(new PanelBuilder("#effectPanel") {
                            {
                                childLayoutVertical();
                                alignCenter();
                                valignTop();
                                marginTop("15%");
                                width("50%");
                                height("50%");
                                padding("5px,20px,26px,19px");
                                onShowEffect(new EffectBuilder("fade") {
                                    {
                                        length(500);
                                        effectParameter("start", "#0");
                                        effectParameter("end", "#f");
                                    }
                                });
                                onHideEffect(new EffectBuilder("fade") {
                                    {
                                        length(500);
                                        effectParameter("start", "#f");
                                        effectParameter("end", "#0");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.registerControlDefintion(nifty);
    }
}
