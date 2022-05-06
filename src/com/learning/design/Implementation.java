package com.learning.design;

import java.util.HashMap;
import java.util.Map;

public class Implementation {
    public static void main(String[] arg) {

    }
}

class TimeMap <X, Y , Z> {
    private Map<X, Map<Z, Y>> map = new HashMap<>();

    public void set(X key, Y value, Z time) {
        if(map.containsKey(key)) {
            Map<Z, Y> valueMap = map.get(key);
            valueMap.put(time, value);
        } else {
            Map<Z, Y> valueMap = new HashMap<>();
            valueMap.put(time, value);
            map.put(key, valueMap);
        }
    }

    public Y get(X key, Z time) {
        if(!map.containsKey(key)) return null;
        return map.get(key).get(time);
    }
}