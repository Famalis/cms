/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/home")
public class TestingPageController extends BaseController {

    public TestingPageController() {
        super("");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(HttpSession session, HttpServletRequest request, ModelMap model) {
        
        return "testingPage";
    }    

}
