/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DictionaryTypeDao;
import controllers.general.BaseController;
import dao.DictionaryDao;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import model.DictionaryType;
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
 * @author sergio
 */
@Controller
@RequestMapping("/dictionaryTypeList")
public class DictionaryTypeController extends BaseController {

    public DictionaryTypeController() {
        super("all", "ViewDictType");
    }

    @RequestMapping("/dictionaryTypeList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "configuration/dictionaryType";
    }

    @RequestMapping(value = "/dictionaryTypeList/dictTypes")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        DictionaryTypeDao dictTypeDao = new DictionaryTypeDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("dictTypes", dictTypeDao.select());
        return Utils.createResponseEntity(session, initData);
    }

    @RequestMapping(value = "/dictionaryTypeList/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        System.out.println(object);
        DictionaryType dictType = (DictionaryType) Utils.convertJSONStringToObject(object, "object", DictionaryType.class);
        if (dictType != null) {
            if (dictType.getId() != null) {
                dictType.update();
            } else {
                dictType.insert();
            }
            Map<String, Object> data = new HashMap<>();
            data.put("id", dictType.getId());
            return Utils.createResponseEntity(session, data);
        }
        return null;
    }

    @RequestMapping(value = "/dictionaryTypeList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object, HttpSession session) {
        DictionaryType dictType = (DictionaryType) Utils.convertJSONStringToObject(object, "object", DictionaryType.class);
        System.out.println(object);
        if (dictType != null) {
            DictionaryDao dictDao = new DictionaryDao();
            dictDao.deleteAllMatching("dictTypeId", dictType.getId() + "");
            dictType.delete();
        }
    }

}
