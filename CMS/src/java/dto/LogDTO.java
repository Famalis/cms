package dto;

import model.Log;

public class LogDTO {
    
    private Long id;
    private String timestamp, terminalId;
    private String employeeName, employeeSurname;
    
    public LogDTO(){
        
    }
    
    public LogDTO(Log log){
        this.id = log.getId();
        this.timestamp = log.getTimestamp();
        this.terminalId = log.getTerminalId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }
    
}
