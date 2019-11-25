package com.james.juc.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class NetWorkServerMain {

    public static void main(String[] args) throws Exception {

        NetWorkServer networkServer = new NetWorkServer(8080, 10);

        System.out.println(">>>>>> begin ");

        Thread thread = new Thread(networkServer);
        thread.start();

        TimeUnit.SECONDS.sleep(3);

        ExecutorService pool = networkServer.getPool();

        shutdownAndAwaitTermination(pool);

    }

    static void shutdownAndAwaitTermination(ExecutorService pool) {
        // 拒绝接收 task
        pool.shutdown();
        System.out.println(">>>>>> shutdown， 再请求一次终止线程");
        System.out.println(">>>>>> 1: isShutdown: " + pool.isShutdown() + ", isTerminated: " + pool.isTerminated());
        try {
            if (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
                pool.shutdownNow();

                System.out.println(">>>>>> 2: isShutdown: " + pool.isShutdown() + ", isTerminated: " + pool.isTerminated());

                if (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.err.println("pool did not termination ");
                }
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println(">>>>>> 3: isShutdown: " + pool.isShutdown() + ", isTerminated: " + pool.isTerminated());

    }


}
