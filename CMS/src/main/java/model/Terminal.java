package model;

import model.general.DatabaseObject;

public class Terminal extends DatabaseObject{
    
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Terminal() {
        super("terminal");
    }
    
}