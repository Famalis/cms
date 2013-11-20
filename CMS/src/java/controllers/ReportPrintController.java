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
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Report;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.HexConverter;
import utils.Utils;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/reportPrint")
public class ReportPrintController extends BaseController {

    public ReportPrintController() {
        super("all", "PrintReports");
    }

    @RequestMapping("/reportPrint")
    public String home(HttpSession session, ModelMap model) {
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        System.out.println("home");
        return "resourceManagment/reportPrint";
    }

    @RequestMapping(value = "/reportPrint/reports")
    public @ResponseBody
    String getData() {
        Map<String, Object> initData = new HashMap<String, Object>();
        ReportDao reportDao = new ReportDao();
        initData.put("reports", reportDao.getReportDtos());
        return Utils.convertOMapToJSON(initData);
    }

    @RequestMapping(value = "/reportPrint/print")
    public @ResponseBody
    void download(@RequestParam("id") Long id, HttpServletResponse response) {
        Report r = new Report();
        r.loadObject("id=" + id);
        Utils.download(r.getHashCode(), r.getName(), r.getMimeType(), response);
    }
    
    @RequestMapping(value = "/reportPrint/testForm")
    public String testFormLoad() {
        return "reportForms/testReportForm";
    }

}
