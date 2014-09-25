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
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        if (request.getParameter(parameter) != null && !request.getParameter(parameter).isEmpty()) {
            processRequest(request, response);
        } else if (request.getParameter(parameter) == null || request.getParameter(parameter).isEmpty()) {
            HttpSession session = request.getSession(false);
            if (session == null || (session.getAttribute("customer") == null && session.getAttribute("agent") == null)) {
                RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
            } else if (session.getAttribute("customer") != null) {
                RequestDispatcher view = request.getRequestDispatcher("CustomerView.jsp");
                view.forward(request, response);
            } else if (session.getAttribute("agent") != null) {
                RequestDispatcher view = request.getRequestDispatcher("AgentView.jsp");
                view.forward(request, response);
            }
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
