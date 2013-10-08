/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.general.BaseController;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.GenericDao;
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
        model.put("someVar", "this is some variable");
        
        return "userList";
    } 
    
    @RequestMapping("/userList/user")
    public @ResponseBody String requestJson(HttpSession session) {
        System.out.println("requestJson");
        User u = (User)session.getAttribute("user");
        System.out.println(JSONGenerator.generate(u));
        return JSONGenerator.generate(u).toString();
    }
    
    @RequestMapping("/userList/users")
    public @ResponseBody String requestJsons(HttpSession session) {
        System.out.println("requestJsons");
        genericDao = new GenericDao(User.class);
        List<User> jsons = new ArrayList<User>();
        for (Object userObj : genericDao.select()) {
            User u = (User) userObj;
            jsons.add(u);
        }
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data;
        try {
            mapper.writeValue(out, jsons);
            data = out.toByteArray();
            System.out.println(new String(data));
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
