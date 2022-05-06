package com.learning.companies;

/* IMPORTANT: Multiple classes and nested static classes are supported */
import java.util.*;
import java.util.regex.*;

class AmazonTest2 {

    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        ArrayList<String> logs = new ArrayList<>();
        while(s.hasNextLine())
            logs.add(s.nextLine());
        String result = maxRepeatingNumber(logs);
        System.out.println(result);
    }

    private static String maxRepeatingNumber(ArrayList<String> logs) {
        Map<String, Integer> map = new HashMap<>();
        String result = null;
        int maxCount = 0;

        for(String entry: logs) {
            Pattern p = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
            Matcher m = p.matcher(entry);
            if(m.find()) {
                String key = m.group(0);
                if(map.containsKey(key)) map.put(key, map.get(key) + 1);
                else map.put(key, 1);
            }
        }

        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();

            if(value > maxCount) {
                maxCount = value;
                result = key;
            }
        }

        return result;
    }
}
