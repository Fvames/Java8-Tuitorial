package dev.fvames.design.design015.myself;

import java.util.Random;

/**
 * Created by James on 2018/8/13.
 */
public class Main {

    public static void main(String[] args) {
        // 1.初始化worker
        Worker worker = new Worker();
        // 2.初始化master
        Master master = new Master(worker, 10);
        // 3.设置任务
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            Task task = new Task(Integer.toString(i), "任务" + i, r.nextInt(1000));
            master.submit(task);
        }
        // 4.执行任务
        master.execute();
        // 5.如果线程完成则获取结果
        long start = System.currentTimeMillis();
        while (true) {
            if (master.isComplete()) {
                long end = System.currentTimeMillis() - start;
                int priceResult = master.getResult();
                System.out.println("最终结果：" + priceResult + ", 执行时间：" + end);
                break;
            }
        }
    }
}
