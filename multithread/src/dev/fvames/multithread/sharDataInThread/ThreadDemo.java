package dev.fvames.multithread.sharDataInThread;

/**
 * Created by James on 2018/10/26.
 */
public class ThreadDemo {

    public static volatile ThreadDemo instance = null;

    public static synchronized ThreadDemo getInstance() {
        if (instance == null) {
            instance = new ThreadDemo();
        }
        return instance;
    }


    public static void main(String[] args) {
        ThreadDemo.getInstance();
    }
}
