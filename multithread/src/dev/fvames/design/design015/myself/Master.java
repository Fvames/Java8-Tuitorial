package dev.fvames.design.design015.myself;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by James on 2018/8/13.
 */
public class Master {

    // 1任务集合
    protected ConcurrentLinkedQueue tasks = new ConcurrentLinkedQueue();
    // 2worker线程集合
    protected HashMap<String, Thread> workers = new HashMap<>();
    // 3worker处理数据集合
    protected ConcurrentHashMap<String, Integer> results = new ConcurrentHashMap<>();

    // 4初始化worker线程数量
    public Master(Worker worker, int conCurrentNum) {
        worker.setTasks(tasks);
        worker.setResults(results);

        for (int i = 0; i < conCurrentNum; i++) {
            this.workers.put(Integer.toString(i), new Thread(worker));
        }
    }

    // 5添加任务
    public void submit(Task task) {
        this.tasks.add(task);
    }

    // 6开始执行
    public void execute() {
        for (Map.Entry<String, Thread> threadEntry : workers.entrySet()) {
            threadEntry.getValue().start();
        }
    }

    // 7线程是否执行完成
    public boolean isComplete() {
        for (Map.Entry<String, Thread> threadEntry : workers.entrySet()) {
            if (threadEntry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    // 8统计结果
    public int getResult() {
        int result = 0;

        for (Map.Entry<String, Integer> doubleEntry : results.entrySet()) {
            result += doubleEntry.getValue();
            System.out.println("任务" + doubleEntry.getKey() + ": " + doubleEntry.getValue());
        }
        return result;
    }

}
