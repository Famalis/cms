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
import java.util.ArrayList;

/**
 * Klasa zarządzająca połączeniem z bazą danych; Aby wykonywać połączenia z 
 * bazą danych należy korzystać z zwawrtych w niej metod.
 * @author sergi_000
 */
public abstract class ConnectionManager {

    private static String url = "jdbc:mysql://famalis.no-ip.biz:3306/cms?useUnicode=true&characterEncoding=utf8";
    private static String login = "cms";
    private static String pass = "G9Dua8d5tnGvda3J";
    private static Connection connection;

    public static void newConnection() {
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

    public static boolean closeConnection() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public static Connection getConnection() {
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
    public static ResultSet select(String query) {
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
    public static boolean update(String query) {
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
