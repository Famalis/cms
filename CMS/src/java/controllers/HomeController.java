/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{
    
    public HomeController() {
        super("");
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) {        
        return "home";
    }
    
    @RequestMapping(value = "/home/{id}", method = RequestMethod.GET, params = "id")
    public String customHome(ModelMap model, @RequestParam String id) {
        System.out.println("load Page");
        return "home";
    }    
    
}
