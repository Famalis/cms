package dto;

import model.Position;

public class PositionDTO{
    
    private Long id;
    public String description, name, hierarhy;
    
    public PositionDTO() {
        super();
    }
    
    public PositionDTO(Position position){
        this.id = position.getId();
        this.name = position.getName();
        this.description = position.getDescription();
        this.hierarhy = position.getHierarhy();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHierarhy() {
        return hierarhy;
    }

    public void setHierarhy(String hierarhy) {
        this.hierarhy = hierarhy;
    }
    
}
