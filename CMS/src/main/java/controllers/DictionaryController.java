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
import model.Dictionary;
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
@RequestMapping("/dictionaryList")
public class DictionaryController extends BaseController {

    public DictionaryController() {
        super("all", "ViewDict");
    }

    @RequestMapping("/dictionaryList")
    public String home(HttpSession session, ModelMap model) {
        System.out.println("home");
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        return "configuration/dictionary";
    }

    @RequestMapping(value = "/dictionaryList/dictTypes")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        DictionaryDao dictDao = new DictionaryDao();
        DictionaryTypeDao dictTypeDao = new DictionaryTypeDao();
        Map<String, Object> initData = new HashMap<String, Object>();
        initData.put("dicts", dictDao.select());
        initData.put("dictTypes", dictTypeDao.select());
        return Utils.createResponseEntity(session, initData);
    }

    @RequestMapping(value = "/dictionaryList/save/:object", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> saveData(@RequestBody String object, HttpSession session) {
        System.out.println(object);
        Dictionary dict = (Dictionary) Utils.convertJSONStringToObject(object, "object", Dictionary.class);
        if (dict != null) {
            if (dict.getId() != null) {
                dict.update();
            } else {
                dict.insert();
            }
            Map<String, Object> data = new HashMap<>();
            data.put("id", dict.getId());
            return Utils.createResponseEntity(session, data);
        }
        return null;
    }

    @RequestMapping(value = "/dictionaryList/delete/:object", method = RequestMethod.POST)
    public @ResponseBody
    void deleteData(@RequestBody String object, HttpSession session) {
        Dictionary dict = (Dictionary) Utils.convertJSONStringToObject(object, "object", Dictionary.class);
        System.out.println(object);
        if (dict != null) {
            dict.delete();
        }
    }

}
