package dao;

import model.Department;

public class DepartmentDao extends GenericDao<Department>{
    
    public DepartmentDao(){
        super(Department.class);
    }
}
