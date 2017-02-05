package no.JoakimEJacobsen.hovedprosjekt.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Joakim on 12.01.2017.
 */
@Entity
@Access(AccessType.FIELD)
public class DayMenu implements Serializable
{
    @Id @GeneratedValue
    private Long dayMenuId;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private Day dayOfWeek;

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
    public DayMenu(Day dayOfWeek, List<Dish> dishList) {
        this.dayOfWeek = dayOfWeek;
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

    public Day getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Day dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
