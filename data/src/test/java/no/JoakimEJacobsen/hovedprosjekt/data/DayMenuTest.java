package no.JoakimEJacobsen.hovedprosjekt.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;
import java.util.logging.Level;


import static no.JoakimEJacobsen.hovedprosjekt.data.DefaultsForTesting.*;
import static no.JoakimEJacobsen.hovedprosjekt.myUtils.LocalUtils.persistAndCommit;
import static org.junit.Assert.*;

/**
 * Created by Joakim on 20.01.2017.
 */
public class DayMenuTest {

    private EntityManagerFactory factory;
    private EntityManager em;

    @Before
    public void init() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        factory = Persistence.createEntityManagerFactory("DB");
        em = factory.createEntityManager();

        setUpDefaults();
    }

    @After
    public void tearDown() {
        if(em.isOpen())
            em.close();
        if(factory.isOpen())
            factory.close();
    }

    @Test
    public void testDayMenu_commitEmptyDaymenuCheckforNull() {

        // SETUP //
        DayMenu dayMenu = new DayMenu();

        // PERSIST // and clear em cache
        assertTrue(persistAndCommit(em, dayMenu));
        em.clear();

        dayMenu = em.find(DayMenu.class, dayMenu.getDayMenuId());

        // ASSERT //
        assertNotNull(dayMenu.getDishList()); // List is created but empty
        assertEquals(0, dayMenu.getDishList().size());
    }

    @Test
    public void testDayMenu_commit2DayMenusWithSameDishes() {

        // PERSIST //
        assertTrue(persistAndCommit(em, dayMenu01));
        assertTrue(persistAndCommit(em, dayMenu02));

        assertEquals(3, em.find(DayMenu.class, dayMenu01.getDayMenuId()).getDishList().size());
        assertEquals(3, em.find(DayMenu.class, dayMenu02.getDayMenuId()).getDishList().size());

        // READ FROM DATABASE AND ASSERT //
        em.clear();

        Query query = em.createQuery("SELECT d FROM DayMenu d");
        List<DayMenu> dayMenus = query.getResultList();

        assertEquals(2, dayMenus.size());

        assertEquals(3, dayMenus.get(0).getDishList().size());
        assertEquals(3, dayMenus.get(1).getDishList().size());

//        for (DayMenu dayMenu : dayMenus) {
//            System.out.println("Daymenu id = " + dayMenu.getDayMenuId());
//            for (Dish dish : dayMenu.getDayMenuList()) {
//                System.out.println(dish);
//            }
//        }
    }
}