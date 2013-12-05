package model;

import model.general.DatabaseObject;

/**
 *
 * @author Macha
 */
public class Company extends DatabaseObject{
    
    public Company() {
        super("company");
    }
    
    private String name, 
            addressId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    
}
