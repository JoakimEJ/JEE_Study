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
    static Map<DaysEnum, DayMenu> dayMenuMap = null;

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
        dayMenu01 = new DayMenu(dishes);
        dayMenu02 = new DayMenu(dishes);
        dayMenu03 = new DayMenu(dishes);

        // Map of DayMenus with days
        dayMenuMap = new HashMap<>();
        dayMenuMap.put(DaysEnum.WEDNESDAY, dayMenu01);
        dayMenuMap.put(DaysEnum.THURSDAY, dayMenu02);
        dayMenuMap.put(DaysEnum.FRIDAY, dayMenu03);

        // WeekMenu ID's
        weekMenuId01 = new WeekMenuId(6, 2017);
        weekMenuId02 = new WeekMenuId(10, 2022);

        // WeekMenus
        weekMenu01 = new WeekMenu();
        weekMenu01.setId(weekMenuId01);
        weekMenu01.setDayMenuMap(dayMenuMap);

        weekMenu02 = new WeekMenu();
        weekMenu02.setId(weekMenuId02);
        weekMenu02.setDayMenuMap(dayMenuMap);
    }
}
