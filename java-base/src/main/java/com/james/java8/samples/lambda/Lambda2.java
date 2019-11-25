package com.james.java8.samples.lambda;

/**
 * Created by James on 2018/2/25.
 */
public class Lambda2 {
    public static void main(String[] args) {
        Convert<String, Integer> convert = (form -> Integer.valueOf(form));
        Integer integer1 = convert.convert("12345");
        System.out.println(integer1);
    }

    @FunctionalInterface
    interface Convert<F, T> {
        T convert(F form);
    }
}
