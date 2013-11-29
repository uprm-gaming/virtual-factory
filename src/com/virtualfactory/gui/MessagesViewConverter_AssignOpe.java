/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.gui;

import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
/**
 *
 * @author David
 */
public class MessagesViewConverter_AssignOpe implements ListBoxViewConverter<ListBoxMessages_AssignOpe>{
    private static final String CHAT_LINE_ID = "#id";
    private static final String CHAT_LINE_TYPE = "#type";
    private static final String CHAT_LINE_NAME = "#name";
    
    public void display(Element listBoxItem, ListBoxMessages_AssignOpe item) {
        final Element textId = listBoxItem.findElementByName(CHAT_LINE_ID);
        if (textId == null)   return;
        final TextRenderer textRendererId = textId.getRenderer(TextRenderer.class);
        final Element text = listBoxItem.findElementByName(CHAT_LINE_TYPE);
        if (text == null)   return;
        final TextRenderer textRenderer = text.getRenderer(TextRenderer.class);
        final Element textValue = listBoxItem.findElementByName(CHAT_LINE_NAME);
        if (textValue == null)   return;
        final TextRenderer textRendererValue = textValue.getRenderer(TextRenderer.class);
        //replace textRendererValue, by TEXTFIELD
        if (item != null){
            textRendererId.setText(item.getId());
            textRenderer.setText(item.getType());
            textRendererValue.setText(item.getName());
        }else{
            textRendererId.setText("");
            textRenderer.setText("");
            textRendererValue.setText("");
        }
    }

    public int getWidth(Element listBoxItem, ListBoxMessages_AssignOpe item) {
        final Element textId = listBoxItem.findElementByName(CHAT_LINE_ID);
        if (textId == null)   return 0;
        final TextRenderer textRendererId = textId.getRenderer(TextRenderer.class);
        final Element text = listBoxItem.findElementByName(CHAT_LINE_TYPE);
        if (text == null)   return 0;
        final TextRenderer textRenderer = text.getRenderer(TextRenderer.class);
        final Element textValue = listBoxItem.findElementByName(CHAT_LINE_NAME);
        if (textValue == null)   return 0;
        final TextRenderer textRendererValue = textValue.getRenderer(TextRenderer.class);
        return ((textRendererId.getFont() == null) ? 0 : textRendererId.getFont().getWidth(item.getId()))
                + ((textRenderer.getFont() == null) ? 0 : textRenderer.getFont().getWidth(item.getType()))
                + ((textRendererValue.getFont() == null) ? 0 : textRendererValue.getFont().getWidth(item.getName()));
    }
}
