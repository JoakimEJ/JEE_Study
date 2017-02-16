package no.JoakimEJacobsen.hovedprosjekt.entities;

/**
 * Created by Joakim on 05.02.2017.
 */
public enum DaysEnum {
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6);

    private int id;

    private DaysEnum(int id) {
        this.id = id;
    }

    public DaysEnum getDayById(int id) {
        switch (id) {
            case 2: return DaysEnum.MONDAY;
            case 3: return DaysEnum.TUESDAY;
            case 4: return DaysEnum.WEDNESDAY;
            case 5: return DaysEnum.THURSDAY;
            case 6: return DaysEnum.FRIDAY;
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
