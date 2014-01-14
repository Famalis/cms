/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.SystemConfigurationDao;
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
        final SystemConfigurationDao sysDao = new SystemConfigurationDao();
        String to = sysDao.getAccountRequestEmail();
        String from = sysDao.getAccountRequestEmail();
        String host = sysDao.getAccountRequestSMTP();
        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", true); // added this line
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", sysDao.getAccountRequestLogin());
        props.put("mail.smtp.password", sysDao.getAccountRequestPassword());
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);
        props.setProperty("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sysDao.getAccountRequestLogin(), sysDao.getAccountRequestPassword());
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Prosba o konto: "+login);
            String msgText = "<table><tr><th>Dane konta:</th><th></th></tr><tr>";
            msgText += "<td>Login: "+login+"</td></tr><tr>";
            msgText += "<td>Imię i nazwisko: "+name+" "+surname+"</td></tr><tr>";
            msgText += "<td>Hasło: "+password+"</td></tr><tr>";
            msgText += "<td>Mail: "+email+"</td></tr>";
            msgText += "</table><br/>"
                    + "<form action='http://localhost:8080/CMS/userList/createAccountFromMail.htm' method='POST'>"
                    + "<input type='text' value='"+login+"' name='login'</input>"
                    + "<input type='text' value='"+name+"' name='name'</input>"
                    + "<input type='text' value='"+surname+"' name='surname'</input>"
                    + "<input type='text' value='"+password+"' name='password'</input>"
                    + "<input type='text' value='"+email+"' name='email'</input>"
                    + "<input type='submit'/>"
                    + "</form>";
            message.setContent(msgText, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Sent message successfully....");
            model.put("mailSent", true);
        } catch (MessagingException mex) {
            model.put("mailSent", false);
            model.put("error", "Błąd podczas wysyałania maila");
            System.err.println(mex);
        }
        return "home";
    }

}
