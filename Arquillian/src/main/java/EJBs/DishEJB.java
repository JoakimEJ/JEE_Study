package EJBs;

import no.JoakimEJacobsen.hovedprosjekt.entities.Dish;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Joakim on 16.02.2017.
 */
@Stateless
public class DishEJB {

    @PersistenceContext//(unitName = "Arq")
    private EntityManager em;

    public DishEJB() {}

    public long registerNewDish(String name, String type, String description) {
        Dish dish = new Dish(name, type, description);

        em.persist(dish);

        return dish.getDishId();
    }

    public Dish getDishById(long id) {
        return em.find(Dish.class, id);
    }

    public List<Dish> getAllDishes() {
        Query q = em.createQuery("select d from Dish d");

        return q.getResultList();
    }
}
