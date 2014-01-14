/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.PrivilegeDao;
import dao.PrivilegeKeyDao;
import java.util.List;
import model.general.DatabaseObject;

/**
 *
 * @author Sergio
 */
public class PrivilegeGroup extends DatabaseObject{
    
    private String name;
    
    public PrivilegeGroup() {
        super("privilege_group");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
