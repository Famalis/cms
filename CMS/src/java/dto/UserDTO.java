/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import model.User;
import model.UserConfiguration;

/**
 *
 * @author Sergio
 */
public class UserDTO {
    
    private Long id;
    private String name, surname, bgcolor, groupId;
    public UserDTO(User user, UserConfiguration userConfig) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.bgcolor = userConfig.getBackgroundColor();
        this.groupId = userConfig.getGroupId();
        
    }
    
    public UserDTO() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    
}
