package model;

import model.general.DatabaseObject;

public class Position extends DatabaseObject{
    
    private String name, description, hierarchy;

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

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarhy) {
        this.hierarchy = hierarhy;
    }
    
    public Position(){
        super("position");
    }
    
}
