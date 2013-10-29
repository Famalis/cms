/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.PrivilegeDao;
import dao.PrivilegeGroupDao;
import dao.PrivilegeKeyDao;
import dto.GroupDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.Privilege;
import model.PrivilegeGroup;
import model.PrivilegeKey;
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
        super("");
    }

    @RequestMapping("/groupList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");

        return "groupList";
    }

    @RequestMapping(value = "/groupList/save/:group", method = RequestMethod.POST)
    public @ResponseBody
    void saveData(@RequestBody String group) {
        GroupDTO dto = (GroupDTO) Utils.convertJSONStringToObject(group, "group", GroupDTO.class);
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

    @RequestMapping(value = "/groupList/delete/:group", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String group) {
        System.out.println("delete");
        GroupDTO dto = (GroupDTO) Utils.convertJSONStringToObject(group, "group", GroupDTO.class);
        if (dto != null) {
            PrivilegeGroupDao privilegeGroupDao = new PrivilegeGroupDao();
            PrivilegeDao privilegeDao = new PrivilegeDao();
            privilegeDao.delete("groupId=" + dto.getId());
            privilegeGroupDao.delete("id=" + dto.getId());

        }

    }

    @RequestMapping(value = "/groupList/groups")
    public @ResponseBody
    String getData() {
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
        return Utils.convertOMapToJSON(initData);
    }
}
