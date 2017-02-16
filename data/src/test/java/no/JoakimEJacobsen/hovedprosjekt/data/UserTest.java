package no.JoakimEJacobsen.hovedprosjekt.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import java.util.List;
import java.util.logging.Level;

import static no.JoakimEJacobsen.hovedprosjekt.myUtils.LocalUtils.*;
import static org.junit.Assert.*;

/**
 * Created by Joakim on 12.01.2017.
 */
public class UserTest {

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
        em.close();
        factory.close();
    }

    /**
     * QUERY-METHODS START
     */
    //Query all users
    public List<User> getAllUsers(EntityManager em) {
        Query query = em.createQuery("select u from User u");

        return query.getResultList();
    }

    // Count all users
    public long getCountAllUsers(EntityManager em) {
        Query query = em.createQuery("select count(u) from User u");

        return (long)query.getSingleResult();
    }
    /**
     * QUERY-METHODS END
     */

    @Test
    public void testCountAllUsers() {
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();

        persistAndCommit(em, u1, u2, u3);

        assertEquals(3L, getCountAllUsers(em));
    }

    @Test
    public void testIdPersistence() {
        User user = new User();
        user.setName("someName");
        user.setSurname("someSurname");

        // By default, no id, until data is committed to the database
        assertNull(user.getUserId());

        // Committing data to database needs to be inside a transaction
        assertTrue(persistAndCommit(em, user));

        assertEquals(1, user.getUserId().longValue());
    }
}