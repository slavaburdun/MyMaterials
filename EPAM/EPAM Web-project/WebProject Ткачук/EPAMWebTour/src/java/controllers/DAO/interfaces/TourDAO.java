package controllers.DAO.interfaces;

import java.util.List;
import models.Customer;
import models.Tour;
/**
 * Specification and functional extension of CRUD.
 * @author kelebra
 * @see CRUD
 */
public interface TourDAO extends CRUD<Tour> {

    public List<Tour> getToursOfCustomer(Customer owner);

    public boolean setOwner(Customer owner, Tour entity);

    public boolean setTourFree(Tour entity);
}
