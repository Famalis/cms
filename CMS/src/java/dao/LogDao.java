package dao;

import dto.LogDTO;
import java.util.ArrayList;
import java.util.List;
import model.Log;

public class LogDao extends GenericDao<Log>{
    
    public LogDao(){
        super(Log.class);
    }
    
    public List<LogDTO> getDepartmentDTOList() {
        List<LogDTO> list = new ArrayList<LogDTO>();
        for (Log log : this.select()) {
            list.add(new LogDTO(log));
        }
        
        return list;
    }
}
