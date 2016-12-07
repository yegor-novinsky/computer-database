package dao;

import model.CompanyEntity;
import model.ComputerEntity;

import javax.persistence.EntityManager;

/**
 * @author yegor
 */
public class EntityDaoFactory {
    static EntityDao createEntityDao(EntityManager entityManager, Class entityClass){
        if (entityClass == ComputerEntity.class){
            return new ComputerDaoImpl(entityManager);
        }else if(entityClass == CompanyEntity.class){
            return new CompanyDaoImpl(entityManager);
        }else{
            throw new UnsupportedOperationException();
        }
    }
}
