/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.gui;

import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
/**
 *
 * @author David
 */
public class MessagesViewConverter_Order implements ListBoxViewConverter<ListBoxMessages_Order>{
    private static final String CHAT_LINE_INDEX = "#index";
    private static final String CHAT_LINE_PART = "#part";
    private static final String CHAT_LINE_QUANTITY = "#quantity";
    private static final String CHAT_LINE_TIMEOVER = "#timeOver";
    private static final String CHAT_LINE_ICON = "#icon";

    public MessagesViewConverter_Order() {
    }
    
    public void display(Element listBoxItem, ListBoxMessages_Order item) {
        final Element index = listBoxItem.findElementByName(CHAT_LINE_INDEX);
        if (index == null)   return;
        final TextRenderer indexRenderer = index.getRenderer(TextRenderer.class);
        final Element part = listBoxItem.findElementByName(CHAT_LINE_PART);
        if (part == null)   return;
        final TextRenderer partRenderer = part.getRenderer(TextRenderer.class);
        final Element quantity = listBoxItem.findElementByName(CHAT_LINE_QUANTITY);
        if (quantity == null)   return;
        final TextRenderer quantityRenderer = quantity.getRenderer(TextRenderer.class);
        final Element timeOver = listBoxItem.findElementByName(CHAT_LINE_TIMEOVER);
        if (timeOver == null)   return;
        final TextRenderer timeOverRenderer = timeOver.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        if (item != null) {
            indexRenderer.setText(item.getIndex());
            partRenderer.setText(item.getPart());
            quantityRenderer.setText(item.getQuantity());
            timeOverRenderer.setText(item.getTimeOver());
            iconRenderer.setImage(item.getIcon());
        } else {
            indexRenderer.setText("");
            partRenderer.setText("");
            quantityRenderer.setText("");
            timeOverRenderer.setText("");
            iconRenderer.setImage(null);
        }
    }

    public int getWidth(Element listBoxItem, ListBoxMessages_Order item) {
        final Element index = listBoxItem.findElementByName(CHAT_LINE_INDEX);
        if (index == null)   return 0;
        final TextRenderer indexRenderer = index.getRenderer(TextRenderer.class);
        final Element part = listBoxItem.findElementByName(CHAT_LINE_PART);
        if (part == null)   return 0;
        final TextRenderer partRenderer = part.getRenderer(TextRenderer.class);
        final Element quantity = listBoxItem.findElementByName(CHAT_LINE_QUANTITY);
        if (quantity == null)   return 0;
        final TextRenderer quantityRenderer = quantity.getRenderer(TextRenderer.class);
        final Element timeOver = listBoxItem.findElementByName(CHAT_LINE_TIMEOVER);
        if (timeOver == null)   return 0;
        final TextRenderer timeOverRenderer = timeOver.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        return ((indexRenderer.getFont() == null) ? 0 : indexRenderer.getFont().getWidth(item.getIndex()))
                + ((partRenderer.getFont() == null) ? 0 : partRenderer.getFont().getWidth(item.getPart()))
                + ((quantityRenderer.getFont() == null) ? 0 : quantityRenderer.getFont().getWidth(item.getQuantity()))
                + ((timeOverRenderer.getFont() == null) ? 0 : timeOverRenderer.getFont().getWidth(item.getTimeOver()))
                + ((item.getIcon() == null) ? 0 : item.getIcon().getWidth());
    }
    
}
