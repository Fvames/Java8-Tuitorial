package dev.fvames.lock.lock22;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShopDemo {
    private static ConcurrentHashMap<String, Item> items = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println(IntStream.rangeClosed(1,10).mapToObj(i->i%2==0).filter(result->result==true).collect(Collectors.toList()));
        System.out.println(IntStream.rangeClosed(1,10).mapToObj(i->i%2==0).filter(result->result).collect(Collectors.toList()));
        System.out.println(IntStream.rangeClosed(1,10).mapToObj(i->i%2==0).map(result->result).collect(Collectors.toList()));

        //deatLock();
    }

    private static void deatLock() {
        IntStream.rangeClosed(0, 10).forEach(i -> items.put("item"+i, new Item("item" + i)));

        long begin = System.currentTimeMillis();
        // 并发进行 100 次下单操作，统计成功次数
        long successCount = IntStream.rangeClosed(1, 100).parallel()
                .mapToObj(i -> {
                    List<Item> cart = createCart();
                    return createOrder(cart);
                })
                .filter(result -> result)
                .count();

        System.out.printf("success:%s totalRemaining:%s took:%s ms items:%s \n",
                successCount,
                items.entrySet().stream().map(item -> item.getValue().remaining).reduce(0, Integer::sum),
                System.currentTimeMillis() - begin,
                items);
    }

    private static List<Item> createCart() {

        return IntStream.rangeClosed(1, 3)
                .mapToObj(i -> "item" + ThreadLocalRandom.current().nextInt(items.size()))
                .map(name -> items.get(name))
                .sorted(Comparator.comparing(Item::getName))
                .collect(Collectors.toList());
    }

    private static boolean createOrder(List<Item> order) {
        List<ReentrantLock> locks = new ArrayList<>();
        for (Item item : order) {
            try {
                if (item.lock.tryLock(10, TimeUnit.SECONDS)) {
                    locks.add(item.lock);
                } else {
                    // 一个失败时释放其他锁
                    locks.forEach(ReentrantLock::unlock);
                }
            } catch (InterruptedException e) {

            }
        }

        try {
            order.forEach(item -> item.remaining--);
        } finally {
            locks.forEach(ReentrantLock::unlock);
        }
        return true;
    }
}
