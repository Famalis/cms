package dto;

import java.util.ArrayList;
import model.Terminal;

public class TerminalDTO {
    
    private Long id;
    private String description, lastLog;
    private ArrayList<String> timestamps;
    
    public TerminalDTO() {
        timestamps = new ArrayList<>();
    }
    
    public TerminalDTO(Terminal terminal) {
        this.id = terminal.getId();
        this.description = terminal.getDescription();
        timestamps = new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastLog() {
        return lastLog;
    }

    public void setLastLog(String lastLog) {
        this.lastLog = lastLog;
    }

    public ArrayList<String> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(ArrayList<String> timestamps) {
        this.timestamps = timestamps;
    }
    
    
}
