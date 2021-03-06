package no.JoakimEJacobsen.hovedprosjekt.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;


import static no.JoakimEJacobsen.hovedprosjekt.entities.DefaultsForTesting.*;
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
    }

    @After
    public void tearDown() {
        em.close();
        factory.close();
    }

    public DayMenu getCurrentDayMenu(EntityManager em) {
        Calendar c = GregorianCalendar.getInstance();
        int week = c.get(GregorianCalendar.WEEK_OF_YEAR);
        int year = c.get(GregorianCalendar.YEAR);
        int day = c.get(GregorianCalendar.DAY_OF_WEEK);
        DaysEnum today = DaysEnum.values()[day-2];

        if (day == 1 || day == 7) {
            today = DaysEnum.MONDAY;
        }

        Query query = em.createQuery("SELECT wm from WeekMenu wm WHERE wm.id.week = ?1 AND wm.id.year = ?2");
        query.setParameter(1, week);
        query.setParameter(2, year);

        WeekMenu tempWeekMenu = (WeekMenu) query.getSingleResult();

        return tempWeekMenu.getDayMenuMap().get(today);
    }

    @Test
    public void testGetCurrentDayMenu() {
        Calendar c = GregorianCalendar.getInstance();
        int week = c.get(GregorianCalendar.WEEK_OF_YEAR);
        int year = c.get(GregorianCalendar.YEAR);
        int day = c.get(GregorianCalendar.DAY_OF_WEEK);
        DaysEnum today = DaysEnum.values()[day-2];

        if (day == 1 || day == 7) {
            today = DaysEnum.MONDAY;
        }


        WeekMenu wm = getValidWeekMenu(week, year);
        persistAndCommit(em, wm);
        em.clear();

        DayMenu dayMenuFromWM = wm.getDayMenuMap().get(today);

        DayMenu dayMenuFromDB = getCurrentDayMenu(em);
        assertNotNull(dayMenuFromDB);

        assertEquals(dayMenuFromDB.getDishList().get(0), dayMenuFromWM.getDishList().get(0));
        assertEquals(dayMenuFromDB.getDishList().get(1), dayMenuFromWM.getDishList().get(1));
        assertEquals(dayMenuFromDB.getDishList().get(2), dayMenuFromWM.getDishList().get(2));
    }

    @Test
    public void testDayMenu_commitEmptyDaymenuCheckforNull() {

        // SETUP //
        DayMenu dayMenu = new DayMenu();

        // PERSIST (And commit..) // and clear em cache
        assertTrue(persistAndCommit(em, dayMenu));
        em.clear();

        dayMenu = em.find(DayMenu.class, dayMenu.getDayMenuId());

        // ASSERT //
        assertNotNull(dayMenu.getDishList()); // List is created but empty
        assertEquals(0, dayMenu.getDishList().size());
    }

    @Test
    public void testDayMenu_commit2DayMenusWithSameDishes() {

        // SETUP DISHES
        List<Dish> dishes = getValidListOfDishes();

        // SETUP DAYMENUS
        DayMenu dayMenu01 = getValidDayMenu(dishes);
        DayMenu dayMenu02 = getValidDayMenu(dishes);

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
    }
}