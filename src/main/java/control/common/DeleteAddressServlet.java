package control.common;

import model.dao.IndirizzoDao;
import model.dao.UtenteIndirizzoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/common/DeleteAddressServlet")
public class DeleteAddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IndirizzoDao indirizzoDao;
    private UtenteIndirizzoDao utenteIndirizzoDao;

    @Override
    public void init() throws ServletException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        indirizzoDao = new IndirizzoDao(ds);
        utenteIndirizzoDao = new UtenteIndirizzoDao(ds);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer addressId = Integer.parseInt(request.getParameter("id"));

        if (addressId == null )
        {
            response.sendRedirect(request.getContextPath() + "/common/manageAddresses.jsp?error=deletionFailed");
            return ;
        }

        try
        {
            int id = addressId;

            boolean deleted1 = utenteIndirizzoDao.doDelete(id);
            boolean deleted2 = indirizzoDao.doDelete(id);

            if (!deleted1 || !deleted2)
            {
                response.sendRedirect(request.getContextPath() + "/common/manageAddresses.jsp?error=deletionFailed");
                return ;
            }

            response.sendRedirect(request.getContextPath() + "/common/RetrieveAccountAddresses");
        } catch (NumberFormatException e)
        {
            response.sendRedirect(request.getContextPath() + "/common/manageAddresses.jsp?error=deletionFailed");
        } catch (SQLException e)
        {
            throw new ServletException(e);
        }
    }
}


