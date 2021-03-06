/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.screen.layer.components;

import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
/**
 *
 * @author David
 */
public class MessagesViewConverter_StationList_DB implements ListBoxViewConverter<ListBoxMessages_StationList_DB>{
    private static final String CHAT_LINE_STATIONNAME = "#station";
    private static final String CHAT_LINE_PARTNAME = "#part";
    private static final String CHAT_LINE_QUANTITY = "#quantity";
    
    public void display(Element listBoxItem, ListBoxMessages_StationList_DB item) {
        final Element station = listBoxItem.findElementByName(CHAT_LINE_STATIONNAME);
        if (station == null)   return;
        final TextRenderer stationRenderer = station.getRenderer(TextRenderer.class);
        final Element part = listBoxItem.findElementByName(CHAT_LINE_PARTNAME);
        if (part == null)   return;
        final TextRenderer partRenderer = part.getRenderer(TextRenderer.class);
        final Element quantity = listBoxItem.findElementByName(CHAT_LINE_QUANTITY);
        if (quantity == null)   return;
        final TextRenderer quantityRenderer = quantity.getRenderer(TextRenderer.class);
        if (item != null) {
            stationRenderer.setText(item.getStationName());
            partRenderer.setText(item.getPartName());
            quantityRenderer.setText(item.getQuantity());
        } else {
            stationRenderer.setText("");
            partRenderer.setText("");
            quantityRenderer.setText("");
        }
    }

    public int getWidth(Element listBoxItem, ListBoxMessages_StationList_DB item) {
        final Element station = listBoxItem.findElementByName(CHAT_LINE_STATIONNAME);
        if (station == null)   return 0;
        final TextRenderer stationRenderer = station.getRenderer(TextRenderer.class);
        final Element part = listBoxItem.findElementByName(CHAT_LINE_PARTNAME);
        if (part == null)   return 0;
        final TextRenderer partRenderer = part.getRenderer(TextRenderer.class);
        final Element quantity = listBoxItem.findElementByName(CHAT_LINE_QUANTITY);
        if (quantity == null)   return 0;
        final TextRenderer quantityRenderer = quantity.getRenderer(TextRenderer.class);
        return ((stationRenderer.getFont() == null) ? 0 : stationRenderer.getFont().getWidth(item.getStationName()))
                + ((partRenderer.getFont() == null) ? 0 : partRenderer.getFont().getWidth(item.getPartName()))
                + ((quantityRenderer.getFont() == null) ? 0 : quantityRenderer.getFont().getWidth(item.getQuantity()));
    }
}
