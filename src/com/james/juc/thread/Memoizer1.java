package com.james.juc.thread;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 2019/5/23 11:14
 */

public class Memoizer1<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    // 线程阻塞，低效
    @Override
    public synchronized V compute(A arg) {
        V result = cache.get(arg);

        if (null == result) {
            result = c.compute(arg);
            cache.put(arg, result);
        }

        return result;
    }

    public static void main(String[] args) {
        Memoizer1<String, BigInteger> memoizer1 = new Memoizer1(new ExpensiveStringToBigIntegerFunction());

        BigInteger result = memoizer1.compute("2");

        System.out.println("result: " + result);
    }
}
