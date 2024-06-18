package control.admin;

import model.bean.IndirizzoBean;
import model.bean.OrdineBean;
import model.bean.UtenteBean;
import model.dao.IndirizzoDao;
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

@WebServlet("/admin/ManageUsersServlet")
public class ManageUsersServlet extends HttpServlet {

    private UtenteDao utenteDao;

    @Override
    public void init() throws ServletException {
        super.init();

        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        utenteDao = new UtenteDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<UtenteBean> utenti = (List<UtenteBean>) utenteDao.doRetrieveAll("id");

            request.setAttribute("users", utenti);

            request.getRequestDispatcher("/admin/manageUsers.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero degli utenti dal database");
        }
    }
}
