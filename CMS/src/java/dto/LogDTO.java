package dto;

import model.Log;

public class LogDTO {
    
    private Long id;
    private String logListId, timestamp, terminalId;
    
    public LogDTO(){
        
    }
    
    public LogDTO(Log log){
        this.id = log.getId();
        this.logListId = log.getLogListId();
        this.timestamp = log.getTimestamp();
        this.terminalId = log.getTerminalId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogListId() {
        return logListId;
    }

    public void setLogListId(String logListId) {
        this.logListId = logListId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
}
