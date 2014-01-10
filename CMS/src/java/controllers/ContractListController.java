package controllers;

import controllers.general.BaseController;
import dao.ContractDao;
import dao.CustomerDao;
import dao.EmployeeDao;
import dto.ContractDTO;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.Contract;
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
 * @author Macha
 */
@Controller
@RequestMapping("/contractList")
public class ContractListController extends BaseController {

    public ContractListController() {
        super("all", "ViewContracts");
    }

    @RequestMapping("/contractList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "resourceManagment/contractList";
    }

    @RequestMapping(value = "/contractList/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        ContractDTO conDTO = (ContractDTO) Utils.convertJSONStringToObject(object, "object", ContractDTO.class);
        System.out.println(object);
        if (conDTO != null) {
            Contract actualCon = new Contract();
            if (conDTO.getId() != null) {
                actualCon.loadObject("id=" + conDTO.getId());
            } else {
                Timestamp sqlDate = new Timestamp(new java.util.Date().getTime());
                actualCon.setDate(sqlDate.toString());
            }
            actualCon.setEmployeeId(conDTO.getEmployeeId());
            actualCon.setCustomerId(conDTO.getCustomerId());
            actualCon.setDescription(conDTO.getDescription());
            actualCon.setPrice(conDTO.getPrice());

            if (actualCon.getId() != null) {
                actualCon.update();
                Map<String, Object> data = new HashMap<>();
                data.put("id", actualCon.getId());
                return Utils.createResponseEntity(session, data);
            } else {
                actualCon.insert();
                Map<String, Object> data = new HashMap<>();
                data.put("id", actualCon.getId());
                return Utils.createResponseEntity(session, data);
            }
        }
        return null;
    }

    @RequestMapping(value = "/contractList/contracts")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        ContractDao conDao = new ContractDao();
        EmployeeDao empDao = new EmployeeDao();
        CustomerDao cusDao = new CustomerDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("contracts", conDao.getContractDTOList());
        initData.put("employees", empDao.select());
        initData.put("customers", cusDao.select());
        return Utils.createResponseEntity(session, initData);
    }

    @RequestMapping(value = "/contractList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        ContractDTO conDTO = (ContractDTO) Utils.convertJSONStringToObject(object, "object", ContractDTO.class);
        if (conDTO != null) {
            ContractDao conDao = new ContractDao();

            conDao.deleteAllWithId(conDTO.getId() + "");
        }
    }
}
