/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.general;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionManager;

/**
 * Klasa będąca podstawą dla wszystkich klas modeli zawartych w bazie danych
 * (obiektów w bazie danych); Klasy dziedziczące po tej muszą mieć konstuktor, w
 * którym podaje się nazwę tabeli; Nazwy atrybutów klas dziedziczących muszą
 * być takie same jak nazwy kolumn w odpowiedniej tabeli w bazie danych; 
 * Klasa zawieta metody pozwalające na zapis obiektu w bazie danych, jego 
 * modyfikację i usunięcie; (Lepiej używać Dao do wykonywania zapytań)
 *
 * @author Sergio
 */
public class DatabaseObject {

    private String tableName;
    protected Long id;
    private ConnectionManager connectionManager;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public DatabaseObject(String tableName) {
        super();
        connectionManager = ConnectionManager.getConnectionManager(
                    ConnectionManager.staticLogin, 
                    ConnectionManager.staticPass, 
                    ConnectionManager.staticUrl);
        this.tableName = tableName;
    }
    
    @JsonIgnore
    public String getTableName(){
        return tableName;
    }
    /**
     * Metoda zwraca nazwy kolumn (atrybuty obiektu) w formie listy stringów.
     * @return 
     */
    @JsonIgnore
    public List<String> getColumnNames() {
        List<String> columns = new ArrayList<String>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            columns.add(f.getName());
        }
        return columns;
    }
    
    /**
     * Metoda zwraca nazwy kolumn (atrybuty obiektu) w formie linijki tekstu;
     * Przykład: name, surname, email
     * @return 
     */
    @JsonIgnore
    public String getColumnString() {
        //List<String> columns = new ArrayList<String>();
        String columnString = "";
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i<fields.length; i++) {
            columnString+=fields[i].getName();
            if(i<fields.length-1){
                columnString+=", ";
            }
        }
        return columnString;
    }

    /**
     * Metoda zapisuje dany obiekt do bazy danych; zwraca wartość boolean w
     * zależności, czy zapis się powiedzie.
     *
     * @return
     */
    public Long insert() {
        try {
            String query = "INSERT INTO " + tableName + " (";
            Field[] fields = this.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                query += fields[i].getName();
                if (i < fields.length - 1) {
                    query += ", ";
                }
            }
            query += ") VALUES (";
            for (int i = 0; i < fields.length; i++) {
                String fieldValue = "";
                fields[i].setAccessible(true);
                fieldValue = (String) fields[i].get(this);
                query += "'" + fieldValue + "'";
                if (i < fields.length - 1) {
                    query += ", ";
                }
            }
            query += ") ";
            if (connectionManager.update(query)) {
                ResultSet set = connectionManager.select("SELECT id FROM "+tableName );
                try {
                    set.last();
                    this.id = set.getLong("id");
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
                }
                return this.id;
            } else {
                return -1L;
            }
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            return -1L;
        }
    }

    /**
     * Metoda aktualizująca dane obiektu do odpowiedniego wpisu w bazie danych;
     * Zwraca wartość boolean w zależności od powodzenia operacji.
     *
     * @return
     */
    public boolean update() {
        try {
            String query = "UPDATE " + tableName + " SET ";
            Field[] fields = this.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                query += fields[i].getName();
                String fieldValue = "";
                fields[i].setAccessible(true);
                fieldValue = (String) fields[i].get(this);
                query += "='" + fieldValue + "'";
                if (i < fields.length - 1) {
                    query += ", ";
                }
            }
            query += "WHERE ID=" + this.getId();
            if (connectionManager.update(query)) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private ResultSet getData(String conditions) {
        String query = "SELECT ";
        Field[] fields = this.getClass().getDeclaredFields();
        query += " id, ";
        for (int i = 0; i < fields.length; i++) {
            query += fields[i].getName();
            if (i < fields.length - 1) {
                query += ", ";
            }
        }
        query += " FROM " + tableName + " WHERE " + conditions;
        ResultSet resultSet = connectionManager.select(query);
        return resultSet;
    }

    /**
     * Metoda pozwalająca na odczytanie danych z tabeli danej klasy; przyjmuje
     * warunki, które muszą spełnić dane, w formie klauzuli WHERE w SQL (np
     * WHERE name='Adam' AND age=25); Metoda zwraca wartość boolean, w
     * zależności czy znajdzie wymagany wpis; W przypadku znalezienia więcej niż
     * jednego wpisu brany jest pod uwagę tylko pierwszy.
     *
     * @param conditions Warunki, które muszą zostać spełnione.
     * @return
     */
    public boolean loadObject(String conditions) {
        ResultSet resultSet = this.getData(conditions);
        try {
            resultSet.next();
            Field[] fields = this.getClass().getDeclaredFields();
            this.id = resultSet.getLong("id");
            for (int i = 0; i < fields.length; i++) {
                String setValue = resultSet.getString(fields[i].getName());
                fields[i].setAccessible(true);
                fields[i].set(this, setValue);
            }
            resultSet.close();
        } catch (SQLException ex) {
            //ex.printStackTrace();
            return false;
        } catch (IllegalAccessException aEx) {
            //aEx.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Metoda usuwa dany obiekt z bazy danych - innymi słowy usuwa wpis w bazie
     * dotyczące teog obiektu; w Javie obiekt będzie istniał do momentu jego
     * nadpisania, restartu aplikacji, etc; Zwracana jest wartość boolean w
     * zależności od powodzenia operacji.
     * @return 
     */
    public boolean delete() {
        String query = "DELETE FROM " + tableName + " WHERE ID=" + this.getId();
        if (connectionManager.update(query)) {
            return true;
        } else {
            return false;
        }
    }
    
    //Potrzebne do uzyskania ostatniego ID w tabeli Address, dzięki temu można ustawić addressId w employee
    public Long findLastId(){
        try{
            String query = "SELECT id FROM " + tableName + " ORDER BY id DESC LIMIT 1;";
            ResultSet resultSet = connectionManager.select(query);
            resultSet.next();
            return resultSet.getLong("id");
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1L;
    }
    //
}
