package controllers;

import controllers.chainofresponsibility.ChainOfResponsibility;
import controllers.chainofresponsibility.handlers.*;
import controllers.chainofresponsibility.interfaces.ChainElement;
import controllers.exceptions.ChainElementException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * Controller, which accepts all requests.Has chain of handlers, which decide,
 * what to do with request one by one.
 *
 * @author kelebra
 * @see controllers.chainelements.
 *
 * @see models.*
 */
@WebServlet(name = "GeneralController", urlPatterns = {"/GeneralController"})
public class GeneralController extends Controller {

    private ChainOfResponsibility chain;

    /**
     * Initialization of chain.
     */
    @Override
    public void init() {
        chain = new ChainOfResponsibility();
        chain.addElement(new AuthorizationHandler());
        chain.addElement(new AddTourToCustomerHandler());
        chain.addElement(new BuyToursHandler());
        chain.addElement(new CreateTourHandler());
        chain.addElement(new CommitTourHandler());
        chain.addElement(new AllToursHandler());
        chain.addElement(new ModifyToursHandler());
        chain.addElement(new CommitModifyHandler());
        chain.addElement(new LogoutHandler());
        chain.addElement(new ErrorHandler());
    }

    /**
     * Handling request in chain.Searching for suitable request in chain one by
     * one.
     *
     * @param request
     * @param response
     * @throws ServletException
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        Logger log = (Logger) getServletContext().getAttribute("log4");
        Logger logdb = (Logger) getServletContext().getAttribute("log4db");
        for (ChainElement elem : chain.getChain()) {
            try {
                elem.processRequest(request, response, log, logdb);
            } catch (ChainElementException ex) {
                RequestDispatcher view = request.getRequestDispatcher("ErrorPage.jsp");
                request.setAttribute("message", "ChainElementException");
                try {
                    view.forward(request, response);
                } catch (IOException ex1) {
                    log.error("IOException");
                }
            }
        }
    }

    /**
     * Checks parameter "command"(unique for every handler in chain).
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        checkRequest("command", request, response);
    }
}
