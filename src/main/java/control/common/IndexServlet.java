package control.common;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.bean.ProdottoBean;
import model.dao.ProdottoDao;

@WebServlet(value = "/common/IndexServlet", loadOnStartup = 1)
public class IndexServlet extends HttpServlet {

    private ProdottoDao prodottoDao;

    @Override
    public void init() throws ServletException {
        super.init();

        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoDao = new ProdottoDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recupera i dati per le sezioni della pagina (novità, più venduti, prossime uscite)
            List<ProdottoBean> latestProducts = (List<ProdottoBean>) prodottoDao.doRetrieveLatest(3);
            List<ProdottoBean> bestSellingProducts = (List<ProdottoBean>) prodottoDao.doRetrieveBestSelling(3);
            List<ProdottoBean> upcomingProducts = (List<ProdottoBean>) prodottoDao.doRetrieveLatest(3);

            // Passa i dati recuperati come attributi alla richiesta
            request.setAttribute("latestProducts", latestProducts);
            request.setAttribute("bestSellingProducts", bestSellingProducts);
            request.setAttribute("upcomingProducts", upcomingProducts);

            System.out.println(latestProducts.toString());
            System.out.println(bestSellingProducts.toString());
            System.out.println(upcomingProducts.toString());

            // Inoltra la richiesta alla JSP per la visualizzazione dei dati
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Errore durante il recupero dei dati dal database", e);
        }
    }
}
