/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.general;

import model.Saveable;

/**
 *
 * @author Sergio
 */
public class WebPage extends Saveable{
    
    protected String content;
    
    public WebPage() {
        super();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
