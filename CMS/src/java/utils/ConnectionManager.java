/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa zarządzająca połączeniem z bazą danych; Aby wykonywać połączenia z 
 * bazą danych należy korzystać z zwawrtych w niej metod.
 * @author sergi_000
 */
public class ConnectionManager {

    private String url = "jdbc:mysql://famalis.no-ip.biz:3306/cms?useUnicode=true&characterEncoding=UTF-8";
    private String login = "cms";
    private String pass = "G9Dua8d5tnGvda3J";
    private Connection connection;
    private static Map<String, ConnectionManager> activeConnections = new HashMap<>();

    
    public void setDBUrl(String url) {
        this.url = url;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public void setPassword(String password) {
        pass = password;
    }
    
    protected ConnectionManager() {
        
    }
    
    public static ConnectionManager getConnectionManager(String login, String password, String url) {
        if(activeConnections.containsKey(login)){
            return activeConnections.get(login);
        } else {
            ConnectionManager cm = new ConnectionManager();
            cm.setLogin(login);
            cm.setDBUrl(url);
            cm.setPassword(password);
            activeConnections.put(login, cm);
            return activeConnections.get(login);
        }
        
    }
    
    public void newConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection c = null;

        try {
            c = DriverManager.getConnection(
                    url, login, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection = c;
    }

    public boolean closeConnection() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                newConnection();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return connection;


    }

    /**
     * Send a select type query to the database; Returns a set of records wich
     * match the requirements of the query.
     * @param query
     * @return 
     */
    public ResultSet select(String query) {
        ResultSet rs = null;
        System.out.println("DEBUG: Execute query - "+query);
        try {
            Statement s = getConnection().createStatement();
            rs = s.executeQuery(query);            
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return rs;
    }
    /**
     * Send an update type query to the databse; Returns true or false depending
     * if the insertion or update was succesful.
     * @param query
     * @return 
     */
    public boolean update(String query) {
        System.out.println("DEBUG: Execute query - "+query);
        try {
            Statement s = getConnection().createStatement();
            s.executeUpdate(query);            
            return true;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return false;
        }
    }
}
