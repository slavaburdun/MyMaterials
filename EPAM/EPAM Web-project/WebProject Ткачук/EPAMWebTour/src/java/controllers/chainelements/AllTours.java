package controllers.chainelements;

import controllers.DAO.CRUD.MySQLTourDAO;
import controllers.chainelements.interfaces.ChainElement;
import exceptions.ChainElementException;
import exceptions.ConnectionToDBException;
import exceptions.ViewForwardException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;
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
public class AllTours extends ChainElement {

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
    public void processRequest(HttpServletRequest request, HttpServletResponse response, Logger log) throws ChainElementException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("values.properties"));
            if (request.getParameter(properties.getProperty("command_param")).equals(properties.getProperty("command_allTours"))) {
                try {
                    HttpSession session = request.getSession(false);
                    try {
                        if (session.getAttribute(properties.getProperty("agent_role")) != null) {
                            MySQLTourDAO action = new MySQLTourDAO();
                            LinkedList<Tour> allTours = (LinkedList<Tour>) action.getAll();
                            request.setAttribute(properties.getProperty("command_allTours"), allTours);
                            viewForward(request, response, properties.getProperty("view_allTours"));
                        } else {
                            viewError(request, response, properties.getProperty("error_not_agent"));
                        }
                    } catch (NullPointerException ex) {
                        viewForward(request, response, properties.getProperty("view_error"));
                    }
                } catch (ViewForwardException ex) {
                    throw new ChainElementException();
                } catch (ConnectionToDBException ex) {
                    log.error(properties.getProperty("error_db"));
                }
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AllTours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
