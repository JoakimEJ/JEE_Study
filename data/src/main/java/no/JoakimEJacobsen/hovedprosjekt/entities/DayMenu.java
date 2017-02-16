package no.JoakimEJacobsen.hovedprosjekt.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Joakim on 12.01.2017.
 */
@Entity
@Access(AccessType.FIELD)
public class DayMenu implements Serializable
{
    @Id @GeneratedValue
    private Long dayMenuId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private List<Dish> dishList;

    // Empty Constructor
    public DayMenu() {}

    // Full Constructor
    public DayMenu(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public Long getDayMenuId() {
        return dayMenuId;
    }

    public void setDayMenuId(Long dayMenuId) {
        this.dayMenuId = dayMenuId;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dayMenuList) {
        this.dishList = dayMenuList;
    }
}
