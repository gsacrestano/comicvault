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

        if (path.equals("/common/login.jsp") || path.equals("/common/Login")) {
            if (isLoggedIn != null && isLoggedIn) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.jsp");
                return ;
            } else {
                chain.doFilter(request, response);
                return ;
            }
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
