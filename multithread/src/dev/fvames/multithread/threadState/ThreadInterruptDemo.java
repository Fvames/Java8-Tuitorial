package dev.fvames.multithread.threadState;

import java.util.concurrent.TimeUnit;

/**
 * Created by James on 2018/10/21.
 */
public class ThreadInterruptDemo {

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        //interruptMethod();

        //recoverInterruptStateMethod();

        recoverByExceptionMethod();

        // 或者通过 volatile 修改的变量控制线程停止
    }

    private static void recoverByExceptionMethod() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        //TimeUnit.SECONDS.sleep(1);
        System.out.println("before: " + thread.isInterrupted());
        thread.interrupt();     // 设置中断标识位
        //TimeUnit.SECONDS.sleep(1);
        System.out.println("after: " + thread.isInterrupted());
    }

    private static void recoverInterruptStateMethod() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                boolean state = Thread.currentThread().isInterrupted();
                if (state) {
                    System.out.println("before :" + state);
                    Thread.interrupted(); // 对线程复位，中断标识为 false
                    System.out.println("after: " + Thread.currentThread().isInterrupted());
                }
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); // 设置中断标识
    }

    private static void interruptMethod() throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 没有中断指令时继续加
            while (!Thread.currentThread().isInterrupted()) {
                i++;
                System.out.println("STATTE: " + Thread.currentThread().getState());
            }
            System.out.println("num: " + i);
        }, "interruptDemo");

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
