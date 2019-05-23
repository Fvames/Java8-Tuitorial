package com.james.juc.thread;

/**
 * @version 2019/5/23 15:07
 */

public class InterruptedDemo1 {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();

        Thread thread1 = new Thread(() -> {
            if (Thread.currentThread().isInterrupted()) {
                thread.interrupt();
            }
        });

        thread1.start();
        thread1.interrupt();
    }

}
