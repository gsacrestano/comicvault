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
        try {
            List<OrdineBean> ordini = (List<OrdineBean>) ordineDao.doRetrieveAll("id");

            //Recupero dati completi
            List<OrdineCompletoBean> ordiniCompleti = new LinkedList<>();

            for (OrdineBean ordine : ordini) {

                OrdineCompletoBean ordineBean = new OrdineCompletoBean();

                UtenteDao userDao = new UtenteDao((DataSource) getServletContext().getAttribute("DataSource"));
                IndirizzoDao indirizzoDao = new IndirizzoDao((DataSource) getServletContext().getAttribute("DataSource"));

                UtenteBean userBean = userDao.doRetrieveByKey(ordine.getIdUtente());
                IndirizzoBean indirizzoBean = indirizzoDao.doRetrieveByKey(ordine.getIdIndirizzo());

                ordineBean.setId(ordine.getId());
                ordineBean.setEmailUtente(userBean.getEmail());
                ordineBean.setIndirizzo(indirizzoBean.getVia() + ", " + indirizzoBean.getCitta() + " (" + indirizzoBean.getCap() + ")");
                ordineBean.setData(ordine.getData());
                ordineBean.setTotale(ordine.getTotale());

                ordiniCompleti.add(ordineBean);
            }

            request.setAttribute("orders", ordiniCompleti);

            request.getRequestDispatcher("/admin/manageOrders.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero degli ordini dal database");
        }
    }
}
