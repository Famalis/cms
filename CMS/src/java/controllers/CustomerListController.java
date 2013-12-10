package controllers;

import controllers.general.BaseController;
import dao.AddressDao;
import dao.CompanyDao;
import dao.ContractDao;
import dao.CustomerDao;
import dto.CustomerDTO;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.Address;
import model.Company;
import model.Customer;
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
@RequestMapping("/customerList")
public class CustomerListController extends BaseController{

    public CustomerListController() {
        super("all","ManageCustomers");
    }
    
    @RequestMapping("/customerList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "resourceManagment/customerList";
    } 
    
    @RequestMapping(value = "/customerList/save/:object", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String object) {
        CustomerDTO cusDto = (CustomerDTO) Utils.convertJSONStringToObject(object, "object", CustomerDTO.class);
        System.out.println(object);
        if (cusDto!=null) {
            Customer actualCus = new Customer();
            Company actualComp = new Company();
            Address actualAdr = new Address();
            if(cusDto.getId()!=null) {
                actualCus.loadObject("id="+cusDto.getId());
                actualComp.loadObject("id="+actualCus.getCompanyId());
                actualAdr.loadObject("id="+actualComp.getAddressId());
            }
            actualCus.setName(cusDto.getName());
            actualCus.setSurname(cusDto.getSurname());
            actualCus.setPhone(cusDto.getPhone());    
            actualCus.setEmail(cusDto.getEmail());    
            
            actualComp.setName(cusDto.getCompanyName());
            
            actualAdr.setCity(cusDto.getCity());
            actualAdr.setCountry(cusDto.getCountry());
            actualAdr.setStreetName(cusDto.getStreetName());
            actualAdr.setStreetNumber(cusDto.getStreetNumber());
            actualAdr.setApartmentNumber(cusDto.getApartmentNumber());
            
            if(actualAdr.getId()!=null) {
                actualAdr.update();
            } else {
                actualAdr.insert();
                actualComp.setAddressId(actualAdr.findLastId()+"");
            }
            if(actualComp.getId()!=null) {
                actualComp.update();
            } else {
                actualComp.insert();
                actualCus.setCompanyId(actualComp.findLastId()+"");
            }
            if(actualCus.getId()!=null) {
                actualCus.update();
            } else {
                actualCus.insert();
            }
        }
        
    }
    
    @RequestMapping(value = "/customerList/customers")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {        
        CustomerDao cusDao = new CustomerDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("customers", cusDao.getCustomerDTOList());
        return Utils.createResponseEntity(session, initData);
    }
    
    @RequestMapping(value = "/customerList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        CustomerDTO cusDto = (CustomerDTO) Utils.convertJSONStringToObject(object, "object", CustomerDTO.class);
        if (cusDto != null) {
            CustomerDao cusDao = new CustomerDao();
            CompanyDao compDao = new CompanyDao();
            AddressDao adrDao = new AddressDao();
            ContractDao conDao = new ContractDao();
            
            Customer actualCus = new Customer();
            Company actualComp = new Company();
            actualCus.loadObject("id="+cusDto.getId());
            actualComp.loadObject("id="+actualCus.getCompanyId());
            
            conDao.deleteAllMatching("customerId", cusDto.getId()+"");
            adrDao.deleteAllWithId(actualComp.getAddressId());
            compDao.deleteAllWithId(actualCus.getCompanyId());
            cusDao.deleteAllWithId(cusDto.getId()+"");
        }
    }
}
