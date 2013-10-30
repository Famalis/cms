/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import model.Employee;

/**
 *
 * @author Sergio
 */
public class EmployeeDTO {
    
    private String name, surname, PESEL, phone, salary;
    
    public EmployeeDTO() {
        
    }
    
    public EmployeeDTO(Employee employee) {
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.PESEL = employee.getPESEL();
        this.phone = employee.getPESEL();
        this.salary = employee.getSalary();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
    
    
}