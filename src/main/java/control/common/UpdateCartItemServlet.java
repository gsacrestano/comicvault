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

@WebServlet("/common/UpdateCartItemServlet")
public class UpdateCartItemServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int cartId = (int) request.getSession().getAttribute("cartId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        try
        {
            // Recupera il prodotto dal database
            ProdottoBean product = prodottoDao.doRetrieveByKey(productId);
            ProdottoCarrelloBean cartProduct = prodottoCarrelloDao.doRetrieveByKey(cartId, productId);

            // Ripristina la quantità in magazzino
            product.setQuantita(product.getQuantita() + cartProduct.getQuantita());

            // Controlla se la quantità richiesta è valida
            if (quantity <= 0 || quantity > product.getQuantita()) {
                response.sendRedirect(request.getContextPath() + "/errors/404.jsp?error=" + "Quantità non valida");
                return ;
            }

            // Aggiorna il prodotto nel carrello
            if (cartProduct != null) {
                cartProduct.setQuantita(quantity);
                prodottoCarrelloDao.doUpdate(cartProduct);
            }

            // Aggiorna la quantità disponibile nel magazzino
            product.setQuantita(product.getQuantita() - quantity);
            prodottoDao.doUpdate(product);
        }
        catch (SQLException e)
        {
            throw new ServletException("Database error while updating cart item", e);
        }

        // Reindirizza alla pagina del carrello dopo l'aggiornamento
        response.sendRedirect(request.getContextPath() + "/common/RetrieveAccountCartServlet");
    }
}