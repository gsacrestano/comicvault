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
            response.sendRedirect(request.getContextPath() + "productList.jsp?error=missingId");
            return ;
        }

        try
        {
            int id = Integer.parseInt(idParam);
            boolean deleted = prodottoDao.doDelete(id);

            if (!deleted)
                response.sendRedirect(request.getContextPath() + "/admin/RetrieveProductsServlet?error=deletionFailed");

            response.sendRedirect(request.getContextPath() + "/admin/RetrieveProductsServlet");
        } catch (NumberFormatException e)
        {
            response.sendRedirect(request.getContextPath() + "/admin/RetrieveProductsServlet?error=deletionFailed");
        } catch (SQLException e)
        {
            throw new ServletException(e);
        }
    }
}

