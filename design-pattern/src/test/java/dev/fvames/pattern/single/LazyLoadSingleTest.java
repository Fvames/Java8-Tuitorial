package dev.fvames.pattern.single;

public class LazyLoadSingleTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new LazyExecutorThread());
        Thread thread2 = new Thread(new LazyExecutorThread());

        thread1.start();
        thread2.start();
        System.out.println("....end");
    }

}
