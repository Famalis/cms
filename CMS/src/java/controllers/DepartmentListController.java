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
@RequestMapping("/departmentList")
public class DepartmentListController extends BaseController {

    public DepartmentListController() {
        super("all", "ManageDepartments");
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
    void saveData(@RequestBody String object) {
        DepartmentDTO depDto = (DepartmentDTO) Utils.convertJSONStringToObject(object, "object", DepartmentDTO.class);
        System.out.println(object);
        //System.out.println(depDto.getId());
        if (depDto != null) {
            Department actualDep = new Department();
            System.out.println(depDto.getId() == null);
            if (depDto.getId() != null) {
                actualDep.loadObject("id=" + depDto.getId());
            }
            actualDep.setManagerId(depDto.getManagerId());
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
            actualDep.setAddressId(a.getId()+"");
            if (depDto.getId() == null) {
                actualDep.insert();
            } else {
                actualDep.update();
            }

        }

    }

    @RequestMapping(value = "/departmentList/deps")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<>();
        DepartmentDao dao = new DepartmentDao();
        EmployeeDao empDao = new EmployeeDao();
        initData.put("departmnets", dao.getDepartmentDTOList());
        initData.put("employees", empDao.select());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        return new ResponseEntity<String>(Utils.convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/departmentList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete "+object);
        DepartmentDTO dto = (DepartmentDTO) Utils.convertJSONStringToObject(object, "object", DepartmentDTO.class);
        if (dto != null) {
            DepartmentDao depDao = new DepartmentDao();
            EmployeeDao empDao = new EmployeeDao();
            depDao.deleteAllWithId(dto.getId()+"");
            empDao.updateFieldForAllElementsWithId("departmentId", dto.getId()+"", 
                    "departmentId", "-1");

        }

    }
}
