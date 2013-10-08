/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.general.BaseController;
import dto.UserDTO;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.User;
import model.UserConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import dao.GenericDao;
import dao.UserConfigurationDao;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/userList")
public class UserListController extends BaseController{
    
    @RequestMapping
    public String home(HttpSession session, ModelMap model) {
        
        return "userList";
    } 
    
    @RequestMapping("/userList/users")
    public @ResponseBody String requestJsons(HttpSession session) {
        //System.out.println("requestJsons");
        UserConfigurationDao userConfigDao = new UserConfigurationDao();
        List<UserConfiguration> configs = userConfigDao.select();
        List<UserDTO> userDtos = new ArrayList<>();
        for (UserConfiguration uc : configs) {
            User u = new User();
            u.loadObject("id="+uc.getUserId());
            userDtos.add(new UserDTO(u, uc));
        }        
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data;
        try {
            mapper.writeValue(out, userDtos);
            data = out.toByteArray();
            System.out.println(new String(data));
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
