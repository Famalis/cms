/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.general;

/**
 *
 * @author Sergio
 */
public class WebPage extends DatabaseObject{
    
    protected String content;
    
    public WebPage(String tableName) {
        super(tableName);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
