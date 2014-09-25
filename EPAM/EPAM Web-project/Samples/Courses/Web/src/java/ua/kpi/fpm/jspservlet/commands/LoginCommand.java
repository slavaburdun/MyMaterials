package ua.kpi.fpm.jspservlet.commands;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.fpm.jspservlet.logic.LoginLogic;
import ua.kpi.fpm.jspservlet.manager.ConfigurationManager;
import ua.kpi.fpm.jspservlet.manager.MessageManager;

public class LoginCommand implements Command{

    private static final String PARAM_NAME_LOGIN = "login";
     private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String page = null;
        //извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        //проверка логина и пароля
        if (LoginLogic.checkLogin(login,pass)){
            request.setAttribute("user",login);
            //определение пути к main.jsp
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        }else{
            request.setAttribute("errorMessage",
                    MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
