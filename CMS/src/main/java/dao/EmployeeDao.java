package dao;

import dto.AddressDTO;
import dto.EmployeeDTO;
import dto.EmploymentDTO;
import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Address;
import model.Department;
import model.Employee;
import model.Position;

public class EmployeeDao extends GenericDao<Employee> {

    public EmployeeDao() {
        super(Employee.class);
    }

    /**
     * Metoda pobiera dane na temat pracowników i zwraca w formie listy
     * EmployeeDTO
     *
     * @return
     */
    public List<EmployeeDTO> getEmployeeDTOList() {
        return getEmployeeDTOList(new HashMap<String, List<String>>());
    }

    /**
     * Metoda pobiera dane na temat pracowników i zwraca w formie listy
     * EmployeeDTO. Obiekty pobrane muszą spełniać podany warunek, to znaczy
     * mieć określoną wartość w określonym polu.
     *
     * @param column
     * @param value
     * @return
     */
    public List<EmployeeDTO> getEmployeeDTOList(String column, String value) {
        Map<String, List<String>> paramMap = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(value);
        paramMap.put(column, values);
        return getEmployeeDTOList(paramMap);
    }

    /**
     * Metoda pobiera dane na temat pracowników i zwraca w formie listy
     * EmployeeDTO. Parametry wyszukiwania podawane są w formie mapy, gdzie
     * klucz to nazwa pola, a wartość klucza jest lista wartości, jakie dane
     * pole może przyjąć.
     *
     * @param params
     * @return
     */
    public List<EmployeeDTO> getEmployeeDTOList(Map<String, List<String>> params) {
        String query = "SELECT emp.name as name, emp.surname as surname, emp.id as id, emp.phone as phone,"
                + "emp.pesel as pesel, emp.salary as salary, emp.positionId as positionId, "
                + "emp.departmentId as departmentId, emp.mainAddressId as addressId ";
        query += "FROM employee as emp ";
        if (!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<EmployeeDTO> empDtos = new ArrayList<>();
        AddressDao addressDao = new AddressDao();
        DepartmentDao depDao = new DepartmentDao();
        PositionDao posDao = new PositionDao();
        List<Address> addrs = addressDao.select();
        List<Department> depts = depDao.select();
        List<Position> pos = posDao.select();
        try {
            while (set.next()) {
                EmployeeDTO empDto = new EmployeeDTO();
                empDto.setId(set.getLong("id"));
                empDto.setName(set.getString("name"));
                empDto.setSurname(set.getString("surname"));
                empDto.setPESEL(set.getString("pesel"));
                empDto.setSalary(set.getString("salary"));
                empDto.setPhone(set.getString("phone"));
                empDto.setPositionId(set.getString("positionId"));
                empDto.setDepartmentId(set.getString("departmentId"));
                Department d = getEmpDepartment(depts, empDto.getDepartmentId());
                Address a = getEmpAddress(addrs, set.getString("addressId"));
                Position p = getEmpPosition(pos, empDto.getPositionId());
                empDto.setDepartmentName(d.getName());
                empDto.setPositionName(p.getName());
                if (a != null) {
                    empDto.setMainAddressId(a.getId()+"");
                }
                empDtos.add(empDto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return empDtos;
        }
        return empDtos;
    }
    
    public List<EmployeeDTO> getEmployeeDTOListWithEmployments() {
        return getEmployeeDTOListWithEmployments(new HashMap<String, List<String>>());
    }
    
    public List<EmployeeDTO> getEmployeeDTOListWithEmployments(Map<String, List<String>> params) {
        List<EmployeeDTO> empDTOs;
        if(params.isEmpty()) {
            empDTOs = getEmployeeDTOList();
        } else {
            empDTOs = getEmployeeDTOList(params);
        }        
        EmploymentDao employmentDao = new EmploymentDao();
        List<EmploymentDTO> emplDtos = employmentDao.getEmploymentDTOList();
        for (EmploymentDTO employmentDTO : emplDtos) {
            for (EmployeeDTO empDTO : empDTOs) {
                if(empDTO.getId() == Long.parseLong(employmentDTO.getEmployeeId())) {
                    empDTO.getEmployments().add(employmentDTO);
                }
            }
        }
        return empDTOs;
    }
    
    public List<EmployeeDTO> getEmployeeDTOListWithEmploymentsAndAddresses() {
        return getEmployeeDTOListWithEmploymentsAndAddresses(new HashMap<String, List<String>>());
    }
    
    public List<EmployeeDTO> getEmployeeDTOListWithEmploymentsAndAddresses(Map<String, List<String>> params) {
        List<EmployeeDTO> empDTOs;
        if(params.isEmpty()) {
            empDTOs = getEmployeeDTOList();
        } else {
            empDTOs = getEmployeeDTOList(params);
        }        
        EmploymentDao employmentDao = new EmploymentDao();
        List<EmploymentDTO> emplDtos = employmentDao.getEmploymentDTOList();
        AddressDao addressDao = new AddressDao();
        List<AddressDTO> addDtos = addressDao.getAddressDTOList();
        for (EmployeeDTO empDTO : empDTOs) {
            for (EmploymentDTO employmentDTO : emplDtos) {
                if(empDTO.getId() == Long.parseLong(employmentDTO.getEmployeeId())) {
                    empDTO.getEmployments().add(employmentDTO);
                }
            }
            for (AddressDTO dto : addDtos) {
                if(empDTO.getId() == Long.parseLong(dto.getPersonId())) {
                    empDTO.getAddresses().add(dto);
                }
            }
        }
        return empDTOs;
    }

    private Address getEmpAddress(List<Address> addresses, String id) {
        for (Address a : addresses) {
            if (a.getId() == Long.parseLong(id)) {
                return a;
            }
        }
        return null;
    }

    private Department getEmpDepartment(List<Department> departments, String id) {
        for (Department d : departments) {
            if (d.getId() == Long.parseLong(id)) {
                return d;
            }
        }
        return null;
    }

    private Position getEmpPosition(List<Position> positions, String id) {
        for (Position p : positions) {
            if (p.getId() == Long.parseLong(id)) {
                return p;
            }
        }
        return null;
    }

}
