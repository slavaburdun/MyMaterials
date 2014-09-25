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
 * Responsible for adding Tour to Customer.Gets all free tours from db and
 * display all data on page of Tour selection.
 *
 * @author kelebra
 * @see controllers.chainelements.interfaces.ChainElement
 * @since 1.5
 */
public class AddTourToCustomerHandler extends ChainElement {

    /**
     * If everything is ok with session and request parameters, gets all free
     * tours and redirects to page, mentioned above.In another case - to error
     * page.
     *
     * @param request
     * @param response
     * @param log for logging
     * @throws ChainElementException
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, Logger log, Logger dbLog) throws ChainElementException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("values.properties"));
            if (request.getParameter(properties.getProperty("command_param")).equals(properties.getProperty("command_addTourToCustomer"))) {
                try {
                    HttpSession session = request.getSession(false);
                    if (session.getAttribute(properties.getProperty("customer_role")) != null) {
                        MySQLTourDAO action = new MySQLTourDAO(dbLog);
                        LinkedList<Tour> freeTours = (LinkedList<Tour>) action.getFreeTours();
                        request.setAttribute(properties.getProperty("attribute_freeTours"), freeTours);
                        action.closeConnection();
                        viewForward(request, response, properties.getProperty("view_AddNewTour"));
                    } else {
                        viewError(request, response, properties.getProperty("error_not_customer"));
                    }
                } catch (ConnectionToDBException ex) {
                    log.error(properties.getProperty("error_db"));
                } catch (ViewForwardException ex) {
                    throw new ChainElementException();
                } catch (NullPointerException ex) {
                    log.error(properties.getProperty("error_not_customer"));
                    viewError(request, response, properties.getProperty("error_not_customer"));
                }
            }
        } catch (IOException ex) {
            log.error("IOException");
        }
    }
}
