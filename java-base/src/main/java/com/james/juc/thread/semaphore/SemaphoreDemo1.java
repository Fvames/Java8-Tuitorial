package com.james.juc.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 对资源限制访问，控制同时访问某个资源的操作数量
 *
 * @version 2019/5/22 13:21
 */

public class SemaphoreDemo1 {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ExecutorService executors = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            executors.execute(new Task(semaphore));
        }

        executors.shutdown();
    }


    static class Task implements Runnable {

        private Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            semaphore.tryAcquire();

            System.out.println(String.format("%s 开始写入数据", Thread.currentThread().getName()));

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();

            System.out.println(String.format("**** %s 完成写入数据", Thread.currentThread().getName()));
        }
    }
}
