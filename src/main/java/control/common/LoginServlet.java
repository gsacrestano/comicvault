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

@WebServlet("/common/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UtenteDao utenteDao;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
        utenteDao = new UtenteDao(ds);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        List<String> errors = validateInputs(email, password);

        if (!errors.isEmpty()) {
            forwardToLoginPage(request, response, errors);
            return ;
        }

        email = email.trim();
        password = toHash(password.trim());

        UtenteBean utenteBean = authenticateUser(email, password);

        if (utenteBean != null)
        {
            setupUserSession(request, utenteBean);
            redirectUser(request, response, utenteBean);
        }
        else
        {
            errors.add("Username o password non validi!");
            forwardToLoginPage(request, response, errors);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private List<String> validateInputs(String email, String password) {
        List<String> errors = new ArrayList<>();

        if (email == null || email.trim().isEmpty())
            errors.add("Il campo 'email' non può essere vuoto!");

        if (password == null || password.trim().isEmpty())
            errors.add("Il campo 'password' non può essere vuoto!");

        return errors;
    }

    private void forwardToLoginPage(HttpServletRequest request, HttpServletResponse response, List<String> errors) throws ServletException, IOException {
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("/common/login.jsp").forward(request, response);
    }

    private UtenteBean authenticateUser(String email, String password) {
        try
        {
            UtenteBean utenteBean = utenteDao.doRetrieveByEmail(email);

            if (utenteBean != null && utenteBean.getPassword().equals(password))
                return utenteBean;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return null;
    }

    private void setupUserSession(HttpServletRequest request, UtenteBean utenteBean) {
        request.getSession().setAttribute("account", utenteBean);
        request.getSession().setAttribute("isAdmin", utenteBean.getIsAdmin());
        request.getSession().setAttribute("isLoggedIn", Boolean.TRUE);
        request.getSession().setAttribute("userId", utenteBean.getId());
    }

    private void redirectUser(HttpServletRequest request, HttpServletResponse response, UtenteBean utenteBean) throws IOException {
        if (utenteBean.getIsAdmin() == 1)
            response.sendRedirect(request.getContextPath() + "/admin/homepage.jsp");
        else
            response.sendRedirect(request.getContextPath() + "/common/homepage.jsp");
    }

    private String toHash(String str) {
        StringBuilder hashString = null;

        try
        {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            hashString = new StringBuilder();

            for (int i = 0; i < hash.length; i++)
                hashString.append(Integer.toHexString((hash[i] & 0xFF) | 0x100), 1, 3);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return hashString.toString();
    }
}