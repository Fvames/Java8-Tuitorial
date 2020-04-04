package dev.fvames.multithread.threadLocal;

/**
 * Created by James on 2018/10/20.
 */
public class ThreadLocalDemo {

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

    static IntergerNum intergerNum = new IntergerNum();
    public static ThreadLocal<IntergerNum> obj = new ThreadLocal<IntergerNum>() {
        @Override
        protected IntergerNum initialValue() {
            // 此时共用同一个对象的引用地址
            //return intergerNum;

            // 此时，每个线程均获得新的地址
            return new IntergerNum();
        }
    };


    static class IntergerNum {
        private Integer num = 0;

        public Integer incr(Integer a) {
            return num += a;
        }

    }

    public static void main(String[] args) {
        constantMethod();

        //objectAddressRefer();
    }

    private static void objectAddressRefer() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    IntergerNum intergerNum = obj.get();
                    Integer localNum = intergerNum.incr(1);
                    System.out.println(Thread.currentThread().getName() + ">>>" + localNum);
                }
            }, "thread-" + i).start();
        }
    }

    private static void constantMethod() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 若不初始化，则 get 方法返回 null
                    if (null == threadLocal.get()) {
                        threadLocal.set(0);
                    }

                    int localNum = threadLocal.get().intValue() + 1;
                    System.out.println(Thread.currentThread().getName() + ">>>" + localNum);
                }
            }, "thread-" + i).start();
        }
    }
}
