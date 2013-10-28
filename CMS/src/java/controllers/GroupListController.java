/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.UserDao;
import dto.UserDTO;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.UserConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/groupList")
public class GroupListController extends BaseController{
    
    public GroupListController() {
        super("");
    }
    
    @RequestMapping("/groupList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        return "groupList";
    } 
    
    @RequestMapping(value = "/groupList/save/:group", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String user) {
        //TODO
        
    }
    
    @RequestMapping(value = "/groupList/groups")
    public @ResponseBody String getData() {       
        //TODO
        return null;
    }
}
