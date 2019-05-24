package com.james.juc.thread.executor;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * @version 2019/5/24 17:49
 */

public class CompletableFutureDemo {


    public static void main(String[] args) {
        CompletableFuture completableFuture = CompletableFuture
                .supplyAsync(() -> String.format("[Thread : %s] Hello,World ...", Thread.currentThread().getName()))
                .thenApply(value -> value + " - 来自于数据查询结果")
                .thenApply(value -> value + " at " + LocalDate.now())
                .thenApply(value -> {
                    System.out.println(value);
                    return value;
                })
                .thenRun(() -> System.out.println("执行完成"));

        while (!completableFuture.isDone()) {

        }

        System.out.println("end .... ");
    }

}
