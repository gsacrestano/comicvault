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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer addressId = parseAddressId(request, response);

        if (addressId == null)
            redirectToErrorPage(response, "deletionFailed");

        try
        {
            if (!deleteAddress(addressId))
                redirectToErrorPage(response, "deletionFailed");

            redirectToPage(response, "/common/RetrieveAccountAddresses");
        }
        catch (SQLException e)
        {
            throw new ServletException(e);
        }
    }

    private Integer parseAddressId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try
        {
            return Integer.parseInt(request.getParameter("id"));
        }
        catch (NumberFormatException e)
        {
            redirectToErrorPage(response, "deletionFailed");
            return null;
        }
    }

    private boolean deleteAddress(int addressId) throws SQLException {
        boolean userAddressDeleted = utenteIndirizzoDao.doDelete(addressId);
        boolean addressDeleted = indirizzoDao.doDelete(addressId);
        return userAddressDeleted && addressDeleted;
    }

    private void redirectToErrorPage(HttpServletResponse response, String errorMessage) throws IOException {
        response.sendRedirect(getServletContext().getContextPath() + "/common/manageAddresses.jsp?error=" + errorMessage);
    }

    private void redirectToPage(HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(getServletContext().getContextPath() + path);
    }
}