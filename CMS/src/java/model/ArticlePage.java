/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.general.DatabaseObject;

/**
 *
 * @author Sergio
 */
public class ArticlePage extends DatabaseObject{
    
    private String text,title,userId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
        
    public ArticlePage(){
        super("article_page");
    }
}
