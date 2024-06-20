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
        DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
        utenteDao = new UtenteDao(ds);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = validateInput(request);

        if (!errors.isEmpty())
        {
            forwardToRegistrationPageWithErrors(request, response, errors);
            return ;
        }

        UtenteBean newUser = createUserBeanFromRequest(request);

        try
        {
            utenteDao.doSave(newUser);

            response.sendRedirect(request.getContextPath() + "/common/login.jsp");
        }
        catch (SQLException e)
        {
            throw new ServletException("Errore durante il salvataggio dei dati", e);
        }
    }

    private List<String> validateInput(HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (isNullOrEmpty(nome)) errors.add("Il nome è obbligatorio.");
        if (isNullOrEmpty(cognome)) errors.add("Il cognome è obbligatorio.");
        if (isInvalidEmail(email)) errors.add("L'email non è valida.");
        if (isNullOrEmpty(password)) errors.add("La password è obbligatoria.");

        return errors;
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isInvalidEmail(String email) {
        return isNullOrEmpty(email) || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private void forwardToRegistrationPageWithErrors(HttpServletRequest request, HttpServletResponse response, List<String> errors)
            throws ServletException, IOException {
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("/common/registration.jsp").forward(request, response);
    }

    private UtenteBean createUserBeanFromRequest(HttpServletRequest request) {
        UtenteBean newUser = new UtenteBean();

        newUser.setNome(request.getParameter("nome"));
        newUser.setCognome(request.getParameter("cognome"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setPassword(toHash(request.getParameter("password")));
        newUser.setTelefono(request.getParameter("telefono"));
        newUser.setIsAdmin(0); // Imposta come non admin per default

        return newUser;
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