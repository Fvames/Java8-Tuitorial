package dev.fvames.chapter4;

/**
 * java -XX:+PrintGC
 */
public class LocalVarGC {

    public void localVarGC1() {
        // 无法回收
        byte[] a = new byte[6 * 1024 * 1024];
        System.gc();
    }

    public void localVarGC2() {
        // 置为 null，失去强引用，可回收
        byte[] a = new byte[6 * 1024 * 1024];
        a = null;
        System.gc();
    }

    public void localVarGC3() {
        // a 虽然离开了作用域，但仍然在局部变量表，故不能被回收
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        System.gc();
    }

    public void localVarGC4() {
        // c 复用 a 的子，可被回收
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        int c = 10;
        System.gc();
    }

    public void localVarGC5() {
        // localVarGC1 执行完，栈帧销毁，可被回收
        localVarGC1();
        System.gc();
    }

    public static void main(String[] args) {
        new LocalVarGC().localVarGC2();
    }
}
