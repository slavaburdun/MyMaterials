package ua.kpi.fpm.jspservlet.commands;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.fpm.jspservlet.manager.ConfigurationManager;

public class NoCommand implements Command{
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        /*в случае прямого обращения к контроллеру переадресация на страницу ввода логина*/
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        return page;
    }

}