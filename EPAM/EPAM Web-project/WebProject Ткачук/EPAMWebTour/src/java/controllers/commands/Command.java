package controllers.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface of required functions for ConnectionCommand.
 *
 * @author kelebra
 * @see ConnectionCommand
 * @since 1.5
 */
public interface Command {

    public int executeStatement(String query, Object... args) throws SQLException;

    public ResultSet getResultSet(String query, Object... args) throws SQLException;
}
