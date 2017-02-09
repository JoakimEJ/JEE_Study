package no.JoakimEJacobsen.hovedprosjekt.data;

import java.util.*;

/**
 * This is now (it used to be something else) a factory for entities used in testing.
 */
abstract class DefaultsForTesting {

    /**
     * DISHES
     */
    static Dish getValidDish(String name, String type, String description) {

        Dish tempDish = new Dish(name, type, description);
        return tempDish;
    }

    static Dish getValidDish() {

        Dish tempDish = new Dish("A_valid_dish", "Any_Type", "This dish was autogenerated");
        return tempDish;
    }

    static Dish getValidDish(int i) {

        switch (i) {
            case 1 : return new Dish("Ant-soup", "Lunch", "Salty and crunchy lunchy");
            case 2 : return new Dish("Beetle-stew", "Dinner", "Protein rich dinner, it makes you slimmer");
            case 3 : return new Dish("Crepes", "Breakfast", "Crepe with ricotta and spinach, its good for your innards");
            case 4 : return new Dish("Dandelion salad", "Lunch", "Who's a good hippo? YOU ARE!");
            case 5 : return new Dish("Enchiladas", "Dinner", "Hola mi amigo, tienes hambre?!");
            case 6 : return new Dish("Freekeh", "Breakfast", "Mufasa on a diet, you also on a diet");
            case 7 : return new Dish("Garlic pork sandwich", "Lunch", "The perfect lunch when you hate your team");
            case 8 : return new Dish("Honey baked ham", "Dinner", "Hug a pig and kiss a bee; you owe them");
            case 9 : return new Dish("Iced Coffee", "Breakfast", "Order, pay, throw it away: you have no time for breakfast!");
            default : return new Dish("Name_Default", "Type_Default", "Description_youguessedit_Default");
        }
    }

    static List<Dish> getValidListOfDishes() {

        Dish dish01 = getValidDish(1);
        Dish dish02 = getValidDish(2);
        Dish dish03 = getValidDish(3);

        return Arrays.asList(dish01, dish02, dish03);
    }

    /**
     * DAYMENUS
     */
    static DayMenu getValidDayMenu(List<Dish> dishList) {

        DayMenu tempDayMenu = new DayMenu(dishList);
        return tempDayMenu;
    }

    static DayMenu getValidDayMenu() {

        DayMenu tempDayMenu = new DayMenu(getValidListOfDishes());
        return tempDayMenu;
    }

    static DayMenu getValidDayMenu(int i) {

        switch (i) {
            case 1 : return new DayMenu(Arrays.asList(getValidDish(1), getValidDish(2), getValidDish(3)));
            case 2 : return new DayMenu(Arrays.asList(getValidDish(4), getValidDish(5), getValidDish(6)));
            case 3 : return new DayMenu(Arrays.asList(getValidDish(7), getValidDish(8), getValidDish(9)));
            default : return new DayMenu(Arrays.asList(getValidDish(0), getValidDish(0), getValidDish(0)));
        }
    }

    static Map<DaysEnum, DayMenu> getValidMapOfDayMenus(int i) {

        Map<DaysEnum, DayMenu> dayMenuMap = new HashMap<>();

        switch (i) {
            case 1: {
                dayMenuMap.put(DaysEnum.WEDNESDAY, getValidDayMenu(1));
                dayMenuMap.put(DaysEnum.THURSDAY, getValidDayMenu(2));
                dayMenuMap.put(DaysEnum.FRIDAY, getValidDayMenu(3));
                break;
            }
            case 2: {
                dayMenuMap.put(DaysEnum.WEDNESDAY, getValidDayMenu(2));
                dayMenuMap.put(DaysEnum.THURSDAY, getValidDayMenu(3));
                dayMenuMap.put(DaysEnum.FRIDAY, getValidDayMenu(1));
                break;
            }
            case 3: {
                dayMenuMap.put(DaysEnum.WEDNESDAY, getValidDayMenu(3));
                dayMenuMap.put(DaysEnum.THURSDAY, getValidDayMenu(1));
                dayMenuMap.put(DaysEnum.FRIDAY, getValidDayMenu(2));
                break;
            }
            case 4: {
                dayMenuMap.put(DaysEnum.MONDAY, getValidDayMenu(1));
                dayMenuMap.put(DaysEnum.TUESDAY, getValidDayMenu(2));
                dayMenuMap.put(DaysEnum.WEDNESDAY, getValidDayMenu(3));
                break;
            }
            case 5: {
                dayMenuMap.put(DaysEnum.MONDAY, getValidDayMenu(2));
                dayMenuMap.put(DaysEnum.TUESDAY, getValidDayMenu(3));
                dayMenuMap.put(DaysEnum.WEDNESDAY, getValidDayMenu(1));
                break;
            }
            case 6: {
                dayMenuMap.put(DaysEnum.MONDAY, getValidDayMenu(3));
                dayMenuMap.put(DaysEnum.TUESDAY, getValidDayMenu(1));
                dayMenuMap.put(DaysEnum.WEDNESDAY, getValidDayMenu(2));
                break;
            }
            default: {
                dayMenuMap.put(DaysEnum.MONDAY, getValidDayMenu(1));
                dayMenuMap.put(DaysEnum.TUESDAY, getValidDayMenu(2));
                dayMenuMap.put(DaysEnum.WEDNESDAY, getValidDayMenu(3));
                dayMenuMap.put(DaysEnum.THURSDAY, getValidDayMenu(1));
                dayMenuMap.put(DaysEnum.FRIDAY, getValidDayMenu(2));
                break;
            }
        }
        return dayMenuMap;
    }

    /**
     * WEEKMENUS
     */

    static WeekMenu getValidWeekMenu() {

        return getValidWeekMenu(20, 2020);
    }

    static WeekMenu getValidWeekMenu(int week, int year) {

        WeekMenu tempWeekMenu = new WeekMenu();
        WeekMenuId tempWeekMenuId = new WeekMenuId();

        tempWeekMenuId.setWeek(week);
        tempWeekMenuId.setYear(year);

        tempWeekMenu.setId(tempWeekMenuId);
        tempWeekMenu.setDayMenuMap(getValidMapOfDayMenus(1));

        return tempWeekMenu;
    }

    static WeekMenu getValidWeekMenu(WeekMenuId id, Map<DaysEnum, DayMenu> dayMenuMap) {

        WeekMenu tempWeekMenu = new WeekMenu();

        tempWeekMenu.setId(id);
        tempWeekMenu.setDayMenuMap(dayMenuMap);

        return tempWeekMenu;
    }

    static WeekMenu getValidWeekMenu(int week, int year, Map<DaysEnum, DayMenu> dayMenuMap) {

        WeekMenu tempWeekMenu = new WeekMenu();
        WeekMenuId tempWeekMenuId = new WeekMenuId(week, year);

        tempWeekMenu.setId(tempWeekMenuId);
        tempWeekMenu.setDayMenuMap(dayMenuMap);

        return tempWeekMenu;
    }
}
