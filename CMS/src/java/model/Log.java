package model;

import model.general.DatabaseObject;

public class Log extends DatabaseObject{

    private String timestamp, terminalId;

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
    
    public Log(){
        super("log");
    }
}
