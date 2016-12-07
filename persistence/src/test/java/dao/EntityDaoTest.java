package dao;

import model.CompanyEntity;
import model.ComputerEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * @author yegor
 */
public class EntityDaoTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityDao computerDao;
    private EntityDao companyDao;

    private static final String firstCompanyName = "First company";
    private static final String firstComputerName = "First computer";

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory( "model" );

        computerDao = EntityDaoFactory.createEntityDao(entityManagerFactory.createEntityManager(), ComputerEntity.class);
        companyDao = EntityDaoFactory.createEntityDao(entityManagerFactory.createEntityManager(), CompanyEntity.class);
    }

    @After
    public void tearDown() throws Exception {
        entityManagerFactory.close();
    }

    @Test
    public void find() throws Exception {

    }

    @Test
    public void add() throws Exception {
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

        computerDao.add(new ComputerEntity(firstComputerName,
                introductionDate,
                discontinuingDate,
                company));
    }

    @Test
    public void refresh() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void listAll() throws Exception {

    }

    public CompanyEntity findCreateFirstCompany(){
        CompanyEntity firstCompany = (CompanyEntity) companyDao.find(firstCompanyName);

        if (firstCompany == null) {
            firstCompany = new CompanyEntity(firstCompanyName);
            companyDao.add(firstCompany);
        }

        return firstCompany;
    }

}