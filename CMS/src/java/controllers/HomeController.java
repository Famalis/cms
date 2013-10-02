/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/home")
@SessionAttributes({"user"})
public class HomeController extends BaseController{
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model, @ModelAttribute User user) {
        if(user!=null){
            model.put("msg", "Zalogowany jako"+user.getLogin());
        }
        return "home";
    }
    
    @RequestMapping(value = "/home/{id}", method = RequestMethod.GET, params = "id")
    public String customHome(ModelMap model, @RequestParam String id) {
        System.out.println("load Page");
        return "home";
    }    
    
}
