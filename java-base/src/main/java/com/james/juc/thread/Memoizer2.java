package com.james.juc.thread;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 2019/5/23 11:10
 */

public class Memoizer2<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    // 复合操作，可能存在重复计算
    @Override
    public V compute(A arg) {
        V result = cache.get(arg);

        if (null == result) {

            result = c.compute(arg);
            cache.put(arg, result);
        }

        return result;
    }

    public static void main(String[] args) {
        Memoizer2<String, BigInteger> memoizer2 = new Memoizer2<>(new ExpensiveStringToBigIntegerFunction());
        BigInteger result = memoizer2.compute("7");
        System.out.println(result);

    }
}
