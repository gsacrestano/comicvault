package control.common;

import model.bean.ProdottoBean;
import model.bean.ProdottoCarrelloBean;
import model.dao.ProdottoCarrelloDao;
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
import java.util.List;

@WebServlet("/common/RetrieveAccountCartServlet")
public class RetrieveAccountCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProdottoCarrelloDao prodottoCarrelloDao;
    private ProdottoDao prodottoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoCarrelloDao = new ProdottoCarrelloDao(dataSource);
        prodottoDao = new ProdottoDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartId = (int) request.getSession().getAttribute("cartId");

        try
        {
            List<ProdottoCarrelloBean> cartProducts = prodottoCarrelloDao.doRetrieveAllByCartKey(cartId);
            List<ProdottoBean> productDetails = getProductDetails(cartProducts);
            float total = calculateTotal(cartProducts);

            request.setAttribute("prodottiCarrello", cartProducts);
            request.setAttribute("items", productDetails);
            request.setAttribute("total", total);

            request.getRequestDispatcher("/common/cart.jsp").forward(request, response);
        }
        catch (SQLException e)
        {
            throw new ServletException("Database error while retrieving cart items", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private List<ProdottoBean> getProductDetails(List<ProdottoCarrelloBean> cartProducts) throws SQLException {
        List<ProdottoBean> productDetails = new LinkedList<>();
        for (ProdottoCarrelloBean cartProduct : cartProducts) {
            ProdottoBean product = prodottoDao.doRetrieveByKey(cartProduct.getIdProdotto());
            productDetails.add(product);
        }
        return productDetails;
    }

    private float calculateTotal(List<ProdottoCarrelloBean> cartProducts) {
        float total = 0;
        for (ProdottoCarrelloBean cartProduct : cartProducts) {
            total += cartProduct.getQuantita() * cartProduct.getPrezzo();
        }
        return total;
    }
}