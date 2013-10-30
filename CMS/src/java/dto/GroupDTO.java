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
import model.PrivilegeGroup;
import model.Privilege;
import model.PrivilegeKey;

/**
 *
 * @author Sergio
 */
public class GroupDTO implements Serializable{

    private Long id;
    private String name;
    private List<Long> privilegeKeyIds;

    public GroupDTO() {
    }

    public GroupDTO(PrivilegeGroup group) {
        this.id = group.getId();
        this.name = group.getName();

        PrivilegeDao privilegeDao = new PrivilegeDao();
        List<Privilege> privList = (List<Privilege>) privilegeDao.select("groupId=" + this.getId());
        String queryConditions = "";
        for (int i = 0; i < privList.size(); i++) {
            queryConditions += "id=" + privList.get(i).getKeyId();
            if (i < privList.size() - 1) {
                queryConditions += " OR ";
            }
        }
        PrivilegeKeyDao privilegeKeyDao = new PrivilegeKeyDao();
        privilegeKeyIds = new ArrayList<>();
        if (!queryConditions.isEmpty()) {
            for (PrivilegeKey key : (List<PrivilegeKey>) privilegeKeyDao.select(queryConditions)) {
                privilegeKeyIds.add(key.getId());
            }
        }

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

    public List<Long> getPrivilegeKeyIds() {
        return privilegeKeyIds;
    }

    public void setPrivilegeKeyIds(List<Long> privilegeKeyIds) {
        this.privilegeKeyIds = privilegeKeyIds;
    }
    
}
