package models;

/**
 * Represents a Tour Agent model.In other words it is a system admin.Can create
 * tours and change field urgent and discount amount.
 * @author kelebra
 * @see Tour
 * @see Model
 */

public class TourAgent extends Model {

    private String login;
    private String password;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TourAgent other = (TourAgent) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 53 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }
}
