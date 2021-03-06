package dao;

import dto.CustomerDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Address;
import model.Company;
import model.Customer;

/**
 *
 * @author Macha
 */
public class CustomerDao extends GenericDao<Customer>{

    public CustomerDao(){
        super(Customer.class);
    }
    
    public List<CustomerDTO> getCustomerDTOList() {
        return getCustomerDTOList(new HashMap<String, List<String>>());
    }
    
    public List<CustomerDTO> getCustomerDTOList(Map<String, List<String>> params) {
        String query = "SELECT cus.name as name, cus.surname as surname, cus.id as id, "
                + "cus.phone as phone, cus.email as email, cus.companyId as companyId ";
        query += "FROM customer as cus ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<CustomerDTO> cusDTOs = new ArrayList<>();
        AddressDao addressDao = new AddressDao();
        CompanyDao comDao = new CompanyDao();
        List<Address> addrs = addressDao.select();
        List<Company> comps = comDao.select();
        try {
            while(set.next()) {
                CustomerDTO dto = new CustomerDTO();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                dto.setSurname(set.getString("surname"));
                dto.setPhone(set.getString("phone"));
                dto.setEmail(set.getString("email"));
                
                Company c = getCusCompany(comps, set.getString("companyId"));
                dto.setCompanyName(c.getName());
                
                Address a = getComAddress(addrs, c.getAddressId());
                dto.setApartmentNumber(a.getApartmentNumber());
                dto.setCountry(a.getCountry());
                dto.setCity(a.getCity());
                dto.setStreetName(a.getStreetName());
                dto.setStreetNumber(a.getStreetNumber());
                dto.setPostalCode(a.getPostalCode());
                cusDTOs.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        
        return cusDTOs;
    }
    
    private Company getCusCompany(List<Company> companys, String id) {
        for (Company c : companys) {
            if(c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }
    
    private Address getComAddress(List<Address> addresses, String id) {
        for (Address a : addresses) {
            if(a.getId() == Long.parseLong(id)) {
                return a;
            }
        }
        return null;
    }
}
