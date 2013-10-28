/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Sergio
 */
public class PositionListController extends BaseController{
    
    public PositionListController() {
        super("");
    }
    
    @RequestMapping("/positiontList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        return "departmentList";
    } 
    
    @RequestMapping(value = "/position/save/:position", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String user) {
        //TODO
        
    }
    
    @RequestMapping(value = "/position/positions")
    public @ResponseBody String getData() {       
        //TODO
        return null;
    }
}
