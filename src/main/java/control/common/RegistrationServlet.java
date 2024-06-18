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
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/common/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private UtenteDao utenteDao;

    @Override
    public void init() throws ServletException {
        super.init();

        DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
        utenteDao = new UtenteDao(ds);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");

        List<String> errors = new ArrayList<>();

        if (nome == null || nome.isEmpty()) {
            errors.add("Il nome è obbligatorio.");
        }
        if (cognome == null || cognome.isEmpty()) {
            errors.add("Il cognome è obbligatorio.");
        }
        if (email == null || email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.add("L'email non è valida.");
        }
        if (password == null || password.isEmpty()) {
            errors.add("La password è obbligatoria.");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/common/registration.jsp").forward(request, response);
            return ;
        }

        UtenteBean newUser = new UtenteBean();
        newUser.setNome(nome);
        newUser.setCognome(cognome);
        newUser.setEmail(email);
        newUser.setPassword(toHash(password));
        newUser.setTelefono(telefono);
        newUser.setIsAdmin(0); // Imposta come non admin per default

        try {
            utenteDao.doSave(newUser);
        } catch (SQLException e) {
            throw new ServletException("Errore durante il salvataggio dei dati", e);
        }

        request.getSession().setAttribute("isAdmin", newUser.getIsAdmin());
        request.getSession().setAttribute("isLoggedIn", Boolean.TRUE);

        response.sendRedirect(request.getContextPath() + "/common/homepage.jsp");
    }

    private String toHash(String str) {
        StringBuilder hashString = null;

        try
        {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            hashString = new StringBuilder();

            for (int i = 0; i < hash.length; i++)  {
                hashString.append(Integer.toHexString((hash[i] & 0xFF) | 0x100), 1, 3);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return hashString.toString();
    }
}

