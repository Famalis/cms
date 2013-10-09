/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import javax.servlet.http.HttpSession;
import model.ArticlePage;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
public class ArticleController extends BaseController{
    
    @RequestMapping("/articleCreation")
    public String home(ModelMap model) {
        
        return "articleCreation";
    }
    
    @RequestMapping(value = "article/save/:article.htm", method = RequestMethod.POST, produces = "boolean")
    public @ResponseBody void createArticle(HttpSession session, @RequestBody String article) {
        User u = (User) session.getAttribute("user");
        System.out.println(article);
        ArticlePage art = (ArticlePage) Utils.convertJSONStringToObject(article, "article", ArticlePage.class);
        ArticlePage actualArt = new ArticlePage();
        actualArt.setText(art.getText());
        actualArt.setTitle(art.getTitle());
        actualArt.setUserId(u.getId()+"");
        actualArt.insert();
    }
    
    @RequestMapping(value = "/article/{id}")
    public String showArticle(ModelMap model, @PathVariable(value="id") String id) {
        ArticlePage art = new ArticlePage();
        art.loadObject("id="+id);
        model.put("title", art.getTitle());
        model.put("text", art.getText());
        return "articleTemplate";
    }
    
}
