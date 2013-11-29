/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtualfactory.nifty.screens.controllers;

/**
 *
 * @author David
 */
public class ListBoxMessages_AssignOpe {
    private String id;
    private String type;
    private String name;
    
    public ListBoxMessages_AssignOpe(final String id, final String type, final String name){
        this.id = id;
        this.type = type;
        this.name = name;
    }
    
    public String getId() {
        return  id;
    }
    
    public String getType() {
        return  type;
    }
    
    public String getName() {
        return  name;
    }
}
