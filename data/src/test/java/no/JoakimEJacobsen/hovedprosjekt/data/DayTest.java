package no.JoakimEJacobsen.hovedprosjekt.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.logging.Level;

import static no.JoakimEJacobsen.hovedprosjekt.data.DefaultsForTesting.setUpDefaults;
import static no.JoakimEJacobsen.hovedprosjekt.myUtils.LocalUtils.persistAndCommit;
import static org.junit.Assert.*;

/**
 * Created by Joakim on 05.02.2017.
 */
public class DayTest {

    private EntityManagerFactory factory;
    private EntityManager em;

    @Before
    public void init() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        factory = Persistence.createEntityManagerFactory("DB");
        em = factory.createEntityManager();

        //setUpDefaults();
    }

    @After
    public void tearDown() {
        em.close();
        factory.close();
    }

    @Test
    public void testDay_commit2DaysWithSameEnum_Fails() {
        Day day01 = new Day(DaysEnum.FRIDAY);
        Day day02 = new Day(DaysEnum.FRIDAY);

        assertTrue(persistAndCommit(em, day01)); // The first one works
        em.clear();
        assertFalse(persistAndCommit(em, day02)); // .. but this should not
    }

    @Test
    public void testDay_getDaysForDaymenuFromDatabase_True() {
        Day friday = new Day(DaysEnum.FRIDAY);

        assertTrue(persistAndCommit(em, friday));
        em.clear();

        friday = em.find(Day.class, DaysEnum.FRIDAY.getDaysEnumId());

        DayMenu dayMenu01 = new DayMenu();
        dayMenu01.setDayOfWeek(friday);

        assertTrue(persistAndCommit(em, dayMenu01));
        em.clear();

        dayMenu01 = em.find(DayMenu.class, dayMenu01.getDayMenuId());
        assertEquals("FRIDAY", dayMenu01.getDayOfWeek().getDayName());
        assertEquals(DaysEnum.FRIDAY.getDaysEnumId(), dayMenu01.getDayOfWeek().getId());
    }
}