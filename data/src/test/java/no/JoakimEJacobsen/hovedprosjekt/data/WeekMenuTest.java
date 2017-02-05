package no.JoakimEJacobsen.hovedprosjekt.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import java.util.*;
import java.util.logging.Level;

import static no.JoakimEJacobsen.hovedprosjekt.data.DefaultsForTesting.*;
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
        factory = Persistence.createEntityManagerFactory("DB_mysql");
        em = factory.createEntityManager();

        //setUpDefaults();
    }

    @After
    public void tearDown() {
        //if(em.isOpen())
        em.close();
        //if(factory.isOpen())
        factory.close();
    }


    @Test
    public void testWeekMenu_commitOneWeekMenu()
    {
        Dish debugDish01 = new Dish("A", "A_type", "A_Desc");
        Dish debugDish02 = new Dish("B", "B_type", "B_Desc");
        Dish debugDish03 = new Dish("C", "C_type", "C_Desc");
        Dish debugDish04 = new Dish("D", "D_type", "D_Desc");

        List<Dish> debugDishList01 = new ArrayList<>(Arrays.asList(debugDish01, debugDish02, debugDish03, debugDish04));
        List<Dish> debugDishList02 = new ArrayList<>(Arrays.asList(debugDish01, debugDish02, debugDish03, debugDish04));
        List<Dish> debugDishList03 = new ArrayList<>(Arrays.asList(debugDish01, debugDish02, debugDish03, debugDish04));

        Day wednesday = new Day(DaysEnum.WEDNESDAY);
        Day thursday = new Day(DaysEnum.THURSDAY);
        Day friday = new Day(DaysEnum.FRIDAY);

        DayMenu debugDayMenu01 = new DayMenu(wednesday, debugDishList01);
        DayMenu debugDayMenu02 = new DayMenu(thursday, debugDishList02);
        DayMenu debugDayMenu03 = new DayMenu(friday, debugDishList03);

        List<DayMenu> debugDayMenuList = Arrays.asList(debugDayMenu01, debugDayMenu02, debugDayMenu03);

        WeekMenuId debugWeekMenuId01 = new WeekMenuId(3, 2019);

        WeekMenu debugWeekMenu01 = new WeekMenu(debugWeekMenuId01, debugDayMenuList);

        assertEquals(3, debugWeekMenu01.getDayMenuList().size());

        // PERSIST //
        assertTrue(persistAndCommit(em, debugWeekMenu01));

        // USING DEFAULTS
//        assertTrue(persistAndCommit(em, weekMenu01));

        // READ FROM DATABASE TO CHECK RESULTS //
        em.clear();


//        WeekMenu testWeekMenu = em.find(WeekMenu.class, debugWeekMenu01.getId());
//        testWeekMenu.getDayMenuList().stream().forEach(x -> System.out.println(x.getDayMenuId()));
//        assertEquals(3, testWeekMenu.getDayMenuList().size());
        /**
         * When using the entitymanager to get the weekmenu, the size of testWeekmenu.getDaymenuList is 12,
         * But when doing the same operation with em.createQuery, the size is 3...
         * So TODO: Find out what the fck is going on...
         */
        Query query = em.createQuery("SELECT k FROM WeekMenu k");
        List<WeekMenu> weekMenus = query.getResultList();
        WeekMenu testWeekMenu = weekMenus.get(0);
        assertEquals(3, testWeekMenu.getDayMenuList().size());

        testWeekMenu.getDayMenuList().stream().forEach(x -> System.out.println(x.getDayMenuId()));


        assertEquals(4, testWeekMenu.getDayMenuList().get(0).getDishList().size());
        assertEquals(4, testWeekMenu.getDayMenuList().get(1).getDishList().size());
        assertEquals(4, testWeekMenu.getDayMenuList().get(2).getDishList().size());
    }

    // TODO: There has to be a better way to write this test (concerning asserts at the end)! Is it better to use streams or something?
    /**
     * ALSO: I had to create new objects for this test instead of using the ones from DefaultsForTesting.
     * When running the tests separately everything is fine, but when i try to run them both at once, one of them
     * always fails. I am actually not 100% sure why this is happening so TODO: Figure out why this is happening!
     * I think it might have something to do with the auto generated ID for Dish and DayMenu objects as they are given
     * an ID for the first test, so when it tries to commit the second time the ID field is not null.
     *
     * FIXED! THIS PROBLEM IS FIXED FOR NOW, LEAVE COMMENT UNTIL 100% SURE.
     */
    @Test
    public void testWeekMenu_commitTwoWeekMenusWithSameDayMenuList()
    {
//        Dish debugDish01 = new Dish("A", "A_type", "A_Desc");
//        Dish debugDish02 = new Dish("B", "B_type", "B_Desc");
//        Dish debugDish03 = new Dish("C", "C_type", "C_Desc");
//
//        Set<Dish> debugDishList = new HashSet<>(Arrays.asList(debugDish01, debugDish02, debugDish03));
//
//        DayMenu debugDayMenu01 = new DayMenu("Wed", debugDishList);
//        DayMenu debugDayMenu02 = new DayMenu("Thu", debugDishList);
//        DayMenu debugDayMenu03 = new DayMenu("Fri", debugDishList);
//
//        List<DayMenu> debugDayMenuList = Arrays.asList(debugDayMenu01, debugDayMenu02, debugDayMenu03);
//
//        WeekMenuId debugWeekMenuId01 = new WeekMenuId(3, 2019);
//        WeekMenuId debugWeekMenuId02 = new WeekMenuId(5, 2021);
//
//        WeekMenu debugWeekMenu01 = new WeekMenu(debugWeekMenuId01, debugDayMenuList);
//        WeekMenu debugWeekMenu02 = new WeekMenu(debugWeekMenuId02, debugDayMenuList);
//
//        // PERSIST //
//        assertTrue(persistAndCommit(em, debugWeekMenu01));
//        assertTrue(persistAndCommit(em, debugWeekMenu02));

        // USING DEFAULTS
        assertTrue(persistAndCommit(em, weekMenu01, weekMenu02));

        // READ FROM DATABASE TO CHECK RESULTS //
        em.clear();

        Query query = em.createQuery("SELECT k FROM WeekMenu k");
        List<WeekMenu> weekMenus = query.getResultList();

        assertEquals(2, weekMenus.size());

        assertEquals(3, weekMenus.get(0).getDayMenuList().size());
        assertEquals(3, weekMenus.get(1).getDayMenuList().size());

        assertEquals(3, weekMenus.get(0).getDayMenuList().get(0).getDishList().size());
        assertEquals(3, weekMenus.get(0).getDayMenuList().get(1).getDishList().size());
        assertEquals(3, weekMenus.get(0).getDayMenuList().get(2).getDishList().size());

        assertEquals(3, weekMenus.get(1).getDayMenuList().get(0).getDishList().size());
        assertEquals(3, weekMenus.get(1).getDayMenuList().get(1).getDishList().size());
        assertEquals(3, weekMenus.get(1).getDayMenuList().get(2).getDishList().size());
    }
}