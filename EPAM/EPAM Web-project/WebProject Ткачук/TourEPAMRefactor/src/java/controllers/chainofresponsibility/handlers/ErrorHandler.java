package controllers.chainofresponsibility.handlers;

import controllers.chainofresponsibility.interfaces.ChainElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class ErrorHandler extends ChainElement {

    /**
     * Stub for chain of responsibility.
     *
     * @param request
     * @param response
     * @param log
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, Logger log, Logger dbLog) {
        if (request.getParameter("command") == null || request.getParameter("command").isEmpty()) {
            viewError(request, response, "Something wrong");
        }
    }
}
