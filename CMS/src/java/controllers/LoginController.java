/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dto.UserDTO;
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
@SessionAttributes({"user"})
public class LoginController extends BaseController {

    public LoginController() {
        super("");
    }

    @RequestMapping
    public String home(ModelMap model) {

        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpSession session, ModelMap model, @RequestParam("login") String login, @RequestParam("password") String password) {

        User user = new User();
        UserConfiguration userConfig = new UserConfiguration();
        if (user.loadObject("login='" + login + "' AND password='" + password + "'")) {
            //userConfig.setUserId(currentUser.getId());
            userConfig.loadObject("userId=" + user.getId());

            model.put("helloUser", "Witaj " + user.getName() + "!");
        } else {
            model.put("helloUser", "Złe dane logowania");
        }

        this.currentUserDto = new UserDTO(user, userConfig);
        model.put("user", this.currentUserDto);
        return "login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(ModelMap model) {

        currentUserDto = new UserDTO();
        model.put("user", this.currentUserDto);
        return "logout";

    }

    @RequestMapping(value = "/bgcolor", method = RequestMethod.GET)
    public String bgcolorChange(ModelMap model, @RequestParam("color") String color) {

        //currentUser = new User();
        currentUserDto.setBgcolor(color);
        UserConfiguration config = new UserConfiguration();
        config.loadObject("userId=" + currentUserDto.getId());
        config.setBackgroundColor(color);
        config.update();
        model.put("userConfig", this.currentUserDto);
        return "login";

    }
}
