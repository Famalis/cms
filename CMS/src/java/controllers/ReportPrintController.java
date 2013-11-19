/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import java.io.File;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/reportPrint")
public class ReportPrintController extends BaseController{
    
    public ReportPrintController() {
        super("all","PrintReports");
    }
    
    @RequestMapping("/reportPrint")
    public String home(HttpSession session, ModelMap model) {
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "resourceManagment/reportPrint";
    } 
    
    @RequestMapping(value = "/reportPrint/reports")
    public @ResponseBody String getData() {      
        return null;
    }
}
