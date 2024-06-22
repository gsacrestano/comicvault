package control.admin;

import model.dao.ProdottoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProdottoDao prodottoDao;

    @Override
    public void init() throws ServletException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoDao = new ProdottoDao(ds);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            handleMissingIdError(request, response);
            return ;
        }

        try
        {
            int id = Integer.parseInt(idParam);
            boolean deleted = deleteProduct(id);

            if (!deleted)
                redirectToProductsPageWithError(request, response, "deletionFailed");

            redirectToProductsPage(request, response);
        }
        catch (NumberFormatException e)
        {
            redirectToProductsPageWithError(request, response, "deletionFailed");
        }
        catch (SQLException e)
        {
            throw new ServletException("Database error during product deletion", e);
        }
    }

    private void handleMissingIdError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/admin/productList.jsp?error=missingId");
    }

    private boolean deleteProduct(int id) throws SQLException {
        return prodottoDao.doDelete(id);
    }

    private void redirectToProductsPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/admin/RetrieveProductsServlet");
    }

    private void redirectToProductsPageWithError(HttpServletRequest request, HttpServletResponse response, String error) throws IOException {
        response.sendRedirect(request.getContextPath() + "/errors/404.jsp?error=" + error);
    }
}