package control.common;

import model.bean.UtenteBean;
import model.dao.UtenteDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/common/UpdateAccountServlet")
public class UpdateAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // Controllo se utente è autenticato
        if (userId == null)
        {
            response.sendRedirect(request.getContextPath() + "/common/login.jsp");
            return ;
        }

        UtenteDao utenteDao = new UtenteDao((DataSource) getServletContext().getAttribute("DataSource"));
        UtenteBean utente;

        try
        {
            utente = utenteDao.doRetrieveByKey(userId);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        //Controllo se l'utente esiste
        if (utente == null)
        {
            response.sendRedirect(request.getContextPath() + "/common/login.jsp");
            return ;
        }

        // Recupera i nuovi dati dal form
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String telefono = request.getParameter("telefono");

        // Aggiorna l'oggetto utente con i nuovi dati
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setTelefono(telefono);

        // Aggiorna il database
        try
        {
            utenteDao.doUpdate(utente);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        // Aggiorna l'oggetto utente nella sessione
        session.setAttribute("account", utente);

        // Successo: Reindirizza alla homepage
        response.sendRedirect(request.getContextPath() + "/common/homepage.jsp");


    }
}
