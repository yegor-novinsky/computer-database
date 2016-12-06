package dao;

import model.CompanyEntity;

import java.util.List;

/**
 * @author yegor
 */
public interface EntityDao<T> {
    /**
     * Finds an entity by its name.
     *
     * @return found company, or null if there is no company with the specified name in the database
     */
    T find(String name);

    /**
     * Adds the entity to database
     */
    void add(T entity);

    /**
     * Synchronize the entity data with the database.
     * Any not saved modification on entity will be overwritten.
     */
    void refresh(T entity);

    /**
     * Update the entity on database.
     */
    void update(T entity);

    /**
     * Retrieves all entities from database.
     */
    List<T> listAll();
}
