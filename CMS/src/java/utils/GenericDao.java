/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.general.DatabaseObject;

/**
 * Klasa której instancja pozwala na wykonywania operacji na wielu wpisach na
 * raz, przy pomocy jednego zapytania; Należy jej używać za każdym razem, gdy
 * chcemy dodać, usunąć lub zmienić wiele wpisów; Można jej właściwie używać za
 * każdym razem gdy wykonujemy operacę na danych w bazie, niezaleznie od ilości
 * wpisów.
 * @author Sergio
 */
public class GenericDao<T extends DatabaseObject> {

    private T objectInstance;
    private Class clazz;

    public GenericDao(Class<T> c) {
        try {
            objectInstance = (T) c.newInstance();
            this.clazz = c;
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public List<T> select() {
        return select("");
    }
    
    /**
     * Metoda służąca do usuwania wpisów w bazie danych
     * @param conditions warunki w WHERE
     * @return 
     */
    public boolean delete(String conditions) {
        String query = "DELETE FROM "+objectInstance.getTableName() + 
                " WHERE "+conditions;
        return ConnectionManager.update(query);
    }
    
    /**
     * Metoda służąca do zmiany danych we wszystkich wierszach spełniających
     * podane wymagania.
     * @param conditions - warunki w WHERE (np id = 1)
     * @param sets - wszystkie wartości które mają zostać zmienione; przykładowo
     * można napisać  name='adam', surname='kowalski'
     * @return 
     */
    public boolean update(String conditions, String... sets){
        String query = "UPDATE "+objectInstance.getTableName()+ " SET ";
        for (int i = 0; i<sets.length; i++) {
            query+=sets[i];
            if(i<sets.length-1) {
                query+=", ";
            }
        }
        query+=" WHERE "+conditions;
        return ConnectionManager.update(query);
    }
    
    /**
     * Metoda służąca do pobierania wpisów z bazy w postaci listy odpowiednich
     * obiektów.
     * @param conditions warunki w WHERE
     * @return 
     */
    public List<T> select(String conditions) {
        List<T> resultList = new ArrayList<T>();
        try {
            String query = "SELECT " + objectInstance.getColumnString();
            query += " FROM " + objectInstance.getTableName();
            if (conditions.length() > 0) {
                query += " WHERE " + conditions;
            }
            ResultSet resultSet = ConnectionManager.select(query);
            while (resultSet.next()) {
                T obj = (T) clazz.newInstance();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field f : fields) {
                    String fieldValue = "";
                    fieldValue = resultSet.getString(f.getName());
                    f.setAccessible(true);
                    f.set(obj, fieldValue);

                }
                resultList.add(obj);
            }
        } catch (Exception e) {
            //System.err.println(get);
            e.printStackTrace();
        }
        return resultList;

    }
}
