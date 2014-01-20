/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import model.general.DatabaseObject;

/**
 *
 * @author sergio
 */
public class DictionaryType extends DatabaseObject{

    public String value, description;
    
    public DictionaryType() {
        super("dictionary_type");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
