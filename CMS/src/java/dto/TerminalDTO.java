package dto;

import model.Terminal;

public class TerminalDTO {
    
    private Long id;
    public String description, lastLog;
    
    public TerminalDTO() {
    }
    
    public TerminalDTO(Terminal terminal) {
        this.id = terminal.getId();
        this.description = terminal.getDescription();
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
}
