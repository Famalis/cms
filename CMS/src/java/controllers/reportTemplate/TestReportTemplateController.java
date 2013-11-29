/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reportTemplate;

import controllers.reportTemplate.general.BaseTemplateController;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
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
            HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException{
        if (!this.checkPrivileges(session)) {
            //return "missingPrivilege";
        }
        Map<String, String[]> inputParams = request.getParameterMap();
        Map<String, String> params = new HashMap<>();

        if (inputParams.get("msg1") != null) {
            params.put("${msg1}", new String(inputParams.get("msg1")[0].getBytes("ISO-8859-1"),"UTF-8"));
        }
        if (inputParams.get("msg2") != null) {
            params.put("${msg2}", new String(inputParams.get("msg2")[0].getBytes("ISO-8859-1"),"UTF-8"));
        }
        if (inputParams.get("msg3") != null) {
            params.put("${msg3}", new String(inputParams.get("msg3")[0].getBytes("ISO-8859-1"),"UTF-8"));
        }
        generatePdf(response, request, params);        
    }

}
