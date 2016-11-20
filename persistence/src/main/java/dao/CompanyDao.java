package dao;

import model.CompanyEntity;

import java.util.List;

/**
 * Data Access Object for Company entry
 *
 * @author yegor
 */
public interface CompanyDao {

    /**
     * Finds a company by its name.
     *
     * @return found company, or null if there is no company with the specified name in the database
     */
    CompanyEntity find(String name);

    /**
     * Adds the company to database
     */
    void add(CompanyEntity company);

    /**
     * Synchronize the company data with the database.
     * Any not saved modification on company will be overwritten.
     */
    void refresh(CompanyEntity company);

    /**
     * Update the company on database.
     */
    void update(CompanyEntity company);

    /**
     * Retrieves all companies from database.
     */
    List<CompanyEntity> listAll();
}
