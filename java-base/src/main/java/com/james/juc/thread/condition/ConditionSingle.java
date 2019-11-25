package com.james.juc.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @version 2019/5/22 9:15
 */

public class ConditionSingle extends Thread {

    private Lock lock;
    private Condition condition;

    public ConditionSingle(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();

        System.out.println("condition single.");
        condition.signal();

        lock.unlock();
    }
}
