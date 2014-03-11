/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.List;
import model.Address;
import model.Department;
import model.Employee;
import model.Position;

/**
 *
 * @author Sergio
 */
public class EmployeeDTO {

    private Long id;
    private String name,
            surname,
            PESEL,
            phone,
            salary,
            country,
            city,
            streetName,
            streetNumber,
            apartmentNumber,
            postalCode,
            positionId,
            departmentId,
            positionName,
            departmentName;

    List<EmploymentDTO> employments = new ArrayList<EmploymentDTO>();

    public EmployeeDTO() {
        super();
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
        this.positionId = employee.getPositionId();
        this.departmentId = employee.getDepartmentId();
        Address address = new Address();
        if (address.loadObject("personId=" + employee.getId())) {
            this.country = address.getCountry();
            this.city = address.getCity();
            this.streetName = address.getStreetName();
            this.streetNumber = address.getStreetNumber();
            this.apartmentNumber = address.getApartmentNumber();
            this.postalCode = address.getPostalCode();
        }
        Position position = new Position();
        position.loadObject("id=" + positionId);
        this.positionName = position.getName();
        Department department = new Department();
        department.loadObject("id=" + departmentId);
        this.departmentName = department.getName();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<EmploymentDTO> getEmployments() {
        return employments;
    }

    public void setEmployments(List<EmploymentDTO> employments) {
        this.employments = employments;
    }

}
