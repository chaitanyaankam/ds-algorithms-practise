package com.learning.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Itinerary {

    public static void main(String arg[]) {
        Map<String, String> map = new HashMap<>();
        map.put("SFO", "HKO");
        map.put("YYZ", "SFO");
        map.put("YUL", "YYZ");
        map.put("HKO", "ORD");

        Map<String, String> reverseMap = new HashMap<>();
        map.entrySet().forEach(entry->reverseMap.put(entry.getValue(), entry.getKey()));
        String start = map.keySet().stream().filter(key -> !reverseMap.containsKey(key)).findAny().get();

        String temp = start;
        List<String> list = new ArrayList<>();
        while(temp != null) {
            list.add(temp);
            temp = map.getOrDefault(temp, null);
        }

        list.stream().forEach(i -> System.out.print(i+" "));
    }
}
