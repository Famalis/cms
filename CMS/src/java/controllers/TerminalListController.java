package controllers;

import controllers.general.BaseController;
import dao.LogDao;
import dao.TerminalDao;
import dto.TerminalDTO;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.Terminal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

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
    
    @RequestMapping(value = "/terminalList/save/:object", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String object) {
        TerminalDTO terDto = (TerminalDTO) Utils.convertJSONStringToObject(object, "object", TerminalDTO.class);
        System.out.println(object);
        if (terDto!=null) {
            Terminal actualTer = new Terminal();
            if(terDto.getId()!=null) {
                actualTer.loadObject("id="+terDto.getId());
            }
            actualTer.setDescription(terDto.getDescription());
             if(actualTer.getId()!=null) {
                actualTer.update();
            } else {
                actualTer.insert();
            }
        }
    }
    
    @RequestMapping(value = "/terminalList/terminals")
    public @ResponseBody String getData() {
        TerminalDao terminalDao = new TerminalDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("terminalDtos", terminalDao.getTerminalDtos());
        System.out.println(Utils.convertOMapToJSON(initData));
        return Utils.convertOMapToJSON(initData);
    }
    
    @RequestMapping(value = "terminalList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        TerminalDTO ter = (TerminalDTO) Utils.convertJSONStringToObject(object, "object", TerminalDTO.class);
        if (ter != null) {
            TerminalDao terDao = new TerminalDao();
            LogDao logDao = new LogDao();
            terDao.deleteAllWithId(ter.getId()+"");
            logDao.deleteAllMatching("terminalId", ter.getId()+"");
        }

    }
    
}
