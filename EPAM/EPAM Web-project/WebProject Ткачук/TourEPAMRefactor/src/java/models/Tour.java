package models;

/**
 * Represent a Tour model.Tour has price(in USD), can be urgent(Customer pays
 * only 25% of its original price), has a discount(for regular customers only),
 * can be vacation, shopping, excursion (other values are restricted on
 * controller level), has a description(any note).
 *
 * @author kelebra
 * @see Customer
 * @see Model
 * @since 1.2
 */
public class Tour extends Model {

    private double price;
    private boolean urgent;
    private double discount;
    private String type;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tour other = (Tour) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.urgent != other.urgent) {
            return false;
        }
        if (Double.doubleToLongBits(this.discount) != Double.doubleToLongBits(other.discount)) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 89 * hash + (this.urgent ? 1 : 0);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.discount) ^ (Double.doubleToLongBits(this.discount) >>> 32));
        hash = 89 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 89 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }
}
