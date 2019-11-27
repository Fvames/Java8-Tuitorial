package dev.fvames.apache.commons;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

/**
 * 多返回值得封装
 */
public class ImmutableDemo {

    public static void main(String[] args) {
        // 一对（两个）
        ImmutablePair pair = ImmutablePair.of("1", "3");
        System.out.println("pair left :" + pair.getLeft());
        System.out.println("pair right :" + pair.getRight());

        // 三个
        ImmutableTriple triple = ImmutableTriple.of("One", "Two", "Three");
        System.out.println("triple right :" + triple.getRight());
        System.out.println("triple middle :" + triple.getMiddle());
        System.out.println("triple left :" + triple.getLeft());
    }
}
