package dao;

import model.Address;

public class AddressDao extends GenericDao<Address>{
    
    public AddressDao(){
        super(Address.class);
    }
}
