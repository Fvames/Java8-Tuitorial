package dev.fvames.multithread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by James on 2018/10/20.
 */
public class ExecutorsDemo {

    public static void main(String[] args) {
        ExecutorService single = Executors.newSingleThreadExecutor();
    }

}
