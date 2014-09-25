package controllers.chainofresponsibility.handlers;

import controllers.chainofresponsibility.interfaces.ChainElement;
import controllers.exceptions.ChainElementException;
import controllers.exceptions.ViewForwardException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class LogoutHandler extends ChainElement {

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response, Logger log, Logger dbLog) throws ChainElementException {
        try {
            if (request.getParameter("command").equals("logout")) {
                String message = null;
                HttpSession session = request.getSession(false);
                session.invalidate();
                String URL = "index.jsp";
                sendTo(request, response, URL, message);
            }
        } catch (ViewForwardException ex) {
            log.error(ex.getCause());
        } catch (NullPointerException ex) {
            viewError(request, response, "Bad role");
        }
    }
}
