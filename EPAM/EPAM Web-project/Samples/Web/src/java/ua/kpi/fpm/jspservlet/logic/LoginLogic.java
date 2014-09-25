package ua.kpi.fpm.jspservlet.logic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import ua.kpi.fpm.jspservlet.manager.ConfigurationManager;

public class LoginLogic {
    public static boolean checkLogin(String login, String password){
        //проверка логина и пароля
        try{
            //организация соединения с базой данных
            String driver = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.DATABASE_DRIVER_NAME);

            Class.forName(driver);
            Connection cn = null;
            try{
                String url = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.DATABASE_URL);
                cn = DriverManager.getConnection(url);
                PreparedStatement st = null;
                try{
                    st = cn.prepareStatement("SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?");
                    st.setString(1,login);
                    st.setString(2,password);
                    ResultSet rs = null;
                    try{
                        rs = st.executeQuery();
                        /*проверка, существует ли пользователь с указанным логином и паролем*/
                        return rs.next();
                    }finally{
                            if (rs != null)
                                rs.close();
                    }
                }finally{
                    if (st != null)
                        st.close();
                }
            }finally {
                if (cn != null)
                    cn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }

}
