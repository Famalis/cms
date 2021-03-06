package controllers;

import controllers.general.BaseController;
import dao.AddressDao;
import dao.ContractDao;
import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.EmploymentDao;
import dao.LogDao;
import dao.PositionDao;
import dao.TaskDao;
import dao.UserConfigurationDao;
import dao.UserDao;
import dto.AddressDTO;
import dto.EmployeeDTO;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.Address;
import model.Employee;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

@Controller
@RequestMapping("/employeeList")
public class EmployeeListController extends BaseController {

    public EmployeeListController() {
        super("all", "ViewEmployees");
    }

    @RequestMapping("/employeeList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "resourceManagment/employeeList";
    }

    @RequestMapping(value = "/employeeList/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        EmployeeDTO empDto = (EmployeeDTO) Utils.convertJSONStringToObject(object, "object", EmployeeDTO.class);
        System.out.println(object);
        if (empDto != null) {
            Employee actualEmp = new Employee();
            Address actualAdr = new Address();
            if (empDto.getId() != null) {
                actualEmp.loadObject("id=" + empDto.getId());
                actualAdr.loadObject("id=" + actualEmp.getAddressId());
            }
            actualEmp.setName(empDto.getName());
            actualEmp.setSurname(empDto.getSurname());
            actualEmp.setPESEL(empDto.getPESEL());
            actualEmp.setPhone(empDto.getPhone());
            actualEmp.setSalary(empDto.getSalary());
            actualEmp.setPhone(empDto.getPhone());
            if (empDto.getDepartmentId().length() > 0) {
                actualEmp.setDepartmentId(empDto.getDepartmentId());
            }
            if (empDto.getPositionId().length() > 0) {
                actualEmp.setPositionId(empDto.getPositionId());
            }
            
            AddressDTO addrDto = empDto.getMainAddress();
            actualAdr.setCity(addrDto.getCity());
            actualAdr.setCountry(addrDto.getCountry());
            actualAdr.setStreetName(addrDto.getStreetName());
            actualAdr.setStreetNumber(addrDto.getStreetNumber());
            actualAdr.setApartmentNumber(addrDto.getApartmentNumber());
            actualAdr.setPostalCode(addrDto.getPostalCode());

            if (actualAdr.getId() != null) {
                actualAdr.update();
            } else {
                actualAdr.insert();
                actualEmp.setAddressId(actualAdr.findLastId() + "");
            }
            if (actualEmp.getId() != null) {
                actualEmp.update();
                Map<String, Object> data = new HashMap<>();
                data.put("id", actualEmp.getId());
                return Utils.createResponseEntity(session, data);
            } else {
                actualEmp.insert();
                Map<String, Object> data = new HashMap<>();
                data.put("id", actualEmp.getId());
                return Utils.createResponseEntity(session, data);
            }
        }
        return null;
    }

    @RequestMapping(value = "/employeeList/emps")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        EmployeeDao empDao = new EmployeeDao();
        DepartmentDao depDao = new DepartmentDao();
        PositionDao posDao = new PositionDao();
        EmploymentDao emplDao = new EmploymentDao();
        AddressDao addressDao = new AddressDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("employees", empDao.getEmployeeDTOListWithEmploymentsAndAddresses());
        initData.put("departments", depDao.select());
        initData.put("positions", posDao.select());
        initData.put("employments", emplDao.getEmploymentDTOList());
        //initData.put("addresses", addressDao.getAddressDTOList());
        return Utils.createResponseEntity(session, initData);
    }

    @RequestMapping(value = "/employeeList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        EmployeeDTO dto = (EmployeeDTO) Utils.convertJSONStringToObject(object, "object", EmployeeDTO.class);
        if (dto != null) {
            EmployeeDao empDao = new EmployeeDao();
            Employee actualEmp = new Employee();
            actualEmp.loadObject("id=" + dto.getId());

            LogDao logDao = new LogDao();
            AddressDao adrDao = new AddressDao();
            DepartmentDao depDao = new DepartmentDao();
            ContractDao conDao = new ContractDao();

            empDao.deleteAllWithId(dto.getId() + "");
            logDao.deleteAllMatching("employeeId", dto.getId() + "");
            adrDao.deleteAllWithId(actualEmp.getAddressId() + "");
            depDao.updateFieldForAllElementsWithId("managerId", dto.getId() + "",
                    "managerId", null);
            conDao.updateFieldForAllElementsWithId("employeeId", dto.getId() + "", "employeeId", null);

            User user = new User();
            if (user.loadObject("employeeId=" + dto.getId())) {
                UserDao usDao = new UserDao();
                TaskDao tskDao = new TaskDao();
                UserConfigurationDao conf = new UserConfigurationDao();
                usDao.deleteAllWithId(user.getId() + "");
                tskDao.updateFieldForAllElementsWithId("userId", user.getId() + "", "userId", null);
                conf.deleteAllMatching("userId", user.getId() + "");
            }
        }

    }
}
