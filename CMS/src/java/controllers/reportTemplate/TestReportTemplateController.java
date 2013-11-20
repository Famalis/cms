/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reportTemplate;

import controllers.general.BaseController;
import java.util.HashMap;
import java.util.Map;
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
public class TestReportTemplateController extends BaseController{
    
    public TestReportTemplateController() {
        super("all", "ReportsPrint");
    }
    
    @RequestMapping("/testReportTemplate")
    public String home(HttpSession session, ModelMap model,
            HttpServletResponse response, HttpServletRequest request) {
        if(!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        Map<String, String[]> inputParams = request.getParameterMap();
        Map<String, Object> params = new HashMap<>();
        
        //params.put("helloAttr", inputParams.get("helloMsg")[0]);
        
        model.addAllAttributes(params);
        response.setHeader("Content-Disposition", "attachment;filename=report.pdf");        
        System.out.println("home");
        return "reportTemplates/testReportTemplate";
    }     
    
}
