package no.JoakimEJacobsen.hovedprosjekt.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * In order to use these properly when writing tests, remember to call the method resetDefaults()
 * in @After method of testing.
 */
abstract class DefaultsForTesting {

    // Dishes
    static Dish dish01 = null;
    static Dish dish02 = null;
    static Dish dish03 = null;

    // List of Dishes
    static final List<Dish> dishes = new ArrayList<>(Arrays.asList(dish01, dish02, dish03));

    // DayMenus
    static DayMenu dayMenu01 = null;
    static DayMenu dayMenu02 = null;
    static DayMenu dayMenu03 = null;

    // List of DayMenus
    static final List<DayMenu> dayMenus = new ArrayList<>(Arrays.asList(dayMenu01, dayMenu02, dayMenu03));

    // WeekMenu ID's
    static WeekMenuId weekMenuId01 = null;
    static WeekMenuId weekMenuId02 = null;

    // WeekMenus
    static WeekMenu weekMenu01 = null;
    static WeekMenu weekMenu02 = null;

    static void setUpDefaults() {
        // Dishes
        dish01 = new Dish("Dish_A", "Dish_A_type", "Dish_A_description");
        dish02 = new Dish("Dish_B", "Dish_B_type", "Dish_B_description");
        dish03 = new Dish("Dish_C", "Dish_C_type", "Dish_C_description");

        // DayMenus
        dayMenu01 = new DayMenu("Wednesday", dishes);
        dayMenu02 = new DayMenu("Thursday", dishes);
        dayMenu03 = new DayMenu("Friday", dishes);

        // WeekMenu ID's
        weekMenuId01 = new WeekMenuId(2, 2017);
        weekMenuId02 = new WeekMenuId(10, 2022);

        // WeekMenus
        weekMenu01 = new WeekMenu(weekMenuId01, dayMenus);
        weekMenu02 = new WeekMenu(weekMenuId02, dayMenus);
    }
}
