package dto;

import model.Contract;
import model.Customer;
import model.Employee;

/**
 *
 * @author Macha
 */
public class ContractDTO {

    private Long id;
        
    private String employeeId,
            customerId,
            employeeName, 
            employeeSurname, 
            customerName, 
            customerSurname,
            date,
            description,
            price;
    
    public ContractDTO() {
        super();
    }
    
    public ContractDTO(Contract contract) {
        this.setId(contract.getId());
        
        this.employeeId = contract.getEmployeeId();
        this.customerId = contract.getCustomerId();
        
        Employee employee = new Employee();
        employee.loadObject("id="+employeeId);
        this.employeeName = employee.getName();
        this.employeeSurname = employee.getSurname();
        
        Customer customer = new Customer();
        customer.loadObject("id="+customerId);
        this.customerName = customer.getName();
        this.customerSurname = customer.getSurname();
        
        this.date = contract.getDate();
        this.description = contract.getDescription();
        this.price = contract.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    
}
