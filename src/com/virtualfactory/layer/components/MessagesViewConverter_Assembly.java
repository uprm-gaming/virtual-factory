/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.layer.components;

import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;

/**
 *
 * @author David
 */
public class MessagesViewConverter_Assembly implements ListBoxViewConverter<ListBoxMessages_Assembly>{
    private static final String CHAT_LINE_PART = "#part";
    private static final String CHAT_LINE_VALUE = "#value";

    public void display(Element listBoxItem, ListBoxMessages_Assembly item) {
        final Element text = listBoxItem.findElementByName(CHAT_LINE_PART);
        if (text == null)   return;
        final TextRenderer textRenderer = text.getRenderer(TextRenderer.class);
        final Element textValue = listBoxItem.findElementByName(CHAT_LINE_VALUE);
        if (textValue == null)   return;
        final TextRenderer textRendererValue = textValue.getRenderer(TextRenderer.class);
        //replace textRendererValue, by TEXTFIELD
        if (item != null){
            textRenderer.setText(item.getPart());
            textRendererValue.setText(item.getValue());
        }else{
            textRenderer.setText("");
            textRendererValue.setText("");
        }
    }

    public int getWidth(Element listBoxItem, ListBoxMessages_Assembly item) {
        final Element text = listBoxItem.findElementByName(CHAT_LINE_PART);
        if (text == null)   return 0;
        final TextRenderer textRenderer = text.getRenderer(TextRenderer.class);
        final Element textValue = listBoxItem.findElementByName(CHAT_LINE_VALUE);
        if (textValue == null)   return 0;
        final TextRenderer textRendererValue = textValue.getRenderer(TextRenderer.class);
        return ((textRenderer.getFont() == null) ? 0 : textRenderer.getFont().getWidth(item.getPart()))
                + ((textRendererValue.getFont() == null) ? 0 : textRendererValue.getFont().getWidth(item.getValue()));
    }
    
}
