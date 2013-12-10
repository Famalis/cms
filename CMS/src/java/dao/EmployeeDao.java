package dao;

import dto.EmployeeDTO;
import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.List;
import java.util.Map;
import model.Address;
import model.Department;
import model.Employee;
import model.Position;
import model.User;
import model.User;
import model.UserConfiguration;
import model.UserConfiguration;

public class EmployeeDao extends GenericDao<Employee>{
    
    public EmployeeDao(){
        super(Employee.class);
    }
    
    public List<EmployeeDTO> getEmployeeDTOList() {
        return getEmployeeDTOList(new HashMap<String, List<String>>());
    }
    
    /**
     * Metoda pobiera dane na temat pracowników i zwraca w formie listy
     * EmployeeDTO
     * 
     * @return 
     */
    public List<EmployeeDTO> getEmployeeDTOList(Map<String, List<String>> params) {
        String query = "SELECT emp.name as name, emp.surname as surname, emp.id as id, "
                + "emp.pesel as pesel, emp.salary as salary, emp.positionId as positionId, "
                + "emp.departmentId as departmentId, emp.addressId as addressId ";
        query += "FROM employee as emp ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<EmployeeDTO> empDtos = new ArrayList<>();
        AddressDao addressDao = new AddressDao();
        DepartmentDao depDao = new DepartmentDao();
        PositionDao posDao = new PositionDao();
        UserDao userDao = new UserDao();
        UserConfigurationDao confDao= new UserConfigurationDao();
        List<Address> addrs = addressDao.select();
        List<Department> depts = depDao.select();
        List<Position> pos = posDao.select();
        List<User> users = userDao.select();
        List<UserConfiguration> configs = confDao.select();
        try {
            while(set.next()) {
                EmployeeDTO empDto = new EmployeeDTO();
                empDto.setId(set.getLong("id"));
                empDto.setName(set.getString("name"));
                empDto.setSurname(set.getString("surname"));
                empDto.setPESEL(set.getString("pesel"));
                empDto.setSalary(set.getString("salary"));
                empDto.setPositionId(set.getString("positionId"));
                empDto.setDepartmentId(set.getString("departmentId"));
                Department d = getEmpDepartment(depts, empDto.getDepartmentId());
                Address a = getEmpAddress(addrs, set.getString("addressId"));
                Position p = getEmpPosition(pos, empDto.getPositionId());
                empDto.setDepartmentName(d.getName());
                empDto.setPositionName(p.getName());
                empDto.setApartmentNumber(a.getApartmentNumber());
                empDto.setCountry(a.getCountry());
                empDto.setCity(a.getCity());
                empDto.setStreetName(a.getStreetName());
                empDto.setStreetNumber(a.getStreetNumber());
                empDto.setPrivilegeGroupId(getEmpGroupId(users, configs, empDto.getId()));
                empDtos.add(empDto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        
        return empDtos;
    }
    
    private Address getEmpAddress(List<Address> addresses, String id) {
        for (Address a : addresses) {
            if(a.getId() == Long.parseLong(id)) {
                return a;
            }
        }
        return null;
    }
    
    private Department getEmpDepartment(List<Department> departments, String id) {
        for (Department d : departments) {
            if(d.getId() == Long.parseLong(id)) {
                return d;
            }
        }
        return null;
    }
    
    private Position getEmpPosition(List<Position> positions, String id) {
        for (Position p : positions) {
            if(p.getId() == Long.parseLong(id)) {
                return p;
            }
        }
        return null;
    }
    
    private String getEmpGroupId(List<User> users, List<UserConfiguration> configs, Long id){
        for (User u : users) {
            if(Long.parseLong(u.getEmployeeId()) == id){
                for (UserConfiguration c : configs) {
                    if(Long.parseLong(c.getUserId()) == u.getId()){
                        return c.getGroupId();
                    }
                }
            }
        }
        
        return null;
    }
    
}
