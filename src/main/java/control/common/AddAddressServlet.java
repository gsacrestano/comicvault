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
import java.util.LinkedList;

@WebServlet("/common/AddAddressServlet")
public class AddAddressServlet extends HttpServlet {

    private IndirizzoDao indirizzoDao;
    private UtenteIndirizzoDao utenteIndirizzoDao;

    @Override
    public void init() throws ServletException {
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        indirizzoDao = new IndirizzoDao(dataSource);
        utenteIndirizzoDao = new UtenteIndirizzoDao(dataSource);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            Integer userId = (Integer) request.getSession().getAttribute("userId");

            IndirizzoBean indirizzoBean = extractAddressFromRequest(request);

            int newAddressId = findNextAddressId();

            indirizzoBean.setId(newAddressId);

            saveAddressAndUserLink(indirizzoBean, userId);

            response.sendRedirect(request.getContextPath() + "/common/RetrieveAccountAddresses");
        }
        catch (SQLException e)
        {
            throw new ServletException("Database error during address insertion", e);
        }
    }

    private IndirizzoBean extractAddressFromRequest(HttpServletRequest request) {
        String via = request.getParameter("via");
        String citta = request.getParameter("citta");
        String provincia = request.getParameter("provincia");
        String cap = request.getParameter("cap");
        String nazione = request.getParameter("nazione");

        IndirizzoBean indirizzoBean = new IndirizzoBean();

        indirizzoBean.setVia(via);
        indirizzoBean.setCitta(citta);
        indirizzoBean.setProvincia(provincia);
        indirizzoBean.setCap(cap);
        indirizzoBean.setNazione(nazione);

        return indirizzoBean;
    }

    private int findNextAddressId() throws SQLException {
        LinkedList<IndirizzoBean> latestAddresses = (LinkedList<IndirizzoBean>) indirizzoDao.doRetrieveAll("id");
        return latestAddresses.getLast().getId() + 1;
    }

    private void saveAddressAndUserLink(IndirizzoBean indirizzoBean, Integer userId) throws SQLException {
        indirizzoDao.doSave(indirizzoBean);

        UtenteIndirizzoBean utenteIndirizzoBean = new UtenteIndirizzoBean();

        utenteIndirizzoBean.setIdIndirizzo(indirizzoBean.getId());
        utenteIndirizzoBean.setIdUtente(userId);
        utenteIndirizzoBean.setIsDefault(0);

        utenteIndirizzoDao.doSave(utenteIndirizzoBean);
    }
}