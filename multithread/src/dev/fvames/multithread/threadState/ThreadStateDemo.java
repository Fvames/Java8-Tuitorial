package dev.fvames.multithread.threadState;

/**
 * 线程状态 new、runnable、blocked、waiting、time_waiting、terminated
 * <p>
 * Created by James on 2018/10/21.
 */
public class ThreadStateDemo implements Runnable {

    public static Thread thread1;
    public static ThreadStateDemo obj;


    public static void main(String[] args) {
        obj = new ThreadStateDemo();
        thread1 = new Thread(obj);

        // 线程1创建状态
        System.out.println("线程1创建之前的状态: " + thread1.getState());

        thread1.start();

        System.out.println("线程1创建之后的状态: " + thread1.getState());


    }

    @Override
    public void run() {
        Thead1 myThread = new Thead1();
        Thread thread2 = new Thread(myThread);

        // 线程2创建状态
        System.out.println("线程2创建之前的状态: " + thread2.getState());

        thread2.start();

        System.out.println("线程2创建之后的状态: " + thread2.getState());

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("线程2状态，在调用 sleep 方法之后："+ thread2.getState()+"，thread1: "+thread1.getState() );

        try {
            /*线程1创建之前的状态: NEW
                线程2创建之前的状态: NEW
                线程2创建之后的状态: RUNNABLE
                线程1创建之后的状态: TIMED_WAITING
                thread2 join() 方法之前，thread1: RUNNABLE, thread2: TIMED_WAITING
                Thread1 内 sleep 1500，线程1调用 start() 方法之后的状态 : WAITING
                线程2 调用join() 方法，此时线程1的状态：RUNNABLE, 线程2的状态：TERMINATED
                线程2完成时的状态： TERMINATED, 线程1的状态：RUNNABLE
            System.out.println("thread2 join() 方法之前，thread1: "+ thread1.getState()+", thread2: "+thread2.getState());*/
            // waiting for thread2 to die
            //System.out.println("------,thread2 state: "+ thread2.getState());
            thread2.join();
            System.out.println("线程2 调用join() 方法，此时线程1的状态：" + thread1.getState() + ", 线程2的状态：" + thread2.getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // thread1 TERMINATED 状态如何展示？
        System.out.println("线程2完成时的状态： " +
                thread2.getState() + ", 线程1的状态：" + thread1.getState());
    }
}
