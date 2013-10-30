/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.EmployeeDao;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.Employee;
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
@RequestMapping("/employeeList")
public class EmployeeListController extends BaseController{
    
    public EmployeeListController() {
        super("all","ManageEmployees");
    }
    
    @RequestMapping("/employeeList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "employeeList";
    } 
    
    @RequestMapping(value = "/employeeList/save/:emp", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String user) {
        
        
    }
    
    @RequestMapping(value = "/employeeList/emps")
    public @ResponseBody String getData() {       
        EmployeeDao empDao = new EmployeeDao();
        return Utils.convertObjectListToJSON(empDao.getEmployeeDTOList());
    }
}
