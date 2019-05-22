package com.james.juc.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 2019/5/22 9:20
 */

public class ConditionDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(new ConditionWait(lock, condition)).start();
        new Thread(new ConditionSingle(lock, condition)).start();

    }

}
