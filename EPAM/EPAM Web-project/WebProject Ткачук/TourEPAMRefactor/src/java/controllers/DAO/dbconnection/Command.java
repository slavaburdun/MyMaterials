package controllers.DAO.dbconnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 * Interface of required functions for ConnectionCommand.
 *
 * @author kelebra
 * @see ConnectionCommand
 * @since 1.5
 */
public interface Command {

    public int executeStatement(String query, Logger log, Object... args) throws SQLException;

    public ResultSet getResultSet(String query, Logger log, Object... args) throws SQLException;
}
