/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import model.Address;
import model.Department;
import model.Employee;

/**
 *
 * @author Sergio
 */
public class DepartmentDTO {

    private Long id;
    private String name;
    private String managerName, managerSurname, managerId;
    private String country, city, street, streetNumber, apartment, addressId;

    public DepartmentDTO() {

    }

    public DepartmentDTO(Department department) {
        this.name = department.getName();
        Employee emp = new Employee();
        if (Long.parseLong(department.getManagerId()) > 0) {
            emp.loadObject("id=" + department.getManagerId());
            managerName = emp.getName();
            managerSurname = emp.getSurname();
            managerId = department.getManagerId();
        }
        Address address = new Address();
        if (Long.parseLong(department.getAddressId()) > 0) {
            address.loadObject("id=" + department.getAddressId());
            country = address.getCountry();
            city = address.getCity();
            street = address.getStreetName();
            streetNumber = address.getStreetNumber();
            apartment = address.getApartmentNumber();
            addressId = department.getAddressId();
        }
    }

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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerSurname() {
        return managerSurname;
    }

    public void setManagerSurname(String managerSurname) {
        this.managerSurname = managerSurname;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

}
