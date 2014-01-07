/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.EmployeeDao;
import dao.PrivilegeGroupDao;
import dto.UserDTO;
import javax.servlet.http.HttpSession;
import model.UserConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import dao.UserDao;
import java.util.HashMap;
import java.util.Map;
import model.User;
import org.springframework.http.ResponseEntity;
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
        super("all", "ManageUsers");
    }
    
    @RequestMapping("/userList")
    public String home(HttpSession session, ModelMap model) {
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "configuration/userList";
    } 
    
    @RequestMapping(value = "/userList/save/:object", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String object) {
        UserDTO userDto = (UserDTO)Utils.convertJSONStringToObject(object, "object", UserDTO.class);
        if(userDto!=null) {
            System.out.println(userDto.getId()+" "+userDto.getName());
            User actualUser = new User();            
            UserConfiguration userConfig = new UserConfiguration();
                        
            //actualUser.loadObject("id="+userDto.getId());
            
            if(userDto.getId() != null) {
                actualUser.loadObject("id="+userDto.getId());
                userConfig.loadObject("userId="+userDto.getId());
            }
            if(userDto.getLogin().length()<=0 || userDto.getPassword().length()<=0) {
                throw new NullPointerException("Brak hasła albo loginu");
            }
            actualUser.setPassword(userDto.getPassword());
            actualUser.setLogin(userDto.getLogin());            
            actualUser.setEmployeeId(userDto.getEmployeeId());
            
            userConfig.setGroupId(userDto.getGroupId());            
            
            if(userDto.getId() != null) {
                actualUser.update();
                userConfig.setUserId(actualUser.getId()+"");
                userConfig.update();
            } else {
                actualUser.insert();
                userConfig.setUserId(actualUser.getId()+"");
                userConfig.insert();
            }
        }
        
    }
    
    @RequestMapping(value = "/userList/users")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {   
        //UserConfigurationDao userConfigDao = new UserConfigurationDao();
        UserDao userDao = new UserDao();
        EmployeeDao empDao = new EmployeeDao();
        PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
        Map<String, Object> initData = new HashMap<>();
        initData.put("users", userDao.getUserWithConfig());
        initData.put("employees", empDao.getEmployeeDTOList());
        initData.put("groups", groupDao.select());
        //List<UserDTO> userDtos = userDao.getUserWithConfig();
        return Utils.createResponseEntity(session, initData);
    }
}
