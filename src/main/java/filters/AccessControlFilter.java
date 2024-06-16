package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "/filters/AccessControlFilter", urlPatterns = "/*")
public class AccessControlFilter extends HttpFilter implements Filter {

    private static final long serialVersionUID = 1L;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Boolean isAdmin = (Boolean) httpServletRequest.getSession().getAttribute("isAdmin");
        String path = httpServletRequest.getServletPath();

        //Consentire accesso alla pagina di login.jsp ed alla servlet Login
        if (path.equals("/common/login.jsp") || path.equals("/common/Login")) {
            chain.doFilter(request, response);
            return;
        }

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
