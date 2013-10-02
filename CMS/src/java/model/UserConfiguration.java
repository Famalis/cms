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
public class UserConfiguration extends DatabaseObject{
    
    private String userId;
    private String backgroundColor;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public UserConfiguration() {
        super("user_configuration");
    }
    
    
}
