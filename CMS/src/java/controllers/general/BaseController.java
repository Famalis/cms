/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.general;

import dto.UserDTO;

/**
 *
 * @author Sergio
 */
public class BaseController {

    /**
     * Zalogowany użytkownik
     */
    protected UserDTO currentUserDto;
    protected String privileges;

    public BaseController(String privileges) {
        this.privileges = privileges;
        currentUserDto = new UserDTO();
    }

}
