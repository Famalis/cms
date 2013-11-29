/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.EmployeeDao;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
            actualUser.loadObject("id="+userDto.getId());
            actualUser.setEmployeeId(userDto.getEmployeeId());
            actualUser.update();
            UserConfiguration userConfig = new UserConfiguration();
            userConfig.loadObject("userId="+userDto.getId());
            userConfig.setGroupId(userDto.getGroupId());
            userConfig.setBackgroundColor(userDto.getBgcolor());
            userConfig.update();
        }
        
    }
    
    @RequestMapping(value = "/userList/users")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {   
        //UserConfigurationDao userConfigDao = new UserConfigurationDao();
        UserDao userDao = new UserDao();
        EmployeeDao empDao = new EmployeeDao();
        Map<String, Object> initData = new HashMap<>();
        initData.put("users", userDao.getUserWithConfig());
        initData.put("employees", empDao.getEmployeeDTOList());
        //List<UserDTO> userDtos = userDao.getUserWithConfig();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        return new ResponseEntity<String>(Utils.convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }
}
