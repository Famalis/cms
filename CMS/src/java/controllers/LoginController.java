/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dto.UserDTO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.UserConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import utils.Utils;

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
    public String login(HttpSession session, ModelMap model, HttpServletRequest request, HttpServletResponse response,
            @RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("stayLogged") boolean stayLogged) {

        System.out.println(stayLogged);
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
        session.setAttribute("user", currentUserDto);
        if (stayLogged) {
            System.out.println("ADDED COOKIE: " + Utils.convertObjectToJSON(currentUserDto));
            Cookie c = new Cookie("user", Utils.convertObjectToJSON(currentUserDto));
            c.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(c);
            //model.put("user", this.currentUserDto);
        }
        return "login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("logoout");
        Cookie nullUser = new Cookie("user", null);
        nullUser.setMaxAge(0);
        response.addCookie(nullUser);
        currentUserDto = new UserDTO();
        session.setAttribute("user", currentUserDto);
        return "home";

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

    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    public String uploadPhoto(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        //currentUser = new User();
        currentUserDto.setBgcolor("");
        UserConfiguration config = new UserConfiguration();
        config.loadObject("userId=" + currentUserDto.getId());
        config.setBackgroundColor("");
        config.update();
        model.put("userConfig", this.currentUserDto);
        return "login";

    }
}
