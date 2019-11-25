package com.james.juc.thread.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并行执行
 * 将文本渲染与图片下载两个任务并行执行
 */
public class PageRenderFuture2 {

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    void renderPage() {
        long start = System.currentTimeMillis();

        final List<String> imageInfos = Arrays.asList("file1", "file2", "file3");

        Callable<List<String>> task = new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                List<String> result = new ArrayList<>();

                for (String imageInfo : imageInfos) {
                    result.add(downloadImage(imageInfo));
                }

                return result;
            }
        };

        Future<List<String>> future = executor.submit(task);
        renderText();

        try {
            List<String> imageDatas = future.get();
            for (String imageData : imageDatas) {
                renderImage(imageData);
            }

        } catch (InterruptedException e) {
            // 重新设置线程的中断状态
            Thread.currentThread().interrupt();
            // 由于不需要结果，因此取消任务
            future.cancel(true);

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start) / 1000);
        System.exit(1);
    }

    private void renderImage(String imageData) {
        System.out.println(String.format("渲染图片: %s", imageData));
    }

    private void renderText() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("渲染文本内容");
    }

    private String downloadImage(String imageInfo) {

        try {
            TimeUnit.SECONDS.sleep(1);
            return imageInfo + " completed downlaod";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        new PageRenderFuture2().renderPage(); // 3 秒

    }
}
