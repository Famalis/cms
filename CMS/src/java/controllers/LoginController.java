/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import javax.servlet.http.HttpSession;
import model.User;
import model.UserConfiguration;
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
@SessionAttributes({"user", "userConfig"})
public class LoginController extends BaseController{
    
    @RequestMapping
    public String home(ModelMap model) {
        
        return "login";
    } 
    
    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpSession session, ModelMap model, @RequestParam("login") String login, @RequestParam("password") String password) {
        
        currentUser = new User();
        userConfig = new UserConfiguration();
        if(currentUser.loadObject("login='"+login+"' AND password='"+password+"'")) {
            //userConfig.setUserId(currentUser.getId());
            userConfig.loadObject("userId="+currentUser.getId());
            model.put("helloUser", "Witaj "+currentUser.getName()+"!");
        } else {
            model.put("helloUser", "Złe dane logowania");
        }
        
        
        model.put("user", this.currentUser);
        model.put("userConfig", this.userConfig);
        return "login";
        
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(ModelMap model) {
        
        currentUser = new User();
        userConfig = new UserConfiguration();
        model.put("user", this.currentUser);
        model.put("userConfig", this.userConfig);
        return "logout";
        
    }
    
    @RequestMapping(value = "/bgcolor", method = RequestMethod.GET)
    public String bgcolorChange(ModelMap model, @RequestParam("color") String color) {
        
        //currentUser = new User();
        userConfig.setBackgroundColor(color);
        userConfig.update();
        model.put("userConfig", this.userConfig);
        return "login";
        
    }
}
