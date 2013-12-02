/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.general.DatabaseObject;
import utils.ConnectionManager;

/**
 * Klasa której instancja pozwala na wykonywania operacji na wielu wpisach na
 * raz, przy pomocy jednego zapytania; Należy jej używać za każdym razem, gdy
 * chcemy dodać, usunąć lub zmienić wiele wpisów; Można jej właściwie używać za
 * każdym razem gdy wykonujemy operacę na danych w bazie, niezaleznie od ilości
 * wpisów.
 *
 * @author Sergio
 * @param <T>
 */
public class GenericDao<T extends DatabaseObject> {

    private T objectInstance;
    protected ConnectionManager connectionManager;

    public GenericDao(Class<T> c) {
        try {
            objectInstance = c.newInstance();
            connectionManager = ConnectionManager.getConnectionManager(
                    ConnectionManager.staticLogin,
                    ConnectionManager.staticPass,
                    ConnectionManager.staticUrl);
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public List<T> select() {
        return select("");
    }
    
    public List<T> findById(String id) {
        return findByFieldName("id", id);
    }

    /**
     * Metoda pobierająca dane z bazy, w której podajemy wartości jakie ma
     * przyjmować dane pole
     *
     * @param fieldName
     * @param values
     * @return
     */
    public List<T> findByFieldName(String fieldName, String... values) {
        if (fieldName.length() <= 0) {
            return select();
        }
        List<String> list = new ArrayList<String>();
        for (String s : values) {
            list.add(s);
        }
        return findByFieldName(fieldName, list);
    }

    /**
     * Metoda pobierająca dane z bazy w której podajemy listę wartości jaką może
     * przymować podane pole (np id);
     *
     * @param fieldName
     * @param values
     * @return
     */
    public List<T> findByFieldName(String fieldName, List<String> values) {
        if (fieldName.length() <= 0) {
            return select();
        }
        String conditions = fieldName + " IN (";
        for (int i = 0; i < values.size(); i++) {
            conditions += values.get(i);
            if (i < values.size() - 1) {
                conditions += ", ";
            }
        }
        conditions += ")";
        return select(conditions);
    }
    
    /**
     * Metoda usuwa wszystkie wpisy w tabeli o podanym id
     * @param id
     * @return 
     */
    public boolean deleteAllWithId(String id) {
        return delete("id="+id);
    }
    
    /**
     * Metoda usuwa wszystkie wpisy w tablei, w których dana kolumna posiada podaną wartość
     * @param fieldName nazwa kolumny
     * @param fieldValue wartość kolumny
     * @return 
     */
    public boolean deleteAllMatching(String fieldName, String fieldValue){
        return delete(fieldName+"="+fieldValue);
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
        return connectionManager.update(query);
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
        return connectionManager.update(query);
    }
    /**
     * Metoda ustawia podaną wartość w podanej kolumnie dla wszystkich wpisów posiadających dane id
     * @param id id elementu który będzie zmieniony
     * @param fieldName nazwa kolumny do zmiany
     * @param fieldValue nowa wartość kolumny
     * @return 
     */
    public boolean updateFieldForAllElementsWithId(String id, String fieldName, String fieldValue){
        return update("id="+id, fieldName+"="+fieldValue);
    }

    /**
     * Metoda ustawia podaną wartość w podanej kolumnie dla wszystkich wpisów spełniających warunki
     * @param conditionFieldName nazwa kolumny na podstawie której wybierane są elementy do zmiany
     * @param conditionFieldValue wartość jaką mają mieć modyfikowane wpisy w danej kolumie
     * @param fieldName nazwa modyfikowanej kolumny
     * @param fieldValue nowa watość
     * @return 
     */
    public boolean updateFieldForAllElementsWithId(String conditionFieldName, String conditionFieldValue, String fieldName, String fieldValue){
        return update(conditionFieldName+"="+conditionFieldValue, fieldName+"="+fieldValue);
    }
    
    /**
     * Metoda dodająca podany w parametrze obiekt do bazy danych.
     *
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
            if (connectionManager.update(query)) {
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
     *
     * @param object
     * @return
     */
    public boolean insert(T... object) {
        for (T o : object) {
            if (!insert(o)) {
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
        List<T> resultList = new ArrayList<>();
        try {
            String query = "SELECT id, " + objectInstance.getColumnString();
            query += " FROM " + objectInstance.getTableName();
            if (conditions.length() > 0) {
                query += " WHERE " + conditions;
            }
            ResultSet resultSet = connectionManager.select(query);
            while (resultSet.next()) {
                T obj = (T) objectInstance.getClass().newInstance();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field f : fields) {
                    String fieldValue = "";
                    fieldValue = resultSet.getString(f.getName());
                    f.setAccessible(true);
                    f.set(obj, fieldValue);

                }
                obj.setId(resultSet.getLong("id"));
                resultList.add(obj);
            }
        } catch (Exception e) {
            //System.err.println(get);
            e.printStackTrace();
        }
        return resultList;

    }

    /**
     * Pobiera określone kolumny tabeli z bazy danych
     *
     * @param conditions
     * @param selectFieldNames
     * @return
     */
    public List<T> selectFields(String conditions, List<String> selectFieldNames) {
        List<T> resultList = new ArrayList<>();
        try {
            String query = "SELECT id";
            for (String field : selectFieldNames) {
                query += ", " + field;
            }
            query += " FROM " + objectInstance.getTableName();
            if (conditions.length() > 0) {
                query += " WHERE " + conditions;
            }
            ResultSet resultSet = connectionManager.select(query);
            while (resultSet.next()) {
                T obj = (T) objectInstance.getClass().newInstance();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field f : fields) {
                    if (selectFieldNames.contains(f.getName())) {
                        String fieldValue = "";
                        fieldValue = resultSet.getString(f.getName());
                        f.setAccessible(true);
                        f.set(obj, fieldValue);
                    }
                }
                obj.setId(resultSet.getLong("id"));
                resultList.add(obj);
            }
        } catch (Exception e) {
            //System.err.println(get);
            e.printStackTrace();
        }
        return resultList;

    }
}
