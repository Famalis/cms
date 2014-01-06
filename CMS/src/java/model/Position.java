package model;

import model.general.DatabaseObject;

public class Position extends DatabaseObject{
    
    private String name, description, hierarhy;

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

    public String getHierarhy() {
        return hierarhy;
    }

    public void setHierarhy(String hierarhy) {
        this.hierarhy = hierarhy;
    }
    
    public Position(){
        super("position");
    }
    
}
