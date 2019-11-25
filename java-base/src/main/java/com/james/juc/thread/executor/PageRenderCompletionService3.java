package com.james.juc.thread.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并行执行
 * 将文本渲染与图片下载两个任务并行执行
 * 每完成一个图片下载即进行渲染
 */
public class PageRenderCompletionService3 {

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    void renderPage() {
        long start = System.currentTimeMillis();

        final List<String> imageInfos = Arrays.asList("file1", "file2", "file3");

        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
        for (String imageInfo : imageInfos) {
            completionService.submit(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    return downloadImage(imageInfo);
                }
            });
        }

        renderText();

        try {
            for (int i = 0; i < imageInfos.size(); i++) {
                Future<String> f = completionService.take();
                renderImage(f.get());
            }

        } catch (InterruptedException e) {
            // 重新设置线程的中断状态
            Thread.currentThread().interrupt();

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
        new PageRenderCompletionService3().renderPage(); // 1 秒

    }
}
