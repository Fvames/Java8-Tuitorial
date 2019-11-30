package dev.fvames.pattern.single;


public class LazyInnerTest {

    public static void main(String[] args) {
        LazyInnerClassSingleton innerClassSingleton = LazyInnerClassSingleton.getInstance();
        Thread thread1 = new Thread(innerClassSingleton);
        Thread thread2 = new Thread(innerClassSingleton);

        thread1.start();
        thread2.start();

        System.out.println("end...");
    }
}
