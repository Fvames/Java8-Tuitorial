package dev.fvames.pattern.single;

public class LazyLoadSingle {

    private LazyLoadSingle() {
    }

    private static LazyLoadSingle single = null;

    /*static synchronized LazyLoadSingle getLazyLoadSingle() {
        if (single == null) {
            single = new LazyLoadSingle();
        }
        return single;
    }*/

    public static LazyLoadSingle getLazyLoadSingle() {
        if (single == null) {
            synchronized (LazyLoadSingle.class) {
                if (single == null) {

                    single = new LazyLoadSingle();
                }
            }
        }
        return single;
    }

    public static void main(String[] args) {
        /*LazyLoadSingle single = new LazyLoadSingle();
        LazyLoadSingle singleClass1 = single.getLazyLoadSingle();
        LazyLoadSingle singleClass2 = single.getLazyLoadSingle();
        System.out.println(singleClass1);
        System.out.println(singleClass2);*/

        Thread thread1 = new Thread(new LazyExecutorThread());
        Thread thread2 = new Thread(new LazyExecutorThread());

        thread1.start();
        thread2.start();
        System.out.println("....end");
    }

}
