package dev.fvames.pattern.single;


public class LazyExecutorThread implements Runnable {

    public void run() {
        LazyLoadSingle lazyLoadSingle = LazyLoadSingle.getLazyLoadSingle();
        System.out.println(Thread.currentThread().getName() + " : " + lazyLoadSingle);
    }
}
