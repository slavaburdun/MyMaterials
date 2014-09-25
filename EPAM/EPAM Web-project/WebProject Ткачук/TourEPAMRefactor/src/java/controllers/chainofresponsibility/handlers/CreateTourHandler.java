package controllers.chainofresponsibility.handlers;

import controllers.chainofresponsibility.interfaces.ChainElement;
import controllers.exceptions.ViewForwardException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class CreateTourHandler extends ChainElement {

    /**
     * Redirects to tour creation page.
     *
     * @param request
     * @param response
     * @param log
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, Logger log, Logger dbLog) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("values.properties"));
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter(properties.getProperty("command_param")).equals(properties.getProperty("command_createTour"))) {
                try {
                    HttpSession session = request.getSession(false);
                    if (session.getAttribute(properties.getProperty("agent_role")) == null) {
                        viewError(request, response, properties.getProperty("error_not_agent"));
                    } else {
                        try {
                            viewForward(request, response, properties.getProperty("view_CreateTour"));
                        } catch (ViewForwardException ex) {
                            log.error(properties.getProperty("error_viewForward"));
                        }
                    }
                } catch (NullPointerException ex) {
                    viewError(request, response, properties.getProperty("error_sessionOver"));
                }
            }
        } catch (IOException ex) {
            log.error("IOException");
        }
    }
}
