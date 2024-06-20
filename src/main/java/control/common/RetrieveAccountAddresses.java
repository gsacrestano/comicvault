package control.common;

import model.bean.IndirizzoBean;
import model.bean.UtenteIndirizzoBean;
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
import java.util.Collection;
import java.util.LinkedList;

@WebServlet("/common/RetrieveAccountAddresses")
public class RetrieveAccountAddresses extends HttpServlet {

    private UtenteIndirizzoDao utenteIndirizzoDao;
    private IndirizzoDao indirizzoDao;

    @Override
    public void init() throws ServletException {
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        utenteIndirizzoDao = new UtenteIndirizzoDao(dataSource);
        indirizzoDao = new IndirizzoDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null)
        {
            redirectToLogin(response, request);
            return ;
        }

        try
        {
            Collection<UtenteIndirizzoBean> userAddresses = utenteIndirizzoDao.doRetrieveAll(userId, "idIndirizzo");
            LinkedList<IndirizzoBean> completeAddresses = getCompleteAddresses(userAddresses);
            request.getSession().setAttribute("addresses", completeAddresses);

            String fromCart = request.getParameter("fromCart");

            if (fromCart != null && fromCart.equals("true")) {
                String total = request.getParameter("total");

                request.setAttribute("total", total);

                request.getRequestDispatcher("/common/selectAddress.jsp").forward(request, response);
                return ;
            }

            response.sendRedirect(request.getContextPath() + "/common/manageAddresses.jsp");
        }
        catch (SQLException e)
        {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private LinkedList<IndirizzoBean> getCompleteAddresses(Collection<UtenteIndirizzoBean> userAddresses) throws SQLException {
        LinkedList<IndirizzoBean> completeAddresses = new LinkedList<>();

        for (UtenteIndirizzoBean userAddress : userAddresses) {
            IndirizzoBean address = indirizzoDao.doRetrieveByKey(userAddress.getIdIndirizzo());
            completeAddresses.add(address);
        }

        return completeAddresses;
    }

    private void redirectToLogin(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.sendRedirect(request.getContextPath() + "/common/login.jsp");
    }
}