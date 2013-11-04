package model;

import model.general.DatabaseObject;

public class Position extends DatabaseObject{
    
    private String name, description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Position(){
        super("position");
    }
    
}
