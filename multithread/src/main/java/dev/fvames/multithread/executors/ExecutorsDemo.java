package dev.fvames.multithread.executors;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Created by James on 2018/10/20.
 */
public class ExecutorsDemo {

    public static void main(String[] args) {
        ExecutorService single = Executors.newSingleThreadExecutor();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 10, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(10),
                new ThreadPoolExecutor.CallerRunsPolicy());

        IntStream.range(1, 100).parallel().forEach(a ->{
            Thread thread = new Thread(() -> {
                System.out.println(a);
            });
            thread.setName("thread" + a);
            Future<?> submit = threadPoolExecutor.submit(thread);
            try {
                System.out.println(submit.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        threadPoolExecutor.shutdown();
    }
}
