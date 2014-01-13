/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dao.PrivilegeDao;
import dao.PrivilegeKeyDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
import model.Privilege;
import model.PrivilegeKey;
import model.User;
import model.UserConfiguration;

/**
 *
 * @author Sergio
 */
public class UserDTO implements Serializable {

    private Long id;
    private String name, surname, groupId, login, password, groupName, employeeId, mail;
    private List<String> privilegeKeyCodes = new ArrayList<>();

    public UserDTO(User user, UserConfiguration userConfig) {
        this.id = user.getId();
        this.groupId = userConfig.getGroupId();
        this.login = user.getLogin();
        this.mail = user.getEmail();
        if (user.getEmployeeId() != null) {
            if (!user.getEmployeeId().isEmpty()) {
                this.employeeId = user.getEmployeeId();
            }
        }
        Employee emp = new Employee();
        emp.loadObject("id="+user.getEmployeeId());
        this.name = emp.getName();
        this.surname = emp.getSurname();
        PrivilegeDao privilegeDao = new PrivilegeDao();
        PrivilegeKeyDao privilegeKeyDao = new PrivilegeKeyDao();
        List<String> keyIds = new ArrayList<>();
        for (Privilege p : privilegeDao.select("groupId=" + userConfig.getGroupId())) {
            keyIds.add(p.getKeyId());
        }
        for (PrivilegeKey key : privilegeKeyDao.findByField("id", keyIds)) {
            privilegeKeyCodes.add(key.getCode());
        }
    }

    public UserDTO() {
        //this.bgcolor = "lightgrey";
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
    
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getPrivilegeKeyCodes() {
        return privilegeKeyCodes;
    }

    public void setPrivilegeKeyCodes(List<String> privilegeKeyIds) {
        this.privilegeKeyCodes = privilegeKeyIds;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
