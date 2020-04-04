package dev.fvames.multithread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by James on 2018/10/27.
 */
public class HungryPatternThread {

    static HungryPatternThread hungryPatternThread = null;

    public HungryPatternThread getInstance() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

        System.out.println("1111: " + hungryPatternThread);
        if (hungryPatternThread == null) {
            //在构造的临界区上加锁，而不是整个方法加锁
            synchronized (HungryPatternThread.class) {
                //获得锁之后继续再一次判断，这样就安全了
                if (hungryPatternThread == null) {
                    hungryPatternThread = new HungryPatternThread();
                }
            }
        }
        System.out.println("1111: " + hungryPatternThread);
        return hungryPatternThread;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                HungryPatternThread hungryPatternThread = new HungryPatternThread();
                System.out.println("2222:" + hungryPatternThread.getInstance().getClass());
                //System.out.println(HungryPatternThread.getInstance().getClass());
            });
        }
    }
}
