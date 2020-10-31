package dev.fvames.multithread.sharDataInThread;

/**
 * 线程间共享数据
 * 1.Atomic
 * 2.volatile
 * 3.向线程间传入数据对象，数据对象内加锁控制
 * 4.wait notify lock...
 * Created by James on 2018/10/19.
 */
public class ShareDataInThread {


    public static void main(String[] args) {
        //atomicMethod();

        volatileMethod();

        //objectMethod();
    }

    private static void objectMethod() {
        final SafeThreadData safeThreadData = new SafeThreadData();
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        safeThreadData.incr();
                    }
                }, "thread" + i).start();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        safeThreadData.dec();
                    }
                }, "thread" + i).start();
            }
        }
    }

    private static void atomicMethod() {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i < 10; i++) {

            new Thread(atomicDemo, "thread" + i).start();
        }
    }

    private static void volatileMethod() {
        VolatileDemo volatileDemo = new VolatileDemo();
        for (int i = 0; i < 50; i++) {

            new Thread(volatileDemo, "thread" + i).start();
        }
    }


}
