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
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoDao = new ProdottoDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            List<ProdottoBean> products = (List<ProdottoBean>) prodottoDao.doRetrieveAll("id");

            int maxPrice = extractMaxPrice(request);

            products = filterProductsByPrice(products, maxPrice);

            request.setAttribute("products", products);

            request.getRequestDispatcher("/common/catalog.jsp").forward(request, response);
        }
        catch (SQLException e)
        {
            throw new ServletException("Errore durante il recupero dei dati dal database", e);
        }
    }

    private int extractMaxPrice(HttpServletRequest request) {
        int maxPrice;

        try
        {
            maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
        }
        catch (NumberFormatException e)
        {
            maxPrice = Integer.MAX_VALUE;
        }

        return maxPrice > 0 ? maxPrice : Integer.MAX_VALUE;
    }

    private List<ProdottoBean> filterProductsByPrice(List<ProdottoBean> products, int maxPrice) {
        return products.stream()
                .filter(product -> product.getPrezzo() <= maxPrice)
                .collect(Collectors.toList());
    }
}