package com.james.juc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    static final Exchanger<List<Integer>> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);

            try {
                list = exchanger.exchange(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1：" + list);

        }).start();

        new Thread(() -> {
            List<Integer> list = new ArrayList<>();
            list.add(3);
            list.add(4);

            try {
                list = exchanger.exchange(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2：" + list);
        }).start();
    }
}
