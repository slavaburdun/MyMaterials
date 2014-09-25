package ua.kpi.fpm.jspservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.fpm.jspservlet.RequestHelper;
import ua.kpi.fpm.jspservlet.commands.Command;
import ua.kpi.fpm.jspservlet.manager.ConfigurationManager;
import ua.kpi.fpm.jspservlet.manager.MessageManager;
/**
 *
 * @author Family
 */
public class Controller extends HttpServlet implements javax.servlet.Servlet{
    //объект, содержащий список возможных команд
    RequestHelper requestHelper = RequestHelper.getInstance();

    public Controller(){
        super();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String page = null;
        try{
            //определение команды, пришедшей из JSP
            Command command=requestHelper.getCommand(request);
            /*вызов реализованного метода execute() интерфейса Command и передача параметров
             классу-обработчику конкретной команды*/
            page = command.execute(request,response);
            //метод возвращает страницу ответа
        }catch (ServletException e){
            e.printStackTrace();
            //генерация сообщения об ошибке
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(
                    MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            //вызов JSP-страницы с сообщением об ошибке
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }catch(IOException e){
            e.printStackTrace();
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        //вызов страницы ответа на запрос
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}