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
        try
        {
            // Recupera i dati per le sezioni della pagina
            List<ProdottoBean> latestProducts = fetchLatestProducts();
            List<ProdottoBean> bestSellingProducts = fetchBestSellingProducts();
            List<ProdottoBean> upcomingProducts = fetchUpcomingProducts();

            // Passa i dati recuperati come attributi alla richiesta
            setRequestAttributes(request, latestProducts, bestSellingProducts, upcomingProducts);

            // Inoltra la richiesta alla JSP per la visualizzazione dei dati
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        catch (SQLException e)
        {
            throw new ServletException("Errore durante il recupero dei dati dal database", e);
        }
    }

    private List<ProdottoBean> fetchLatestProducts() throws SQLException {
        return (List<ProdottoBean>) prodottoDao.doRetrieveLatest(3);
    }

    private List<ProdottoBean> fetchBestSellingProducts() throws SQLException {
        return (List<ProdottoBean>) prodottoDao.doRetrieveBestSelling(3);
    }

    private List<ProdottoBean> fetchUpcomingProducts() throws SQLException {
        return (List<ProdottoBean>) prodottoDao.doRetrieveLatest(3);  // Assuming doRetrieveUpcoming is a method in ProdottoDao
    }

    private void setRequestAttributes(HttpServletRequest request, List<ProdottoBean> latestProducts, List<ProdottoBean> bestSellingProducts, List<ProdottoBean> upcomingProducts) {
        request.setAttribute("latestProducts", latestProducts);
        request.setAttribute("bestSellingProducts", bestSellingProducts);
        request.setAttribute("upcomingProducts", upcomingProducts);
    }
}