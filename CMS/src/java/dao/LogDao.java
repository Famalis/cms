package dao;

import dto.LogDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Log;

public class LogDao extends GenericDao<Log> {

    public LogDao() {
        super(Log.class);
    }

    public List<LogDTO> getDepartmentDTOList() {
        List<LogDTO> list = new ArrayList<LogDTO>();
        for (Log log : this.select()) {
            list.add(new LogDTO(log));
        }

        return list;
    }

    public Map<String, List<LogDTO>> findLogsByTerminal() {
        String query = "SELECT log.id as id, log.timestamp as timestamp, "
                + "log.terminalId as terminalId, log.employeeId as employeeId, "
                + "employee.name as name, employee.surname as surname "
                + "FROM log, employee "
                + "WHERE log.employeeId = employee.id";
        ResultSet resultSet = this.connectionManager.select(query);
        Map<String, List<LogDTO>> logDtos = new HashMap<>();
        try {
            while (resultSet.next()) {
                LogDTO dto = new LogDTO();
                dto.setId(resultSet.getLong("id"));
                dto.setTimestamp(resultSet.getString("timestamp"));
                dto.setTerminalId(resultSet.getString("terminalId"));
                dto.setEmployeeSurname(resultSet.getString("surname"));
                dto.setEmployeeName(resultSet.getString("name"));
                if (logDtos.containsKey(dto.getTerminalId())) {
                    logDtos.get(dto.getTerminalId()).add(dto);
                } else {
                    logDtos.put(dto.getTerminalId(), new ArrayList<LogDTO>());
                    logDtos.get(dto.getTerminalId()).add(dto);
                }
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return logDtos;
    }
}
