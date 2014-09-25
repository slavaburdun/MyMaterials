package logger;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Object of this class represents a logger, which is passed through all the
 * controllers.All necessary settings are located in WEB-INF/log4j.properties
 * file.This servlet is loaded first and all other servlets are loaded after it
 * to be able to do logging.
 *
 * @author kelebra
 * @see HttpServlet
 * @since 1.5
 */
public class Log4jInit extends HttpServlet {

    /**
     * Initialization of servlet.Getting all parameters from file, mentioned
     * above.
     */
    @Override
    public void init() {
        String logfilename = getInitParameter("logfile");
        String pref;
        pref = getServletContext().getRealPath("/");
        PropertyConfigurator.configure(pref + logfilename);
        Logger globallog = Logger.getRootLogger();
        getServletContext().setAttribute("log4", globallog);
        getServletContext().setAttribute("logfilename", logfilename);
        globallog.info("Load-onstart-up Servlet");
    }
/**
 * In fact not necessary.Created for test aims.
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException 
 */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String pref = getServletContext().getRealPath("/");
            String logfile = (String) getServletContext().getAttribute("logfilename");
            out.println("<html><head>");
            out.println("<title>Servlet Log4jInit</title>");
            out.println("</head><body>");
            out.println("<h2>File properties Log4jInit " + pref + logfile + "</h2>");
            out.println("</body></html>");
        } finally {
            out.close();
        }
    }
}