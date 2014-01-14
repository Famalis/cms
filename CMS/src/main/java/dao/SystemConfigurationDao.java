/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.SystemConfiguration;

/**
 *
 * @author Sergio
 */
public class SystemConfigurationDao extends GenericDao<SystemConfiguration>{
    
    public SystemConfigurationDao() {
        super(SystemConfiguration.class);
    }
    
    public String getAccountRequestEmail() {
        return this.findByField("name", "AccountRequestEmail").get(0).getValue();
    }
    
    public String getAccountRequestPassword() {
        return this.findByField("name", "AccountRequestPassword").get(0).getValue();
    }
    
    public String getAccountRequestSMTP() {
        return this.findByField("name", "AccountRequestSMTP").get(0).getValue();
    }
    
    public String getAccountRequestLogin() {
        return this.findByField("name", "AccountRequestLogin").get(0).getValue();
    }
}
