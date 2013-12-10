/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.PrivilegeKeyDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.PrivilegeKey;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/privilegeKeyList")
public class PrivilegeKeyListController extends BaseController {

    public PrivilegeKeyListController() {
        super("all","ManagePrivilegeKeys");
    }

    @RequestMapping("/privilegeKeyList")
    public String home(HttpSession session, ModelMap model) {
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "configuration/privilegeKeyList";
    }

    @RequestMapping(value = "/privilegeKeyList/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    void saveData(@RequestBody String object) {
        PrivilegeKey privilegeKey = (PrivilegeKey) Utils.convertJSONStringToObject(object, "object", PrivilegeKey.class);
        if (privilegeKey != null) {
            if (privilegeKey.getId() == null) {
                privilegeKey.insert();
            } else {
                privilegeKey.update();
            }
        }


    }

    @RequestMapping(value = "/privilegeKeyList/privKeys")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        //UserConfigurationDao userConfigDao = new UserConfigurationDao();
        PrivilegeKeyDao privilegeDao = new PrivilegeKeyDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("privilegeKeys", privilegeDao.select());
        return Utils.createResponseEntity(session, initData);
    }

    @RequestMapping(value = "/privilegeKeyList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        PrivilegeKey privilegeKey = (PrivilegeKey) Utils.convertJSONStringToObject(object, "object", PrivilegeKey.class);
        if (privilegeKey != null) {
            privilegeKey.delete();
        }
    }
}
