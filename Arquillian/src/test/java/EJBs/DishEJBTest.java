package EJBs;

import EJBs.DishEJB;
import no.JoakimEJacobsen.hovedprosjekt.entities.Dish;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Joakim on 16.02.2017.
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DishEJBTest {

//    protected static EJBContainer ec;
//    protected static Context ctx;

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

    @Test
    public void testCreateDish2() {
        long findId = dishEJB.registerNewDish("dish01", "type01", "description01");

        Dish dishFromDB = dishEJB.getDishById(findId);

        assertNotNull(dishFromDB);

        assertTrue(dishFromDB.getName().equals("dish01"));
    }

    @Test
    public void zTest() {
        List<Dish> tempList = dishEJB.getAllDishes();

        assertEquals(2, tempList.size());
    }

}