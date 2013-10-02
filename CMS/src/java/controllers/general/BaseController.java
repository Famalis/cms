/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.general;

import javax.inject.Inject;
import model.User;
import model.UserConfiguration;

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
    
    @Inject
    protected UserConfiguration userConfig;
    
    public BaseController(){
        currentUser = new User();
        userConfig = new UserConfiguration();
    }
    
    
}
