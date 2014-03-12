package dao;

import dto.AddressDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;

public class AddressDao extends GenericDao<Address>{
    
    public static String ZAMIESZKANIA = "2", ZAMELDOWANIA = "3";
    
    public AddressDao(){
        super(Address.class);
    }
    
    
    public List<AddressDTO> getAddressDTOList() {
        return getAddressDTOList(new HashMap<String, List<String>>());
    }
    
    public List<AddressDTO> getAddressDTOList(String field, String... values) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (String value : values) {
            list.add(value);
        }
        map.put(field, list);
        return getAddressDTOList(map);
    }
    
    /**
     * Metoda pobiera dane na temat adresów i zwraca w postaci listy. Parametry
     * wyszukiwania podaje się w formie mapy w której klucze to nazwy kolumn, a
     * wartości to listy wartości, które pole może przyjąć.
     * @param params
     * @return 
     */
    public List<AddressDTO> getAddressDTOList(Map<String, List<String>> params) {
        String query = "SELECT a.id as id, a.country as country, a.city as city,"
                + "a.streetName as streetName, a.streetNumber as streetNumber, "
                + "a.apartmentNumber as apartmentNumber, a.postalCode as postalCode, "
                + "a.personId as personId, a.dictId as dictId, d.description as des "
                + "FROM address a, dictionary d "
                + "WHERE d.id=a.dictId";
        if(params.isEmpty()) {
            this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<AddressDTO> result = new ArrayList<>();
        try {
            while(set.next()) {
                AddressDTO dto = new AddressDTO();
                dto.setApartmentNumber(set.getString("apartmentNumber"));
                dto.setCity(set.getString("city"));
                dto.setCountry(set.getString("country"));
                dto.setDictId(set.getString("dictId"));
                dto.setDictName(set.getString("des"));
                dto.setId(set.getLong("id"));
                dto.setPersonId(set.getString("personId"));
                dto.setPostalCode(set.getString("postalCode"));
                dto.setStreetName(set.getString("streetName"));
                dto.setStreetNumber(set.getString("streetNumber"));
                result.add(dto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
