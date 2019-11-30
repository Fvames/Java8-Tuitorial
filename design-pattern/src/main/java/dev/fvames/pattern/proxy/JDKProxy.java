package dev.fvames.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {

    private Object object;

    public Object getInstance(Object target) {
        this.object = target;
        Class clazz = target.getClass();

        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();
        Object result = method.invoke(this.object, args);
        after();

        return result;
    }

    private void after() {
        System.out.println("jdk proxy after");
    }

    private void before() {
        System.out.println("jdk proxy before");
    }
}
