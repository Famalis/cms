package model;

import model.general.DatabaseObject;

public class LogList extends DatabaseObject{
    
    private String employeeId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public LogList(){
        super("loglist");
    }
}
