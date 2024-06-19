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
import java.util.LinkedList;

@WebServlet("/common/ProductDetailsServlet")
public class ProductDetailsServlet extends HttpServlet {

    private ProdottoDao prodottoDao;

    @Override
    public void init() throws ServletException {
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoDao = new ProdottoDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = parseProductId(request.getParameter("id"));

        try
        {

            if (!checkProductIdValidity(productId))
            {
                redirectToPageWithError(request, response,"Id del prodotto non valido");
                return ;
            }

            ProdottoBean product = prodottoDao.doRetrieveByKey(productId);

            if (product == null)
            {
                redirectToPageWithError(request, response,"Prodotto non trovato");
                return ;
            }

            request.setAttribute("product", product);

            request.getRequestDispatcher("/common/productDetails.jsp").forward(request, response);
        }
        catch (SQLException e)
        {
            throw new ServletException("Errore durante il recupero del prodotto", e);
        }
    }

    private int parseProductId(String idParam) {
        try
        {
            return Integer.parseInt(idParam);
        }
        catch (NumberFormatException e)
        {
            return -1;
        }
    }

    private Boolean checkProductIdValidity(int productId) throws SQLException {
        LinkedList<ProdottoBean> latestProducts = (LinkedList<ProdottoBean>) prodottoDao.doRetrieveAll("id");

        if ((productId <= 0) || (productId > latestProducts.getLast().getId()))
            return Boolean.FALSE;

        return Boolean.TRUE;
    }

    private void redirectToPageWithError(HttpServletRequest request, HttpServletResponse response, String error) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, error);
    }
}
