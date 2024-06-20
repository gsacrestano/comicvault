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
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoCarrelloDao = new ProdottoCarrelloDao(ds);
        prodottoDao = new ProdottoDao(ds);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartId = (int) request.getSession().getAttribute("cartId");

        try {
            List<ProdottoCarrelloBean> prodottiCarrello = prodottoCarrelloDao.doRetrieveAllByCartKey(cartId);
            List<ProdottoBean> prodotti = new LinkedList<>();

            // Aggiungi le informazioni dettagliate di ciascun prodotto
            for (ProdottoCarrelloBean prodottoCarrello : prodottiCarrello) {
                ProdottoBean prodotto = prodottoDao.doRetrieveByKey(prodottoCarrello.getIdProdotto());
                prodotti.add(prodotto);
            }


            request.setAttribute("prodottiCarrello", prodottiCarrello);
            request.setAttribute("items", prodotti);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        request.getRequestDispatcher("/common/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implementazione se necessario per aggiornare o rimuovere prodotti dal carrello
    }
}
