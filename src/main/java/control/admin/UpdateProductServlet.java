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

@WebServlet("/admin/UpdateProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,    // 1 MB
        maxFileSize = 1024 * 1024 * 5,      // 5 MB
        maxRequestSize = 1024 * 1024 * 10)  // 10 MB
public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProdottoDao prodottoDao;
    private static final String UPLOAD_DIR = "/images/products";

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
            String filename = uploadFile(request);

            ProdottoBean updatedProduct = extractProductFromRequest(request, filename);

            prodottoDao.doUpdate(updatedProduct);

            response.sendRedirect(request.getContextPath() + "/admin/RetrieveProductsServlet");
        }
        catch (NumberFormatException | SQLException e)
        {
            handleExceptionAndRedirect(request, response, e);
        }
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("image_path");

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); //Sanitizzare il nome del file

        if (fileName.isEmpty() || fileName.trim().equals(""))
            return "";

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

    private ProdottoBean extractProductFromRequest(HttpServletRequest request, String fileName) throws NumberFormatException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String isbn = request.getParameter("isbn");
        float prezzo = Float.parseFloat(request.getParameter("prezzo"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));

        ProdottoBean updatedProduct = new ProdottoBean();

        updatedProduct.setId(id);
        updatedProduct.setNome(nome);
        updatedProduct.setDescrizione(descrizione);
        updatedProduct.setIsbn(isbn);
        updatedProduct.setPrezzo(prezzo);
        updatedProduct.setQuantita(quantita);

        if (fileName.isEmpty() || fileName.trim().equals(""))
            updatedProduct.setImage_path(prodottoDao.doRetrieveByKey(id).getImage_path());
        else
            updatedProduct.setImage_path(fileName);

        return updatedProduct;
    }

    private void handleExceptionAndRedirect(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        e.printStackTrace();
        response.sendRedirect(request.getContextPath() + "/admin/updateProduct.jsp?error=true");
    }
}