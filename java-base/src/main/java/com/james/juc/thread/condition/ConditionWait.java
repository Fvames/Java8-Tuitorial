package com.james.juc.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @version 2019/5/22 9:15
 */

public class ConditionWait implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();

        try {
            System.out.println("condition wait.");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.unlock();
    }
}
