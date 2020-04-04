package dev.fvames.sync.sync001;

/**
 * Created by James on 2018/8/17.
 */
public class Test {
    private int count = 0;

    public Runnable run1 = new Runnable() {
        @Override
        public void run() {

            synchronized (this) {
                while (count < 1000) {
                    System.out.println(Thread.currentThread().getName() + "run1:" + count);
                }
            }
        }
    };
    public Runnable run2 = new Runnable() {
        @Override
        public void run() {

            synchronized (this) {
                while (count < 1000) {
                    System.out.println(Thread.currentThread().getName() + "run2:" + count);
                }
            }
        }
    };

    public static void main(String[] args) {
        Test test = new Test();

        new Thread(test.run1, "线程1").start();
        new Thread(test.run2, "线程2").start();

    }
}
