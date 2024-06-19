package control.common;

import model.bean.IndirizzoBean;
import model.bean.UtenteBean;
import model.dao.IndirizzoDao;
import model.dao.UtenteDao;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/common/UpdateAddressServlet")
public class UpdateAddressServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        int addressId = Integer.parseInt(request.getParameter("id"));


        IndirizzoDao indirizzoDao = new IndirizzoDao((DataSource) getServletContext().getAttribute("DataSource"));

        IndirizzoBean indirizzoBean;

        try
        {
            indirizzoBean = indirizzoDao.doRetrieveByKey(addressId);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        //Controllo se l'indirizzo esiste
        if (indirizzoBean == null)
        {
            response.sendRedirect(request.getContextPath() + "/common/login.jsp");
            return ;
        }

        // Recupera i nuovi dati dal form
        String via = request.getParameter("via");
        String citta = request.getParameter("citta");
        String provincia = request.getParameter("provincia");
        String cap = request.getParameter("cap");
        String nazione = request.getParameter("nazione");

        // Aggiorna l'oggetto utente con i nuovi dati
        indirizzoBean.setVia(via);
        indirizzoBean.setCitta(citta);
        indirizzoBean.setProvincia(provincia);
        indirizzoBean.setCap(cap);
        indirizzoBean.setNazione(nazione);

        // Aggiorna il database
        try
        {
            indirizzoDao.doUpdate(indirizzoBean);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        // Successo: Reindirizza alla lista di indirizzi
        response.sendRedirect(request.getContextPath() + "/common/RetrieveAccountAddresses");
    }
}
