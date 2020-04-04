package dev.fvames.multithread.callable;

import java.util.concurrent.*;

/**
 * callable
 * future
 * Created by James on 2018/10/20.
 */
public class CalableDemo implements Callable<String> {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new CalableDemo());
        System.out.println("main result: " + future.get());
        executorService.shutdown();
    }

    @Override
    public String call() throws Exception {
        int a = 1;
        int b = 2;

        Thread.sleep(1000);

        int resut = a + b;
        System.out.println("call:" + resut);
        return String.format("执行结果：%s", resut);
    }
}
