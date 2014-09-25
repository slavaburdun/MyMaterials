package controllers.DAO.CRUD;

import controllers.DAO.interfaces.TourAgentDAO;
import controllers.commands.ConnectionCommand;
import exceptions.ConnectionToDBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import models.TourAgent;

/**
 * Implementation of CRUD for TourAgent model
 *
 * @author kelebra
 * @see controllers.commands.ConnectionCommand
 * @see models.TourAgent
 */
public class MySQLTourAgentDAO implements TourAgentDAO {

    private ConnectionCommand command;
    private Properties properties;

    public MySQLTourAgentDAO() throws ConnectionToDBException, IOException {
        try {
            command = new ConnectionCommand();
            properties = new Properties();
            properties.load(new FileInputStream("values.properties"));
        } catch (SQLException ex) {
            throw new ConnectionToDBException();
        }
    }

    @Override
    public int insert(TourAgent entity) {
        try {
            Object[] args = {entity.getLogin(), entity.getPassword()};
            int id = command.executeStatement(properties.getProperty("agent_insert"), args);
            entity.setId(id);
            return id;
        } catch (SQLException ex) {
        }
        return -1;
    }

    @Override
    public boolean update(TourAgent entity) {
        try {
            Object[] args = {entity.getLogin(), entity.getPassword(), entity.getId()};
            if (command.executeStatement(properties.getProperty("agent_update"), args) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public TourAgent get(int id) {
        try {
            TourAgent agent = new TourAgent();
            agent.setId(id);
            Object[] args = {id};
            ResultSet result = command.getResultSet(properties.getProperty("agent_get"), args);
            if (result.next()) {
                agent.setLogin(result.getString("login"));
                agent.setPassword(result.getString("password"));
                return agent;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(TourAgent entity) {
        try {
            Object[] args = {entity.getId()};
            ResultSet result = command.getResultSet(properties.getProperty("agent_delete"), args);
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }

    @Override
    public List<TourAgent> getAll() {
        try {
            LinkedList<TourAgent> tourAgents = new LinkedList<TourAgent>();
            Object[] args = {};
            ResultSet result = command.getResultSet(properties.getProperty("agent_getAll"), args);
            while (result.next()) {
                TourAgent agent = new TourAgent();
                agent.setLogin(result.getString("login"));
                agent.setPassword(result.getString("password"));
                tourAgents.add(agent);
            }
            return tourAgents;
        } catch (SQLException ex) {
        }
        return null;
    }

    public int exists(String login, String password) {
        try {
            Object[] args = {login, password};
            ResultSet result = command.getResultSet(properties.getProperty("agent_exists"), args);
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException ex) {
        }
        return -1;
    }
}
