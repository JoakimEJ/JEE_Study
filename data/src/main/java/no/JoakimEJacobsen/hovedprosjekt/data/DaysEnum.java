package no.JoakimEJacobsen.hovedprosjekt.data;

/**
 * Created by Joakim on 05.02.2017.
 */
public enum DaysEnum {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY;

    public Long getDaysEnumId() {
        Long temp = 0L;
        switch (this) {
            case MONDAY: temp = 1L; break;
            case TUESDAY: temp = 2L; break;
            case WEDNESDAY: temp = 3L; break;
            case THURSDAY: temp = 4L; break;
            case FRIDAY: temp = 5L; break;
        }
        return temp;
    }
}
