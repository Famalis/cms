/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.EmployeeDao;
import dao.EmploymentDao;
import dto.EmployeeDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/employeePage")
public class EmployeePageController extends BaseController {

    public EmployeePageController() {
        super("all", "ViewEmployees");
    }

    @RequestMapping(value = "/employeePage/{id}")
    public String home(@PathVariable("id") Long id, HttpSession session, ModelMap model) {
        LOGGER.info(LOGGER.getName());
        LOGGER.info("Emp page");
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        Employee emp = new Employee();
        emp.loadObject("id=" + id);
        EmployeeDTO employeeDto = new EmployeeDTO(emp);
        EmploymentDao emplDao = new EmploymentDao();
        model.put("employee", employeeDto);
        model.put("employments", emplDao.getEmploymentDTOList());
        return "resourceManagment/employeePage";
    }

    @RequestMapping(value = "/employeePage/loadData/:id", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getData(@RequestBody String id, HttpSession session, ModelMap model) {
        LOGGER.info(LOGGER.getName());
        LOGGER.info("Get employee data");
        EmployeeDao empDao = new EmployeeDao();
        Integer iid = (Integer) Utils.convertJSONStringToObject(id, "id", Integer.class);
        EmployeeDTO empDTO = empDao.getEmployeeDTOList("id", iid+"").get(0);
        EmploymentDao emplDao = new EmploymentDao();
        Map<String, List<String>> params = new HashMap();
        List<String> paramList = new ArrayList<>();
        paramList.add(empDTO.getId() + "");
        params.put("employeeId", paramList);

        Map<String, Object> initData = new HashMap<>();
        initData.put("employee", empDTO);
        initData.put("employments", emplDao.getEmploymentDTOList(params));
        return Utils.createResponseEntity(session, initData);
    }

}
