/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import model.User;
import model.UserConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/newUser")
public class NewUserController extends BaseController {

    @RequestMapping
    public String createUser(ModelMap model,
            @RequestParam String login,
            @RequestParam String pass,
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String surname) {

        User u = new User();
        u.setEmail(email);
        u.setLogin(login);
        u.setName(name);
        u.setSurname(surname);
        u.setPassword(pass);
        if(u.insert()) {
            UserConfiguration uc = new UserConfiguration();
            u.loadObject("login='"+u.getLogin()+"'");
            uc.setUserId(u.getId()+"");
            uc.insert();
            uc.loadObject("userId="+u.getId());
        }
        model.put("user", this.currentUser);
        return "login";
    }
}
