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
public class Accountstatuses implements Serializable{

    protected int accountstatusid;
    protected String accountstatus;
    public Accountstatuses() {
    }

    public int getAccountstatusid() {
        return accountstatusid;
    }

    public void setAccountstatusid(int accountstatusid) {
        this.accountstatusid = accountstatusid;
    }

    public String getAccountstatus() {
        return accountstatus;
    }

    public void setAccountstatus(String accountstatus) {
        this.accountstatus = accountstatus;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("ua.kpi.jdbc.dbom.Accountstatuses: ");
        ret.append("accountstatusid=").append(accountstatusid);
        ret.append(", accountstatus=").append(accountstatus);
        return ret.toString();
    }
}
