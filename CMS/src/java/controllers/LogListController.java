package controllers;

import controllers.general.BaseController;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Macha
 */
@Controller
@RequestMapping("/logList")
public class LogListController extends BaseController{
    
    public LogListController(){
        super("all","ManageLogs");
    }
    @RequestMapping("/logList")
    public String home(HttpSession session, ModelMap model) {
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "resourceManagment/logList";
    } 
    
    @RequestMapping(value = "/logList/save/:dep", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String user) {
        //TODO
        
    }
    
    @RequestMapping(value = "/logList/logs")
    public @ResponseBody String getData() {       
        //TODO
        return null;
    }
}
