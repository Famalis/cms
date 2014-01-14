package dao;

import model.Task;

/**
 *
 * @author Macha
 */
public class TaskDao extends GenericDao<Task> {
    
    public TaskDao() {
        super(Task.class);
    }
    
}
