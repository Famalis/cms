/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.DepartmentDao;
import dao.EmployeeDao;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/taskList")
public class TaskListController extends BaseController {

    public TaskListController() {
        super("all", "ViewTasks");
    }

    @RequestMapping("/taskList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "resourceManagment/taskList";
    }

    @RequestMapping(value = "/taskList/tasks")
    public @ResponseBody
    String getData() {
        Map<String, Object> initData = new HashMap<>();
        DepartmentDao dao = new DepartmentDao();
        EmployeeDao empDao = new EmployeeDao();
        initData.put("departmnets", dao.getDepartmentDTOList());
        initData.put("employees", empDao.select());
        return Utils.convertOMapToJSON(initData);
    }
}
