package dev.fvames.apache.commons;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;

public class MethodUtilsDemo {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object o = MethodUtils.invokeStaticMethod(StringUtils.class, "isNotBlank", "test");
        System.out.println(o);
    }
}
