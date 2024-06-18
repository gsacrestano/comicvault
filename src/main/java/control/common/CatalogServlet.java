package control.common;

import model.bean.ProdottoBean;
import model.dao.ProdottoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/common/CatalogServlet")
public class CatalogServlet extends HttpServlet {

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
            // Recupera tutti i prodotti
            List<ProdottoBean> products = (List<ProdottoBean>) prodottoDao.doRetrieveAll("id");
            int priceAttribute = Integer.MAX_VALUE;

            try
            {
                priceAttribute = Integer.parseInt(request.getParameter("maxPrice"));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            if (priceAttribute <= 0)
                priceAttribute = Integer.MAX_VALUE;

            final int  maxPrice = priceAttribute;

            //Filtrare i prodotti da mostrare
            products = products.stream().filter(product -> product.getPrezzo() <= maxPrice).collect(Collectors.toList());

            // Passa i dati recuperati come attributi alla richiesta
            request.setAttribute("products", products);

            // Inoltra la richiesta alla JSP
            request.getRequestDispatcher("/common/catalog.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Errore durante il recupero dei dati dal database", e);
        }
    }
}
