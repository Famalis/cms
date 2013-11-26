/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reportTemplate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import controllers.general.BaseController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/testReportTemplate")
public class TestReportTemplateController extends BaseController {

    public TestReportTemplateController() {
        super("all", "ReportsPrint");
    }

    @RequestMapping("/testReportTemplate")
    public String home(HttpSession session, ModelMap model,
            HttpServletResponse response, HttpServletRequest request) {
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        Map<String, String[]> inputParams = request.getParameterMap();
        Map<String, Object> params = new HashMap<>();

        if (inputParams.get("helloMsg") != null) {
            params.put("helloAttr", inputParams.get("helloMsg")[0]);
        }
        model.addAllAttributes(params);
        try {
            ServletContext context = request.getServletContext();
            String appPath = context.getRealPath("");
            String filePath = "\\WEB-INF\\templates\\testReportTemplate.html";
            File sourceFile = new File(appPath + filePath);

            Document document = new Document();
            //FileOutputStream fos = new FileOutputStream("report.pdf");
            OutputStream out = response.getOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, out);            
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            FileInputStream fis = new FileInputStream(sourceFile);
            worker.parseXHtml(writer, document, fis);
            response.setHeader("Content-Disposition", "attachment;filename=report.pdf");     
            document.close();
            fis.close();
            out.close();
            //fos.close();
            
            System.out.println(appPath + filePath + "\n" + sourceFile.isFile());
        } catch (IOException io) {

        } catch (DocumentException docEx) {

        }
        
        //response.setHeader("Content-Disposition", "attachment;filename=report.pdf");        
        System.out.println("home");
        return "reportTemplates/testReportTemplate";
    }

}
