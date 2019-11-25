package dev.fvames.apache.commons;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionsDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        List<String> list2 = Arrays.asList("1", "6", "3", "9", "7");
        List<String> list3 = null;

        Collection union = CollectionUtils.union(list, list2);
        System.out.println("union: " + union);

        //Collection union2 = CollectionUtils.union(list, list3);
        //System.out.println("union2: "+ union2);

        Collection subtract = CollectionUtils.subtract(list, list2);
        System.out.println("subtract: " + subtract);

        //Collection subtract2 = CollectionUtils.subtract(list, list3);
        //System.out.println("subtract2: "+ subtract2);

        Collection retainAll = CollectionUtils.retainAll(list, list2);
        System.out.println("retainAll: " + retainAll);

        //Collection retainAll2 = CollectionUtils.retainAll(list, list3);
        //System.out.println("retainAll2: "+ retainAll2);


    }
}
