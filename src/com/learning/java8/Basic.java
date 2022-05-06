package com.learning.java8;

import java.util.*;
import java.util.stream.Collectors;

public class Basic {

    public static void main(String[] arg) {
        Dish _1 = new Dish("Biryani", 430, "Arab Emirates");
        Dish _2 = new Dish("Pizza", 210, "India");
        Dish _3 = new Dish("Cake", 180, "India");
        List<Dish> menu  = Arrays.asList(new Dish[]{_1, _2, _3});

        List<String> result = findDishNamesWithCalorieLessThank(menu, 400);
        for (String str : result)
            System.out.println(str);
    }

    private static Map<String, List<Dish>> findDishesGroupByCountry(List<Dish> menu) {
        return menu
                .stream()
                .collect(Collectors.groupingBy(Dish::getCountry));
    }

    private static List<String> findDishNamesWithCalorieLessThank(List<Dish> menu, final int kCalorie) {
        Comparator<Dish> dishComparator01 = (d1, d2) ->
                        d1.calories > d2.calories ? 1
                        : d1.calories < d2.calories ? -1: 0;
        return menu
                .stream()
                .filter(d -> d.calories < kCalorie)
                .sorted(dishComparator01)
                .map(d -> d.name)
                .collect(Collectors.toList());
    }
}

class Dish {
    int calories;
    String name;
    String country;

    public Dish(String name, int calories, String country) {
        this.name = name;
        this.calories = calories;
        this.country = country;
    }

    public int getCalories() {
        return calories;
    }

    public String getCountry() {
        return country;
    }
}
