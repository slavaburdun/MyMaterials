package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Parent for controller, which accepts all requests.Has several checking
 * functions.
 *
 * @author kelebra
 */
public abstract class Controller extends HttpServlet {

    abstract protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected void processView(HttpServletRequest request, HttpServletResponse response, String URL) {
        try {
            RequestDispatcher view = request.getRequestDispatcher(URL);
            view.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected boolean checkRequest(String parameter, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter(parameter) != null && !request.getParameter(parameter).isEmpty()) {
            processRequest(request, response);
        } else if (request.getParameter(parameter) == null || request.getParameter(parameter).isEmpty()) {
            String URL = "";
            HttpSession session = request.getSession(false);
            if (session == null) {
                URL = "index.jsp";
            } else {
                if (session.getAttribute("customer") != null) {
                    URL = "CustomerView.jsp";
                } else {
                    if (session.getAttribute("agent") != null) {
                        URL = "AgentView.jsp";
                    } else {
                        URL = "index.jsp";
                    }
                }
            }
            RequestDispatcher view = request.getRequestDispatcher(URL);
            view.forward(request, response);
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        doGet(request, response);
    }
}
