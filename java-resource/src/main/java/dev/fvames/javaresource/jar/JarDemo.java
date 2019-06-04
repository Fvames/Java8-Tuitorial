package dev.fvames.javaresource.jar;

import dev.fvames.javaresource.JavaResourceApplication;

import java.net.URL;

/**
 * @version 2019/6/4 17:08
 */

public class JarDemo {

    public static void main(String[] args) {
        //ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = JavaResourceApplication.class.getClassLoader();
        URL url = classLoader.getResource("META-INF/license.txt");
        System.out.println(url);
    }

}
