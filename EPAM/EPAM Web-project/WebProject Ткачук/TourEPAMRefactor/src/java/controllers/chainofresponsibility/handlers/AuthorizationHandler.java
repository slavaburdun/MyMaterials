package controllers.chainofresponsibility.handlers;

import controllers.DAO.CRUD.MySQLCustomerDAO;
import controllers.DAO.CRUD.MySQLTourAgentDAO;
import controllers.chainofresponsibility.interfaces.ChainElement;
import controllers.exceptions.ConnectionToDBException;
import controllers.exceptions.ViewForwardException;
import controllers.sha256.Sha256;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
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
public class AuthorizationHandler extends ChainElement {

    /**
     * Simple Authorization.
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
            if (request.getParameter(properties.getProperty("command_param")).equals(properties.getProperty("command_authorization"))) {
                String destination = "";
                String message = null;
                MySQLTourAgentDAO agentAction = new MySQLTourAgentDAO(dbLog);
                MySQLCustomerDAO customerAction = new MySQLCustomerDAO(dbLog);
                String[] params = {"login", "password"};
                if (checkParameters(request, response, params)) {
                    int userId = agentAction.exists(request.getParameter("login"), Sha256.getHash(request.getParameter("password")));
                    if (userId != -1) {
                        TourAgent agent = agentAction.get(userId);
                        HttpSession session = request.getSession(true);
                        session.setAttribute(properties.getProperty("agent_role"), agent);
                        destination = "AgentView.jsp";
                    } else {
                        userId = customerAction.exists(request.getParameter("login"), Sha256.getHash(request.getParameter("password")));
                        if (userId != -1) {
                            Customer customer = customerAction.get(userId);
                            HttpSession session = request.getSession(true);
                            session.setAttribute(properties.getProperty("customer_role"), customer);
                            destination = "CustomerView.jsp";
                        } else {
                            message = properties.getProperty("error_smthInParams");
                        }
                    }
                } else {
                    message = properties.getProperty("no_data_provided");
                }
                System.out.println(message);
                agentAction.closeConnection();
                customerAction.closeConnection();
                sendTo(request, response, destination, message);
            }
        } catch (ViewForwardException ex) {
            log.error("ViewForwardException");
        } catch (IOException ex) {
            log.error("IOException");
        } catch (ConnectionToDBException ex) {
            log.error("ConnectionToDBException");
        }
    }
}
