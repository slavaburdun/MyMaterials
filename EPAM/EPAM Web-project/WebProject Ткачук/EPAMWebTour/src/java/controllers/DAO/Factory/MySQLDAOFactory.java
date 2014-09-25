package controllers.DAO.Factory;

import controllers.DAO.CRUD.MySQLCustomerDAO;
import controllers.DAO.CRUD.MySQLTourAgentDAO;
import controllers.DAO.CRUD.MySQLTourDAO;
import controllers.DAO.interfaces.CustomerDAO;
import controllers.DAO.interfaces.TourAgentDAO;
import controllers.DAO.interfaces.TourDAO;
import exceptions.ConnectionToDBException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MySQL DAO factory.Implementation for MySQL source.
 *
 * @author kelebra
 * @see DAOFactory
 */
public class MySQLDAOFactory extends DAOFactory {

    @Override
    public CustomerDAO getCustomerDAO() {
        try {
            try {
                return new MySQLCustomerDAO();
            } catch (IOException ex) {
                Logger.getLogger(MySQLDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ConnectionToDBException ex) {
            Logger.getLogger(MySQLDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public TourDAO getTourDAO() {
        try {
            try {
                return new MySQLTourDAO();
            } catch (IOException ex) {
                Logger.getLogger(MySQLDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ConnectionToDBException ex) {
        }
        return null;
    }

    @Override
    public TourAgentDAO getTourAgentDAO() {
        try {
            try {
                return new MySQLTourAgentDAO();
            } catch (IOException ex) {
                Logger.getLogger(MySQLDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ConnectionToDBException ex) {
            Logger.getLogger(MySQLDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
