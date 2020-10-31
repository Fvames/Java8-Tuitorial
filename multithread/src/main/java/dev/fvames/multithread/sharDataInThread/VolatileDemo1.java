package dev.fvames.multithread.sharDataInThread;

/**
 * Created by James on 2018/10/26.
 */
public class VolatileDemo1 {

    volatile int i;

    public void incr() {
        i++;
    }

    public static void main(String[] args) {
        new VolatileDemo1().incr();

    }

}
