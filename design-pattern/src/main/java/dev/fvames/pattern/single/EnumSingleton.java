package dev.fvames.pattern.single;

/**
 * 枚举注册式单例
 */

public enum EnumSingleton {

    INSTANCE;

    private EnumSingleton() {
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
