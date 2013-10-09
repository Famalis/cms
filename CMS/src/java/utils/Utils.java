/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * Tutaj przetrzymywane będą różne przydatne metody wykorzystywane w więcej niż
 * jedenj klasie.
 * @author Sergio
 */
public class Utils {
    
    /**
     * Metoda zamieniająca JSON w postaci Stringa na object Javovy; zwracany 
     * obiekt wciąż trzeba zrzutować do konkretnego obiektu.
     * @param json - JSON w formie Stringa
     * @param objectName - Nazwa zmiennej String w której zawarty jest JSON (innymi słowy nazwa zmiennej ktora jest podana jako pierwszy paramter)
     * @param javaClass - Klasa na podstawie której ma zostać stworzony zwracany obiekt
     * @return 
     */
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
