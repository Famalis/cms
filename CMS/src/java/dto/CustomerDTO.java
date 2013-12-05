package dto;

import model.Address;
import model.Company;
import model.Customer;

/**
 *
 * @author Macha
 */
public class CustomerDTO {

    private Long id;
    
    private String name, 
            surname, 
            phone, 
            email,
            companyName,
            country, 
            city, 
            streetName, 
            streetNumber, 
            apartmentNumber;
    
    public CustomerDTO() {
        super();
    }
    
    public CustomerDTO(Customer customer) {
        this.setId(customer.getId());
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.phone = customer.getPhone();
        this.email = customer.getEmail();
        Company company = new Company();
        if(company.loadObject("id="+customer.getCompanyId())){
            this.companyName = company.getName();
        }
        Address address = new Address();
        if(address.loadObject("id="+company.getAddressId())){
            this.country = address.getCountry();
            this.city = address.getCity();
            this.streetName = address.getStreetName();
            this.streetNumber = address.getStreetNumber();
            this.apartmentNumber = address.getApartmentNumber();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
