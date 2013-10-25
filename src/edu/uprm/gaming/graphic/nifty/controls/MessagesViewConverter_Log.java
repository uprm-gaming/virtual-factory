/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.graphic.nifty.controls;
import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
/**
 *
 * @author David
 */
public class MessagesViewConverter_Log implements ListBoxViewConverter<ListBoxMessages_Log>{
    private static final String CHAT_LINE_TIME = "#time";
    private static final String CHAT_LINE_ICON = "#icon";
    private static final String CHAT_LINE_MESSAGE = "#message";
    
    public MessagesViewConverter_Log() {
    }
    
    public void display(Element listBoxItem, ListBoxMessages_Log item) {
        final Element time = listBoxItem.findElementByName(CHAT_LINE_TIME);
        if (time == null)   return;
        final TextRenderer timeRenderer = time.getRenderer(TextRenderer.class);
        final Element message = listBoxItem.findElementByName(CHAT_LINE_MESSAGE);
        if (message == null)   return;
        final TextRenderer messageRenderer = message.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        if (item != null) {
            timeRenderer.setText(String.valueOf(item.getTime()));
            messageRenderer.setText(item.getMessage());
            iconRenderer.setImage(item.getIcon());
        } else {
            timeRenderer.setText("");
            messageRenderer.setText("");
            iconRenderer.setImage(null);
        }
    }

    public int getWidth(Element listBoxItem, ListBoxMessages_Log item) {
        final Element time = listBoxItem.findElementByName(CHAT_LINE_TIME);
        if (time == null)   return 0;
        final TextRenderer timeRenderer = time.getRenderer(TextRenderer.class);
        final Element message = listBoxItem.findElementByName(CHAT_LINE_MESSAGE);
        if (message == null)   return 0;
        final TextRenderer messageRenderer = message.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        return ((timeRenderer.getFont() == null) ? 0 : timeRenderer.getFont().getWidth(String.valueOf(item.getTime())))
                + ((messageRenderer.getFont() == null) ? 0 : messageRenderer.getFont().getWidth(item.getMessage()))
                + ((item.getIcon() == null) ? 0 : item.getIcon().getWidth());
    }
}
