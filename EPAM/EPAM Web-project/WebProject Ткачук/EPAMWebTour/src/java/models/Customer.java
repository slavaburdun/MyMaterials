package models;

import java.util.List;

/**
 * Represents a Customer model.Customer can buy (money(USD) are stored in variable
 * balance) Tour (it stores in tours variable).To enter site customer has to
 * enter valid login and password.Customers can be regular (regular clients have
 * discounts on Tours).
 *
 * @author kelebra
 * @since 1.2
 * @see Tour
 * @see Model
 */
public class Customer extends Model {

    private String login;
    private String password;
    private double balance;
    private boolean regular;
    private List<Tour> tours;

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getProfile() {
        return password;
    }

    public void setProfile(String profile) {
        this.password = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if (Double.doubleToLongBits(this.balance) != Double.doubleToLongBits(other.balance)) {
            return false;
        }
        if (this.regular != other.regular) {
            return false;
        }
        if (this.tours != other.tours && (this.tours == null || !this.tours.equals(other.tours))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 79 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.balance) ^ (Double.doubleToLongBits(this.balance) >>> 32));
        hash = 79 * hash + (this.regular ? 1 : 0);
        hash = 79 * hash + (this.tours != null ? this.tours.hashCode() : 0);
        return hash;
    }
}
