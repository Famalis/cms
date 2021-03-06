package controllers;

import controllers.general.BaseController;
import dao.DepartmentDao;
import dao.EmployeeDao;
import dto.DepartmentDTO;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.Address;
import model.Department;
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
@RequestMapping("/departmentList")
public class DepartmentListController extends BaseController {

    public DepartmentListController() {
        super("all", "ViewDepartments");
    }

    @RequestMapping("/departmentList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "resourceManagment/departmentList";
    }

    @RequestMapping(value = "/departmentList/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        DepartmentDTO depDto = (DepartmentDTO) Utils.convertJSONStringToObject(object, "object", DepartmentDTO.class);
        System.out.println(object);
        if (depDto != null) {
            Department actualDep = new Department();
            if (depDto.getId() != null) {
                actualDep.loadObject("id=" + depDto.getId());
            }
            if (depDto.getManagerId() != null) {
                actualDep.setManagerId(depDto.getManagerId());
            } else {
                actualDep.setManagerId("-1");
            }
            actualDep.setName(depDto.getName());
            Address a = new Address();
            if (a.loadObject("id=" + depDto.getAddressId())) {
                a.setApartmentNumber(depDto.getApartmentNumber());
                a.setStreetName(depDto.getStreetName());
                a.setStreetNumber(depDto.getStreetNumber());
                a.setCity(depDto.getCity());
                a.setCountry(depDto.getCountry());
                a.update();
            } else {
                a.setApartmentNumber(depDto.getApartmentNumber());
                a.setStreetName(depDto.getStreetName());
                a.setStreetNumber(depDto.getStreetNumber());
                a.setCity(depDto.getCity());
                a.setCountry(depDto.getCountry());
                a.insert();
                System.out.println(a.getId() + "");
            }
            actualDep.setAddressId(a.getId() + "");
            if (depDto.getId() == null) {
                actualDep.insert();
                Map<String, Object> data = new HashMap<>();
                data.put("id", actualDep.getId());
                return Utils.createResponseEntity(session, data);
            } else {
                actualDep.update();
                Map<String, Object> data = new HashMap<>();
                data.put("id", actualDep.getId());
                return Utils.createResponseEntity(session, data);
            }

        }
        return null;

    }

    @RequestMapping(value = "/departmentList/deps")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<>();
        DepartmentDao dao = new DepartmentDao();
        EmployeeDao empDao = new EmployeeDao();
        initData.put("departments", dao.getDepartmentDTOList());
        initData.put("employees", empDao.select());
        return Utils.createResponseEntity(session, initData);
    }

    @RequestMapping(value = "/departmentList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete " + object);
        DepartmentDTO dto = (DepartmentDTO) Utils.convertJSONStringToObject(object, "object", DepartmentDTO.class);
        if (dto != null) {
            DepartmentDao depDao = new DepartmentDao();
            EmployeeDao empDao = new EmployeeDao();
            depDao.deleteAllWithId(dto.getId() + "");
            empDao.updateFieldForAllElementsWithId("departmentId", dto.getId() + "",
                    "departmentId", "-1");

        }

    }
}
