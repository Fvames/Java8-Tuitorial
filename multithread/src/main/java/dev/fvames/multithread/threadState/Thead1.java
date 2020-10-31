package dev.fvames.multithread.threadState;

/**
 * Created by James on 2018/10/21.
 */
public class Thead1 implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread1 内 sleep 1500，线程1调用 start() 方法之后的状态 : " + ThreadStateDemo.thread1.getState());

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println("thread1 最终结束状态："+ ThreadStateDemo.thread1.getState());
    }
}
