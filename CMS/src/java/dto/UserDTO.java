/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dao.PrivilegeDao;
import java.util.ArrayList;
import java.util.List;
import model.Privilege;
import model.User;
import model.UserConfiguration;

/**
 *
 * @author Sergio
 */
public class UserDTO {
    
    private Long id;
    private String name, surname, bgcolor, groupId, login, password, groupName;
    private List<String> privilegeKeyIds = new ArrayList<>();
    public UserDTO(User user, UserConfiguration userConfig) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.bgcolor = userConfig.getBackgroundColor();
        this.groupId = userConfig.getGroupId();
        this.login = user.getLogin();
        PrivilegeDao privilegeDao = new PrivilegeDao();
        for (Privilege p : (List<Privilege>) privilegeDao.select("groupId="+userConfig.getGroupId())) {
            privilegeKeyIds.add(p.getKeyId());
        }
        
    }
    
    public boolean contains(String o) {
        return privilegeKeyIds.contains(o);
    }
    
    public UserDTO() {
        
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<String> getPrivilegeKeyIds() {
        return privilegeKeyIds;
    }

    public void setPrivilegeKeyIds(List<String> privilegeKeyIds) {
        this.privilegeKeyIds = privilegeKeyIds;
    }
    
    
}
