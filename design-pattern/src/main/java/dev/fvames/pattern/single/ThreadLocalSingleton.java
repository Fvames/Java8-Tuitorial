package dev.fvames.pattern.single;

/**
 *
 */

public class ThreadLocalSingleton {
    public static final ThreadLocal<ThreadLocalSingleton> THREAD_LOCAL = new ThreadLocal<ThreadLocalSingleton>() {
        // 每个线程内唯一
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance() {
        return THREAD_LOCAL.get();
    }

}
