package dev.fvames.pattern.strategy;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by James on 2018/11/23.
 */
public abstract class Card implements PayMethod {

    static Map<String, PayMethod> payMethodMap = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        payMethodMap.put(getType(), this);
    }

    @Override
    public void pay(int cents) {
        System.out.println("use " + getType() + "-> payd " + cents + "cents ");
        executeTransaction(cents); // 具体执行支付操作
    }

    protected abstract String getType();

    protected abstract void executeTransaction(int cents);
}
