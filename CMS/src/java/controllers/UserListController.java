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
import utils.JSONGenerator;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/userList")
public class UserListController extends BaseController{
    
    @RequestMapping
    public String home(HttpSession session, ModelMap model) {
        User u = (User)session.getAttribute("user");
        model.put("json", JSONGenerator.generate(u));
        
        return "userList";
    } 
}
