package no.JoakimEJacobsen.hovedprosjekt.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

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
        factory = Persistence.createEntityManagerFactory("DB");
        em = factory.createEntityManager();

        setUpDefaults();
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

        DayMenu debugDayMenu01 = new DayMenu(debugDishList01);
        DayMenu debugDayMenu02 = new DayMenu(debugDishList02);
        DayMenu debugDayMenu03 = new DayMenu(debugDishList03);

        Map<DaysEnum, DayMenu> debugDayMenuMap = new HashMap<>();
        debugDayMenuMap.put(DaysEnum.WEDNESDAY, debugDayMenu01);
        debugDayMenuMap.put(DaysEnum.THURSDAY, debugDayMenu02);
        debugDayMenuMap.put(DaysEnum.FRIDAY, debugDayMenu03);

        WeekMenuId debugWeekMenuId01 = new WeekMenuId(3, 2019);

        WeekMenu debugWeekMenu01 = new WeekMenu();
        debugWeekMenu01.setId(debugWeekMenuId01);
        debugWeekMenu01.setDayMenuMap(debugDayMenuMap);

        assertEquals(3, debugWeekMenu01.getDayMenuMap().size());

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
        assertEquals(3, testWeekMenu.getDayMenuMap().size());


        assertEquals(4, testWeekMenu.getDayMenuMap().get(DaysEnum.WEDNESDAY).getDishList().size());
        assertEquals(4, testWeekMenu.getDayMenuMap().get(DaysEnum.THURSDAY).getDishList().size());
        assertEquals(4, testWeekMenu.getDayMenuMap().get(DaysEnum.FRIDAY).getDishList().size());
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

        assertEquals(3, weekMenus.get(0).getDayMenuMap().size());
        assertEquals(3, weekMenus.get(1).getDayMenuMap().size());

        assertEquals(3, weekMenus.get(0).getDayMenuMap().get(DaysEnum.WEDNESDAY).getDishList().size());
        assertEquals(3, weekMenus.get(0).getDayMenuMap().get(DaysEnum.THURSDAY).getDishList().size());
        assertEquals(3, weekMenus.get(0).getDayMenuMap().get(DaysEnum.FRIDAY).getDishList().size());

        assertEquals(3, weekMenus.get(1).getDayMenuMap().get(DaysEnum.WEDNESDAY).getDishList().size());
        assertEquals(3, weekMenus.get(1).getDayMenuMap().get(DaysEnum.THURSDAY).getDishList().size());
        assertEquals(3, weekMenus.get(1).getDayMenuMap().get(DaysEnum.FRIDAY).getDishList().size());
    }

    private void query_CurrentWeekMenu() {
        Calendar c = GregorianCalendar.getInstance();
        int week = c.get(GregorianCalendar.WEEK_OF_YEAR);
        int year = c.get(GregorianCalendar.YEAR);
        int dayOfWeek = c.get(GregorianCalendar.DAY_OF_WEEK);
        System.out.println("Year: " + year + " | Week: " + week + " | DayOfWeek: " + dayOfWeek);

        DaysEnum tDay = DaysEnum.values()[dayOfWeek-2];
        System.out.println("Day of week name = " + tDay.name());
    }

    @Test
    public void getCurrentDayMenu() {

        /**
         * SETUP SETUP SETUP
         * THE WEEK THAT I AM GOING TO LOOK FOR
         */
        assertTrue(persistAndCommit(em, weekMenu01));
        em.clear();

        /**
         * THIS IS SETUP FOR QUERY + THE QUERY ITSELF!
         *
         * NOTHING WORKS FUCK MY LIFE IM ABOUT TO START TAKING DRUGS!
         */
        Calendar c = GregorianCalendar.getInstance();
        int week = c.get(GregorianCalendar.WEEK_OF_YEAR);
        int year = c.get(GregorianCalendar.YEAR);
        DaysEnum day = DaysEnum.values()[c.get(GregorianCalendar.DAY_OF_WEEK)-2];

        WeekMenuId weekId = new WeekMenuId(week, year);

        //=========================
        // TESTING CRITERIA BUILDER
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<WeekMenu> query = cb.createQuery(WeekMenu.class);
//        Metamodel m = em.getMetamodel();
//        EntityType<WeekMenu> WeekMenu_ = m.entity(WeekMenu.class);
//
//        Root<WeekMenu> weekMenuRoot = query.from(WeekMenu.class);
//        MapJoin<WeekMenu, DaysEnum, DayMenu> iNeedThis = weekMenuRoot.join(WeekMenu_.);


        //=========================
        //=========================
//        Query q3 = em.createQuery(
//                "SELECT DayMenu from WeekMenu wm, IN (wm.dayMenuMap) AS map " +
//                        "WHERE KEY(map) = " + day
//        );

//        Query query2 = em.createQuery(
//                 "SELECT NEW " + DayMenu.class.getName() + "(wm.dayMenuMap) FROM WeekMenu wm " +
//                         "WHERE wm.id.week = " + week +
//                         " AND wm.id.year = " + year +
//                         " AND key(dayMenuMap) = '" + day + "'"
//        );
        System.out.println("KAKKMADDAFAKKA");
//        Query query2 = em.createQuery(
//                "select DayMenu AS dm2 from WeekMenu.dayMenuMap AS map1 " +
//                        "where WeekMenu.id.week = " + week + " AND WeekMenu .id.year = " + year +
//                        " AND key(map1) = " + day
//        );

//        "SELECT r FROM WeekMenu r join r.dayMenuMap m " +
//                "where r.id.week = " + week +
//                " AND r.id.year = " + year +
//                " AND key(m) = '" + day + "'"
//        );

//        List<DayMenu> dayMenuFromQuery = q3.getResultList();
//        assertFalse(dayMenuFromQuery.isEmpty());
//        assertEquals(1, dayMenuFromQuery.size());

//        WeekMenu weekMenu = dayMenuFromQuery.get(0);
//        System.out.println("============ HERE WE GO ============\n" +
//        weekMenu.getDayMenuMap().size());
//        Set<DaysEnum> keys = weekMenu.getDayMenuMap().keySet();
//        for (DaysEnum d : keys)
//            System.out.println(weekMenu.getDayMenuMap().get(d).getDayMenuId());

//        DayMenu testDayMenu = dayMenuFromQuery.get(0);
//        System.out.println("THIS IS THE CLASS TYPE: " + dayMenuFromQuery.getClass().getName());
//        System.out.println(dayMenuFromQuery.size());
//        if (dayMenuFromQuery.get(0).getClass().equals(DayMenu.class)) {
//            for (Dish d : dayMenuFromQuery.get(0).getDishList())
//                System.out.println(d);
//        } else if (dayMenuFromQuery.get(0).getClass().equals(WeekMenu.class)) {
//            System.out.println("THIS IS A FUUKEN WEEKMENU NOW COOL I AM SO HAPPY..");
//        } else if (dayMenuFromQuery.get(0).equals(null)) {
//            System.out.println("HOW IS THIS POSSIBLE, LOOK AT THE GOD DAMN ASSERTS ABOVE!!!!");
//        } else {
//            System.out.println("I have no idea what is going on.. is this real life?");
//        }

    }

    public void testWeekMenu_getWeekMenuFromQueryMethod_True() {
        System.out.println("HERE COMES THE PADDLE");
        query_CurrentWeekMenu();
    }
}