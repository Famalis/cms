/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import dao.DictionaryDao;
import model.Address;

/**
 *
 * @author sergio
 */
public class AddressDTO {
    
    private Long id;
    private String 
            country, 
            city, 
            streetName, 
            streetNumber, 
            apartmentNumber, 
            postalCode, 
            personId, 
            dictId,
            dictName;
    
    public AddressDTO() {
        super();
    }
    
    public AddressDTO(Address address) {
        super();
        this.apartmentNumber = address.getApartmentNumber();
        this.city = address.getCity();
        this.country = address.getCountry();
        this.dictId = address.getDictId();
        DictionaryDao dictDao = new DictionaryDao();
        this.dictName = dictDao.findById(dictId).getDescription();
        this.personId = address.getPersonId();
        this.postalCode = address.getPostalCode();
        this.streetName = address.getStreetName();
        this.streetNumber = address.getStreetNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
    
}
