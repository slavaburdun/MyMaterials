package controllers.DAO.interfaces;

import java.util.List;

/**
 * Basic CRUD operation requirements.
 *
 * @author kelebra
 * @param <T> model to be returned and accepted
 */
public interface CRUD<T> {

    public int insert(T entity);

    public boolean update(T entity);

    public T get(int id);

    public boolean delete(T entity);

    public List<T> getAll();
}
