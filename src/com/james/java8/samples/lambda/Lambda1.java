package com.james.java8.samples.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by James on 2018/2/22.
 */
public class Lambda1 {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("zhangsan", "lisi", "wangwu", "gouliu");

        //collectionSort(names);

        //lambdaSort(names);

        lambdaSortSimple(names);

        System.out.println(names.toString());
    }

    static void collectionSort(List<String> names) {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    static void lambdaSort(List<String> names) {
        Collections.sort(names, (o1, o2) -> o1.compareTo(o2));
    }

    static void lambdaSortSimple(List<String> names) {
        Collections.sort(names, String::compareTo);
    }
}
