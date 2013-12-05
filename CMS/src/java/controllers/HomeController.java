/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.UserDao;
import dto.UserDTO;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

    public HomeController() {
        super("");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(HttpSession session, HttpServletRequest request, ModelMap model) {        
        UserDTO user = (UserDTO) session.getAttribute("user");
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie c : cookies) {
                System.out.println(c.getName());
                if(c.getName().equals("user")){                    
                    String json = c.getValue();
                    System.out.println("COOKIE VALUE: "+json);
                    user = (UserDTO) Utils.convertJSONStringToObject(json, UserDTO.class);
                }
            }
        }
        //System.out.println(user.getId()+"");
        if (user != null) {
            if (user.getId() != null) {
                this.currentUserDto = user;
                session.setAttribute("user", user);
                //model.put("user", user);
                return "login";
            } else {
                return "home";
            }
        } else {
            return "home";
        }
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String sendEmail(ModelMap model,
        @RequestParam("login") String login,
        @RequestParam("password") String password,
        @RequestParam("name") String name,
        @RequestParam("surname") String surname,
        @RequestParam("email") String email) {
        System.out.println("Trying to send email");
        UserDao userDao = new UserDao();
        if(!userDao.findByField("login", login).isEmpty()) {
            model.put("error", "Dany login jest już zajęty");
            return "home";
        }
        String to = "accreqehr@gmail.com";
        String from = "accreqehr@gmail.com";
        String host = "smtp.gmail.com";
        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", true); // added this line
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "accreqehr");
        props.put("mail.smtp.password", "easyhr12345");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);
        props.setProperty("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("accreqehr", "easyhr12345");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Prosba o konto: "+login);
            String msgText = "Dane konta:\n";
            msgText += "Login: "+login;
            msgText += "\nImię i nazwisko: "+name+" "+surname;
            msgText += "\nHasło: "+password;
            msgText += "\nMail: "+email;
            message.setText(msgText);
            Transport.send(message);
            System.out.println("Sent message successfully....");
            model.put("mailSent", true);
        } catch (MessagingException mex) {
            model.put("mailSent", false);
            model.put("error", "Błąd podczas wysyałania maila");
        }
        return "home";
    }

}
