/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.UserDao;
import dto.UserDTO;
import model.User;
import model.UserConfiguration;
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
@RequestMapping("/newUser")
public class NewUserController extends BaseController {

    public NewUserController() {
        super("");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) {

        return "newUser";
    }

    @RequestMapping
    public String createUser(ModelMap model,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String surname) {

        //GenericDao genericDao = new GenericDao(User.class);
        UserDao userDao = new UserDao();
        if (userDao.select("login='" + login + "'").size() > 0) {
            model.put("error", "Podany login został już użyty");
            return "newUser";
        }
        User u = new User();
        u.setEmail(email);
        u.setLogin(login);
        u.setPassword(password);
        if (u.insert()>-1) {
            UserConfiguration uc = new UserConfiguration();
            u.loadObject("login='" + u.getLogin() + "'");
            uc.setUserId(u.getId() + "");
            uc.setGroupId("2");
            uc.insert();
            uc.loadObject("userId=" + u.getId());
            this.currentUserDto = new UserDTO(u, uc);
        }
        model.put("user", this.currentUserDto);
        return "login";
    }

}
