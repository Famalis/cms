/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reportTemplate.general;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import controllers.general.BaseController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sergio
 */
public class BaseTemplateController extends BaseController {

    public BaseTemplateController() {
        super("all", "ReportsPrint");
    }

    protected void generatePdf(HttpServletResponse response, HttpServletRequest request,
            Map<String, String> params) {
        File newFile = new File("file");
        response.setHeader("Content-Disposition", "attachment;filename=report.pdf");
        response.setContentType("application/pdf");
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        String filePath = "\\WEB-INF\\templates\\testReportTemplate.html";
        File sourceFile = new File(appPath + filePath);
        
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "UTF-8"));
            String line = "";
            List<String> lines = new ArrayList<>();
            List<String> usedKeys = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                for (String attrName : params.keySet()) {
                    if (line.contains(attrName) && !usedKeys.contains(attrName)) {
                        String attrValue = params.get(attrName);
                        line = line.replace(attrName, attrValue);
                        usedKeys.add(attrName);
                    }

                }
                lines.add(line);
            }

            br.close();
            PrintWriter out = new PrintWriter(newFile);
            for (String l : lines) {
                out.println(l);
            }
            out.close();
        } catch (IOException io) {
            System.err.println("Error preparing html template for pdf");
            io.printStackTrace();
        } 

        try {
            Document document = new Document();
            //FileOutputStream fos = new FileOutputStream("report.pdf");
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            
            document.open();
            
            FileInputStream fis = new FileInputStream(newFile);
            
            worker.parseXHtml(writer, document, fis);
            
            document.close();
            fis.close();
        } catch (IOException | DocumentException io) {
            System.err.println("DEBUG: Error creating pdf file");
            io.printStackTrace();
        }
    }
}
