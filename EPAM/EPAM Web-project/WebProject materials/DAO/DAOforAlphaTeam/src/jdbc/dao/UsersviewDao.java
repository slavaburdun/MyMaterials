/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import jdbc.dbom.Usersview;
/**
 *
 * @author epam
 */
public interface UsersviewDao {

    public Usersview[] findAll() throws Exception;
    public Usersview[] findWhereUseridEquals(int userid) throws Exception;
    public Usersview[] findWhereLoginEquals(String login) throws Exception;
    public Usersview[] findWhereAccountstatusEquals(String accountstatus) throws Exception;
    public Usersview[] findWhereAccounttypeEquals(String accounttype) throws Exception;
    public void setMaxRows(int maxRows);
    public int getMaxRows();
    public Usersview[] findByDynamicSelect(String sql, Object[] sqlParams) throws Exception;
}
