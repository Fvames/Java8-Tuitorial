package dev.fvames.pattern.single;

import java.io.Serializable;

/**
 * 序列化
 */

public class SerializableSingleton implements Serializable {

    public static final SerializableSingleton SINGLETON = new SerializableSingleton();

    private SerializableSingleton() {
    }

    public static SerializableSingleton getInstance() {
        return SINGLETON;
    }

    /**
     * 源码入口 objectInputStream.readObject();
     */
    public Object readResolve() {
        return SINGLETON;
    }
}
