package controllers;

import controllers.general.BaseController;
import dao.ContractDao;
import dto.CustomerDTO;
import javax.servlet.http.HttpSession;
import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Macha
 */
@Controller
@RequestMapping("/customerPage")
public class CustomerPageController extends BaseController {

    public CustomerPageController() {
        super("all", "ViewCustomers");
    }

    @RequestMapping(value = "/customerPage/{id}")
    public String home(@PathVariable("id") Long id, HttpSession session, ModelMap model) {
        System.out.println("customer page");
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        Customer cus = new Customer();
        cus.loadObject("id="+id);
        CustomerDTO customerDto = new CustomerDTO(cus);
        model.put("customer", customerDto);
        ContractDao conDao = new ContractDao();
        
        model.put("contracts", conDao.getContractsByCustomer(""+id));
        
        return "resourceManagment/customerPage";
    }
}
