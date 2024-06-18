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
        super.init();

        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoDao = new ProdottoDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int productId = -1;

        try
        {
            productId = Integer.parseInt(request.getParameter("id"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try {

            ProdottoBean product = prodottoDao.doRetrieveByKey(productId);
            LinkedList<ProdottoBean> latestProducts = (LinkedList<ProdottoBean>) prodottoDao.doRetrieveAll("id");

            if (product == null || (productId <= 0) || (productId > latestProducts.getLast().getId())) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Prodotto non trovato con ID: " + productId);
                return ;
            }

            request.setAttribute("product", product);
            request.getRequestDispatcher("/common/productDetails.jsp").forward(request, response);
        } catch (NumberFormatException | SQLException e) {
            throw new ServletException("Errore durante il recupero del prodotto", e);
        }
    }
}
