/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.SystemConfigurationDao;
import dto.UserDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SystemConfiguration;
import model.SystemFile;
import model.User;
import model.UserConfiguration;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import utils.HexConverter;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/login")
@SessionAttributes({"user"})
public class LoginController extends BaseController {

    public LoginController() {
        super("");
    }

    @RequestMapping
    public String home(ModelMap model) {

        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpSession session, ModelMap model, HttpServletRequest request, HttpServletResponse response,
            @RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("stayLogged") boolean stayLogged) {

        System.out.println(stayLogged);
        User user = new User();
        UserConfiguration userConfig = new UserConfiguration();
        if (user.loadObject("login='" + login + "' AND password='" + password + "'")) {
            //userConfig.setUserId(currentUser.getId());
            userConfig.loadObject("userId=" + user.getId());

            model.put("helloUser", "Witaj " + user.getName() + "!");
        } else {
            model.put("helloUser", "Złe dane logowania");
        }

        this.currentUserDto = new UserDTO(user, userConfig);
        session.setAttribute("user", currentUserDto);
        if (stayLogged) {
            System.out.println("ADDED COOKIE: " + Utils.convertObjectToJSON(currentUserDto));
            Cookie c = new Cookie("user", Utils.convertObjectToJSON(currentUserDto));
            SystemConfigurationDao sysConfigDao = new SystemConfigurationDao();
            SystemConfiguration sc = sysConfigDao.findByField("name", "LoginPersistanceTime").get(0);
            int duration = Integer.parseInt(sc.getValue());
            c.setMaxAge(duration);
            response.addCookie(c);
            //model.put("user", this.currentUserDto);
        }
        return "login";

    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("logoout");
        Cookie nullUser = new Cookie("user", null);
        nullUser.setMaxAge(0);
        response.addCookie(nullUser);
        currentUserDto = new UserDTO();
        session.setAttribute("user", currentUserDto);
        return "home";

    }

    @RequestMapping(value = "/bgcolor", method = RequestMethod.GET)
    public String bgcolorChange(ModelMap model, @RequestParam("color") String color) {

        //currentUser = new User();
        currentUserDto.setBgcolor(color);
        UserConfiguration config = new UserConfiguration();
        config.loadObject("userId=" + currentUserDto.getId());
        config.setBackgroundColor(color);
        config.update();
        model.put("userConfig", this.currentUserDto);
        return "login";

    }

    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    public String uploadPhoto(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            FileItem item = items.get(0);
            if (item.getSize() == 0) {
                model.put("error", "Nie wybrano żadnego pliku");
                return "redirect:/login.htm";
                //System.out.println("no file");
            }
            if (!item.getContentType().equals("image/jpeg")) {
                model.put("error", "Plik musi posiadać rozszerzenie jpg");
                return "redirect:/login.htm";
            }
            SystemFile r = new SystemFile();
            UserDTO user = (UserDTO) request.getSession().getAttribute("user");
            System.out.println(items.get(0).getContentType() + " " + item.getSize());
            if (r.loadObject("name='" + user.getName() + "Photo'")) {

            } else {
                r.setName(user.getName() + "Photo");
                r.setMimeType(item.getContentType());
                r.setDescription("Zdjęcie użytkownika "+user.getLogin());
            }
            InputStream input = item.getInputStream();
            byte[] barr = new byte[(int) item.getSize()];
            input.read(barr);
            String s = HexConverter.toHexFromBytes(barr);
            r.setHashCode(s);
            if(r.getId()!=null) {
                r.update();
            } else {
                r.insert();
            }
        } catch (FileUploadException ex) {
            System.err.println("Can't process file\n" + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return "redirect:/login.htm";

    }
    
    @RequestMapping(value = "/getPhoto")
    public String getPhoto(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("get Photo");
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        SystemFile photo = new SystemFile();
        if(photo.loadObject("name='"+user.getName()+"Photo'")) {
            try {
                File photoFile = new File("userPhoto.jpg");
                FileOutputStream fos = new FileOutputStream(photoFile);
                fos.write(HexConverter.toBytesFromHex(photo.getHashCode()));
                fos.close();
                model.put("photo", photoFile.getAbsolutePath());
            } catch (IOException io) {
                io.printStackTrace();
            }
            
        }
        return "userPicture";

    }
}
