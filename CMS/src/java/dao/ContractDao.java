package dao;

import dto.ContractDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Company;
import model.Contract;
import model.Customer;
import model.Employee;

/**
 *
 * @author Macha
 */
public class ContractDao extends GenericDao<Contract>{

    public ContractDao(){
        super(Contract.class);
    }
    
    public List<ContractDTO> getContractDTOList() {
        return getContractDTOList(new HashMap<String, List<String>>());
    }
    
    public List<ContractDTO> getContractDTOList(Map<String, List<String>> params) {
        String query = "Select con.id as id, con.employeeId as empId, con.customerId as cusId, con.date as date, "
                + "con.description as description, con.price as price ";
        query += "FROM contract as con ";
        
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<ContractDTO> cusDTOs = new ArrayList<>();
        CustomerDao cusDao = new CustomerDao();
        EmployeeDao empDao = new EmployeeDao();
        List<Customer> custs = cusDao.select();
        List<Employee> emps = empDao.select();
        try {
            while(set.next()) {
                ContractDTO dto = new ContractDTO();
                dto.setId(set.getLong("id"));
                dto.setCustomerId(set.getString("cusId"));
                dto.setEmployeeId(set.getString("empId"));
                dto.setDate(set.getString("date"));
                dto.setDescription(set.getString("description"));
                dto.setPrice(set.getString("price"));
                
                Customer c = getConCustomer(custs, dto.getCustomerId());
                dto.setCustomerName(c.getName());
                dto.setCustomerSurname(c.getSurname());
                
                Employee e = getConEmployee(emps, dto.getEmployeeId());
                dto.setEmployeeName(e.getName());
                dto.setEmployeeSurname(e.getSurname());
                cusDTOs.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        
        return cusDTOs;
    }
    
    private Customer getConCustomer(List<Customer> customers, String id) {
        for (Customer c : customers) {
            if(c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }
    
    private Employee getConEmployee(List<Employee> employees, String id) {
        for (Employee e : employees) {
            if(e.getId() == Long.parseLong(id)) {
                return e;
            }
        }
        return null;
    }
    
    public List<ContractDTO> getContractsByCustomer(String id){
        List<ContractDTO> conDTOs = new ArrayList<>();
        for (Contract con : this.select("customerId="+id)) {
            conDTOs.add(new ContractDTO(con));
        }
        return conDTOs;
    }
}
