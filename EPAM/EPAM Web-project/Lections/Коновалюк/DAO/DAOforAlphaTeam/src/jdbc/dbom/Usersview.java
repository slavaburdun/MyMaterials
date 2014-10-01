/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dbom;

import java.io.Serializable;
/**
 *
 * @author epam
 */
public class Usersview implements Serializable{

    protected int userid;
    protected String login;
    protected String accountstatus;
    protected String accounttype;

    public Usersview() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAccountstatus() {
        return accountstatus;
    }

    public void setAccountstatus(String accountstatus) {
        this.accountstatus = accountstatus;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("ua.kpi.jdbc.dbom.Usersview: ");
        ret.append("userid=").append(userid);
        ret.append(", login=").append(login);
        ret.append(", accountstatus=").append(accountstatus);
        ret.append(", accounttype=").append(accounttype);
        return ret.toString();
    }
}
