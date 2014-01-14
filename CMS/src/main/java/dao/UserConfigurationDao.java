/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.UserConfiguration;

/**
 *
 * @author Sergio
 */
public class UserConfigurationDao extends GenericDao<UserConfiguration>{
    
    public UserConfigurationDao() {
        super(UserConfiguration.class);
    }
}
