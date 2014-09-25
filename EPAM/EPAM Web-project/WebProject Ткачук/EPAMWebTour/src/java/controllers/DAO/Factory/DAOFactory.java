package controllers.DAO.Factory;

import controllers.DAO.interfaces.CustomerDAO;
import controllers.DAO.interfaces.TourAgentDAO;
import controllers.DAO.interfaces.TourDAO;

/**
 * DAO Factory for multiple sources.Only MySQL avaliable.
 *
 * @author kelebra
 */
public abstract class DAOFactory {

    public static final int MySQL = 1;

    public abstract CustomerDAO getCustomerDAO();

    public abstract TourDAO getTourDAO();

    public abstract TourAgentDAO getTourAgentDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MySQL:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }
}
