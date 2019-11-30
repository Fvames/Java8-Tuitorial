package dev.fvames.pattern.single;

/**
 *
 */

public class ThreadLocalSingletonTest {

    public static void main(String[] args) {
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());

        System.out.println("thread start...");
        ThreadLocalSingleExecutorThread target = new ThreadLocalSingleExecutorThread();
        Thread thread1 = new Thread(target);
        Thread thread2 = new Thread(target);
        Thread thread3 = new Thread(new ThreadLocalSingleExecutorThread());

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
