package controllers;

import controllers.general.BaseController;
import dao.EmployeeDao;
import dto.EmployeeDTO;
import javax.servlet.http.HttpSession;
import model.Address;
import model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;


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
            Address actualAdr = new Address();
            if(empDto.getId()!=null) {
                actualEmp.loadObject("id="+empDto.getId());
                actualAdr.loadObject("id="+actualEmp.getAddressId());
            }
            actualEmp.setName(empDto.getName());
            actualEmp.setSurname(empDto.getSurname());
            actualEmp.setPESEL(empDto.getPESEL());
            actualEmp.setSalary(empDto.getSalary());
            actualEmp.setPhone(empDto.getPhone());
            
            actualAdr.setCity(empDto.getCity());
            actualAdr.setCountry(empDto.getCountry());
            actualAdr.setStreetName(empDto.getStreetName());
            actualAdr.setStreetNumber(empDto.getStreetNumber());
            actualAdr.setApartmentNumber(empDto.getApartmentNumber());
            
            if(actualAdr.getId()!=null) {
                actualAdr.update();
            } else {
                actualAdr.insert();
                actualEmp.setAddressId(actualAdr.getLastId()+"");
            }
            //TODO wydział, stanowisko
            actualEmp.setDepartmentId(-1+"");
            actualEmp.setPositionId(-1+"");
            
            if(actualEmp.getId()!=null) {
                actualEmp.update();
            } else {
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
