package dao;

import model.CompanyEntity;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Implementation for the CompanyDao interface
 * @author yegor
 */
public class CompanyDaoImpl implements CompanyDao {

    private final EntityManager entityManager;

    public CompanyDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CompanyEntity find(String name) {
        return (CompanyEntity) entityManager
                .createQuery("from CompanyEntity where name = :searchName")
                .setParameter("searchName", name)
                .getSingleResult();
    }

    @Override
    public void add(CompanyEntity company) {
        entityManager.persist(company);
    }

    @Override
    public void refresh(CompanyEntity company) {
        entityManager.unwrap(Session.class).refresh(company);
    }

    @Override
    public void update(CompanyEntity company) {
        entityManager.merge(company);
    }

    @Override
    public List<CompanyEntity> listAll() {
        return entityManager.createQuery("from CompanyEntity", CompanyEntity.class).getResultList();
    }
}
