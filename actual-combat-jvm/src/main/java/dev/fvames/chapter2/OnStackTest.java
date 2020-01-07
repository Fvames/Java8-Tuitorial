package dev.fvames.chapter2;

/**
 * 逃逸对象：可能被任何线程访问（常量）
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB -XX:+EliminateAllocations
 * <p>
 * -server                  server 模式下才能启用逃逸分析
 * -Xmx10m -Xms10m
 * -XX:+DoEscapeAnalysis    启用逃逸分析
 * -XX:+PrintGC
 * -XX:-UseTLAB             关闭 TLAB
 * -XX:+EliminateAllocations    开启标量替换，允许将对象打散分配在栈帧上
 * 如果关闭逃逸分析或标量替换，就会有大量的 GC 日志输出。
 * 栈空间较小，大对象无法也不适合在栈上分配
 */
public class OnStackTest {

    public static class User {
        public int id = 0;
        public String name = "";
    }

    public static void alloc() {
        User u = new User();
        u.id = 5;
        u.name = "lsj";
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            alloc(); // 未发生逃逸，对象分配在栈帧
        }
        long b = System.currentTimeMillis();
        System.out.println(b - a);
    }
}
