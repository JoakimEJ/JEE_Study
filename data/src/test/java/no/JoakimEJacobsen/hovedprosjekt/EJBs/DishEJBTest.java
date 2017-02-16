package no.JoakimEJacobsen.hovedprosjekt.EJBs;

import no.JoakimEJacobsen.hovedprosjekt.entities.Dish;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Joakim on 16.02.2017.
 */
@RunWith(Arquillian.class)
public class DishEJBTest {

    protected static EJBContainer ec;
    protected static Context ctx;

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(DishEJB.class, Dish.class)
                .addAsResource("META-INF/persistence.xml");
    }

    @EJB
    private DishEJB dishEJB;


    @Test
    public void testCreateDish() {
        long findId = dishEJB.registerNewDish("dish01", "type01", "description01");

        Dish dishFromDB = dishEJB.getDishById(findId);

        assertNotNull(dishFromDB);

        assertTrue(dishFromDB.getName().equals("dish01"));
    }

}