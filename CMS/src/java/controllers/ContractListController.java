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
 * @author Macha
 */

@Controller
@RequestMapping("/contractList")
public class ContractListController extends BaseController{

    public ContractListController() {
        super("all","ManageContracts");
    }
    
    @RequestMapping("/contractList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "resourceManagment/contractList";
    }
    
    @RequestMapping(value = "/contractList/save/:object", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String object) {
        ContractDTO conDTO = (ContractDTO) Utils.convertJSONStringToObject(object, "object", ContractDTO.class);
        System.out.println(object);
        if (conDTO!=null) {
            Contract actualCon = new Contract();
            if(conDTO.getId()!=null) {
                actualCon.loadObject("id="+conDTO.getId());
            }else{
               Timestamp sqlDate = new Timestamp(new java.util.Date().getTime());
                actualCon.setDate(sqlDate.toString()); 
            }
            actualCon.setEmployeeId(conDTO.getEmployeeId());
            actualCon.setCustomerId(conDTO.getCustomerId());
            actualCon.setDescription(conDTO.getDescription());
            
            if(actualCon.getId()!=null) {
                actualCon.update();
            } else {
                actualCon.insert();
            }
        }
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
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        return new ResponseEntity<String>(Utils.convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/contractList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        ContractDTO conDTO = (ContractDTO) Utils.convertJSONStringToObject(object, "object", ContractDTO.class);
        if (conDTO != null) {
            ContractDao conDao = new ContractDao();
            
            conDao.deleteAllWithId(conDTO.getId()+"");
        }
    }
}
