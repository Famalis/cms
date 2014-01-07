/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reportTemplate;

import controllers.reportTemplate.general.BaseTemplateController;
import dao.ContractDao;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Contract;
import model.Customer;
import model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/contractReportTemplate")
public class ContractReportTemplateController extends BaseTemplateController {

    public ContractReportTemplateController() {
        super();
    }

    @RequestMapping("/contractReportTemplate")
    public void home(HttpSession session,
            HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException{
        if (!this.checkPrivileges(session)) {
            return;
        }
        Map<String, String[]> inputParams = request.getParameterMap();
        Map<String, String> params = new HashMap<>();
        
        ContractDao contractDao = new ContractDao();
        Contract c;
        

        if (inputParams.get("contractNumber") != null) {
            String num = inputParams.get("contractNumber")[0];
            c = contractDao.findById(num).get(0);
            params.put("${contractNumber}", num);
            Employee emp = new Employee();
            emp.loadObject("id="+c.getEmployeeId());
            Customer cus = new Customer();
            cus.loadObject("id="+c.getCustomerId());
            params.put("${empName}", emp.getName());
            params.put("${empSurname}", emp.getSurname());
            params.put("${cusName}", cus.getName());
            params.put("${cusSurname}", cus.getSurname());
            params.put("${price}", c.getPrice());
            params.put("${desc}", c.getDescription());
        }
        
        generatePdf(response, request, params, "/WEB-INF/templates/contractReportTemplate.html");        
    }

}
