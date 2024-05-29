package config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class MainContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        DataSource ds = null;

        try
        {
            Context initCx = new InitialContext();
            Context envCtx = (Context) initCx.lookup("java:comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/comicvault");
        }
        catch (NamingException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        context.setAttribute("DataSource", ds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
