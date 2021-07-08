package com.learning.disjointset;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String arg[]){
        /*DisjointSet ds = new DisjointSetImpl();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.unionSet(1, 2);
        ds.unionSet(2, 3);
        ds.unionSet(4, 5);
        ds.unionSet(6, 7);
        ds.unionSet(5, 6);
        ds.unionSet(3, 7);

        System.out.println(ds.findSet(1).data);
        System.out.println(ds.findSet(3).data);
        System.out.println(ds.findSet(4).data);*/

        //SJ->LA
        //LA->LV
        //LV->WD  find path
        Map<String, String> paths = new HashMap<>();
        paths.put("LA", "LV");
        paths.put("SJ", "LA");
        paths.put("LV", "WD");

        Map<String, Place> map = new HashMap<>();
        paths.entrySet().forEach(i -> {
            String key = i.getKey();
            String val = i.getValue();

            Place start = map.getOrDefault(key, new Place(key));
            Place end = map.getOrDefault(val, new Place(val));
            start.to = end;

            map.put(val, end);
        });

        for(Map.Entry<String, Place> entry: map.entrySet()){
            System.out.println(entry.getKey());
            Place temp = entry.getValue();
            while(temp!=null){
                System.out.print(temp.name+"->");
                temp=temp.to;
            }
        }

        Place start = null;
        for(Map.Entry<String, String> entry: paths.entrySet()){
            if(!map.containsKey(entry.getKey())) {
                start = new Place(entry.getKey());
                start.to = map.get(entry.getValue());
                break;
            }
        };

        /*System.out.println("Start is "+start.name);
        while (start!=null){
            System.out.print(start.name+"->");
            start = start.to;
        }*/
    }

}

class Place {
    String name;
    Place to;

    public Place(String name){
        this.name = name;
    }
}

class DisJointSet {
    Map<String, Item> items = new HashMap<>();

    public void makeSet(String data){
        Item item = new Item();
        item.parent = item;
        items.put(item.data, item);
    }

    public Item findSet(Item i){
        Item elem = items.get(i.data);
        if(elem.parent == elem)  return elem;
        else return findSet(elem.parent);
    }

    public void unionSet(Item item1, Item item2){
        if(item1.rank > item2.rank)
            item2.parent = item1;
        else if(item2.rank > item1.rank)
            item1.parent = item2;
        else if(item1.rank == item2.rank){
            item2.parent = item1;
            item1.rank = item1.rank+1;
        }
    }
}

class Item {
    String data;
    Item parent;
    int rank = 0;
}
