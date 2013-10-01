/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.Enumeration;
import model.HomePage;
import model.Saveable;
import model.User;
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
public class HomeController {
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) {
        Saveable.Load();
        Enumeration e = Saveable.getTable(HomePage.class).elements();
        ArrayList<Long> ids = new ArrayList<>();
        while(e.hasMoreElements()) {
            HomePage hp = (HomePage) e.nextElement();
            ids.add(hp.getId());
        }
        model.put("list", ids);
        return "home";
    }
    
    @RequestMapping(value = "/home/{id}", method = RequestMethod.GET, params = "id")
    public String customHome(ModelMap model, @RequestParam String id) {
        System.out.println("load Page");
        HomePage hp = (HomePage) Saveable.loadObjecT(HomePage.class, 1L);
        model.put("content", hp.getContent());
        return "home";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String login(ModelMap model, @RequestParam("login") String login, @RequestParam("password") String password) {
        
        User u = new User();
        if(u.loadObject("login='"+login+"' AND password='"+password+"'")) {
            model.put("helloUser", "Witaj "+u.getName()+"!");
        } else {
            model.put("helloUser", "Złe dane logowania");
        }
        
        return "home";
        
    }
}
