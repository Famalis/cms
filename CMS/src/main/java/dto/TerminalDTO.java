package dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Terminal;

public class TerminalDTO {
    
    private Long id;
    private String description, lastLog;
    private Map<String, String> timestamps;
    
    public TerminalDTO() {
        //timestamps = new ArrayList<>();
        timestamps = new HashMap<String, String>();
    }
    
    public TerminalDTO(Terminal terminal) {
        this.id = terminal.getId();
        this.description = terminal.getDescription();
        //timestamps = new ArrayList<>();
        timestamps = new HashMap<String, String>();
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

    public Map<String, String> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Map<String, String> timestamps) {
        this.timestamps = timestamps;
    }
}
