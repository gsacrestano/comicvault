package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "/filters/AccessControlFilter", urlPatterns = "/*")
public class AccessControlFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Boolean isAdmin = null;
        Boolean isLoggedIn = null;

        try {
            Object isAdminObj = httpServletRequest.getSession().getAttribute("isAdmin");
            Object isLoggedInObj = httpServletRequest.getSession().getAttribute("isLoggedIn");

            if (isAdminObj instanceof Boolean) {
                isAdmin = (Boolean) isAdminObj;
            } else if (isAdminObj instanceof Integer) {
                isAdmin = ((Integer) isAdminObj) != 0;
            }

            if (isLoggedInObj instanceof Boolean) {
                isLoggedIn = (Boolean) isLoggedInObj;
            } else if (isLoggedInObj instanceof Integer) {
                isLoggedIn = ((Integer) isLoggedInObj) != 0;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String path = httpServletRequest.getServletPath();

        // Reindirizza a IndexServlet se la richiesta è per index.jsp
        if (path.equals("/index.jsp")) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/common/IndexServlet");
            return;
        }

        //Consentire sempre accesso al catalogo
        if (path.equals("/common/catalog.jsp") || path.equals("/common/CatalogServlet")) {
            chain.doFilter(request, response);
            return ;
        }

        //Consentire sempre accesso alle pagine del footer
        if (path.equals("/common/catalog.jsp") || path.equals("/common/info.jsp")) {
            chain.doFilter(request, response);
            return ;
        }

        //Consentire sempre accesso alla pagina di dettaglio del prodotto
        if (path.equals("/common/productDetails.jsp") || path.equals("/common/ProductDetailsServlet")) {
            chain.doFilter(request, response);
            return ;
        }

        //Consentire sempre accesso alla pagina index
        if (path.equals("/common/IndexServlet")) {
            chain.doFilter(request, response);
            return ;
        }

        //Gestione accesso alla pagina di login e registrazione
        if (path.equals("/common/login.jsp") || path.equals("/common/Login") || path.equals("/common/signup.jsp")) {
            if (isLoggedIn != null && isLoggedIn) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.jsp");
                return ;
            } else {
                chain.doFilter(request, response);
                return ;
            }
        }

        //Gestione permessi generale per utenti loggati
        if (path.contains("/common/") && (isAdmin==null)) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/common/login.jsp");
            return ;
        } else if (path.contains("/admin/") && (isAdmin==null || !isAdmin)) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/common/login.jsp");
            return ;
        }

        chain.doFilter(request, response);
    }
}
