package controllers.chainofresponsibility.handlers;

import controllers.DAO.CRUD.MySQLCustomerDAO;
import controllers.DAO.CRUD.MySQLTourDAO;
import controllers.chainofresponsibility.interfaces.ChainElement;
import controllers.exceptions.ViewForwardException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;
import models.Tour;
import org.apache.log4j.Logger;

/**
 * Responsible for money calculation.See models comments for detailed
 * information.
 *
 * @author kelebra
 * @see controllers.chainelements.interfaces.ChainElement
 */
public class BuyToursHandler extends ChainElement {

    /**
     * According to parameters in request and user status (regular or not)
     * calculates sum of selected tours.
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
            if (request.getParameter(properties.getProperty("command_param")).equals(properties.getProperty("parameter_buy"))) {
                try {
                    HttpSession session = request.getSession(false);
                    if (session.getAttribute(properties.getProperty("customer_role")) == null) {
                        viewError(request, response, properties.getProperty("error_not_customer"));
                    } else {
                        try {
                            MySQLTourDAO tourAction = new MySQLTourDAO(dbLog);
                            MySQLCustomerDAO customerAction = new MySQLCustomerDAO(dbLog);
                            session = request.getSession(false);
                            Customer customer = null;
                            try {
                                customer = (Customer) session.getAttribute(properties.getProperty("customer_role"));
                            } catch (NullPointerException ex) {
                                viewError(request, response, properties.getProperty("error_sessionOver"));
                            }
                            Enumeration<String> parameters = request.getParameterNames();
                            LinkedList<Tour> selectedTours = new LinkedList<Tour>();
                            int sumOforeder = 0;
                            if (customer.isRegular()) {
                                while (parameters.hasMoreElements()) {
                                    String parameter = parameters.nextElement();
                                    if (parameter.equals(properties.getProperty("command_param"))) {
                                        continue;
                                    } else {
                                        Tour currentTour = tourAction.get(Integer.parseInt(request.getParameter(parameter)));
                                        if (currentTour != null) {
                                            selectedTours.add(currentTour);
                                            if (currentTour.isUrgent()) {
                                                sumOforeder += ((currentTour.getPrice() / 4) - currentTour.getDiscount());
                                            } else {
                                                sumOforeder += (currentTour.getPrice() - currentTour.getDiscount());
                                            }
                                        } else {
                                            viewError(request, response, properties.getProperty("error_wrongId_buyTours"));
                                        }
                                    }
                                }
                            } else {
                                while (parameters.hasMoreElements()) {
                                    String parameter = parameters.nextElement();
                                    if (parameter.equals(properties.getProperty("command_param"))) {
                                        continue;
                                    } else {
                                        Tour currentTour = tourAction.get(Integer.parseInt(request.getParameter(parameter)));
                                        if (currentTour != null) {
                                            selectedTours.add(currentTour);
                                            if (currentTour.isUrgent()) {
                                                sumOforeder += (currentTour.getPrice() / 4);
                                            } else {
                                                sumOforeder += currentTour.getPrice();
                                            }
                                        } else {
                                            viewError(request, response, properties.getProperty("error_wrongId_buyTours"));
                                        }
                                    }
                                }
                            }
                            if (sumOforeder <= customer.getBalance()) {
                                customer.setBalance(customer.getBalance() - sumOforeder);
                                customer.getTours().addAll(selectedTours);
                                customerAction.update(customer);
                                request.setAttribute("message", "You have bought " + selectedTours.size() + " tours!");
                                try {
                                    tourAction.closeConnection();
                                    customerAction.closeConnection();
                                    viewForward(request, response, properties.getProperty("customer_view"));
                                } catch (ViewForwardException ex) {
                                    log.error("ViewForwardException in BuyTours");
                                }
                            } else {
                                request.setAttribute("message", properties.getProperty("not_enough_money"));
                                try {
                                    viewForward(request, response, properties.getProperty("customer_view"));
                                } catch (ViewForwardException ex) {
                                    log.error(properties.getProperty("error_viewForward"));
                                }
                            }
                        }
                        catch (controllers.exceptions.ConnectionToDBException ex) {
                            log.error(properties.getProperty("error_db"));
                        }                    }
                } catch (NullPointerException ex) {
                    viewError(request, response, properties.getProperty("error_sessionOver"));
                }
            }
        } catch (IOException ex) {
            log.error("IOException");
        }
    }
}
