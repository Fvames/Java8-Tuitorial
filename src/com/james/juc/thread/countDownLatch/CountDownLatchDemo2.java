package com.james.juc.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @version 2019/5/22 9:47
 */

public class CountDownLatchDemo2 {

    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        new Thread(() -> {
            System.out.println("thread 1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread 2");

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread 3");

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        latch.countDown();
        System.out.println("耗时：" + (System.currentTimeMillis() - start) / 1000);
    }
}
