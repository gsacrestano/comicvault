package control.admin;

import model.bean.IndirizzoBean;
import model.bean.UtenteBean;
import model.dao.IndirizzoDao;
import model.dao.OrdineDao;
import model.bean.OrdineBean;
import model.dao.UtenteDao;
import model.temp.OrdineCompletoBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/admin/ManageOrdersServlet")
public class ManageOrdersServlet extends HttpServlet {

    private OrdineDao ordineDao;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        ordineDao = new OrdineDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            List<OrdineCompletoBean> ordiniCompleti = retrieveCompleteOrders();

            request.setAttribute("orders", ordiniCompleti);

            request.getRequestDispatcher("/admin/manageOrders.jsp").forward(request, response);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero degli ordini dal database");
        }
    }

    private List<OrdineCompletoBean> retrieveCompleteOrders() throws SQLException {
        List<OrdineBean> ordini = (List<OrdineBean>) ordineDao.doRetrieveAll("id");

        List<OrdineCompletoBean> ordiniCompleti = new LinkedList<>();

        for (OrdineBean ordine : ordini) {
            UtenteBean utente = retrieveUser(ordine.getIdUtente());
            IndirizzoBean indirizzo = retrieveAddress(ordine.getIdIndirizzo());

            OrdineCompletoBean ordineCompleto = new OrdineCompletoBean();

            ordineCompleto.setId(ordine.getId());
            ordineCompleto.setEmailUtente(utente.getEmail());
            ordineCompleto.setIndirizzo(indirizzo.getVia() + ", " + indirizzo.getCitta() + " (" + indirizzo.getCap() + ")");
            ordineCompleto.setData(ordine.getData());
            ordineCompleto.setTotale(ordine.getTotale());

            ordiniCompleti.add(ordineCompleto);
        }

        return ordiniCompleti;
    }

    private UtenteBean retrieveUser(int userId) throws SQLException {
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        UtenteDao userDao = new UtenteDao(dataSource);
        return userDao.doRetrieveByKey(userId);
    }

    private IndirizzoBean retrieveAddress(int addressId) throws SQLException {
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        IndirizzoDao indirizzoDao = new IndirizzoDao(dataSource);
        return indirizzoDao.doRetrieveByKey(addressId);
    }
}