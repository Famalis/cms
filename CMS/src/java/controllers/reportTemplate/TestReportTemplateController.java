/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reportTemplate;

import controllers.reportTemplate.general.BaseTemplateController;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String getTemplate(HttpSession session,
            HttpServletResponse response, HttpServletRequest request, Model model) {
        if (!this.checkPrivileges(session)) {
            return null;
        }
        Map<String, String[]> inputParams = request.getParameterMap();
        Map<String, String> params = new HashMap<>();

        if (inputParams.get("msg1") != null) {
            model.addAttribute("msg1", inputParams.get("msg1")[0]);
        }
        if (inputParams.get("msg2") != null) {
            model.addAttribute("msg2", inputParams.get("msg2")[0]);
        }
        if (inputParams.get("msg3") != null) {
            model.addAttribute("msg3", inputParams.get("msg3")[0]);
        }
        return "templates/testReportTemplate";

    }
}
