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

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/newUser")
public class NewUserController extends BaseController{
    
    @RequestMapping
    public String createUser(ModelMap model) {
        
        model.put("user", this.currentUser);
        return "newUser";
    }
}
