package models;

/**
 * Parent class for all models.Id is a primary key in db.
 * @author kelebra
 * @since 1.2
 */

public class Model {

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
