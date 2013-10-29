/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.general;

import dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/base")
public class BaseController {
    /**
     *Zalogowany użytkownik
     */
    protected UserDTO currentUserDto;
    protected String privileges;
    
    public BaseController(String privileges){
        this.privileges = privileges;
        currentUserDto = new UserDTO();
    }    
    
}
