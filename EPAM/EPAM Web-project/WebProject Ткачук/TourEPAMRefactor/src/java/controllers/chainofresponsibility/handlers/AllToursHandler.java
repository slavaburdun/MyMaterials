package controllers.chainofresponsibility.handlers;

import controllers.DAO.CRUD.MySQLTourDAO;
import controllers.chainofresponsibility.interfaces.ChainElement;
import controllers.exceptions.ChainElementException;
import controllers.exceptions.ConnectionToDBException;
import controllers.exceptions.ViewForwardException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Tour;
import org.apache.log4j.Logger;

/**
 * Displays all tours to Agent.
 *
 * @author kelebra
 */
public class AllToursHandler extends ChainElement {

    /**
     * Checks session and parameters, then retrieves all tours from db.
     *
     * @param request
     * @param response
     * @param log
     * @throws ChainElementException
     * @see controllers.chainelements.interfaces.ChainElement
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, Logger log, Logger dbLog) throws ChainElementException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("values.properties"));
            if (request.getParameter(properties.getProperty("command_param")).equals(properties.getProperty("command_allTours"))) {
                try {
                    HttpSession session = request.getSession(false);
                    try {
                        if (session.getAttribute(properties.getProperty("agent_role")) != null) {
                            MySQLTourDAO action = new MySQLTourDAO(dbLog);
                            LinkedList<Tour> allTours = (LinkedList<Tour>) action.getAll();
                            action.closeConnection();
                            request.setAttribute(properties.getProperty("command_allTours"), allTours);
                            viewForward(request, response, properties.getProperty("view_allTours"));
                        } else {
                            viewError(request, response, properties.getProperty("error_not_agent"));
                        }
                    } catch (ConnectionToDBException ex) {
                        log.error("DB exception");
                        viewError(request, response, "DB exception");
                    } catch (NullPointerException ex) {
                        log.error(properties.getProperty("view_error"));
                        viewForward(request, response, properties.getProperty("view_error"));
                    }
                } catch (ViewForwardException ex) {
                    throw new ChainElementException();
                }
            }
        } catch (IOException ex) {
            log.error("IOException");
        }
    }
}
