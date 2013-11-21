/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dto.UserDTO;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

    public HomeController() {
        super("");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        //System.out.println(user.getId()+"");
        if (user != null) {
            if (user.getId() != null) {
                return "login";
            } else {
                return "home";
            }
        } else {
            return "home";
        }
    }

}
