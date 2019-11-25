package dev.fvames.apache.commons;

import org.apache.commons.collections.*;
import org.apache.commons.collections.bag.HashBag;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;

public class MapDemo {

    public static void main(String[] args) {

        //iterableMap();

        //orderMap();

        //bidirectionalMap();

        bagMap();
    }

    private static void bagMap() {
        Bag bag = new HashBag();
        bag.add("ONE", 6);
        bag.add("ONE", 1);

        System.out.println(bag.getCount("ONE"));
        ;

        bag.remove("ONE", 2);
        System.out.println(bag.getCount("ONE"));

    }

    // key - value 保持唯一
    private static void bidirectionalMap() {
        BidiMap map = new TreeBidiMap();
        map.put("ONE", "1");
        System.out.println(map.get("ONE"));

        map.put("ONE-1", "1");
        //map.put("ONE", "2");

        System.out.println(map.get("ONE"));
        System.out.println(map.getKey("ONE"));
        System.out.println(map.getKey("1"));
    }

    private static void orderMap() {
        OrderedMap map = new LinkedMap();
        map.put("ONE", "1");
        map.put("TWO", "2");
        map.put("THREE", "3");

        System.out.println(map.firstKey());
        System.out.println(map.nextKey("THREE"));
        System.out.println(map.nextKey("TWO"));
    }

    private static void iterableMap() {
        IterableMap map = new HashedMap();
        map.put("ONE", "1");
        map.put("TWO", "2");
        map.put("THREE", "3");

        MapIterator mapIterator = map.mapIterator();
        while (mapIterator.hasNext()) {
            Object key = mapIterator.next();
            Object key1 = mapIterator.getKey();
            Object value = mapIterator.getValue();

            System.out.printf("key:%s, key1:%s, value:%s \n",
                    key, key1, value);
        }
    }
}
