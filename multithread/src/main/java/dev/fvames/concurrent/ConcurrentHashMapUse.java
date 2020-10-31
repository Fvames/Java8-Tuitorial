package dev.fvames.concurrent;


import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConcurrentHashMapUse {
    // 循环次数
    public static final int LOOP_COUNT = 1000_000;
    // 线程数量
    public static final int THREAD_COUNT = 10;
    // 元素数量
    public static final int ITEM_COUNT = 10;

    /**

     StopWatch '': running time = 1105768280 ns
     ---------------------------------------------
     ns         %     Task name
     ---------------------------------------------
     719785110  065%  normalUse
     385983170  035%  goodUse
    */
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("normalUse");
        Map<String, Long> normalUse = normalUse();
        stopWatch.stop();
        // 校验元素数量
        Assert.isTrue(normalUse.size() == ITEM_COUNT, "normalUse size error");
        long loop_normal = normalUse.entrySet().stream().mapToLong(item -> item.getValue()).reduce(0, Long::sum);
        Assert.isTrue(loop_normal == LOOP_COUNT, "normaluse count error");

        stopWatch.start("goodUse");
        Map<String, Long> goodUse = goodUse();
        stopWatch.stop();
        Assert.isTrue(goodUse.size() ==  ITEM_COUNT, "goodUse size error");
        long loop_goodUse = goodUse.entrySet().stream().mapToLong(item -> item.getValue()).reduce(0, Long::sum);
        Assert.isTrue(loop_goodUse == LOOP_COUNT, "goodUse count error");
        System.out.println(stopWatch.prettyPrint());
    }

    private static Map<String, Long> normalUse() {
        ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(i ->{
                    // 获得一个随机 key
                    String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    synchronized (freqs) {
                        if (freqs.containsKey(key)) {
                            freqs.put(key, freqs.get(key) + 1);
                        } else {
                            freqs.put(key, 1L);
                        }
                    }
                }
        ));

        forkJoinPool.shutdown();
        forkJoinPool.awaitQuiescence(1, TimeUnit.HOURS);
        return freqs;
    }

    private static Map<String, Long> goodUse() {
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(i ->{
                    String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
                })
        );
        forkJoinPool.shutdown();
        forkJoinPool.awaitQuiescence(1, TimeUnit.HOURS);
        return freqs.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().longValue()));
    }
}
