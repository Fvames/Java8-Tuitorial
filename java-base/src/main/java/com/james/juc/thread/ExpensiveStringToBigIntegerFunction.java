package com.james.juc.thread;

import java.math.BigInteger;

/**
 * @version 2019/5/23 11:13
 */

public class ExpensiveStringToBigIntegerFunction implements Computable<String, BigInteger> {

    @Override
    public BigInteger compute(String arg) {
        return new BigInteger(arg);
    }
}
