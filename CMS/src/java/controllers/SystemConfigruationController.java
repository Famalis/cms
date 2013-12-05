/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.general.BaseController;
import dao.SystemConfigurationDao;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/systemConfig")
public class SystemConfigruationController extends BaseController{
    
    public SystemConfigruationController() {
        super("all","SystemConfig");
    }
    
    @RequestMapping("/systemConfig")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "configuration/systemConfig";
    }
    
    @RequestMapping(value = "/systemConfig/configs")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<>();
        SystemConfigurationDao sysDao = new SystemConfigurationDao();
        initData.put("systemConfigs", sysDao.select());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        System.out.println("SEND: "+Utils.convertOMapToJSON(initData));
        return new ResponseEntity<String>(Utils.convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }
    
}
