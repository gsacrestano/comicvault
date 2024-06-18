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
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
        // Estrai i parametri della richiesta
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String isbn = request.getParameter("isbn");
        float prezzo = Float.parseFloat(request.getParameter("prezzo"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));

        // Gestisci il caricamento del file
        Part filePart = request.getPart("image_path");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Sanitize the file name
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

        // Crea la cartella se non esiste
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Salva il file
        File file = new File(uploadDir, fileName);
        filePart.write(file.getAbsolutePath());

        // Crea un nuovo ProdottoBean
        ProdottoBean prodotto = new ProdottoBean();
        prodotto.setNome(nome);
        prodotto.setDescrizione(descrizione);
        prodotto.setIsbn(isbn);
        prodotto.setPrezzo(prezzo);
        prodotto.setQuantita(quantita);
        prodotto.setImage_path(fileName);

        try {
            // Salva il prodotto usando il DAO
            prodottoDao.doSave(prodotto);
            // Reindirizza a una pagina di successo o mostra un messaggio di successo
            request.getRequestDispatcher("/common/catalog.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error during product insertion", e);
        }
    }
}

