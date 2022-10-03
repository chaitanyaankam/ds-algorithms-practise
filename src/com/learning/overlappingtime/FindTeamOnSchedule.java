package com.learning.overlappingtime;

import java.util.*;
import java.util.stream.Collectors;

public class FindTeamOnSchedule {

    private static void solve(List<WorkCalendar> calendarList, int max) {
        HashSet<String>[] hours = new HashSet[max + 1];

        for(int i = 0; i < calendarList.size(); i++) {
            WorkCalendar cal = calendarList.get(i);
            if(hours[cal.start] == null) hours[cal.start] = new HashSet<>();
            if(hours[cal.end] == null) hours[cal.end] = new HashSet<>();
            hours[cal.start].add(cal.people);
            hours[cal.end].add(cal.people);
        }

        Set<String> curr = new HashSet<>();
        for(int i = 0; i < hours.length; i++) {
            if(curr.isEmpty()) {
                hours[i + 1].forEach(curr::add);
                i = i + 2;
            }
            HashSet<String> ppl = hours[i];
            if(ppl == null) hours[i] = ppl = new HashSet<>();

            for(String person: curr) {
                if(!ppl.contains(person)) ppl.add(person);
            }
            for(String person: ppl) {
                if(curr.contains(person)) curr.remove(person);
                else curr.add(person);
            }
        }

        for(int i = 1; i < hours.length; i ++) {
            System.out.println(i +" "+ hours[i].stream().collect(Collectors.joining(",")));
        }
    }

    public static void main(String arg[]) {
        int[][] time = {
                {1, 3},
                {2, 4},
                {3, 4},
                {5, 7},
                {6, 8}
        };
        String[] names = {"Jhon", "Chaitanya", "Sachin", "Bappu", "Kristine"};
        List<WorkCalendar> calendarList = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < time.length; i++) {
            WorkCalendar curr = new WorkCalendar(time[i][0], time[i][1], names[i]);
            calendarList.add(curr);
            max = Math.max(max, time[i][1]);
        }
        solve(calendarList, max);
    }
}

class WorkCalendar {
    int start;
    int end;
    String people;

    public WorkCalendar(int start, int end, String people) {
        this.start = start;
        this.end = end;
        this.people = people;
    }
}
