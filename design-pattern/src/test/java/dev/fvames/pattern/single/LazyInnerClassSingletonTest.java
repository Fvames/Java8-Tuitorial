package dev.fvames.pattern.single;

import java.lang.reflect.Constructor;

/**
 * 破坏内部类单例
 */

public class LazyInnerClassSingletonTest {
    public static void main(String[] args) throws Exception {
        // 反射
        reflectionDamage();

    }

    private static void reflectionDamage() throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Class clazz = LazyInnerClassSingleton.class;

        // 反射拿到无参构造方法
        Constructor c = clazz.getDeclaredConstructor(null);
        // 强制访问
        c.setAccessible(true);

        // 初始化
        Object o1 = c.newInstance();
        // 初始化第二次
        Object o2 = c.newInstance();

        System.out.println("result :" + o1 == o2); // false
    }
}
