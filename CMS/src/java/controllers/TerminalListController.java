package controllers;

import controllers.general.BaseController;
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
    
    @RequestMapping(value = "/terminalList/save/:terminal", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String terminal) {
        TerminalDTO terDto = (TerminalDTO) Utils.convertJSONStringToObject(terminal, "terminal", TerminalDTO.class);
        System.out.println(terminal);
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
        return Utils.convertOMapToJSON(initData);
    }
}
