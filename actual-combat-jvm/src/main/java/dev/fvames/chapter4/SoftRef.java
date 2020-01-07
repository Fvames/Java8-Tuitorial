package dev.fvames.chapter4;

import java.lang.ref.SoftReference;

/**
 * 软引用，当堆空间不足时会被回收
 * -Xmx10m
 */
public class SoftRef {

    public static void main(String[] args) {
        User u = new User(1, "lj1");
        SoftReference<User> userSoftRef = new SoftReference<>(u);
        u = null;

        System.out.println("first get: " + userSoftRef.get());
        System.gc();

        System.out.println("After GC:");
        System.out.println(userSoftRef.get());

        byte[] b = new byte[7 * 973 * 1024];
        // 堆空间不足，GC 回收软件用
        System.gc();
        System.out.println(userSoftRef.get()); // null
    }
}
