/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDTO;
import java.io.IOException;

/**
 *
 * @author Sergio
 */
public class Utils {
    public static Object convertJSONStringToObject(String json, String objectName, Class javaClass) {
        String actualJsonString = json.substring(
                ("'"+objectName+"':'").length(), json.length()-1);
        ObjectMapper mapper = new ObjectMapper();        
        Object o = null;
        try {
            o  = mapper.readValue(actualJsonString, javaClass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return o;
    }
}
