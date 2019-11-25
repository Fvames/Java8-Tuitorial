package com.james.juc.thread;

import java.util.concurrent.TimeUnit;

public class InterruptedDemo {

    private static volatile int i;
    private static volatile int j;

    public static void main(String[] args) throws InterruptedException {

        //interrupt1();
        System.out.println("------------------------------");

        //interrupt2();
        System.out.println("------------------------------");

        interrupt3();
        System.out.println("------------------------------");
    }

    private static void interrupt1() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (Thread.currentThread().isInterrupted()) {
                System.out.println("before............");

                // 复位
                Thread.interrupted();

                System.out.println("after............");
            }
        }, "interruptDemo1");

        thread1.start();
        thread1.interrupt();

        System.out.println("thread1 state: " + thread1.isInterrupted());
    }

    private static void interrupt2() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("i：" + i);

        }, "interruptDemo2");

        thread1.start();
        TimeUnit.SECONDS.sleep(2);
        thread1.interrupt();

        System.out.println("thread2 state: " + thread1.isInterrupted());
    }

    private static void interrupt3() throws InterruptedException {
        Thread thread1 = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("before............");

                // 复位
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("after............");
            }


        }, "interruptDemo3");

        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.interrupt();
        System.out.println("thread3 state: " + thread1.isInterrupted());
        System.out.println("thread3 state: " + thread1.isInterrupted());

    }
}
