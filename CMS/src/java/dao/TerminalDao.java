package dao;

import dto.TerminalDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Terminal;

public class TerminalDao extends GenericDao<Terminal> {

    public TerminalDao() {
        super(Terminal.class);
    }

    public List<TerminalDTO> getTerminalDtos() {
        return getTerminalDtos("", "");
    }

    public List<TerminalDTO> getTerminalDtos(String field, String... values) {
        String query = "SELECT log.timestamp as timestamp, terminal.id as id, terminal.description as description, "
                + "employee.name as name, employee.surname as surname";
        query += " FROM log, terminal, employee";
        query += " WHERE terminal.id = log.terminalId and employee.id = log.employeeId";
        if (field.length() > 0) {
            query += " " + field + " (";
            for (int i = 0; i<values.length; i++) {                
                query += values[i];
                if(i<values.length) {
                    query+=", ";
                } else {
                    query+=")";
                }
            }
        }
        //List<String> terminalIds = new ArrayList<String>();
        Map<String, TerminalDTO> dtoMap = new HashMap<String, TerminalDTO>();
        List<TerminalDTO> list = new ArrayList<TerminalDTO>();
        try {
            ResultSet resultSet = this.connectionManager.select(query);
            while (resultSet.next()) {
                //Long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                String timestamp = resultSet.getString("timestamp");
                String terminalId = resultSet.getString("id");
                String employee = resultSet.getString("name")+" "+
                        resultSet.getString("surname");
                if (dtoMap.containsKey(terminalId)) {
                    TerminalDTO existingDto = dtoMap.get(terminalId);
                    existingDto.getTimestamps().put(timestamp, employee);
                    //existingDto.getTimestamps().add(timestamp);
                    dtoMap.put(terminalId, existingDto);
                    //dtoMap.get(terminalIds).getTimestamps().add(timestamp);
                } else {
                    TerminalDTO newDto = new TerminalDTO();
                    newDto.setDescription(description);
                    newDto.setId(Long.parseLong(terminalId));
                    //newDto.getTimestamps().add(timestamp);
                    newDto.getTimestamps().put(timestamp, employee);
                    dtoMap.put(terminalId, newDto);
                }
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        //List<Terminal> selectList = this.findByFieldName(field, values);
        for (TerminalDTO dto : dtoMap.values()) {
            list.add(dto);
        }
        return list;
    }
}
