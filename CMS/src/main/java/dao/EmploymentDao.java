/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.EmploymentDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Employment;

/**
 *
 * @author sergio
 */
public class EmploymentDao extends GenericDao<Employment> {

    public EmploymentDao() {
        super(Employment.class);
    }

    public List<EmploymentDTO> getEmploymentDTOList() {
        return getEmploymentDTOList(new HashMap<String, List<String>>());
    }

    public List<EmploymentDTO> getEmploymentDTOList(Map<String, List<String>> params) {
        String select = "SELECT emp.id as id, "
                + "emp.employeeId as employeeId, "
                + "emp.dateFrom as dateFrom, "
                + "emp.dateTo as dateTo, "
                + "dict.value as dictValue, "
                + "dict.description as dictDesc ";
        String from = "FROM employment as emp, "
                + "employment_type as empType, "
                + "dictionary as dict ";
        String where = "";
        if (!params.isEmpty()) {
            where = "WHERE empType.id = emp.employmentTypeId "
                    + "AND dict.id = empType.dictId ";
        }
        ResultSet set = this.connectionManager.select(select + from + where);
        List<EmploymentDTO> resultList = new ArrayList<>();
        try {
            while (set.next()) {
                EmploymentDTO newEmpDto = new EmploymentDTO();
                newEmpDto.setEmploymentTypeName(set.getString("dictDesc"));
                newEmpDto.setId(set.getLong("id"));
                newEmpDto.setDateFrom(set.getString("dateFrom"));
                newEmpDto.setDateTo(set.getString("dateTo"));
                newEmpDto.setEmployeeId(set.getString("employeeId"));
                resultList.add(newEmpDto);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return resultList;
    }

}
