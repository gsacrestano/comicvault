package control.common;

import model.bean.UtenteBean;
import model.dao.UtenteDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/common/UpdateAccountServlet")
public class UpdateAccountServlet extends HttpServlet {

    private UtenteDao utenteDao;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        utenteDao = new UtenteDao(dataSource);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null)
        {
            redirectToLogin(response, request);
            return ;
        }

        UtenteBean utente = retrieveUserById(userId);

        if (utente == null)
        {
            redirectToLogin(response, request);
            return ;
        }

        updateUserData(request, utente);

        try
        {
            utenteDao.doUpdate(utente);
        }
        catch (SQLException e)
        {
            throw new ServletException("Errore durante l'aggiornamento dell'utente", e);
        }

        request.getSession().setAttribute("account", utente);

        response.sendRedirect(request.getContextPath() + "/common/homepage.jsp");
    }

    private UtenteBean retrieveUserById(int userId) throws ServletException {
        try
        {
            return utenteDao.doRetrieveByKey(userId);
        }
        catch (SQLException e)
        {
            throw new ServletException("Errore durante il recupero dell'utente", e);
        }
    }

    private void updateUserData(HttpServletRequest request, UtenteBean utente) {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String telefono = request.getParameter("phone");

        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setTelefono(telefono);
    }

    private void redirectToLogin(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.sendRedirect(request.getContextPath() + "/common/login.jsp");
    }
}