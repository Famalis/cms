/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.general.DatabaseObject;

/**
 *
 * @author Sergio
 */
public class PrivilegeKey extends DatabaseObject{
    
    private String code, description;
    
    public PrivilegeKey() {
        super("privilege_key");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
