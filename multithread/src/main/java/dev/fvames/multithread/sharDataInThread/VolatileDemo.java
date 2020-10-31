package dev.fvames.multithread.sharDataInThread;

/**
 * Created by James on 2018/10/19.
 */
public class VolatileDemo implements Runnable {
    // 仅能保证线程间数据共享，但不能保证原子性，需要使用 synchronized，
    //      有序性：volatile 修饰的变量之前的代码不会再变量之后执行，但是之前的代码涉及到： 读，计算，回写；这个过程是不安全的，如果其他cpu进入就没有办法保证原子性，而 synchronized 关键字编译后会使用 jvm高级指令的 monitorenter 和 monitorexit 包裹代码块，保证原子性
    // 当前线程的修改立即同步到主内存，但其他线程依然需要从主线程中获取数据，时间差
    private volatile int anInt;

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("threadName:" + Thread.currentThread().getName() + " >>>" + anInt++);
    }
}
