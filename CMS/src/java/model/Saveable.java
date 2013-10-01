/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;

/**
 *
 * @author Sergio
 */
public class Saveable implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id = 0L;
    private static Long maxId = 0L;
    private static String filePath = "database.obj";
    private static Hashtable<Class, Hashtable<Long, Object>> database = new Hashtable<>();

    public Long getId() {
        return id;
    }
    
    public Saveable() {
        super();
        id = ++maxId;
        if (database.containsKey(this.getClass())) {
            database.get(this.getClass()).put(this.id, this);
        } else {
            database.put(this.getClass(), new Hashtable<Long,Object>());
            database.get(this.getClass()).put(this.id, this);
        }
    }
    
    public static  boolean Save() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(new File(filePath));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(database);
            oos.writeObject(maxId);
            oos.close();
            fos.close();
        } catch (IOException io) {
            return false;
        }
        return true;
    }
    
    public static boolean Load() {
        FileInputStream fos = null;
        ObjectInputStream oos = null;
        try {
            fos = new FileInputStream(new File(filePath));
            oos = new ObjectInputStream(fos);
            database = (Hashtable<Class, Hashtable<Long, Object>>) oos.readObject();
            maxId = (Long) oos.readObject();
            oos.close();
            fos.close();
        } catch (Exception io) {
            return false;
        }
        return true;
    }
    
    public static Object loadObjecT(Class c, long i) {
        return database.get(c).get(i);
    }
    
    public static Hashtable<Long, Object> getTable (Class c){
        return database.get(c);
    }
}
