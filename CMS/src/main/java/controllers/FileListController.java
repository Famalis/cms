/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.SystemFileDao;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SystemFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/fileList")
public class FileListController extends BaseController {

    public FileListController() {
        super("all", "FileList");
    }

    @RequestMapping("/fileList")
    public String home(HttpSession session, ModelMap model) {
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "resourceManagment/fileList";
    }

    @RequestMapping(value = "/fileList/files")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<String, Object>();
        SystemFileDao fileDao = new SystemFileDao();
        initData.put("files", fileDao.getReportDtos());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        return new ResponseEntity<String>(Utils.convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/fileList/download")
    public @ResponseBody
    void download(@RequestParam("id") Long id, HttpServletResponse response) {
        SystemFile r = new SystemFile();
        r.loadObject("id=" + id);
        Utils.download(r.getHashCode(), r.getName(), r.getMimeType(), response);
    }

}
