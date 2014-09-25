/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import logging.LoggerLoader;
import jdbc.connection.Database;
import jdbc.dbom.Usersview;

/**
 *
 * @author epam
 */
public class UsersviewDaoImpl implements UsersviewDao {

    private static final Logger logger = LoggerLoader.getLogger(UsersviewDaoImpl.class);
    protected Database userConn;
   // protected final JDBCConnectionPool connPool;
    protected final String SQL_SELECT = "SELECT USERID, LOGIN, ACCOUNTSTATUS, ACCOUNTTYPE FROM " + getTableName() + "";
    protected int maxRows;
    protected static final int COLUMN_USERID = 1;
    protected static final int COLUMN_LOGIN = 2;
    protected static final int COLUMN_ACCOUNTSTATUS = 3;
    protected static final int COLUMN_ACCOUNTTYPE = 4;
    protected static final int NUMBER_OF_COLUMNS = 4;

    public Usersview[] findAll() throws Exception {
        return findByDynamicSelect(SQL_SELECT, null);
    }

    public Usersview[] findWhereUseridEquals(int userid) throws Exception {
        return findByDynamicSelect(SQL_SELECT + " WHERE USERID = ? ORDER BY USERID", new Object[]{new Integer(userid)});
    }

    public Usersview[] findWhereLoginEquals(String login) throws Exception {
        return findByDynamicSelect(SQL_SELECT + " WHERE LOGIN = ? ORDER BY LOGIN", new Object[]{login});
    }

    public Usersview[] findWhereAccountstatusEquals(String accountstatus) throws Exception {
        return findByDynamicSelect(SQL_SELECT + " WHERE ACCOUNTSTATUS = ? ORDER BY ACCOUNTSTATUS", new Object[]{accountstatus});
    }

    public Usersview[] findWhereAccounttypeEquals(String accounttype) throws Exception {
        return findByDynamicSelect(SQL_SELECT + " WHERE ACCOUNTTYPE = ? ORDER BY ACCOUNTTYPE", new Object[]{accounttype});
    }

     public UsersviewDaoImpl(final Database userConn) {
        this.userConn = userConn;
 //!       this.connPool = JDBCConnectionPool.getInstance(userConn.getDriver(), userConn.getUrl(), userConn.getLogin(), userConn.getPassword());
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public String getTableName() {
        return "usersview";
    }

    protected Usersview fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Usersview dto = new Usersview();
            populateDto(dto, rs);
            return dto;
        } else {
            return null;
        }

    }

    protected Usersview[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection resultList = new ArrayList();
        while (rs.next()) {
            Usersview dto = new Usersview();
            populateDto(dto, rs);
            resultList.add(dto);
        }

        Usersview ret[] = new Usersview[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    protected void populateDto(Usersview dto, ResultSet rs) throws SQLException {
        dto.setUserid(rs.getInt(COLUMN_USERID));
        dto.setLogin(rs.getString(COLUMN_LOGIN));
        dto.setAccountstatus(rs.getString(COLUMN_ACCOUNTSTATUS));
        dto.setAccounttype(rs.getString(COLUMN_ACCOUNTTYPE));
    }

    protected void reset(Usersview dto) {
    }

    public Usersview[] findByDynamicSelect(String sql, Object[] sqlParams) throws Exception {
        // declare variables
        final boolean isConnSupplied = (userConn != null);
        Database conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
   //!         conn = isConnSupplied ? userConn : connPool.getConnection();

            // construct the SQL statement
            final String SQL = sql;


            System.out.println("Executing " + SQL);
            // prepare statement
            stmt = conn.prepareStatement(SQL);
            stmt.setMaxRows(maxRows);

            // bind parameters
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                stmt.setObject(i + 1, sqlParams[i]);
            }


            rs = stmt.executeQuery();

            // fetch the results
            return fetchMultiResults(rs);
        } catch (Exception ex) {
            logger.error(ex, ex);
            throw new Exception("Exception: " + ex.getMessage(), ex);
        } finally {

//!            if (!isConnSupplied) {
//!                connPool.returnConnection(conn);
//!            }

        }

    }
}
