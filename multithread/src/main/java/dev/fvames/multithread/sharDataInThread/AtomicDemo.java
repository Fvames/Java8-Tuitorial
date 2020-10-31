package dev.fvames.multithread.sharDataInThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by James on 2018/10/19.
 */
public class AtomicDemo implements Runnable {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("threadName:" + Thread.currentThread().getName() + " >>>" + atomicInteger.addAndGet(1));
    }
}
