package dao;

import dto.DepartmentDTO;
import java.util.ArrayList;
import java.util.List;
import model.Department;

public class DepartmentDao extends GenericDao<Department>{
    
    public DepartmentDao(){
        super(Department.class);
    }
    
    public List<DepartmentDTO> getDepartmentDTOList() {
        List<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
        for (Department dept : this.select()) {
            list.add(new DepartmentDTO(dept));
        }
        
        return list;
    }
}
