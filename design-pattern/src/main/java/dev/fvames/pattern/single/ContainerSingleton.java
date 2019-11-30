package dev.fvames.pattern.single;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器类单例
 */

public class ContainerSingleton {

    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();

    public static Object getBean(String className) throws Exception {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object object = Class.forName(className).newInstance();
                ioc.put(className, object);
                return object;
            } else {
                return ioc.get(className);
            }
        }
    }

}
