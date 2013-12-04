/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.general;

import dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sergio
 */
public class BaseController {

    /**
     * Zalogowany użytkownik
     */
    protected UserDTO currentUserDto;
    protected List<String> privileges;

    /**
     *
     * @param privileges
     */
    public BaseController(String... privileges) {
        this.privileges = new ArrayList<>();
        for (String priv : privileges) {
            this.privileges.add(priv);
        }
        currentUserDto = new UserDTO();
    }

    /**
     * Metoda sprawdza czy użytkownik zalogowany w podanej sesji posiada
     * uprawnienia wymagane do otwarcia strony (co najmniej jedno)
     *
     * @param session
     * @return
     */
    protected boolean checkPrivileges(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        for (String priv : privileges) {
            if (user != null) {
                if (user.getPrivilegeKeyCodes().contains(priv)) {
                    return true;
                }
            }
        }
        return false;
    }

}
