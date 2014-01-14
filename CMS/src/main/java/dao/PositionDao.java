package dao;

import dto.PositionDTO;
import java.util.ArrayList;
import java.util.List;
import model.Position;

public class PositionDao extends GenericDao<Position>{
    
    public PositionDao(){
        super(Position.class);
    }
    
    public List<PositionDTO> getPositionDTOList() {
        List<PositionDTO> list = new ArrayList<PositionDTO>();
        for (Position pos : this.select()) {
            list.add(new PositionDTO(pos));
        }
        
        return list;
    }
}
