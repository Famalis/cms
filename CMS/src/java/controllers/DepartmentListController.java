/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.DepartmentDao;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/departmentList")
public class DepartmentListController extends BaseController{
    
    public DepartmentListController() {
        super("all","ManageDepartments");
    }
    
    @RequestMapping("/departmentList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "resourceManagment/departmentList";
    } 
    
    @RequestMapping(value = "/department/save/:dep", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String user) {
        //TODO
        
    }
    
    @RequestMapping(value = "/departmentList/deps")
    public @ResponseBody String getData() {       
        DepartmentDao dao = new DepartmentDao();
        return Utils.convertObjectListToJSON(dao.getDepartmentDTOList());
    }
}
