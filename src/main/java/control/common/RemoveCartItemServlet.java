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

@WebServlet("/common/RemoveCartItemServlet")
public class RemoveCartItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProdottoCarrelloDao prodottoCarrelloDao;
    private ProdottoDao prodottoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoCarrelloDao = new ProdottoCarrelloDao(ds);
        prodottoDao = new ProdottoDao(ds);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int cartId = (int) request.getSession().getAttribute("cartId");

        try
        {
            // Recupera il prodotto dal database
            ProdottoBean product = prodottoDao.doRetrieveByKey(productId);

            ProdottoCarrelloBean prodottoCarrello = prodottoCarrelloDao.doRetrieveByKey(cartId, productId);

            //Ripristinare quantità in magazzino
            product.setQuantita(product.getQuantita() + prodottoCarrello.getQuantita());
            prodottoDao.doUpdate(product);

            prodottoCarrelloDao.doDelete(cartId, productId);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // Reindirizza alla pagina del carrello dopo la rimozione
        response.sendRedirect(request.getContextPath() + "/common/RetrieveAccountCartServlet");
    }
}