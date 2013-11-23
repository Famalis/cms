package dao;

import dto.EmployeeDTO;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

public class EmployeeDao extends GenericDao<Employee>{
    
    public EmployeeDao(){
        super(Employee.class);
    }
    
    public List<EmployeeDTO> getEmployeeDTOList() {
        return getEmployeeDTOList("",null);
    }
    
    /**
     * Metoda pobiera dane na temat pracowników i zwraca w formie listy
     * EmployeeDTO
     * @param field
     * @param conditions
     * @return 
     */
    public List<EmployeeDTO> getEmployeeDTOList(String field, List<String> conditions) {
        List<Employee> emps;
        List<EmployeeDTO> empDTOs = new ArrayList<>();
        if(field.length()==0){
            emps = this.select();
        } else {
            emps = this.findByFieldName(field, conditions);
        }
        
        for (Employee emp : emps) {
            empDTOs.add(new EmployeeDTO(emp));
        }
        
        return empDTOs;
    }
}
