package controllers.chainelements;

import controllers.chainelements.interfaces.ChainElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class ErrorElement extends ChainElement {

    /**
     * Stub for chain of responsibility.
     *
     * @param request
     * @param response
     * @param log
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, Logger log) {
        if (request.getParameter("command") == null || request.getParameter("command").isEmpty()) {
            viewError(request, response, "Something wrong");
        }
    }
}
