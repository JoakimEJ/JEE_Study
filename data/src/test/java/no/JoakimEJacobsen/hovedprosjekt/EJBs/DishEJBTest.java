package no.JoakimEJacobsen.hovedprosjekt.EJBs;

import no.JoakimEJacobsen.hovedprosjekt.entities.Dish;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
public class DishEJBTest {

    protected static EJBContainer ec;
    protected static Context ctx;

    @Before
    public void initContainer() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        ec = EJBContainer.createEJBContainer(properties);
        ctx = ec.getContext();
    }

    protected <T> T getEJB(Class<T> klass){
        try {
            return (T) ctx.lookup("java:global/classes/"+klass.getSimpleName()+"!"+klass.getName());
        } catch (NamingException e) {
            return null;
        }
    }

    @After
    public void closeContainer() throws Exception {
        if (ctx != null)
            ctx.close();
        if (ec != null)
            ec.close();
    }

    @Test
    public void testCreateDish() {
        DishEJB dishEJB = getEJB(DishEJB.class);
        long findId = dishEJB.registerNewDish("dish01", "type01", "description01");

        Dish dishFromDB = dishEJB.getDishById(findId);

        assertNotNull(dishFromDB);

        assertTrue(dishFromDB.getName().equals("dish01"));
    }

}