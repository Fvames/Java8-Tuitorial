package dev.fvames.chapter4;

/**
 * finalize 内如果引用外泄，对象就会复活
 */
public class CanReliveObj {
    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj = this;
    }

    public static void main(String[] args) throws InterruptedException {
        obj = new CanReliveObj();
        obj = null;
        System.gc();

        Thread.sleep(1000);

        judegObj(1);

        obj = null;
        System.gc();
        Thread.sleep(1000);
        judegObj(2);
    }

    private static void judegObj(int i) {
        if (obj == null) {
            System.out.println(i + " obj is null");
        } else {
            System.out.println(i + " obj is available");
        }
    }
}
