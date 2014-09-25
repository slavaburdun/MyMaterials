package controllers.DAO.CRUD;

import controllers.DAO.interfaces.CustomerDAO;
import controllers.commands.ConnectionCommand;
import exceptions.ConnectionToDBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.Tour;

/**
 * Implementation of CRUD for Customer model
 *
 * @author kelebra
 * @see controllers.commands.ConnectionCommand
 * @see models.Customer
 */
public class MySQLCustomerDAO implements CustomerDAO {

    private ConnectionCommand command;
    private Properties properties;

    public MySQLCustomerDAO() throws ConnectionToDBException, IOException {
        try {
            command = new ConnectionCommand();
            properties = new Properties();
            properties.load(new FileInputStream("values.properties"));
        } catch (SQLException ex) {
            throw new ConnectionToDBException();
        }
    }

    @Override
    public int insert(Customer entity) {
        try {
            Object[] args = {entity.getLogin(), entity.getPassword(), entity.getBalance(), entity.isRegular()};
            int id = command.executeStatement(properties.getProperty("customer_insert"), args);
            entity.setId(id);
            return id;
        } catch (SQLException ex) {
        }
        return -1;
    }

    @Override
    public boolean update(Customer entity) {
        try {
            MySQLTourDAO tourAction = new MySQLTourDAO();
            Object[] args = {entity.getLogin(), entity.getPassword(), entity.getBalance(), entity.isRegular(), entity.getId()};
            if (command.executeStatement(properties.getProperty("customer_update"), args) > 0) {
                for (Tour tour : entity.getTours()) {
                    tourAction.setOwner(entity, tour);
                }
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(MySQLCustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionToDBException ex) {
            Logger.getLogger(MySQLCustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer get(int id) {
        try {
            MySQLTourDAO tourAction = new MySQLTourDAO();
            Customer customer = new Customer();
            customer.setId(id);
            Object[] args = {id};
            ResultSet result = command.getResultSet(properties.getProperty("customer_get"), args);
            if (result.next()) {
                customer.setBalance(result.getDouble("balance"));
                customer.setLogin(result.getString("login"));
                customer.setPassword(result.getString("password"));
                customer.setRegular(result.getBoolean("regular"));
                List<Tour> tours = tourAction.getToursOfCustomer(customer);
                customer.setTours(tours);
                return customer;
            }
            return null;
        } catch (IOException ex) {
            Logger.getLogger(MySQLCustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionToDBException ex) {
            Logger.getLogger(MySQLCustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Customer entity) {
        try {
            Object[] args = {entity.getId()};
            ResultSet result = command.getResultSet(properties.getProperty("customer_delete"), args);
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }

    @Override
    public List<Customer> getAll() {
        try {
            LinkedList<Customer> customers = new LinkedList<Customer>();
            Object[] args = {};
            ResultSet result = command.getResultSet(properties.getProperty("customer_getAll"), args);
            while (result.next()) {
                Customer customer = new Customer();
                customer.setBalance(result.getDouble("balance"));
                customer.setLogin(result.getString("login"));
                customer.setPassword(result.getString("password"));
                customer.setRegular(result.getBoolean("regular"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int exists(String login, String password) {
        try {
            Object[] args = {login, password};
            ResultSet result = command.getResultSet(properties.getProperty("customer_exists"), args);
            if (result.next()) {
                return result.getInt("id");
            }
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
