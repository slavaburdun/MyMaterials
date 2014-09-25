package controllers.DAO.dbconnection;

import com.mysql.jdbc.PreparedStatement;
import controllers.exceptions.ConnectionToDBException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 * This class represents a number of repeating functions during working with
 * database.This class contains connection with db and prepared statement, which
 * is setted again with every new db query.Implementation of interface Command.
 *
 * @author kelebra
 * @see Command
 * @see PreparedStatement
 * @see ConnectionPool
 * @since 1.5
 */
public class ConnectionCommand implements Command {

    private Connection connection;
    private PreparedStatement prest;

    /**
     * Getting connection from connection pool.
     *
     * @see ConnectionPool
     * @throws SQLException
     */
    public ConnectionCommand() throws SQLException, ConnectionToDBException {
            connection = ConnectionPool.getInstance().getConnection();
    }

    /**
     * Inserts an array of objects into prepared statement.
     *
     * @param preparedStatement statement to be executed
     * @param values array of objects to be inserted
     * @throws SQLException
     */
    private void setValues(PreparedStatement preparedStatement, Object... values)
            throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    /**
     * Executes insert(returns id), update and delete queries.
     *
     * @param query
     * @param args
     * @return if if request is insert
     * @throws SQLException
     */
    @Override
    public int executeStatement(String query, Logger log, Object... args) throws SQLException {
        prest = (PreparedStatement) connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        setValues(prest, args);
        log.info(prest.toString());
        int res = prest.executeUpdate();
        ResultSet resultSet = prest.getGeneratedKeys();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            return id;
        } else {
            return res;
        }
    }

    /**
     * Executes select query and returns resultset.
     *
     * @param query to be executed
     * @param args
     * @return result of select queries
     * @throws SQLException
     */
    @Override
    public ResultSet getResultSet(String query, Logger log, Object... args) throws SQLException {
        prest = (PreparedStatement) connection.prepareStatement(query);
        setValues(prest, args);
        log.info(prest.toString());
        return prest.executeQuery();
    }

    /**
     * Returns connection to pool.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }
}
