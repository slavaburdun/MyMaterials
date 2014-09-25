package controllers.DAO.CRUD;

import controllers.DAO.interfaces.TourDAO;
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
 * Implementation of CRUD for Tour model
 *
 * @author kelebra
 * @see controllers.commands.ConnectionCommand
 * @see models.Tour
 */
public class MySQLTourDAO implements TourDAO {

    private ConnectionCommand command;
    private Properties properties;

    public MySQLTourDAO() throws ConnectionToDBException, IOException {
        try {
            command = new ConnectionCommand();
            properties = new Properties();
            properties.load(new FileInputStream("values.properties"));
        } catch (SQLException ex) {
            throw new ConnectionToDBException();
        }
    }

    @Override
    public int insert(Tour entity) {
        try {
            Object[] args = {entity.getPrice(), entity.isUrgent(), entity.getDiscount(), entity.getType(), entity.getDescription()};
            int id = command.executeStatement(properties.getProperty("tour_insert"), args);
            entity.setId(id);
            return id;
        } catch (SQLException ex) {
        }
        return -1;
    }

    @Override
    public boolean update(Tour entity) {
        try {
            Object[] args = {entity.getPrice(), entity.isUrgent(), entity.getDiscount(), entity.getType(), entity.getDescription(), entity.getId()};
            if (command.executeStatement(properties.getProperty("tour_update"), args) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Tour get(int id) {
        try {
            Tour tour = new Tour();
            tour.setId(id);
            Object[] args = {id};
            ResultSet result = command.getResultSet(properties.getProperty("tour_get"), args);
            if (result.next()) {
                tour.setDiscount(result.getDouble("discount"));
                tour.setPrice(result.getDouble("price"));
                tour.setType(result.getString("type"));
                tour.setUrgent(result.getBoolean("urgent"));
                tour.setDescription(result.getString("description"));
                return tour;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Tour entity) {
        try {
            Object[] args = {entity.getId()};
            ResultSet result = command.getResultSet(properties.getProperty("tour_delete"), args);
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }

    @Override
    public List<Tour> getAll() {
        try {
            LinkedList<Tour> tours = new LinkedList<Tour>();
            Object[] args = {};
            ResultSet result = command.getResultSet(properties.getProperty("tour_getAll"), args);
            while (result.next()) {
                Tour tour = new Tour();
                tour.setDiscount(result.getDouble("discount"));
                tour.setPrice(result.getDouble("price"));
                tour.setType(result.getString("type"));
                tour.setUrgent(result.getBoolean("urgent"));
                tour.setId(result.getInt("id"));
                tour.setDescription(result.getString("description"));
                tours.add(tour);
            }
            return tours;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Tour> getToursOfCustomer(Customer owner) {
        try {
            LinkedList<Tour> tours = new LinkedList<Tour>();
            Object[] args = {owner.getId()};
            ResultSet result = command.getResultSet(properties.getProperty("tour_getTourOfCustomer"), args);
            while (result.next()) {
                Tour tour = new Tour();
                tour.setDiscount(result.getDouble("discount"));
                System.out.println(tour.getDiscount() + "!!!!!!!!!!!!!!!!!");
                tour.setPrice(result.getDouble("price"));
                tour.setType(result.getString("type"));
                tour.setUrgent(result.getBoolean("urgent"));
                tour.setId(result.getInt("id"));
                tour.setDescription(result.getString("description"));
                tours.add(tour);
            }
            return tours;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean setOwner(Customer owner, Tour entity) {
        try {
            Object[] args = {owner.getId(), entity.getId()};
            if (command.executeStatement(properties.getProperty("tour_updateCustomer"), args) > 0) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean setTourFree(Tour entity) {
        try {
            Object[] args = {null, entity.getId()};
            if (command.executeStatement(properties.getProperty("tour_setTourFree"), args) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Tour> getFreeTours() {
        try {
            LinkedList<Tour> tours = new LinkedList<Tour>();
            Object[] args = {};
            ResultSet result = command.getResultSet(properties.getProperty("tour_getFreeTours"), args);
            while (result.next()) {
                Tour tour = new Tour();
                tour.setDiscount(result.getDouble("discount"));
                tour.setPrice(result.getDouble("price"));
                tour.setType(result.getString("type"));
                tour.setUrgent(result.getBoolean("urgent"));
                tour.setId(result.getInt("id"));
                tour.setDescription(result.getString("description"));
                tours.add(tour);
            }
            return tours;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean hasOwner(Tour tour) {
        try {
            Object[] args = {tour.getId()};
            ResultSet result = command.getResultSet(properties.getProperty("tour_hasOwner"), args);
            if (result.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
