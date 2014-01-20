/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.EmploymentDao;
import dto.EmployeeDTO;
import javax.servlet.http.HttpSession;
import model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/employeePage")
public class EmployeePageController extends BaseController {

    public EmployeePageController() {
        super("all", "ViewEmployees");
    }

    @RequestMapping(value = "/employeePage/{id}")
    public String home(@PathVariable("id") Long id, HttpSession session, ModelMap model) {
        System.out.println("emp page");
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        Employee emp = new Employee();
        emp.loadObject("id="+id);
        EmployeeDTO employeeDto = new EmployeeDTO(emp);
        EmploymentDao emplDao = new EmploymentDao();
        model.put("employee", employeeDto);
        model.put("employments", emplDao.getEmploymentDTOList());
        return "resourceManagment/employeePage";
    }

}
