package dao;

import model.Employee;

public class EmployeeDao extends GenericDao{
    
    public EmployeeDao(){
        super(Employee.class);
    }
}
