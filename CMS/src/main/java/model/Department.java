package model;

import model.general.DatabaseObject;

public class Department extends DatabaseObject {

    private String name, addressId, managerId;

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

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
       
    public Department() {
        super("department");
    }
}
