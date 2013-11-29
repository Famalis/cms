/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/privilegeList")
public class PrivilegeListController extends BaseController{
    
    public PrivilegeListController() {
        super("");
    }
    
    @RequestMapping("/privilegeList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        return "configuration/privilegeList";
    } 
    
    @RequestMapping(value = "/privilegeList/save/:priv", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String user) {
        //TODO
        
    }
    
    @RequestMapping(value = "/privilegeList/privs")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {  
        //TODO
        //HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        //return new ResponseEntity<String>(Utils.convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
        return null;
    }
}
