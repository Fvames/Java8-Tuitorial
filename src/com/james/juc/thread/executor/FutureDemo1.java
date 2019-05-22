package com.james.juc.thread.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @version 2019/5/22 11:19
 */

public class FutureDemo1 {

    public static void main(String[] args) {
        Future<String> future = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1);
            return "1";
        });

        try {
            new Thread((Runnable) future).start();
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
