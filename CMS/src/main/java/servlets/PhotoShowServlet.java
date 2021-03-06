package servlets;

import dao.EmployeeDao;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SystemFileDao;
import java.util.logging.Logger;
import model.Employee;
import model.SystemFile;
import utils.HexConverter;

public class PhotoShowServlet extends HttpServlet {

    private Logger LOGGER; 
    private static final long serialVersionUID = 1L;

    public PhotoShowServlet() {
        super();
        LOGGER = Logger.getLogger(this.getClass().getCanonicalName());
    }
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String pesel = request.getParameter("pesel");
        String empId = request.getParameter("empId");
        SystemFileDao fileDao = new SystemFileDao();
        try {
            SystemFile photo = null;
            if (pesel != null) {
                photo = fileDao.findByField("name", pesel + "Photo").get(0);
            } else if (empId != null){
                EmployeeDao employeeDao = new EmployeeDao();           
                Employee emp = employeeDao.findById(empId);
                photo = fileDao.findByField("name",emp.getPESEL() + "Photo").get(0);
            }

            byte barray[] = HexConverter.toBytesFromHex(photo.getHashCode());
            //String get_price = rs.getString(5);
            response.setContentType("image/gif");
            response.setContentLength(barray.length);
            response.getOutputStream().write(barray);
            //out.println("Price in Rs. " + get_price);

            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (NullPointerException nullEx) {
            LOGGER.warning(nullEx.getLocalizedMessage());
        } catch (IndexOutOfBoundsException indexEx) {
            LOGGER.warning(indexEx.getLocalizedMessage());
        }
    }

}
