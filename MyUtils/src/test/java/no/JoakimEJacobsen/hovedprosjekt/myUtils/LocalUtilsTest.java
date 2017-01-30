package no.JoakimEJacobsen.hovedprosjekt.myUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.*;

/**
 * Pretty sure its not necessary to test this, but it was a good exercise in
 * understanding project structure better
 * Created by Joakim on 24.01.2017.
 */
@SuppressWarnings("JpaQlInspection")
public class LocalUtilsTest {

    private EntityManagerFactory factory;
    private EntityManager em;

    @Before
    public void init() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        factory = Persistence.createEntityManagerFactory("DB");
        em = factory.createEntityManager();
    }

    @After
    public void tearDown() {
        if(em.isOpen())
            em.close();
        if(factory.isOpen())
            factory.close();
    }

    @Test
    public void testMultipleEntitiesInMethodParametersForMethod_PersistInATransaction() {
        UserEntityForTesting user01 = new UserEntityForTesting();
        UserEntityForTesting user02 = new UserEntityForTesting();
        UserEntityForTesting user03 = new UserEntityForTesting();

        user01.setName("Joakim");
        user02.setName("Nikita");
        user03.setName("Eva");

        boolean isPersisted = LocalUtils.persistAndCommit(em, user01, user02, user03);
        assertTrue(isPersisted);

        // READ FROM DATABASE TO CHECK RESULTS //
        em.close();
        em = factory.createEntityManager();

        Query query = em.createQuery("SELECT u FROM UserEntityForTesting u");
        List<UserEntityForTesting> users = query.getResultList();

        users.forEach(System.out::println);
    }

}