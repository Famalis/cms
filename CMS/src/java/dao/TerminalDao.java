package dao;

import dto.TerminalDTO;
import java.util.ArrayList;
import java.util.List;
import model.Terminal;

public class TerminalDao extends GenericDao<Terminal>{
    
    public TerminalDao(){
        super(Terminal.class);
    }
    
    public List<TerminalDTO> getTerminalDtos() {
        return getTerminalDtos("", "");
    }
    
    public List<TerminalDTO> getTerminalDtos(String field, String... values) {
        List<Terminal> selectList = this.findByFieldName(field, values);
        List<TerminalDTO> list = new ArrayList<TerminalDTO>();
        for (Terminal t : selectList) {
            list.add(new TerminalDTO(t));
        }
        return list;
    }
}
