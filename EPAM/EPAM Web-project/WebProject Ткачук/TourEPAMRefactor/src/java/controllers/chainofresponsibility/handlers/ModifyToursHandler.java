package controllers.chainofresponsibility.handlers;

import controllers.DAO.CRUD.MySQLTourDAO;
import controllers.chainofresponsibility.interfaces.ChainElement;
import controllers.exceptions.ConnectionToDBException;
import controllers.exceptions.ViewForwardException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Tour;
import org.apache.log4j.Logger;

public class ModifyToursHandler extends ChainElement {

    /**
     * Allows tour modification by Agent.
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
            if (request.getParameter("command").equals(properties.getProperty("command_modifyTours"))) {
                try {
                    HttpSession session = request.getSession(false);
                    if (session.getAttribute("agent") == null) {
                        viewError(request, response, "You are not agent! Source: ModifyTours");
                    } else {
                        try {
                            Enumeration<String> parameters = request.getParameterNames();
                            LinkedList<Tour> tours = new LinkedList<Tour>();
                            MySQLTourDAO action = new MySQLTourDAO(dbLog);
                            while (parameters.hasMoreElements()) {
                                String name = parameters.nextElement();
                                if (name.equals("command")) {
                                    continue;
                                } else {
                                    try {
                                        Tour tour = action.get(Integer.parseInt(request.getParameter(name)));
                                        if (tour != null) {
                                            tours.add(tour);
                                        } else {
                                            viewError(request, response, "Bad data. Source: ModifyTours");
                                        }
                                    } catch (NumberFormatException ex) {
                                        viewError(request, response, "Bad data. Source: ModifyTours");
                                    }
                                }
                            }
                            request.setAttribute("selectedTours", tours);
                            try {
                                viewForward(request, response, properties.getProperty("view_ModifyTours"));
                            } catch (ViewForwardException ex) {
                                log.error("ViewForwardException ex");
                            }
                        }
                        catch (ConnectionToDBException ex) {
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
