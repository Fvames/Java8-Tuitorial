package dev.fvames.chapter7;

public class StringDemo {
    public static void main(String[] args) {
        String str1 = new String("abc");
        String str2 = new String("abc");

        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println("str1 eql str2: " + str1.equals(str2));
        System.out.println("str1 == str2.intern(): " + (str1 == str2.intern()));
        System.out.println("abc == str2.intern(): " + ("abc" == str2.intern()));
        System.out.println("str1.intern() == str2.intern(): " + (str1.intern() == str2.intern()));

        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("maxMemory: " + maxMemory / 1024 / 1024);
    }
}
