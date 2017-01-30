package no.JoakimEJacobsen.hovedprosjekt.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
        if(em.isOpen())
            em.close();
        if(factory.isOpen())
            factory.close();
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

    /**
     * Here are all the test/code which has notes/comments included for
     * learning purposes. All tests/methods in this format has a name that
     * ends with .._Notes
     */
    @Test
    public void testIdPersistence_Notes() {
        User user = new User();
        user.setName("someName");
        user.setSurname("someSurname");

        // By default, no id, until data is committed to the database
        assertNull(user.getUserId());

        // Committing data to database needs to be inside a transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            /*
                The following is actually executing this SQL statement:

                insert into User (name, surname, id) values (?, ?, ?)

             */
            em.persist(user);

            // There can be several operations on the "cache" EntityManager before we actually commit the transaction
            tx.commit();
        } catch (Exception e) {
            // Abort the transaction if there was any exception
            // OBS: The entity might actually have an id now, despite the rollback
            tx.rollback();
            fail();
        }
    }

}