package no.JoakimEJacobsen.hovedprosjekt.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Joakim on 12.01.2017.
 */
@Entity
@Access(AccessType.FIELD)
public class WeekMenu implements Serializable
{
    @EmbeddedId
    private WeekMenuId id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private List<DayMenu> dayMenuList;

    // Empty constructor
    public WeekMenu() {}

    // Full constructor
    public WeekMenu(WeekMenuId id, List<DayMenu> dayMenuList)
    {
        this.id = id;
        this.dayMenuList = dayMenuList;
    }

    public WeekMenuId getId() {
        return id;
    }

    public void setId(WeekMenuId id) {
        this.id = id;
    }

    public List<DayMenu> getDayMenuList() {
        return dayMenuList;
    }

    public void setDayMenuList(List<DayMenu> weekMenuList) {
        this.dayMenuList = weekMenuList;
    }
}
