package dao;

import model.Employee;

public class EmployeeDao extends GenericDao<Employee>{
    
    public EmployeeDao(){
        super(Employee.class);
    }
}
