package no.JoakimEJacobsen.hovedprosjekt.data;

import com.sun.istack.internal.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Joakim on 05.02.2017.
 */
@SuppressWarnings("JpaAttributeMemberSignatureInspection")
@Entity
public class Day {

    @Id @NotNull
    private Long id;

    @NotNull
    private String dayName;

    // Empty constructor
    public Day() {}

    // Full constructor
    public Day(DaysEnum daysEnum) {
        setDayName(daysEnum);
    }

    public Long getId() {
        return id;
    }

    public void setId(DaysEnum day) {
        setDayName(day);
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(DaysEnum day) {
        this.dayName = day.name();
        this.id = (long) day.getDaysEnumId();
    }
}
