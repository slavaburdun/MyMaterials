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
public class Users implements Serializable{

    protected int userid;
    protected String login;
    protected String password;
    protected int accountstatusid;
    protected int accounttypeid;
    protected String fio;
    protected int createdby;

    public Users() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountstatusid() {
        return accountstatusid;
    }

    public void setAccountstatusid(int accountstatusid) {
        this.accountstatusid = accountstatusid;
    }

    public int getAccounttypeid() {
        return accounttypeid;
    }

    public void setAccounttypeid(int accounttypeid) {
        this.accounttypeid = accounttypeid;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getCreatedby() {
        return createdby;
    }

    public void setCreatedby(int createdby) {
        this.createdby = createdby;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("ua.kpi.jdbc.dbom.Users: ");
        ret.append("userid=").append(userid);
        ret.append(", login=").append(login);
        ret.append(", password=").append(password);
        ret.append(", accountstatusid=").append(accountstatusid);
        ret.append(", accounttypeid=").append(accounttypeid);
        ret.append(", fio=").append(fio);
        ret.append(", createdby=").append(createdby);
        return ret.toString();
    }
}
