/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PrivilegeGroup;
import model.User;

/**
 *
 * @author Sergio
 */
public class UserDao extends GenericDao<User> {

    
    public List<UserDTO> getUserWithConfig() {
        return getUserWithConfig("");
    }
    public List<UserDTO> getUserWithConfig(String conditions) {
        List<UserDTO> dtos = new ArrayList<>();
        if(conditions.length()>0) {
            conditions+=" AND "; 
        }
        String query = "SELECT user.id as id, emp.name as name, emp.surname as surname, "
                + "user.login as login, user.password as password, user.employeeId as employeeId, "
                + "user_configuration.groupId as groupId FROM user, user_configuration, employee as emp "
                + "WHERE " + conditions + "user.id = user_configuration.userId AND user.employeeId = emp.id";
        ResultSet resultSet = this.connectionManager.select(query);
        PrivilegeGroupDao privGroupDao = new PrivilegeGroupDao();
        List<PrivilegeGroup> groups = privGroupDao.select();
        try {
            while (resultSet.next()) {
                UserDTO dto = new UserDTO();
                dto.setName(resultSet.getString("name"));
                dto.setSurname(resultSet.getString("surname"));
                dto.setLogin(resultSet.getString("login"));
                dto.setId(resultSet.getLong("id"));
                dto.setGroupId(resultSet.getString("groupId"));
                dto.setEmployeeId(resultSet.getString("employeeId"));
                if(dto.getGroupId()!=null){
                    for (PrivilegeGroup g : groups) {
                        if(g.getId() == Long.parseLong(dto.getGroupId())) {
                            dto.setGroupName(g.getName());
                        }
                    }
                }
                dtos.add(dto);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return dtos;
    }

    public UserDao() {
        super(User.class);
    }
}
