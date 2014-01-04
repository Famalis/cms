package servlets;

import dao.EmployeeDao;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SystemFileDao;
import model.SystemFile;
import utils.HexConverter;

/**
 * Servlet implementation class FileCounter
 */
public class PhotoShowServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    int count;
    private SystemFileDao dao;

    @Override
    public void init() throws ServletException {
        dao = new SystemFileDao();
        try {
            count = dao.select().size();
        } catch (Exception e) {
            getServletContext().log("An exception occurred in FileCounter", e);
            throw new ServletException("An exception occurred in FileCounter"
                    + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String pesel = request.getParameter("pesel");
        String empId = request.getParameter("empId");
        try {
            SystemFile photo = null;
            if (pesel.length() > 0) {
                photo = dao.findByField("name", pesel + "Photo").get(0);
            } else if (empId.length()>0){
                EmployeeDao employeeDao = new EmployeeDao();                
                photo = dao.findByField("name", employeeDao.findById(empId).get(0) + "Photo").get(0);
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
            nullEx.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
