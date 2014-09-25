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
public class Accounttypes implements Serializable{

    protected int accounttypeid;
    protected String accounttype;

    public Accounttypes() {
    }

    public int getAccounttypeid() {
        return accounttypeid;
    }

    public void setAccounttypeid(int accounttypeid) {
        this.accounttypeid = accounttypeid;
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
        ret.append("ua.kpi.jdbc.dbom.Accounttypes: ");
        ret.append("accounttypeid=").append(accounttypeid);
        ret.append(", accounttype=").append(accounttype);
        return ret.toString();
    }
}
