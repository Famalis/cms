/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.lang.reflect.Field;
import model.general.DatabaseObject;
import net.sf.json.JSONObject;

/**
 * Klasa generująca obiekty JSON na podstawie obiektów Java
 * @author Sergio
 */
public abstract class JSONGenerator {

    /**
     * Metoda zwraca obiekt JSON stworzony na podstawie podanego obiektu Javowego.
     * @param pojo
     * @return 
     */
    public static JSONObject generate(DatabaseObject pojo) {
        
        JSONObject json = new JSONObject();
        try {

            Field[] fields = pojo.getClass().getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                String fieldValue = (String) f.get(pojo);
                json.put(f.getName(), fieldValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;

    }
}
