package com.james.juc.thread;

/**
 * @version 2019/5/23 11:07
 */

public interface Computable<A, V> {

    V compute(A arg);

}
