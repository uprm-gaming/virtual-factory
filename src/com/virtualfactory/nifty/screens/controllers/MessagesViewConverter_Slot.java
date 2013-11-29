/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty.screens.controllers;

import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
/**
 *
 * @author David
 */
public class MessagesViewConverter_Slot implements ListBoxViewConverter<ListBoxMessages_Slot>{
    private static final String CHAT_LINE_PART = "#part";
    private static final String CHAT_LINE_QUANTITY = "#quantity";
    private static final String CHAT_LINE_PARTSNUMBER = "#partsNumber";
    private static final String CHAT_LINE_ICON = "#icon";
    
    public MessagesViewConverter_Slot() {
    }
    
    public void display(Element listBoxItem, ListBoxMessages_Slot item) {
        final Element part = listBoxItem.findElementByName(CHAT_LINE_PART);
        if (part == null)   return;
        final TextRenderer partRenderer = part.getRenderer(TextRenderer.class);
        final Element quantity = listBoxItem.findElementByName(CHAT_LINE_QUANTITY);
        if (quantity == null)   return;
        final TextRenderer quantityRenderer = quantity.getRenderer(TextRenderer.class);
        final Element partsNumber = listBoxItem.findElementByName(CHAT_LINE_PARTSNUMBER);
        if (partsNumber == null)   return;
        final TextRenderer partsNumberRenderer = partsNumber.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        if (item != null) {
            partRenderer.setText(item.getPartDescription());
            quantityRenderer.setText(String.valueOf(item.getQuantity()));
            partsNumberRenderer.setText(String.valueOf(item.getPartsNumber()));
            iconRenderer.setImage(item.getIcon());
        } else {
            partRenderer.setText("");
            quantityRenderer.setText("");
            partsNumberRenderer.setText("");
            iconRenderer.setImage(null);
        }
    }

    public int getWidth(Element listBoxItem, ListBoxMessages_Slot item) {
        final Element part = listBoxItem.findElementByName(CHAT_LINE_PART);
        if (part == null)   return 0;
        final TextRenderer partRenderer = part.getRenderer(TextRenderer.class);
        final Element quantity = listBoxItem.findElementByName(CHAT_LINE_QUANTITY);
        if (quantity == null)   return 0;
        final TextRenderer quantityRenderer = quantity.getRenderer(TextRenderer.class);
        final Element partsNumber = listBoxItem.findElementByName(CHAT_LINE_PARTSNUMBER);
        if (partsNumber == null)   return 0;
        final TextRenderer partsNumberRenderer = partsNumber.getRenderer(TextRenderer.class);
        final Element icon = listBoxItem.findElementByName(CHAT_LINE_ICON);
        final ImageRenderer iconRenderer = icon.getRenderer(ImageRenderer.class);
        return ((partRenderer.getFont() == null) ? 0 : partRenderer.getFont().getWidth(item.getPartDescription()))
                + ((quantityRenderer.getFont() == null) ? 0 : quantityRenderer.getFont().getWidth(String.valueOf(item.getQuantity())))
                + ((partsNumberRenderer.getFont() == null) ? 0 : partsNumberRenderer.getFont().getWidth(String.valueOf(item.getPartsNumber())))
                + ((item.getIcon() == null) ? 0 : item.getIcon().getWidth());
    }
}
