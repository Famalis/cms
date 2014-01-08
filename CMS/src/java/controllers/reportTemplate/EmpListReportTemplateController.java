/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reportTemplate;

import controllers.reportTemplate.general.BaseTemplateController;
import dao.DepartmentDao;
import dao.EmployeeDao;
import dto.EmployeeDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sergio
 */
@Controller
@RequestMapping("/empListReportTemplate")
public class EmpListReportTemplateController extends BaseTemplateController {

    public EmpListReportTemplateController() {
        super();
    }

    @RequestMapping("/empListReportTemplate")
    public String print(HttpSession session,
            HttpServletResponse response, HttpServletRequest request, Model model) {
        if (!this.checkPrivileges(session)) {
            return "missingPrivilege";
        }
        Map<String, String[]> inputParams = request.getParameterMap();
        Map<String, Object> params = new HashMap<>();

        DepartmentDao deptDao = new DepartmentDao();        
        EmployeeDao empDao = new EmployeeDao();

        if (inputParams.get("deptNumber") != null) {
            String num = inputParams.get("deptNumber")[0];
            Department d = deptDao.findById(num).get(0);
            List<EmployeeDTO> empDto = empDao.getEmployeeDTOList("id", num);
            params.put("employees", empDto);
            params.put("deptName", d.getName());
        }
        model.addAllAttributes(params);
        return "templates/empListReportTemplate";
    }

}
