package controllers.commands;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * This class represents connection pooling.Must be a synchronized singletone
 * (synchronized - because of tomcat and singletone, because several clients
 * must not create own pool after connection to the server).In fact, we only set
 * properties(avaliable in context.xml) of datasource.
 *
 * @author kelebra
 * @see DataSource
 * @since 1.5
 */
public class ConnectionPool {

    private static DataSource datasource;

    /**
     * Singletone requirement
     */
    private ConnectionPool() {
    }

    /**
     * Getting parameters from file, mentioned above(in context.xml resource is
     * created).
     *
     * @return DataSource, from which connection to db can be gotten.
     */
    public static synchronized DataSource getInstance() {
        if (datasource == null) {
            try {
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                datasource = (DataSource) envCtx.lookup("jdbc/tourdb");
            } catch (NamingException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return datasource;
    }
}