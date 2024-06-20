package control.common;

import model.dao.ProdottoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/common/NumProductServlet")
public class NumProductServlet  extends HttpServlet {

    ProdottoDao p;
    @Override
    public void init() throws ServletException {
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        p = new ProdottoDao(dataSource);
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        int n = 0;
        if(!name.isEmpty()) {
            try {
                System.out.println(name);
                n = (int) p.doRetrieveLatest(1000000000).stream().filter(prodottoBean -> {
                    System.out.println(prodottoBean.getNome() + "-" + prodottoBean.getNome().contains(name));
                    return prodottoBean.getNome().contains(name);
                }).count();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(n);
        // Imposta il tipo di contenuto della risposta
        response.setContentType("text/plain");

        // Scrivi il contenuto del file nel flusso di output della risposta
        try (PrintWriter out = response.getWriter()) {
            out.write(String.valueOf(n));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
