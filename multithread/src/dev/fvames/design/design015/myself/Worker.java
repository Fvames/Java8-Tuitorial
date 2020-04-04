package dev.fvames.design.design015.myself;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by James on 2018/8/13.
 */
public class Worker implements Runnable {
    // 任务集合
    protected ConcurrentLinkedQueue tasks;
    // worker处理数据集合
    protected ConcurrentHashMap<String, Integer> results;

    public void setTasks(ConcurrentLinkedQueue tasks) {
        this.tasks = tasks;
    }

    public void setResults(ConcurrentHashMap<String, Integer> results) {
        this.results = results;
    }

    @Override
    public void run() {
        // 处理
        while (true) {
            Task task = (Task) this.tasks.poll();
            if (null == task) {
                break;
            }

            results.put(task.getId(), handler(task));
        }

    }

    private int handler(Task task) {
        int out = 0;
        try {
            Thread.sleep(500);
            out = task.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return out;
    }


}
