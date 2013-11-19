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
    private String groupId;
    private String photoHash;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhotoHash() {
        return photoHash;
    }

    public void setPhotoHash(String photoHash) {
        this.photoHash = photoHash;
    }
    
    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
        
    public UserConfiguration() {
        super("user_configuration");
    }
    
    
}
