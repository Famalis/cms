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
import dao.UserConfigurationDao;
import dao.UserDao;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/userList")
public class UserListController extends BaseController{
    
    public UserListController() {
        super("");
    }
    
    @RequestMapping("/userList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        return "userList";
    } 
    
    @RequestMapping(value = "/userList/save/:user", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String user) {
        UserDTO userDto = (UserDTO)Utils.convertJSONStringToObject(user, "user", UserDTO.class);
        if(userDto!=null) {
            System.out.println(userDto.getId()+" "+userDto.getName());
            //User actualUser = new User();
            //actualUser.loadObject("id="+userDto.getId());
            UserConfiguration userConfig = new UserConfiguration();
            userConfig.loadObject("userId="+userDto.getId());
            userConfig.setGroupId(userDto.getGroupId());
            userConfig.setBackgroundColor(userDto.getBgcolor());
            userConfig.update();
        }
        
    }
    
    @RequestMapping(value = "/userList/users")
    public @ResponseBody String getData() {        
        //UserConfigurationDao userConfigDao = new UserConfigurationDao();
        UserDao userDao = new UserDao();
        List<UserDTO> userDtos = userDao.getUserWithConfig();
        return Utils.convertObjectListToJSON(userDtos);
    }
}
