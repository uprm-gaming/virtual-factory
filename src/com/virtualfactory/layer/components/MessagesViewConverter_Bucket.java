/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.layer.components;

import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;

/**
 *
 * @author David
 */
public class MessagesViewConverter_Bucket implements ListBox.ListBoxViewConverter<ListBoxMessages_Bucket>{
    private static final String CHAT_LINE_INDEX = "#index";
    private static final String CHAT_LINE_UNIT = "#unit";
    private static final String CHAT_LINE_SIZECAPACITY = "#sizeCapacity";
    private static final String CHAT_LINE_PARTASSIGNED = "#partAssigned";
    private static final String CHAT_LINE_UNITSTOARRIVEREMOVE = "#unitsToArriveRemove";
    
    public MessagesViewConverter_Bucket() {
    }
    
    public void display(Element listBoxItem, ListBoxMessages_Bucket item) {
        final Element index = listBoxItem.findElementByName(CHAT_LINE_INDEX);
        if (index == null)   return;
        final TextRenderer indexRenderer = index.getRenderer(TextRenderer.class);
        
        final Element unit = listBoxItem.findElementByName(CHAT_LINE_UNIT);
        if (unit == null)   return;
        final TextRenderer unitRenderer = unit.getRenderer(TextRenderer.class);
        
        final Element sizeCapacity = listBoxItem.findElementByName(CHAT_LINE_SIZECAPACITY);
        if (sizeCapacity == null)   return;
        final TextRenderer sizeCapacityRenderer = sizeCapacity.getRenderer(TextRenderer.class);
        
        final Element partAssigned = listBoxItem.findElementByName(CHAT_LINE_PARTASSIGNED);
        if (partAssigned == null)   return;
        final TextRenderer partAssignedRenderer = partAssigned.getRenderer(TextRenderer.class);
        
        final Element unitsToArriveRemove = listBoxItem.findElementByName(CHAT_LINE_UNITSTOARRIVEREMOVE);
        if (unitsToArriveRemove == null)   return;
        final TextRenderer unitsToArriveRemoveRenderer = unitsToArriveRemove.getRenderer(TextRenderer.class);
        
        if (item != null) {
            indexRenderer.setText(item.getIndex());
            unitRenderer.setText(item.getUnit());
            sizeCapacityRenderer.setText(item.getSizeCapacity());
            partAssignedRenderer.setText(item.getPartAssigned());
            unitsToArriveRemoveRenderer.setText(item.getUnitsToArriveRemove());
        } else {
            indexRenderer.setText("");
            unitRenderer.setText("");
            sizeCapacityRenderer.setText("");
            partAssignedRenderer.setText("");
            unitsToArriveRemoveRenderer.setText("");
        }
    }

    public int getWidth(Element listBoxItem, ListBoxMessages_Bucket item) {
        final Element index = listBoxItem.findElementByName(CHAT_LINE_INDEX);
        if (index == null)   return 0;
        final TextRenderer indexRenderer = index.getRenderer(TextRenderer.class);
        
        final Element unit = listBoxItem.findElementByName(CHAT_LINE_UNIT);
        if (unit == null)   return 0;
        final TextRenderer unitRenderer = unit.getRenderer(TextRenderer.class);
        
        final Element sizeCapacity = listBoxItem.findElementByName(CHAT_LINE_SIZECAPACITY);
        if (sizeCapacity == null)   return 0;
        final TextRenderer sizeCapacityRenderer = sizeCapacity.getRenderer(TextRenderer.class);
        
        final Element partAssigned = listBoxItem.findElementByName(CHAT_LINE_PARTASSIGNED);
        if (partAssigned == null)   return 0;
        final TextRenderer partAssignedRenderer = partAssigned.getRenderer(TextRenderer.class);
        
        final Element unitsToArriveRemove = listBoxItem.findElementByName(CHAT_LINE_UNITSTOARRIVEREMOVE);
        if (unitsToArriveRemove == null)   return 0;
        final TextRenderer unitsToArriveRemoveRenderer = unitsToArriveRemove.getRenderer(TextRenderer.class);
        return ((indexRenderer.getFont() == null) ? 0 : indexRenderer.getFont().getWidth(item.getIndex()))
                + ((unitRenderer.getFont() == null) ? 0 : unitRenderer.getFont().getWidth(item.getUnit()))
                + ((sizeCapacityRenderer.getFont() == null) ? 0 : sizeCapacityRenderer.getFont().getWidth(item.getSizeCapacity()))
                + ((partAssignedRenderer.getFont() == null) ? 0 : partAssignedRenderer.getFont().getWidth(item.getPartAssigned()))
                + ((unitsToArriveRemoveRenderer.getFont() == null) ? 0 : unitsToArriveRemoveRenderer.getFont().getWidth(item.getUnitsToArriveRemove()));
    }
    
}
