/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.ArticleDao;
import java.util.List;
import model.ArticlePage;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{
    
    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) {
        ArticleDao articleDao = new ArticleDao();
        List<ArticlePage> articles = articleDao.select();
        User u = new User();
        u.loadObject(articles.get(articles.size()-1).getUserId());
        model.put("lastUser", u);
        model.put("lastArticle", articles.get(articles.size()-1));
        
        return "home";
    }
    
    @RequestMapping(value = "/home/{id}", method = RequestMethod.GET, params = "id")
    public String customHome(ModelMap model, @RequestParam String id) {
        System.out.println("load Page");
        return "home";
    }    
    
}
