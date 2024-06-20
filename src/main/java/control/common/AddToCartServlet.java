package control.common;

import model.bean.CarrelloBean;
import model.bean.ProdottoBean;
import model.bean.ProdottoCarrelloBean;
import model.dao.CarrelloDao;
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

@WebServlet("/common/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProdottoDao prodottoDao;
    private CarrelloDao carrelloDao;
    private ProdottoCarrelloDao prodottoCarrelloDao;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoDao = new ProdottoDao(ds);
        carrelloDao = new CarrelloDao(ds);
        prodottoCarrelloDao = new ProdottoCarrelloDao(ds);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity")); // Nuovo parametro per la quantità desiderata

        try {
            // Recupera il prodotto dal database
            ProdottoBean product = prodottoDao.doRetrieveByKey(productId);

            if (product == null) {
                // Gestione caso prodotto non trovato
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // Verifica se la quantità richiesta è disponibile
            if (quantity <= 0 || quantity > product.getQuantita()) {
                // Gestione caso quantità non valida o non disponibile
                response.sendRedirect(request.getContextPath() + "/productNotAvailable.jsp");
                return;
            }

            // Recupera il carrello dell'utente dalla sessione
            CarrelloBean carrello = (CarrelloBean) request.getSession().getAttribute("cart");

            if (carrello == null || carrello.getId() == -1) {
                // Se il carrello non esiste, crea un nuovo carrello per l'utente
                carrello = new CarrelloBean();
                carrello.setIdUtente((int) request.getSession().getAttribute("userId"));
                carrelloDao.doSave(carrello);
                request.getSession().setAttribute("carrello", carrello);
            }

            // Verifica se il prodotto è già nel carrello
            ProdottoCarrelloBean existingItem = prodottoCarrelloDao.doRetrieveByKey(carrello.getId(), productId);

            if (existingItem != null) {
                // Se il prodotto è già nel carrello, incrementa la quantità
                existingItem.setQuantita(existingItem.getQuantita() + quantity);
                prodottoCarrelloDao.doUpdate(existingItem);
            } else {
                // Altrimenti, crea un nuovo elemento nel carrello
                ProdottoCarrelloBean newItem = new ProdottoCarrelloBean();

                newItem.setIdCarrello(carrello.getId());
                newItem.setIdProdotto(productId);
                newItem.setPrezzo(product.getPrezzo());
                newItem.setQuantita(quantity);

                prodottoCarrelloDao.doSave(newItem);
            }

            // Aggiorna la quantità disponibile nel magazzino
            product.setQuantita(product.getQuantita() - quantity);
            prodottoDao.doUpdate(product);

            // Reindirizza alla pagina di conferma aggiunta al carrello
            response.sendRedirect(request.getContextPath() + "/common/RetrieveAccountCartServlet");

        } catch (SQLException e) {
            // Gestione dell'errore SQL
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/errore.jsp");
        }
    }
}
