package no.JoakimEJacobsen.hovedprosjekt.myUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by Joakim on 24.01.2017.
 */
public abstract class LocalUtils {

    public static boolean persistAndCommit(EntityManager em, Object... obj) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            for (Object o : obj)
                em.persist(o);
            tx.commit();
        } catch (Exception e) {
            System.out.println("FAILED TRANSACTION: " + e.toString() + "\n");
            e.printStackTrace();
            tx.rollback();
            return false;
        }

        return true;
    }
}
