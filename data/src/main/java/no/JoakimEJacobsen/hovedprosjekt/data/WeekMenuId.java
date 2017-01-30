package no.JoakimEJacobsen.hovedprosjekt.data;

import com.sun.istack.internal.NotNull;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Joakim on 26.01.2017.
 */
@Embeddable
public class WeekMenuId implements Serializable {

    @NotNull
    private int week;

    @NotNull
    private int year;

    public WeekMenuId() {
    }

    public WeekMenuId(int week, int year) {
        this.week = week;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Week number: " + week + " Year: " + year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeekMenuId that = (WeekMenuId) o;

        if (week != that.week) return false;
        return year == that.year;
    }

    @Override
    public int hashCode() {
        int result = week;
        result = 31 * result + year;
        return result;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
