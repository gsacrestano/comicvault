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
import javax.servlet.http.HttpSession;
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

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        utenteIndirizzoDao = new UtenteIndirizzoDao(ds);
        indirizzoDao = new IndirizzoDao(ds);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // Controllo se utente è autenticato
        if (userId == null)
        {
            response.sendRedirect(request.getContextPath() + "/common/login.jsp");
            return ;
        }

        try
        {
            Collection<UtenteIndirizzoBean> indirizzi = utenteIndirizzoDao.doRetrieveAll(userId, "idIndirizzo");

            LinkedList<IndirizzoBean> indirizziCompleti = new LinkedList<>();

            for (UtenteIndirizzoBean indirizzo : indirizzi)
            {
                indirizziCompleti.add(indirizzoDao.doRetrieveByKey(indirizzo.getIdIndirizzo()));
            }

            session.setAttribute("addresses", indirizziCompleti);

        } catch (SQLException e)
        {
            throw new ServletException("Database error", e);
        }

        //SUCCESSO: Reindirizzare alla pagin per visualizzare gli indirizzi
        response.sendRedirect(request.getContextPath() + "/common/manageAddresses.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

