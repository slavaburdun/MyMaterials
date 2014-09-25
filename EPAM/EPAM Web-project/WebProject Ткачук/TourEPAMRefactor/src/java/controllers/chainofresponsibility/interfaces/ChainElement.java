package controllers.chainofresponsibility.interfaces;

import controllers.exceptions.ChainElementException;
import controllers.exceptions.ViewForwardException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * Abstract class, which contains a number of redirect functions for every
 * handler in chain of responsibility.
 *
 * @author kelebra
 */
public abstract class ChainElement {

    /**
     * Method to do some individual task for every element in chain.
     *
     * @param request
     * @param response
     * @param log do logging
     * @throws ChainElementException
     * @see logger.Log4jInit
     * @see controllers.chainelements.ChainOfResponsibility
     */
    public abstract void processRequest(HttpServletRequest request, HttpServletResponse response, Logger log, Logger dbLogger) throws ChainElementException;

    /**
     * "Redirect" to given URL.
     *
     * @param request
     * @param response
     * @param URL destination
     * @throws ViewForwardException
     * @see RequestDispatcher
     */
    public void viewForward(HttpServletRequest request, HttpServletResponse response, String URL) throws ViewForwardException {
        RequestDispatcher view = request.getRequestDispatcher(URL);
        try {
            view.forward(request, response);
        } catch (ServletException ex) {
            throw new ViewForwardException();
        } catch (IOException ex) {
            throw new ViewForwardException();
        }
    }

    /**
     * Redirects to ErrorPage.jsp with given message.
     *
     * @param request
     * @param response
     * @param message message of error
     */
    public void viewError(HttpServletRequest request, HttpServletResponse response, String message) {
        try {
            request.setAttribute("message", message);
            viewForward(request, response, "ErrorPage.jsp");
        } catch (ViewForwardException ex) {
        }
    }

    public boolean checkParameters(HttpServletRequest request, HttpServletResponse response, String[] params) {
        for (String str : params) {
            if (request.getParameter(str) == null || request.getParameter(str).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void sendTo(HttpServletRequest request, HttpServletResponse response, String URL, String message) throws ViewForwardException {
        if (message == null) {
            viewForward(request, response, URL);
        } else {
            viewError(request, response, message);
        }
    }
}
