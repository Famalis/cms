/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.EmployeeDao;
import dto.EmployeeDTO;
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
        return "resourceManagment/employeeList";
    } 
    
    @RequestMapping(value = "/employeeList/save/:emp", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String emp) {
        EmployeeDTO empDto = (EmployeeDTO) Utils.convertJSONStringToObject(emp, "emp", EmployeeDTO.class);
        if (empDto!=null) {
            Employee actualEmp = new Employee();
            if(empDto.getId()!=null) {
                actualEmp.loadObject("id="+empDto.getId());
            }
            actualEmp.setName(empDto.getName());
            actualEmp.setSurname(empDto.getSurname());
            actualEmp.setPESEL(empDto.getPESEL());
            actualEmp.setSalary(empDto.getSalary());
            actualEmp.setPhone(empDto.getPhone());
            //TODO adres, wydział, stanowisko
            actualEmp.setAddressId(-1+"");
            actualEmp.setDepartmentId(-1+"");
            actualEmp.setPositionId(-1+"");
            if(actualEmp.getId()!=null) {
                actualEmp.update();
            } else {
                actualEmp.setId(empDto.getId());
                actualEmp.insert();
            }
        }
        
    }
    
    @RequestMapping(value = "/employeeList/emps")
    public @ResponseBody String getData() {       
        EmployeeDao empDao = new EmployeeDao();
        return Utils.convertObjectListToJSON(empDao.getEmployeeDTOList());
    }
}
