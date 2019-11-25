package com.james.juc.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @version 2019/5/23 13:57
 */

public class Memoizer3<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    // 较少可能存在重复运算
    @Override
    public V compute(A arg) {

        Future<V> future = cache.get(arg);
        if (null == future) {
            future = new FutureTask<>(() -> c.compute(arg));
            cache.put(arg, future);

            ((FutureTask<V>) future).run();
        }

        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
