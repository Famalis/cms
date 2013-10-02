/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.general;

import javax.inject.Inject;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Sergio
 */
public class BaseController {
    /**
     *Zalogowany użytkownik
     */
    @Inject
    protected User currentUser;
    
    public BaseController(){
        currentUser = new User();
    }
    
    
}
