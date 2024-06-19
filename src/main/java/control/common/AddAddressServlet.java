package control.common;

import model.bean.IndirizzoBean;
import model.bean.ProdottoBean;
import model.bean.UtenteIndirizzoBean;
import model.dao.IndirizzoDao;
import model.dao.ProdottoDao;
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

        //Prelevare id dell'account
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        // Estrai i parametri della richiesta
        String via = request.getParameter("via");
        String citta = request.getParameter("citta");
        String provincia = request.getParameter("provincia");
        String cap = request.getParameter("cap");
        String nazione = request.getParameter("nazione");


        // Crea un nuovo IndirizzoBean
        IndirizzoBean indirizzoBean = new IndirizzoBean();
        indirizzoBean.setVia(via);
        indirizzoBean.setCitta(citta);
        indirizzoBean.setProvincia(provincia);
        indirizzoBean.setCap(cap);
        indirizzoBean.setNazione(nazione);

        //Trovare id disponibile
        LinkedList<IndirizzoBean> latestAddresses = new LinkedList<>();

        try
        {
            latestAddresses= (LinkedList<IndirizzoBean>) indirizzoDao.doRetrieveAll("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        indirizzoBean.setId(latestAddresses.getLast().getId() + 1);

        System.out.println(indirizzoBean.toString());

        try
        {
            // Salva il prodotto usando il DAO
            indirizzoDao.doSave(indirizzoBean);

            UtenteIndirizzoBean utenteIndirizzoBean = new UtenteIndirizzoBean();

            utenteIndirizzoBean.setIdIndirizzo(indirizzoBean.getId());
            utenteIndirizzoBean.setIdUtente(userId);
            utenteIndirizzoBean.setIsDefault(0);

            utenteIndirizzoDao.doSave(utenteIndirizzoBean);
        } catch (SQLException e) {
            throw new ServletException("Database error during product insertion", e);
        }

        //SUCCESSO: Reindirizzare alla pagina per visualizzare gli indirizzi
        response.sendRedirect(request.getContextPath() + "/common/RetrieveAccountAddresses");
    }
}


