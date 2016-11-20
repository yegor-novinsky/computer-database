package model;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yegor on 11/16/16.
 */
public class ComputerEntityTest {
    private EntityManagerFactory entityManagerFactory;

    private static final String firstCompanyName = "First company";
    private static final String firstComputerName = "First computer";

    @org.junit.Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory( "model" );
    }

    @org.junit.After
    public void tearDown() throws Exception {
        entityManagerFactory.close();
    }

    @Test
    public void testCompanyCreation() throws Exception {

        EntityManager creator = entityManagerFactory.createEntityManager();

        creator.getTransaction().begin();
        creator.persist(new CompanyEntity(firstCompanyName));
        creator.getTransaction().commit();

        creator.close();

        List<CompanyEntity> firstCompany = findEntity("CompanyEntity", firstCompanyName);

        assertEquals(1, firstCompany.size());
        assertEquals(firstCompany.get(0).getName(), firstCompanyName);
    }

    @Test
    public void testComputerCreation() throws Exception {

        Timestamp introductionDate = new Timestamp(new Calendar.Builder()
                .setDate(2013, 11, 11)
                .build()
                .getTimeInMillis());
        Timestamp discontinuingDate = new Timestamp(new Calendar.Builder()
                .setDate(2016, 11, 11)
                .build()
                .getTimeInMillis());
        EntityManager creator = entityManagerFactory.createEntityManager();

        CompanyEntity company = findCreateFirstCompany();

        try {
            creator.getTransaction().begin();
            creator.persist(new ComputerEntity(firstComputerName,
                    introductionDate,
                    discontinuingDate,
                    company));
            creator.getTransaction().commit();
        } finally {
            creator.close();
        }

        List<ComputerEntity> computersWithNameOfFirstCompany = findEntity("ComputerEntity", firstComputerName);

        assertEquals(1, computersWithNameOfFirstCompany.size());
        assertEquals(computersWithNameOfFirstCompany.get(0).getName(), firstComputerName);
    }

    public CompanyEntity findCreateFirstCompany(){
        List<CompanyEntity> companiesWithNameOfFirstCompany = findEntity("CompanyEntity", firstCompanyName);

        if (companiesWithNameOfFirstCompany.isEmpty()){
            CompanyEntity company = new CompanyEntity(firstCompanyName);

            EntityManager creator = entityManagerFactory.createEntityManager();

            creator.getTransaction().begin();
            creator.persist(company);
            creator.getTransaction().commit();

            creator.close();

            return company;
        }
        else
            return companiesWithNameOfFirstCompany.get(0);
    }

    public List findEntity(String entityName, String searchName){
        EntityManager searcher = entityManagerFactory.createEntityManager();

        List companiesWithNameOfFirstCompany = searcher.createQuery("from " + entityName + " where name = :searchName")
                .setParameter("searchName", searchName)
                .getResultList();

        searcher.close();

        return companiesWithNameOfFirstCompany;
    }
}