package no.JoakimEJacobsen.hovedprosjekt.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import java.util.*;
import java.util.logging.Level;

import static no.JoakimEJacobsen.hovedprosjekt.entities.DefaultsForTesting.*;
import static no.JoakimEJacobsen.hovedprosjekt.myUtils.LocalUtils.persistAndCommit;
import static org.junit.Assert.*;

/**
 * Created by Joakim on 12.01.2017.
 */
public class WeekMenuTest
{
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


    @Test
    public void testWeekMenu_commitOneWeekMenu()
    {

        // Setup
        WeekMenu weekMenu01 = getValidWeekMenu();

        // Persist and commit
        assertTrue(persistAndCommit(em, weekMenu01));

        // READ FROM DATABASE TO CHECK RESULTS //
        em.clear();


//        WeekMenu testWeekMenu = em.find(WeekMenu.class, debugWeekMenu01.getId());
//        testWeekMenu.getDayMenuList().stream().forEach(x -> System.out.println(x.getDayMenuId()));
//        assertEquals(3, testWeekMenu.getDayMenuList().size());
        /**
         * When using the entitymanager to get the weekmenu, the size of testWeekmenu.getDaymenuList is 9,
         * But when doing the same operation with em.createQuery, the size is 3...
         * Solved: This only happens when fetchType of collection is EAGER
         */
        Query query = em.createQuery("SELECT k FROM WeekMenu k");
        List<WeekMenu> weekMenus = query.getResultList();
        WeekMenu testWeekMenu = weekMenus.get(0);
        assertEquals(3, testWeekMenu.getDayMenuMap().size());


        assertEquals(3, testWeekMenu.getDayMenuMap().get(DaysEnum.WEDNESDAY).getDishList().size());
        assertEquals(3, testWeekMenu.getDayMenuMap().get(DaysEnum.THURSDAY).getDishList().size());
        assertEquals(3, testWeekMenu.getDayMenuMap().get(DaysEnum.FRIDAY).getDishList().size());
    }

    @Test
    public void testWeekMenu_commitTwoWeekMenusWithSameDayMenuList()
    {
        // Setup
        WeekMenu weekMenu01 = getValidWeekMenu(8, 2018, getValidMapOfDayMenus(1));
        WeekMenu weekMenu02 = getValidWeekMenu(12, 2018, getValidMapOfDayMenus(4));

        // Persist and commit
        assertTrue(persistAndCommit(em, weekMenu01, weekMenu02));

        // READ FROM DATABASE TO CHECK RESULTS //
        em.clear();

        Query query = em.createQuery("SELECT k FROM WeekMenu k");
        List<WeekMenu> weekMenus = query.getResultList();

        assertEquals(2, weekMenus.size());

        assertEquals(3, weekMenus.get(0).getDayMenuMap().size());
        assertEquals(3, weekMenus.get(1).getDayMenuMap().size());

        // TODO: DishList.size -> Use java 8 streams to check size of DishList in all DayMenus of both WeekMenus
    }

    public WeekMenu getCurrentWeekMenu() {
        Calendar c = GregorianCalendar.getInstance();
        int week = c.get(GregorianCalendar.WEEK_OF_YEAR);
        int year = c.get(GregorianCalendar.YEAR);
        int dayOfWeek = c.get(GregorianCalendar.DAY_OF_WEEK);
        System.out.println("Year: " + year + " | Week: " + week + " | DayOfWeek: " + dayOfWeek);

        DaysEnum tDay = DaysEnum.values()[dayOfWeek-2];
        System.out.println("Day of week name = " + tDay.name());

        Query query = em.createQuery("select wm from WeekMenu wm WHERE wm.id.week = ?1 AND wm.id.year = ?2");
        query.setParameter(1, week);
        query.setParameter(2, year);

        if (query.getSingleResult() != null) {
            System.out.println("Found entry!");
        }

        return (WeekMenu) query.getSingleResult();
    }

    public WeekMenu getWeekMenu(EntityManager em, int week, int year) {
        Query query = em.createQuery("select wm from WeekMenu wm WHERE wm.id.week = ?1 AND wm.id.year = ?2");
        query.setParameter(1, week);
        query.setParameter(2, year);

        return (WeekMenu) query.getSingleResult();
    }

    @Test
    public void testGetWeekMenu() {
        WeekMenu wm1 = getValidWeekMenu(9, 2017);
        persistAndCommit(em, wm1);
        em.clear();

        WeekMenu weekMenuFromDB = getWeekMenu(em, 9, 2017);
        assertNotNull(weekMenuFromDB);
    }

    @Test
    public void TestGetCurrentWeekMenu() {
         // Setup for query + the query itself.
        Calendar c = GregorianCalendar.getInstance();
        int week = c.get(GregorianCalendar.WEEK_OF_YEAR);
        int year = c.get(GregorianCalendar.YEAR);
        DaysEnum day = DaysEnum.values()[c.get(GregorianCalendar.DAY_OF_WEEK)-2];

        WeekMenu weekMenu01 = getValidWeekMenu(week, year, getValidMapOfDayMenus(0));

        // Setup: put WeekMenu to search for in database
        assertTrue(persistAndCommit(em, weekMenu01));
        em.clear();

        WeekMenu weekMenuFromDB = getCurrentWeekMenu();
        assertNotNull(weekMenuFromDB);
        assertEquals(week, weekMenuFromDB.getId().getWeek());
        assertEquals(year, weekMenuFromDB.getId().getYear());
    }
}