package dev.fvames.multithread.sharDataInThread;

/**
 * Created by James on 2018/10/19.
 */
public class SafeThreadData {
    private int anInt = 0;

    public synchronized void incr() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        anInt = anInt + 1;
        System.out.println("incr >>threadName:" + Thread.currentThread().getName() + " >>>" + anInt);
    }

    public synchronized void dec() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        anInt = anInt - 1;
        System.out.println("dec  >>threadName:" + Thread.currentThread().getName() + " >>>" + anInt);
    }
}
