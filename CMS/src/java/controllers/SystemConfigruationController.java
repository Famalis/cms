/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.PrivilegeDao;
import dao.PrivilegeGroupDao;
import dao.SystemConfigurationDao;
import dao.UserConfigurationDao;
import dto.GroupDTO;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.SystemConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/systemConfig")
public class SystemConfigruationController extends BaseController {

    public SystemConfigruationController() {
        super("all", "SystemConfig");
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
        return Utils.createResponseEntity(session, initData);
    }

    @RequestMapping(value = "/systemConfig/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    void saveData(@RequestBody String object) {
        SystemConfiguration sc = (SystemConfiguration) Utils.convertJSONStringToObject(object, "object", SystemConfiguration.class);
        if (sc.getId() == null) {
            sc.insert();
        } else {
            sc.update();
        }
        //System.out.println(sc.getName());
    }

    @RequestMapping(value = "/systemConfig/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        SystemConfiguration sc = (SystemConfiguration) Utils.convertJSONStringToObject(object, "object", SystemConfiguration.class);
        if (sc.getId() != null) {
            sc.delete();
        }
    }
}
