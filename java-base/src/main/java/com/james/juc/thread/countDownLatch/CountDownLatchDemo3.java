package com.james.juc.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @version 2019/5/22 10:41
 */

public class CountDownLatchDemo3 {

    public long timeTask(int nThreads) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            int finalI = i;
            new Thread(() -> {
                try {

                    startGate.await();
                    System.out.println(String.format("第 %s 此执行", String.valueOf(finalI)));


                } catch (Exception e) {

                } finally {
                    endGate.countDown();
                }
            }).start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long end = System.currentTimeMillis();

        return (end - start) / 1000;
    }


    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo3 demo3 = new CountDownLatchDemo3();
        System.out.println("执行耗时： " + demo3.timeTask(5));

    }

}
