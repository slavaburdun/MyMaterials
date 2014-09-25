package controllers.chainelements;

import controllers.DAO.CRUD.MySQLCustomerDAO;
import controllers.DAO.CRUD.MySQLTourAgentDAO;
import controllers.DAO.CRUD.MySQLTourDAO;
import controllers.chainelements.interfaces.ChainElement;
import exceptions.ConnectionToDBException;
import exceptions.ViewForwardException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;
import models.TourAgent;
import org.apache.log4j.Logger;

/**
 * Authorize user in system.Checks login, password according to selected role.
 *
 * @author kelebra
 * @see controllers.chainelements.interfaces.ChainElement
 */
public class Authorization extends ChainElement {

    /**
     * Simple Authorization.
     *
     * @param request
     * @param response
     * @param log
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, Logger log) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("values.properties"));
            if (request.getParameter(properties.getProperty("command_param")).equals(properties.getProperty("command_authorization"))
                    && request.getParameter(properties.getProperty("parameter_role")) != null
                    && !request.getParameter(properties.getProperty("parameter_role")).isEmpty()) {
                if (request.getParameter(properties.getProperty("parameter_role")).equals(properties.getProperty("customer_role"))) {
                    try {
                        MySQLCustomerDAO action = new MySQLCustomerDAO();
                        int customerId = action.exists(request.getParameter(properties.getProperty("login")), request.getParameter(properties.getProperty("password")));
                        if (customerId != -1) {
                            Customer customer = action.get(customerId);
                            if (customer != null) {
                                MySQLTourDAO tours = new MySQLTourDAO();
                                customer.setTours(tours.getToursOfCustomer(customer));
                                HttpSession session = request.getSession(true);
                                session.setAttribute(properties.getProperty("customer_role"), customer);
                                session.removeAttribute("authorize");
                                viewForward(request, response, properties.getProperty("customer_view"));
                            } else {
                                viewError(request, response, properties.getProperty("error_parameters_authorization_wrongId"));
                            }
                        } else {
                            viewError(request, response, properties.getProperty("error_parameters_authorization_wrongId"));
                        }
                    } catch (ViewForwardException ex) {
                        log.error(properties.getProperty("error_viewForward"));
                    } catch (ConnectionToDBException ex) {
                        log.error(properties.getProperty("error_db"));
                    }
                } else if (request.getParameter(properties.getProperty("parameter_role")).equals(properties.getProperty("role_manager"))) {
                    try {
                        MySQLTourAgentDAO action = new MySQLTourAgentDAO();
                        int agentId = action.exists(request.getParameter(properties.getProperty("login")), request.getParameter(properties.getProperty("password")));
                        if (agentId != -1) {
                            MySQLTourAgentDAO actionAgent = new MySQLTourAgentDAO();
                            TourAgent agent = actionAgent.get(agentId);
                            HttpSession session = request.getSession(true);
                            session.setAttribute(properties.getProperty("agent_role"), agent);
                            session.removeAttribute("authorize");
                            try {
                                viewForward(request, response, properties.getProperty("agent_view"));
                            } catch (ViewForwardException ex) {
                                log.error(properties.getProperty("error_viewForward"));
                            }
                        } else {
                            viewError(request, response, properties.getProperty("error_smthInParams"));
                        }
                    } catch (ConnectionToDBException ex) {
                        log.error(properties.getProperty("error_db"));
                    }
                }
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
