/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.general.BaseController;
import dao.ReportDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Report;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
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
@RequestMapping("/reportList")
public class ReportListController extends BaseController {

    public ReportListController() {
        super("all", "ManageReports");
    }

    @RequestMapping("/reportList")
    public String home(HttpSession session, ModelMap model) {
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "configuration/reportList";
    }

    @RequestMapping(value = "/reportList/save/:report", method = RequestMethod.POST)
    public @ResponseBody
    void saveData(@RequestBody String user) {
        //TODO

    }

    @RequestMapping(value = "/reportList/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            Report r = new Report();
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
        return "redirect:/reportList.htm";
    }

    @RequestMapping(value = "/reportList/download")
    public @ResponseBody
    void download(@RequestParam("id") Long id, HttpServletResponse response) {
        File file;
        try {
            Report r = new Report();
            r.loadObject("id=" + id);
            String hash = r.getHashCode();
            byte[] barr = HexConverter.toBytesFromHex(hash);
            file = new File(r.getName());
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(barr);
            fos.close();
            
            FileInputStream fis = new FileInputStream(file);
            response.setContentType(r.getMimeType());
            
            
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",
                    r.getName());            
            response.setHeader(headerKey, headerValue);
            
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException io) {
            System.err.println("Download IO error");
            io.printStackTrace();
        }
    }

    @RequestMapping(value = "/reportList/reports")
    public @ResponseBody
    String getData() {
        Map<String, Object> initData = new HashMap<String, Object>();
        ReportDao reportDao = new ReportDao();
        initData.put("reports", reportDao.select());
        return Utils.convertOMapToJSON(initData);
    }
}
