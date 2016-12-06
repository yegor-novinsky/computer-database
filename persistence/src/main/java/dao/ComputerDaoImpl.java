package dao;

import model.ComputerEntity;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author yegor
 */
public class ComputerDaoImpl implements EntityDao<ComputerEntity> {
    private final EntityManager entityManager;

    public ComputerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ComputerEntity find(String name) {
        return (ComputerEntity) entityManager
                .createQuery("from ComputerEntity where name = :searchName")
                .setParameter("searchName", name)
                .getSingleResult();
    }

    @Override
    public void add(ComputerEntity computer) {
        entityManager.persist(computer);
    }

    @Override
    public void refresh(ComputerEntity computer) {
        entityManager.unwrap(Session.class).refresh(computer);
    }

    @Override
    public void update(ComputerEntity computer) {
        entityManager.merge(computer);
    }

    @Override
    public List<ComputerEntity> listAll() {
        return entityManager.createQuery("from ComputerEntity", ComputerEntity.class).getResultList();
    }
}
