package control.admin;

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

@WebServlet("/admin/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProdottoDao prodottoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoDao = new ProdottoDao(dataSource);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            ProdottoBean updatedProduct = extractProductFromRequest(request);

            prodottoDao.doUpdate(updatedProduct);

            response.sendRedirect(request.getContextPath() + "/admin/RetrieveProductsServlet");
        }
        catch (NumberFormatException | SQLException e)
        {
            handleExceptionAndRedirect(request, response, e);
        }
    }

    private ProdottoBean extractProductFromRequest(HttpServletRequest request) throws NumberFormatException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String isbn = request.getParameter("isbn");
        float prezzo = Float.parseFloat(request.getParameter("prezzo"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        String image_path = request.getParameter("image_path");

        ProdottoBean updatedProduct = new ProdottoBean();

        updatedProduct.setId(id);
        updatedProduct.setNome(nome);
        updatedProduct.setDescrizione(descrizione);
        updatedProduct.setIsbn(isbn);
        updatedProduct.setPrezzo(prezzo);
        updatedProduct.setQuantita(quantita);
        updatedProduct.setImage_path(image_path);

        return updatedProduct;
    }

    private void handleExceptionAndRedirect(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        e.printStackTrace();
        response.sendRedirect(request.getContextPath() + "/admin/updateProduct.jsp?error=true");
    }
}