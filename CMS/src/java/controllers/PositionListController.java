/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.PositionDao;
import dto.PositionDTO;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.Position;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;


@Controller
@RequestMapping("/positionList")
public class PositionListController extends BaseController{
    
    public PositionListController() {
        super("all","ManagePositions");
    }
    
    @RequestMapping("/positionList")
    public String home(HttpSession session, ModelMap model) {
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "resourceManagment/positionList";
    } 
    
    @RequestMapping(value = "/positionList/save/:object", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody String object) {
        PositionDTO posDto = (PositionDTO) Utils.convertJSONStringToObject(object, "object", PositionDTO.class);
        System.out.println(object);
        if (posDto!=null) {
            Position actualPos = new Position();
            if(posDto.getId()!=null) {
                actualPos.loadObject("id="+posDto.getId());
            }
            actualPos.setName(posDto.getName());
            actualPos.setDescription(posDto.getDescription());
             if(actualPos.getId()!=null) {
                actualPos.update();
            } else {
                actualPos.insert();
            }
        }
    }
    
    @RequestMapping(value = "/positionList/positions")
    public @ResponseBody String getData() {       
        PositionDao positionDao = new PositionDao();     
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("positions", positionDao.select());
        return Utils.convertOMapToJSON(initData);
    }
}
