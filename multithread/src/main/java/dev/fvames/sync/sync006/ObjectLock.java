package dev.fvames.sync.sync006;

/**
 * 使用synchronized代码块加锁,比较灵活
 *
 * @author alienware
 */
public class ObjectLock {

    public void method1(String str) {
        synchronized (this) {    //对象锁
            try {
                System.out.println("do method1.." + str);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2(String str) {        //类锁
        synchronized (ObjectLock.class) {
            try {
                System.out.println("do method2.." + str);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object lock = new Object();

    public void method3(String str) {        //任何对象锁
        synchronized (lock) {
            try {
                System.out.println("do method3.." + str);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        final ObjectLock objLock = new ObjectLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method1("t1");
            }
        });
        Thread t11 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method1("t11");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method2("t2");
            }
        });
        Thread t22 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method2("t22");
            }
        });


        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method3("t3");
            }
        });

        t3.start();
        t2.start();
        t22.start();
        t1.start();
        t11.start();


    }

}
