/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import dao.SystemConfigurationDao;

/**
 *
 * @author sergio
 */
public class SystemConfigurationService {
    
    private Long idleTimeout;
    
    public SystemConfigurationService() {
        SystemConfigurationDao sysConfigDao = new SystemConfigurationDao();        
        idleTimeout = Long.parseLong(sysConfigDao.findByField("name", "IdleTimeout").get(0).getValue());
    }

    public Long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(Long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }
    
    
}
