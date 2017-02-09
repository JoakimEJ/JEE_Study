package no.JoakimEJacobsen.hovedprosjekt.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DaysEnum, DayMenu> dayMenuMap;

    // Empty constructor
    public WeekMenu() {}

    public WeekMenuId getId() {
        return id;
    }

    public void setId(WeekMenuId id) {
        this.id = id;
    }

    public Map<DaysEnum, DayMenu> getDayMenuMap() {
        return dayMenuMap;
    }

    public void setDayMenuMap(Map<DaysEnum, DayMenu> dayMenuMap) {
        this.dayMenuMap = dayMenuMap;
    }
}
