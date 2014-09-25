package controllers.chainofresponsibility.handlers;

import controllers.DAO.CRUD.MySQLTourDAO;
import controllers.chainofresponsibility.interfaces.ChainElement;
import controllers.exceptions.ViewForwardException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Tour;
import org.apache.log4j.Logger;

/**
 * Checks and saves modified values of selected Tours.
 *
 * @author kelebra
 *
 */
public class CommitModifyHandler extends ChainElement {

    /**
     * Checks and saves modified values of selected Tours.
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
            if (request.getParameter(properties.getProperty("command_param")).equals(properties.getProperty("command_commitModify"))) {
                try {
                    HttpSession session = request.getSession(false);
                    if (session.getAttribute(properties.getProperty("agent_role")) == null) {
                        viewError(request, response, properties.getProperty("error_not_agent"));
                    } else {
                        try {
                            Enumeration<String> names = request.getParameterNames();
                            MySQLTourDAO action = new MySQLTourDAO(dbLog);
                            try {
                                while (names.hasMoreElements()) {
                                    String name = names.nextElement();
                                    if (name.contains(properties.getProperty("parameter_tourId"))) {
                                        String idStr = request.getParameter(name);
                                        Tour tour = action.get(Integer.parseInt(idStr));
                                        if (tour != null) {
                                            tour.setDiscount(Double.parseDouble(request.getParameter(properties.getProperty("tour_discount") + idStr)));
                                            tour.setUrgent(Boolean.parseBoolean(request.getParameter(properties.getProperty("tour_urgent") + idStr)));
                                            action.update(tour);
                                        } else {
                                            viewError(request, response, properties.getProperty("error_wrongId_buyTours"));
                                        }
                                    }
                                }
                                try {
                                    viewForward(request, response, properties.getProperty("agent_view"));
                                } catch (ViewForwardException ex) {
                                    log.error(properties.getProperty("error_viewForward"));
                                }
                            } catch (NumberFormatException ex) {
                                viewError(request, response, properties.getProperty("error_wrongId_buyTours"));
                            } catch (NullPointerException ex) {
                                viewError(request, response, properties.getProperty("error_smthInParams"));
                            }
                        } catch (controllers.exceptions.ConnectionToDBException ex) {
                            log.error(properties.getProperty("error_db"));
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
