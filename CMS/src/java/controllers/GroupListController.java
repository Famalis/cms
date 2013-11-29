/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.PrivilegeDao;
import dao.PrivilegeGroupDao;
import dao.PrivilegeKeyDao;
import dao.UserConfigurationDao;
import dto.GroupDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.Privilege;
import model.PrivilegeGroup;
import model.PrivilegeKey;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/groupList")
public class GroupListController extends BaseController {

    public GroupListController() {
        super("all","ManageGroups");
    }

    @RequestMapping("/groupList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "configuration/groupList";
    }

    @RequestMapping(value = "/groupList/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    void saveData(@RequestBody String object) {
        GroupDTO dto = (GroupDTO) Utils.convertJSONStringToObject(object, "object", GroupDTO.class);
        if (dto != null) {
            PrivilegeGroup actualGroup = new PrivilegeGroup();
            PrivilegeDao privilegeDao = new PrivilegeDao();
            if (dto.getId() != null) {
                actualGroup.loadObject("id=" + dto.getId());
                actualGroup.setName(dto.getName());
                actualGroup.update();
                privilegeDao.delete("groupId=" + actualGroup.getId());
            } else {
                actualGroup.setName(dto.getName());
                actualGroup.insert();
            }
            if (dto.getPrivilegeKeyIds() != null) {
                for (Long keyId : dto.getPrivilegeKeyIds()) {
                    Privilege p = new Privilege();
                    p.setGroupId(actualGroup.getId() + "");
                    p.setKeyId(keyId + "");
                    p.insert();
                }
            }

        }

    }

    @RequestMapping(value = "/groupList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object) {
        System.out.println("delete");
        GroupDTO dto = (GroupDTO) Utils.convertJSONStringToObject(object, "object", GroupDTO.class);
        if (dto != null) {
            PrivilegeGroupDao privilegeGroupDao = new PrivilegeGroupDao();
            UserConfigurationDao userConfigDao = new UserConfigurationDao();
            PrivilegeDao privilegeDao = new PrivilegeDao();
            privilegeDao.delete("groupId=" + dto.getId());
            privilegeGroupDao.delete("id=" + dto.getId());
            userConfigDao.updateFieldForAllElementsWithId(
                    "groupId", dto.getId()+"", 
                    "groupId", null);

        }

    }

    @RequestMapping(value = "/groupList/groups")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        PrivilegeGroupDao groupDao = new PrivilegeGroupDao();
        List<PrivilegeGroup> list = groupDao.select();
        List<GroupDTO> groups = new ArrayList<>();
        for (PrivilegeGroup g : list) {
            groups.add(new GroupDTO(g));
        }

        PrivilegeKeyDao privKeyDao = new PrivilegeKeyDao();
        List<PrivilegeKey> privKeys = privKeyDao.select();

        Map<String, Object> initData = new HashMap<>();
        initData.put("groups", groups);
        initData.put("privilegeKeys", privKeys);
        System.out.println(Utils.convertOMapToJSON(initData));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        return new ResponseEntity<String>(Utils.convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }
}
