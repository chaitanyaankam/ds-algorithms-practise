package com.learning.treeset;

import java.util.Arrays;
import java.util.TreeSet;

/*
You have heard rumours of UFOs, and you want to see if you can spot one.
You record the times you see objects in the sky one night from 6-11:59pm (1800-2359).
You also have a list of airplane flights for your location, with time they appeared in the sky and their duration in minutes visible in the sky.
A flight at 1950 with a duration of 3 was visible at 1950, 1951 and 1952 (not visible at 1953).
Your task is to write a function that takes a list of times you saw an object in the sky and a list of flight data,
and returns the time you saw an object that was a UFO (not an airplane). Times are represented in 24 hour time HHMM.

        sightings1 = [2130, 1855, 1902, 2001, 2200]
        flights1 = [
        [1850, 15], # Period 1850-1904 covers sightings at 1855 and 1902
        [2130, 1],  # Period 2130 covers sighting at 2130
        [2218, 10], # Period 2218-2227 Not seen
        [1959, 3]   # Period 1959-2001 covers sighting at 2001
        ]The sighting which was not a flight was: 2200
        finder(sightings1, flights1) => 2200
        --------------------------------------------------
        Additional Inputs
        sightings2 = [1930, 2217]
        flights2 = [
        [1800, 121] #1800-2000

        //2217 will be UFO
        ]

        sightings3 = [1804, 2217, 2301]
        flights3 = [
        [2200, 17], //2200-2216
        [1800, 5], //1800-1804
        [2259, 4] //2259-2303
        ]
//1804
        --------------------------------------------------
        All Test Cases:
        finder(sightings1, flights1) => 2200
        finder(sightings2, flights2) => 2217
        finder(sightings3, flights3) => 2217
        --------------------------------------------------
        Complexity Variables:
        s = number of sightings
        f = number of flights
        t = minutes in the night */
public class UFOAndFlights {

    public static void main(String arg[]) {
        int[] sightings = {2130, 1855, 1902, 2001, 2200};
        int[][] flights = {
                {1850, 15}, // Period 1850-1904 covers sightings at 1855 and 1902
                {2130, 1},  // Period 2130 covers sighting at 2130
                {2218, 10}, // Period 2218-2227 Not seen
                {1959, 3}   // Period 1959-2001 covers sighting at 2001
        };
        int result = findUfo(sightings, flights);
        System.out.println("sighting "+ result);

        int[] sightings2 = {1804, 2217, 2301};
        int[][] flights2 = {
                {2200, 17}, //2200-2216
                {1800, 5}, //1800-1804
                {2259, 4} //2259-2303
        };
        result = findUfo(sightings2, flights2);
        System.out.println("sighting "+ result);
    }

    /**
     * constructs a tree set checks floor & ceiling of every sight.
     * Decides if every sight is in range or not.
     * If any sight not in range returns true
     * */
    public static int findUfo(int[] sights, int[][] flights) {
        TreeSet<int[]> treeSet = new TreeSet<>((x, y) -> x[0] < y[0] ? -1 : 1);
        Arrays.stream(flights).forEach(treeSet::add);
        treeSet.stream().forEach(i -> System.out.println(i[0] +" "+ i[1]));

        for(int sight: sights) {
            int[] s_arr = new int[] {sight, 0};
            boolean ufo = isNotInRange(sight, treeSet.floor(s_arr))
                    && isNotInRange(sight, treeSet.ceiling(s_arr));
            if(ufo) return sight;
        }
        return -1;
    }

    /**
     * fd -> flight destination
     * lft -> flight start time (HHMM), lfh -> start time HH, lfm -> start time MM
     * rft -> flight end time (HHMM), rfh -> end time HH, rfm -> end time MM
     * @return boolean
     * */
    public static boolean isNotInRange(int sight, int[] flight) {
        String lftStr = String.valueOf(flight[0]);
        int fd  = flight[1];
        int lft = Integer.parseInt(lftStr);
        int lfh = Integer.parseInt(lftStr.substring(0, 2));
        int lfm = Integer.parseInt(lftStr.substring(2, 4));

        int rfm = 0, rfh = 0, rft = 0;

        //TODO need to change minutes logic here
        if (lfm + fd >= 60) {
            rfh = lfh + 1;
            rfm = lfm + fd - 60 - 1;
        } else if (lfm + fd < 60) {
            rfh = lfh;
            rfm = lfm + fd - 1;
        }

        String temp = "00" + rfm;
        int len = temp.length();
        rft = Integer.parseInt(rfh + temp.substring(len - 2, len));

        System.out.println(lft + " "+ sight +" "+ rft);
        return !(lft <= sight  && sight <= rft);
    }
}
