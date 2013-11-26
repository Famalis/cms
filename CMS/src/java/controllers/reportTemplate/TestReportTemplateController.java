/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reportTemplate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import controllers.reportTemplate.general.BaseTemplateController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/testReportTemplate")
public class TestReportTemplateController extends BaseTemplateController {

    public TestReportTemplateController() {
        super();
    }

    @RequestMapping("/testReportTemplate")
    public void home(HttpSession session,
            HttpServletResponse response, HttpServletRequest request) {
        if (!this.checkPrivileges(session)) {
            //return "missingPrivilege";
        }
        Map<String, String[]> inputParams = request.getParameterMap();
        Map<String, String> params = new HashMap<>();

        if (inputParams.get("msg1") != null) {
            params.put("${msg1}", inputParams.get("msg1")[0]);
        }
        if (inputParams.get("msg2") != null) {
            params.put("${msg2}", inputParams.get("msg2")[0]);
        }
        if (inputParams.get("msg3") != null) {
            params.put("${msg3}", inputParams.get("msg3")[0]);
        }
        generatePdf(response, request, params);        
    }

}
