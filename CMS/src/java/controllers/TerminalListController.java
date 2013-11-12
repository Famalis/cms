/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.TerminalDao;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/terminalList")
public class TerminalListController extends BaseController{
 
    public TerminalListController() {
        super("all", "ManageTerminals");
    }
    
    @RequestMapping("/terminalList")
    public String home(HttpSession session, ModelMap model) {
        if(!this.checkPrivileges(session)) {
            return "terminalList";
        }
        System.out.println("home");
        return "resourceManagment/terminalList";
    } 
    
    @RequestMapping(value = "/terminalList/save/:terminal", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String user) {
        //TODO
        
    }
    
    @RequestMapping(value = "/terminalList/terminals")
    public @ResponseBody String getData() {       
        TerminalDao terminalDao = new TerminalDao();     
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("terminals", terminalDao.select());
        return Utils.convertOMapToJSON(initData);
    }
}
