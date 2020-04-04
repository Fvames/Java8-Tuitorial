package dev.fvames.sync.sync001;

/**
 * Created by James on 2018/8/16.
 */
public class RunAndStart {


    public static void main(String[] args) {
        RunAndStart a = new RunAndStart();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程【" + Thread.currentThread().getName() + "】 runable....");

            }
        };

        System.out.println("start time=" + System.currentTimeMillis());

        Thread thread = new Thread(runnable, "start 线程");
        thread.start();// start 线程 异步执行

        runnable.run(); // MAIN runable 主线程同步执行

        System.out.println("end   time=" + System.currentTimeMillis());
    }
}
