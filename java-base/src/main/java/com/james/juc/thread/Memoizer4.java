package com.james.juc.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @version 2019/5/23 14:09
 */

public class Memoizer4<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer4(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) {
        Future<V> result = cache.get(arg);

        if (null == result) {
            Future<V> futureTask = new FutureTask<>(() -> c.compute(arg));
            // 原子操作
            result = cache.putIfAbsent(arg, futureTask);

            if (null == result) {
                result = futureTask;
                ((FutureTask<V>) futureTask).run();
            }
        }

        try {
            result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
