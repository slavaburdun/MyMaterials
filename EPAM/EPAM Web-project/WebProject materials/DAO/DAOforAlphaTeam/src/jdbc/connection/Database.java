/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.connection;

import logging.LoggerLoader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 *
 * @author epam
 */
public class Database {

    private final String url;
    private final String login;
    private final String password;
    private Connection conn;
    private final DBDriverBase driver;
    private boolean inuse;
    private long timestamp;
    private static final Logger logger = LoggerLoader.getLogger(Database.class);

    public Database(DBDriverBase driver, String url, String password, String login) throws SQLException {
        this.driver = driver;
        this.url = url;
        this.password = password;
        this.login = login;
        this.inuse = false;
        this.timestamp = 0;
    }

    public void connect() throws SQLException {
        this.conn = getDriver().getConnection(url, login, password);
    }

    public synchronized boolean lease() {
        if (inuse) {
            return false;
        } else {
            inuse = true;
            timestamp = System.currentTimeMillis();
            return true;
        }
    }

    public boolean validate() {
        try {
            conn.getAutoCommit();
        } catch (SQLException e) {
            logger.warn(e, e);
            return false;
        }
        return true;
    }

    public boolean inUse() {
        return inuse;
    }

    public long getLastUse() {
        return timestamp;
    }

    public void expireLease() {
        inuse = false;
    }

    public Connection getConnection() {
        return conn;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return conn.prepareCall(sql);
    }

    public Statement createStatement() throws SQLException {
        return conn.createStatement();
    }

    public String nativeSQL(String sql) throws SQLException {
        return conn.nativeSQL(sql);
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        conn.setAutoCommit(autoCommit);
    }

    public boolean getAutoCommit() throws SQLException {
        return conn.getAutoCommit();
    }

    public void commit() throws SQLException {
        conn.commit();
    }

    public void rollback() throws SQLException {
        conn.rollback();
    }

    public boolean isClosed() throws SQLException {
        return conn.isClosed();
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return conn.getMetaData();
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        conn.setReadOnly(readOnly);
    }

    public boolean isReadOnly() throws SQLException {
        return conn.isReadOnly();
    }

    public Savepoint setSavepoint() throws SQLException {
        return conn.setSavepoint();
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return conn.setSavepoint(name);
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        conn.rollback(savepoint);
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        conn.releaseSavepoint(savepoint);
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public DBDriverBase getDriver() {
        return driver;
    }
}
