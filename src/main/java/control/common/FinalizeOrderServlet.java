package control.common;

import model.bean.OrdineBean;
import model.bean.ProdottoCarrelloBean;
import model.bean.ProdottoOrdineBean;
import model.dao.OrdineDao;
import model.dao.ProdottoCarrelloDao;
import model.dao.ProdottoOrdineDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/common/FinalizeOrderServlet")
public class FinalizeOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrdineDao ordineDao;
    private ProdottoCarrelloDao prodottoCarrelloDao;
    private ProdottoOrdineDao prodottoOrdineDao;

    @Override
    public void init() throws ServletException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ordineDao = new OrdineDao(ds);
        prodottoCarrelloDao = new ProdottoCarrelloDao(ds);
        prodottoOrdineDao = new ProdottoOrdineDao(ds);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        int cartId = (int) request.getSession().getAttribute("cartId");
        float cartTotal = Float.valueOf(request.getParameter("total"));


        int addressId = Integer.parseInt(request.getParameter("addressId"));

        try
        {
            List<ProdottoCarrelloBean> prodottiCarrello = prodottoCarrelloDao.doRetrieveAllByCartKey(cartId);

            OrdineBean ordine = new OrdineBean();
            ordine.setIdUtente(userId);
            ordine.setIdIndirizzo(addressId);
            ordine.setTotale(cartTotal);

            // Salva l'ordine e ottieni l'ID generato
            int ordineId = ordineDao.doSave(ordine);

            // Salva ciascun prodotto del carrello come ProdottoOrdineBean
            for (ProdottoCarrelloBean prodottoCarrello : prodottiCarrello) {
                ProdottoOrdineBean prodottoOrdine = new ProdottoOrdineBean();

                prodottoOrdine.setIdOrdine(ordineId);
                prodottoOrdine.setIdProdotto(prodottoCarrello.getIdProdotto());
                prodottoOrdine.setPrezzo(prodottoCarrello.getPrezzo());
                prodottoOrdine.setQuantita(prodottoCarrello.getQuantita());

                prodottoOrdineDao.doSave(prodottoOrdine);
            }

            //Rimuovere dati temporanei del carrello
            prodottoCarrelloDao.doDeleteProductsByCartId(cartId);


            // Rimuovi l'attributo totale dalla sessione dopo aver finalizzato l'ordine
            request.getSession().removeAttribute("total");

            response.sendRedirect(request.getContextPath() + "/common/homepage.jsp");
        }
        catch (SQLException e)
        {
            throw new ServletException("Database error", e);
        }
    }
}
