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
 *
 * @author Sergio
 */
public class GenericDao<T extends DatabaseObject> {

    private T objectInstance;

    public GenericDao(Class<T> c) {
        try {
            objectInstance = c.newInstance();
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
     *
     * @param conditions warunki w WHERE
     * @return
     */
    public boolean delete(String conditions) {
        String query = "DELETE FROM " + objectInstance.getTableName()
                + " WHERE " + conditions;
        return ConnectionManager.update(query);
    }

    /**
     * Metoda służąca do zmiany danych we wszystkich wierszach spełniających
     * podane wymagania.
     *
     * @param conditions - warunki w WHERE (np id = 1)
     * @param sets - wszystkie wartości które mają zostać zmienione; przykładowo
     * można napisać name='adam', surname='kowalski'
     * @return
     */
    public boolean update(String conditions, String... sets) {
        String query = "UPDATE " + objectInstance.getTableName() + " SET ";
        for (int i = 0; i < sets.length; i++) {
            query += sets[i];
            if (i < sets.length - 1) {
                query += ", ";
            }
        }
        query += " WHERE " + conditions;
        return ConnectionManager.update(query);
    }

    /**
     * Metoda dodająca podany w parametrze obiekt do bazy danych.
     * @param o
     * @return 
     */
    public boolean insert(T o) {
        try {
            String query = "INSERT INTO " + o.getTableName() + " ("
                    + o.getColumnString() + ") VALUES (";
            Field[] fields = o.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                query += "'";
                query += (String) fields[i].get(o);
                query += "'";
                if (i < fields.length - 1) {
                    query += ", ";
                }
            }
            query += ")";
            if (ConnectionManager.update(query)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
    /**
     * Pozwala dodać wiele obiektów do bazy danych
     * @param object
     * @return 
     */
    public boolean insert(T... object) {
        for (T o : object) {
            if(!insert(o)){
                return false;
            }
        }
        return true;
    }

    /**
     * Metoda służąca do pobierania wpisów z bazy w postaci listy odpowiednich
     * obiektów.
     *
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
                T obj = (T) objectInstance.getClass().newInstance();
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
