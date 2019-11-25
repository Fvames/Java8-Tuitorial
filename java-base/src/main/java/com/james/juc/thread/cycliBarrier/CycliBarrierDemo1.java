package com.james.juc.thread.cycliBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一组线程互相等待，然后同时执行
 *
 * @version 2019/5/22 13:21
 */

public class CycliBarrierDemo1 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        ExecutorService executors = Executors.newFixedThreadPool(2); // 3

        for (int i = 0; i < 10; i++) {
            executors.execute(new Task(cyclicBarrier));
        }

        executors.shutdown();
    }


    static class Task implements Runnable {

        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(String.format("%s 开始写入数据", Thread.currentThread().getName()));
                barrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(String.format(">>> %s 完成写入数据", Thread.currentThread().getName()));
        }
    }
}
