/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import javax.servlet.http.HttpSession;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/login")
@SessionAttributes({"user"})
public class LoginController extends BaseController{
    
    @RequestMapping
    public String home(ModelMap model) {
        
        model.put("user", this.currentUser);
        return "login";
    } 
    
    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpSession session, ModelMap model, @RequestParam("login") String login, @RequestParam("password") String password) {
        
        currentUser = new User();
        if(currentUser.loadObject("login='"+login+"' AND password='"+password+"'")) {
            model.put("helloUser", "Witaj "+currentUser.getName()+"!");
        } else {
            model.put("helloUser", "Złe dane logowania");
        }
        
        
        model.put("user", this.currentUser);
        return "login";
        
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(ModelMap model) {
        
        currentUser = new User();
        model.put("user", this.currentUser);
        return "logout";
        
    }
}
