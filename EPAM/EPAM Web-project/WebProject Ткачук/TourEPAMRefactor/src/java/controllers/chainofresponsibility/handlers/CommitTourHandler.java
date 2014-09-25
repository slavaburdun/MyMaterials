package controllers.chainofresponsibility.handlers;

import controllers.DAO.CRUD.MySQLTourDAO;
import controllers.chainofresponsibility.interfaces.ChainElement;
import controllers.exceptions.ViewForwardException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Tour;
import org.apache.log4j.Logger;

public class CommitTourHandler extends ChainElement {

    /**
     * Responsible for creation of tour by Agent.
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
            response.setCharacterEncoding("UTF-8");
            log.info("CommitTour enterance");
            if (request.getParameter(properties.getProperty("command_param")).equals(properties.getProperty("command_commitTour"))) {
                if (request.getParameter(properties.getProperty("tour_price")) != null
                        && request.getParameter(properties.getProperty("tour_urgent")) != null
                        && request.getParameter(properties.getProperty("tour_discount")) != null
                        && request.getParameter(properties.getProperty("tour_type")) != null
                        && !request.getParameter(properties.getProperty("tour_price")).isEmpty()
                        && !request.getParameter(properties.getProperty("tour_urgent")).isEmpty()
                        && !request.getParameter(properties.getProperty("tour_discount")).isEmpty()
                        && !request.getParameter(properties.getProperty("tour_type")).isEmpty()
                        && (request.getParameter(properties.getProperty("tour_type")).equals(properties.getProperty("constant_values_shopping"))
                        || request.getParameter(properties.getProperty("tour_type")).equals(properties.getProperty("constant_values_excursion"))
                        || request.getParameter(properties.getProperty("tour_type")).equals(properties.getProperty("constant_values_vacation")))) {
                    try {
                        HttpSession session = request.getSession(false);
                        if (session.getAttribute(properties.getProperty("agent_role")) != null) {
                            double price = Double.parseDouble(request.getParameter(properties.getProperty("tour_price")));
                            boolean urgent = Boolean.parseBoolean(request.getParameter(properties.getProperty("tour_urgent")));
                            double discount = Double.parseDouble(request.getParameter(properties.getProperty("tour_discount")));
                            String description = request.getParameter(properties.getProperty("tour_description"));
                            String type = request.getParameter(properties.getProperty("tour_type"));
                            Tour tour = new Tour();
                            tour.setDiscount(discount);
                            tour.setPrice(price);
                            tour.setType(type);
                            tour.setUrgent(urgent);
                            tour.setDescription(description);
                            MySQLTourDAO action = new MySQLTourDAO(dbLog);
                            action.insert(tour);
                            try {
                                sendTo(request, response, (properties.getProperty("agent_view")), null);
                            } catch (ViewForwardException ex) {
                                viewError(request, response, "ViewError");
                            }
                        } else {
                            viewError(request, response, properties.getProperty("error_not_agent"));
                        }
                    } catch (controllers.exceptions.ConnectionToDBException ex) {
                        log.error(properties.getProperty("error_db"));
                    } catch (NumberFormatException ex) {
                        viewError(request, response, "Bad values provided. Source: CommitTour");
                    } catch (NullPointerException ex) {
                        viewError(request, response, properties.getProperty("error_sessionOver"));
                    }
                } else {
                    viewError(request, response, "Bad values provided. Source: CommitTour");
                }
            }
        } catch (IOException ex) {
            log.error("IOException");
        }
    }
}
