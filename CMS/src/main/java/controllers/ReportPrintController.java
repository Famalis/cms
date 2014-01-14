/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.ContractDao;
import dao.CustomerDao;
import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.ReportDao;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SystemFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/reportPrint")
public class ReportPrintController extends BaseController {

    public ReportPrintController() {
        super("all", "ReportsPrint");
    }

    @RequestMapping("/reportPrint")
    public String home(HttpSession session, ModelMap model) {
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "resourceManagment/reportPrint";
    }

    @RequestMapping(value = "/reportPrint/reports")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<String, Object>();
        ReportDao reportDao = new ReportDao();
        EmployeeDao empDao = new EmployeeDao();
        ContractDao conDao = new ContractDao();
        CustomerDao cusDao = new CustomerDao();
        DepartmentDao deptDao = new DepartmentDao();
        //List<Report> reports = reportDao.select();
        initData.put("reports", reportDao.select());
        initData.put("employees", empDao.getEmployeeDTOList());
        initData.put("contract", conDao.getContractDTOList());
        initData.put("customers", cusDao.getCustomerDTOList());
        initData.put("departments", deptDao.getDepartmentDTOList());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        return new ResponseEntity<String>(Utils.convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/reportPrint/print")
    public @ResponseBody
    void download(@RequestParam("id") Long id, HttpServletResponse response) {
        SystemFile r = new SystemFile();
        r.loadObject("id=" + id);
        Utils.download(r.getHashCode(), r.getName(), r.getMimeType(), response);
    }
    
    @RequestMapping(value = "/reportPrint/form/{form}")
    public String testFormLoad(@PathVariable("form") String form) {        
        return "reportForms/"+form;
    }

}
