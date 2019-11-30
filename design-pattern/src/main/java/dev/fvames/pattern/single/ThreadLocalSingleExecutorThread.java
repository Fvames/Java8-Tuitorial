package dev.fvames.pattern.single;


public class ThreadLocalSingleExecutorThread implements Runnable {

    public void run() {
        ThreadLocalSingleton s = ThreadLocalSingleton.getInstance();
        System.out.println("thread name:" + Thread.currentThread().getName() + ", " + s);
    }
}
