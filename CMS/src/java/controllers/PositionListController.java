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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/positionList")
public class PositionListController extends BaseController{
    
    public PositionListController() {
        super("all","ManagePositions");
    }
    
    @RequestMapping("/positionList")
    public String home(HttpSession session, ModelMap model) {
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "resourceManagment/positionList";
    } 
    
    @RequestMapping(value = "/positionList/save/:position", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String user) {
        //TODO
        
    }
    
    @RequestMapping(value = "/positionList/positions")
    public @ResponseBody String getData() {       
        //TODO
        return null;
    }
}
