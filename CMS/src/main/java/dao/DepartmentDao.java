package dao;

import dto.DepartmentDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Department;
import model.Employee;

public class DepartmentDao extends GenericDao<Department> {

    public DepartmentDao() {
        super(Department.class);
    }

    public List<DepartmentDTO> getDepartmentDTOList() {
        return getDepartmentDTOList(new HashMap<String, List<String>>());
    }
    /**
     * Metoda zwraca listę obiektów DeprtmentDTO dla podanych warunków, które
     * przyjmowane sa w postaci mapy String, String gdzie klucze to nazwy pól a
     * wartości kluczy to lista wartości które mogą być przyjmowane przez dane
     * pole
     * @param params
     * @return 
     */
    public List<DepartmentDTO> getDepartmentDTOList(Map<String, List<String>> params) {
        String query = "SELECT dep.id as id, dep.name as name, "
                + "dep.managerId as managerId, a.country as country, "
                + "a.city as city, a.streetName as streetName, "
                + "a.streetNumber as streetNumber, a.apartmentNumber as apartment, "
                + "a.id as addressId ";
        query += "FROM department as dep, address as a ";
        query += "WHERE a.id = dep.addressId";
        query = this.addParamConditions(query, params);
        
        List<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
        ResultSet set = this.connectionManager.select(query);
        try {
            while (set.next()) {
                DepartmentDTO dto = new DepartmentDTO();
                dto.setAddressId(set.getString("addressId"));
                dto.setApartmentNumber(set.getString("apartment"));
                dto.setCity(set.getString("city"));
                dto.setCountry(set.getString("country"));
                dto.setId(set.getLong("id"));
                dto.setManagerId(set.getString("managerId"));
                
                Employee emp = new Employee();
                if (Long.parseLong(dto.getManagerId()) > 0) {
                    emp.loadObject("id=" + dto.getManagerId());
                    dto.setManagerName(emp.getName());
                    dto.setManagerSurname(emp.getSurname());
                }
                
                dto.setName(set.getString("name"));
                dto.setStreetName(set.getString("streetName"));
                dto.setStreetNumber(set.getString("streetNumber"));
                list.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
