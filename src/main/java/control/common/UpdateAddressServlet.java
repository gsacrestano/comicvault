package control.common;

import model.bean.IndirizzoBean;
import model.dao.IndirizzoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/common/UpdateAddressServlet")
public class UpdateAddressServlet extends HttpServlet {

    private IndirizzoDao indirizzoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        indirizzoDao = new IndirizzoDao(dataSource);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int addressId = Integer.parseInt(request.getParameter("id"));

        IndirizzoBean indirizzoBean = retrieveAddressById(addressId);

        if (indirizzoBean == null)
        {
            redirectToLogin(response, request);
            return ;
        }

        updateAddressData(request, indirizzoBean);

        try
        {
            indirizzoDao.doUpdate(indirizzoBean);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Errore durante l'aggiornamento dell'indirizzo", e);
        }

        response.sendRedirect(request.getContextPath() + "/common/RetrieveAccountAddresses");
    }

    private IndirizzoBean retrieveAddressById(int addressId) {
        try
        {
            return indirizzoDao.doRetrieveByKey(addressId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Errore durante il recupero dell'indirizzo", e);
        }
    }

    private void updateAddressData(HttpServletRequest request, IndirizzoBean indirizzoBean) {
        String via = request.getParameter("via");
        String citta = request.getParameter("citta");
        String provincia = request.getParameter("provincia");
        String cap = request.getParameter("cap");
        String nazione = request.getParameter("nazione");

        indirizzoBean.setVia(via);
        indirizzoBean.setCitta(citta);
        indirizzoBean.setProvincia(provincia);
        indirizzoBean.setCap(cap);
        indirizzoBean.setNazione(nazione);
    }

    private void redirectToLogin(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.sendRedirect(request.getContextPath() + "/common/login.jsp");
    }
}