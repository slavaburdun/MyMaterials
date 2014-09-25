package controllers;

import controllers.chainelements.*;
import controllers.chainelements.interfaces.ChainElement;
import exceptions.ChainElementException;
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
        chain.addElement(new Authorization());
        chain.addElement(new AddTourToCustomer());
        chain.addElement(new BuyTours());
        chain.addElement(new CreateTour());
        chain.addElement(new CommitTour());
        chain.addElement(new AllTours());
        chain.addElement(new ModifyTours());
        chain.addElement(new CommitModify());
        chain.addElement(new ErrorElement());
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
        for (ChainElement elem : chain.getChain()) {
            try {
                elem.processRequest(request, response, log);
            } catch (ChainElementException ex) {
                RequestDispatcher view = request.getRequestDispatcher("ErrorPage.jsp");
                try {
                    view.forward(request, response);
                } catch (IOException ex1) {
                    log.error("IOException in GeneralController");
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
