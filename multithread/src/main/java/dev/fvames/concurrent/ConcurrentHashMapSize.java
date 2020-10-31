package dev.fvames.concurrent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class ConcurrentHashMapSize {
    // 线程个数
    private static int THREAD_COUNT = 10;
    // 总元素数量
    private static int ITEM_COUNT = 1000;

    public static void main(String[] args) {
        wrong();
    }

    // 获取指定元素数量模拟数据的 ConcurrentHashMap
    public static ConcurrentHashMap<String, Long> getData(int count){
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(),
                        (k, v) -> k, ConcurrentHashMap::new));
    }

    public static void wrong() {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        // 初始 900 个元素
        System.out.printf("inti size: %s \n", concurrentHashMap.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        // 使用线程池并发处理逻辑
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i ->{
            // 查询还需要补充多少个元素
            synchronized (concurrentHashMap){
                int gap = ITEM_COUNT - concurrentHashMap.size();
                System.out.printf("gap size: %s \n", gap);
                // 补充元素
                concurrentHashMap.putAll(getData(gap));
            }
        }));

        // 等待所有任务完成
        forkJoinPool.shutdown();
        forkJoinPool.awaitQuiescence(1, TimeUnit.HOURS);
        // 最后元素个数
        System.out.printf("finish size: %s \n", concurrentHashMap.size());
    }

}
