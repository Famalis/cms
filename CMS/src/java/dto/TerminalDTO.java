/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import java.util.ArrayList;
import java.util.List;
import static javax.swing.text.html.HTML.Tag.I;
import model.Terminal;

/**
 *
 * @author Sergio
 */
public class TerminalDTO {
    
    private Long id;
    public String description, lastLog;
    public List<String> logList;
    
    public TerminalDTO() {
        logList = new ArrayList<String>();
    }
    
    public TerminalDTO(Terminal terminal) {
        this.id = terminal.getId();
        this.description = terminal.getDescription();        
    }
}
