package dev.fvames.chapter10;

public class FindClassOrder {
    public static void main(String[] args) {
        // #-Xbootclasspath:/Users/james/devSpace/jvm
        //HelloLoader loader = new HelloLoader();
        //loader.print();

        integerDemo();
    }

    private static void integerDemo() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a +b));
    }


}
