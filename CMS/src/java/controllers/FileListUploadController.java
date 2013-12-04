/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.SystemFileDao;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SystemFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.HexConverter;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/fileListUpload")
public class FileListUploadController extends BaseController {

    public FileListUploadController() {
        super("all", "ManageFiles");
    }

    @RequestMapping("/fileListUpload")
    public String home(HttpSession session, ModelMap model) {
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "configuration/fileList";
    }

    @RequestMapping(value = "/fileListUpload/save/:report", method = RequestMethod.POST)
    public @ResponseBody
    void saveData(@RequestBody String user) {
        //TODO

    }

    @RequestMapping(value = "/fileListUpload/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            SystemFile r = new SystemFile();
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldname = item.getFieldName();
                    String fieldvalue = item.getString();
                    if (fieldname.equals("fileExt")) {
                        r.setMimeType(fieldvalue);
                    } else {
                        r.setDescription(fieldvalue);
                    }
                } else {
                    //String fieldname = item.getFieldName();
                    //String filename = item.getName();
                    r.setName(item.getName());
                    InputStream input = item.getInputStream();
                    byte[] barr = new byte[(int) item.getSize()];
                    input.read(barr);
                    String s = HexConverter.toHexFromBytes(barr);
                    System.out.println(s.length());
                    r.setHashCode(s);
                }
            }
            r.insert();
        } catch (FileUploadException ex) {
            System.err.println("Can't process file\n" + ex.getMessage());
        } catch (IOException io) {
            System.err.println("Can't process file\n" + io.getMessage());
        }
        return "redirect:/fileList.htm";
    }

    @RequestMapping(value = "/fileListUpload/download")
    public @ResponseBody
    void download(@RequestParam("id") Long id, HttpServletResponse response) {
        SystemFile r = new SystemFile();
        r.loadObject("id=" + id);
        Utils.download(r.getHashCode(), r.getName(), r.getMimeType(), response);
    }

    @RequestMapping(value = "/fileListUpload/reports")
    @ResponseBody
    public ResponseEntity<String> getData(HttpSession session, ModelMap model) {
        Map<String, Object> initData = new HashMap<String, Object>();
        SystemFileDao reportDao = new SystemFileDao();
        initData.put("reports", reportDao.getReportDtos());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        return new ResponseEntity<String>(Utils.convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }
}
