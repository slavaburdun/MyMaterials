package controllers.DAO.CRUD;

import controllers.DAO.CRUD.interfaces.CustomerDAO;
import controllers.DAO.dbconnection.ConnectionCommand;
import controllers.exceptions.ConnectionToDBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import models.Customer;
import models.Tour;
import org.apache.log4j.Logger;

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
    private static Logger log;

    public MySQLCustomerDAO(Logger log) throws ConnectionToDBException, IOException {
        try {
            MySQLCustomerDAO.log = log;
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
            int id = command.executeStatement(properties.getProperty("customer_insert"), log, args);
            entity.setId(id);
            return id;
        } catch (SQLException ex) {
        }
        return -1;
    }

    @Override
    public boolean update(Customer entity) {
        try {
            MySQLTourDAO tourAction = new MySQLTourDAO(log);
            Object[] args = {entity.getLogin(), entity.getPassword(), entity.getBalance(), entity.isRegular(), entity.getId()};
            if (command.executeStatement(properties.getProperty("customer_update"), log, args) > 0) {
                for (Tour tour : entity.getTours()) {
                    tourAction.setOwner(entity, tour);
                }
                return true;
            }
        } catch (IOException ex) {
        } catch (ConnectionToDBException ex) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer get(int id) {
        try {
            MySQLTourDAO tourAction = new MySQLTourDAO(log);
            Customer customer = new Customer();
            customer.setId(id);
            Object[] args = {id};
            ResultSet result = command.getResultSet(properties.getProperty("customer_get"), log, args);
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
        } catch (ConnectionToDBException ex) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Customer entity) {
        try {
            Object[] args = {entity.getId()};
            ResultSet result = command.getResultSet(properties.getProperty("customer_delete"), log, args);
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
            ResultSet result = command.getResultSet(properties.getProperty("customer_getAll"), log, args);
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
        }
        return null;
    }

    public int exists(String login, String password) {
        try {
            Object[] args = {login, password};
            ResultSet result = command.getResultSet(properties.getProperty("customer_exists"), log, args);
            if (result.next()) {
                int id = result.getInt("id");
                return id;
            }
            return -1;
        } catch (SQLException ex) {
        }
        return -1;
    }

    @Override
    public void closeConnection() {
        command.closeConnection();
    }
}
