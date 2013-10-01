/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import model.HomePage;
import model.Saveable;

/**
 *
 * @author Sergio
 */
public class FirstRun {
    
    
    public static void main(String[] args) {
        
        HomePage hp = new HomePage();
        hp.setContent("To jest content strony o id "+hp.getId());
        Saveable.Save();
    }
}
