package no.JoakimEJacobsen.hovedprosjekt.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;

import static no.JoakimEJacobsen.hovedprosjekt.myUtils.LocalUtils.persistAndCommit;
import static org.junit.Assert.*;

/**
 * Created by Joakim on 24.01.2017.
 */
public class DishTest {

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
    //Query all dishes
    public List<Dish> getAllDishes(EntityManager em) {
        Query query = em.createQuery("select d from Dish d");

        return query.getResultList();
    }

    // Count all dishes
    public long getCountAllDishes(EntityManager em) {
        Query query = em.createQuery("select count(d) from Dish d");

        return (long)query.getSingleResult();
    }
    /**
     * QUERY-METHODS END
     */

    @Test
    public void testCreateAndPersistDishWithEmptyConstructor() {
        Dish dish = new Dish();
        dish.setName("Meat");
        dish.setType("Dinner");
        dish.setDescription("Meat from cow, sauce from hamster");

        boolean isPersisted = persistAndCommit(em, dish);
        assertTrue(isPersisted);
    }

    @Test
    public void testCreateAndPersistDishWithFullConstructor() {
        Dish dish = new Dish("Meat", "Dinner", "Meat form cow, sauce from hamster");

        boolean isPersisted = persistAndCommit(em, dish);
        assertTrue(isPersisted);
    }

    @Test
    public void testDishWithIdWillFail() {
        Dish dish = new Dish("Meat", "Dinner", "Meat from cow, sauce from hamster");
        dish.setDishId(1L);

        boolean isPersisted = persistAndCommit(em, dish);
        assertFalse(isPersisted);
    }

}