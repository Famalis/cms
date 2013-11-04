/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.Address;
import model.Employee;

/**
 *
 * @author Sergio
 */
public class EmployeeDTO {
    
    private Long id;
    private String name, surname, PESEL, phone, salary, country, city, streetName, streetNumber, apartmentNumber;
    
    public EmployeeDTO() {
        
    }
    
    public EmployeeDTO(Employee employee) {
        //this.id = employee.getId();
        this.setId(employee.getId());
        //this.name = employee.getId()+"";
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.PESEL = employee.getPESEL();
        this.phone = employee.getPhone();
        this.salary = employee.getSalary();
        
        Address address = new Address();
        if(address.loadObject("id="+employee.getAddressId())){
            this.country = address.getCountry();
            this.city = address.getCity();
            this.streetName = address.getStreetName();
            this.streetNumber = address.getStreetNumber();
            this.apartmentNumber = address.getApartmentNumber();
        }
    }

    //@JsonIgnore <-blokuje przekazywanie id w EmployeeListController co psuje dodawanie i edycję pracowników
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    
    
    
}
