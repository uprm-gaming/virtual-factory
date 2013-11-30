/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.screen.menu.components;

import com.virtualfactory.screen.menu.components.ListBoxMessages_NewGame2;
import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
/**
 *
 * @author David
 */
public class MessagesViewConverter_NewGame2 implements ListBoxViewConverter<ListBoxMessages_NewGame2> {
    private static final String CHAT_LINE_ICON = "#icon";
    private static final String CHAT_LINE_ITEM = "#item";    
    private static final String CHAT_LINE_VALUE = "#value";
    
    /**
     * Default constructor.
     */
    public MessagesViewConverter_NewGame2() {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void display(final Element listBoxItem, final ListBoxMessages_NewGame2 item) {
        final Element text = listBoxItem.findElementByName(CHAT_LINE_ITEM);
        if (text == null)   return;
        final TextRenderer textRenderer = text.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
//        final Element textElement = listBoxItem.findElementByName(CHAT_LINE_VALUE); 
//        if (textElement == null)   return;
        if (item != null) {
            textRenderer.setText(item.getText());
            iconRenderer.setImage(item.getIcon());
//            textElement.getRenderer(TextRenderer.class).setText(item.getTextFieldValue()); 
        } else {
            textRenderer.setText("");
            iconRenderer.setImage(null);
//            textElement.getRenderer(TextRenderer.class).setText(""); 
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getWidth(final Element listBoxItem, final ListBoxMessages_NewGame2 item) {
        final Element text = listBoxItem.findElementByName(CHAT_LINE_ITEM);
        if (text == null)   return 0;
        final TextRenderer textRenderer = text.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
//        final TextRenderer renderer = listBoxItem.findElementByName(CHAT_LINE_VALUE).getRenderer(TextRenderer.class);
        
        return ((textRenderer.getFont() == null) ? 0 : textRenderer.getFont().getWidth(item.getText()))
//                + ((renderer.getFont() == null) ? 0 : renderer.getFont().getWidth(item.getText()))
                + ((item.getIcon() == null) ? 0 : item.getIcon().getWidth());
    }
}
