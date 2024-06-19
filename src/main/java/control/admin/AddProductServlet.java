package control.admin;

import model.bean.ProdottoBean;
import model.dao.ProdottoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

@WebServlet("/admin/AddProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,    // 1 MB
        maxFileSize = 1024 * 1024 * 5,      // 5 MB
        maxRequestSize = 1024 * 1024 * 10)  // 10 MB
public class AddProductServlet extends HttpServlet {

    private ProdottoDao prodottoDao;
    private static final String UPLOAD_DIR = "/images/products";

    @Override
    public void init() throws ServletException {
        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        prodottoDao = new ProdottoDao(dataSource);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            String fileName = uploadFile(request);

            ProdottoBean prodotto = createProdottoBean(request, fileName);

            prodottoDao.doSave(prodotto);

            redirectToCatalogPage(request, response);
        }
        catch (SQLException e)
        {
            throw new ServletException("ERROR IN 'AddProductServlet.java': PRODUCT CREATION FAILED", e);
        }
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("image_path");

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); //Sanitizzare il nome del file
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

        //Controllo esistenza directory di salvataggio
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File file = new File(uploadDir, fileName);
        filePart.write(file.getAbsolutePath());

        return fileName;
    }

    private ProdottoBean createProdottoBean(HttpServletRequest request, String fileName) {
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String isbn = request.getParameter("isbn");
        float prezzo = Float.parseFloat(request.getParameter("prezzo"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));

        ProdottoBean prodotto = new ProdottoBean();
        prodotto.setNome(nome);
        prodotto.setDescrizione(descrizione);
        prodotto.setIsbn(isbn);
        prodotto.setPrezzo(prezzo);
        prodotto.setQuantita(quantita);
        prodotto.setImage_path(fileName);

        return prodotto;
    }

    private void redirectToCatalogPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/common/catalog.jsp").forward(request, response);
    }
}