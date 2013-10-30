/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.PrivilegeKeyDao;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.PrivilegeKey;
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
        return "privilegeKeyList";
    }

    @RequestMapping(value = "/privilegeKeyList/save/:privKey", method = RequestMethod.POST)
    public @ResponseBody
    void saveData(@RequestBody String privKey) {
        PrivilegeKey privilegeKey = (PrivilegeKey) Utils.convertJSONStringToObject(privKey, "privKey", PrivilegeKey.class);
        if (privilegeKey != null) {
            if (privilegeKey.getId() == null) {
                privilegeKey.insert();
            } else {
                privilegeKey.update();
            }
        }


    }

    @RequestMapping(value = "/privilegeKeyList/privKeys")
    public @ResponseBody
    String getData() {
        //UserConfigurationDao userConfigDao = new UserConfigurationDao();
        PrivilegeKeyDao privilegeDao = new PrivilegeKeyDao();
        List<PrivilegeKey> pKeys = privilegeDao.select();
        return Utils.convertObjectListToJSON(pKeys);
    }

    @RequestMapping(value = "/privilegeKeyList/delete/:privKey", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String privKey) {
        PrivilegeKey privilegeKey = (PrivilegeKey) Utils.convertJSONStringToObject(privKey, "privKey", PrivilegeKey.class);
        if (privilegeKey != null) {
            privilegeKey.delete();
        }
    }
}
