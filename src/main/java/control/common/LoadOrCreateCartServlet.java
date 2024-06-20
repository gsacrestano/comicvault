package control.common;

import model.bean.CarrelloBean;
import model.bean.UtenteBean;
import model.dao.CarrelloDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/common/LoadOrCreateCartServlet")
public class LoadOrCreateCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarrelloDao carrelloDao;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        carrelloDao = new CarrelloDao(ds);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteBean utente = (UtenteBean) request.getSession().getAttribute("account");

        if (utente == null)
        {
            response.sendRedirect(request.getContextPath() + "/common/login.jsp");
            return ;
        }

        try
        {
            // Controlla se l'utente ha già un carrello nel database
            CarrelloBean carrello = carrelloDao.doRetrieveByKey(utente.getId());

            if (carrello == null || carrello.getId() == -1) {
                // Se non esiste, crea un nuovo carrello
                carrello = new CarrelloBean();
                carrello.setIdUtente(utente.getId());
                carrelloDao.doSave(carrello);
            }

            // Salva il carrello nella sessione
            request.getSession().setAttribute("cart", carrello);

            // Redirect alla homepage dopo aver caricato/creato il carrello
            response.sendRedirect(request.getContextPath() + "/common/homepage.jsp");

        }
        catch (SQLException e)
        {
            // Gestione dell'errore SQL
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/errore.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

