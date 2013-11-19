/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/reportList")
public class ReportListController extends BaseController{
    
    public ReportListController() {
        super("all","ManageReports");
    }
    
    @RequestMapping("/reportList")
    public String home(HttpSession session, ModelMap model) {
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "configuration/reportList";
    } 
    
    @RequestMapping(value = "/reportList/save/:report", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String user) {
        //TODO
        
    }
    
    @RequestMapping(value = "/reportList/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getName());
        return "configuration/reportList";        
    }
    
    @RequestMapping(value = "/reportList/reports")
    public @ResponseBody String getData() {       
        //TODO
        return null;
    }
}
