/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty;

import com.virtualfactory.nifty.ListBoxMessages;
import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;

/**
 *
 * @author David
 */
public class MessagesViewConverter implements ListBoxViewConverter<ListBoxMessages> {
    private static final String CHAT_LINE_ICON = "#icon";
    private static final String CHAT_LINE_TEXT = "#message";

    /**
     * Default constructor.
     */
    public MessagesViewConverter() {
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final void display(final Element listBoxItem, final ListBoxMessages item) {
        final Element text = listBoxItem.findElementByName(CHAT_LINE_TEXT);
        if (text == null)   return;
        final TextRenderer textRenderer = text.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        if (item != null) {
            textRenderer.setText(item.getText());
            iconRenderer.setImage(item.getIcon());
        } else {
            textRenderer.setText("");
            iconRenderer.setImage(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getWidth(final Element listBoxItem, final ListBoxMessages item) {
        final Element text = listBoxItem.findElementByName(CHAT_LINE_TEXT);
        if (text == null)   return 0;
        final TextRenderer textRenderer = text.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        return ((textRenderer.getFont() == null) ? 0 : textRenderer.getFont().getWidth(item.getText()))
                + ((item.getIcon() == null) ? 0 : item.getIcon().getWidth());
    }
}
