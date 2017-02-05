package no.JoakimEJacobsen.hovedprosjekt.data;

import java.util.*;

/**
 * In order to use these properly when writing tests, remember to call the method setUpDefaults()
 * in @Before method of testing.
 */
abstract class DefaultsForTesting {

    // Dishes
    static Dish dish01 = null;
    static Dish dish02 = null;
    static Dish dish03 = null;

    // Set of Dishes
    static List<Dish> dishes = null;

    // DayMenus
    static DayMenu dayMenu01 = null;
    static DayMenu dayMenu02 = null;
    static DayMenu dayMenu03 = null;

    // List of DayMenus
    static List<DayMenu> dayMenus = null;

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

        // Set of dishes
        dishes = new ArrayList<>(Arrays.asList(dish01, dish02, dish03));

        // DayMenus
        dayMenu01 = new DayMenu(new Day(DaysEnum.WEDNESDAY), dishes);
        dayMenu02 = new DayMenu(new Day(DaysEnum.THURSDAY), dishes);
        dayMenu03 = new DayMenu(new Day(DaysEnum.FRIDAY), dishes);

        // List of DayMenus
        dayMenus = new ArrayList<>(Arrays.asList(dayMenu01, dayMenu02, dayMenu03));

        // WeekMenu ID's
        weekMenuId01 = new WeekMenuId(2, 2017);
        weekMenuId02 = new WeekMenuId(10, 2022);

        // WeekMenus
        weekMenu01 = new WeekMenu(weekMenuId01, dayMenus);
        weekMenu02 = new WeekMenu(weekMenuId02, dayMenus);
    }
}
