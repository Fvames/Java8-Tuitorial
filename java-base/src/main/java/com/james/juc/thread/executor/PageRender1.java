package com.james.juc.thread.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 串行化执行
 */
public class PageRender1 {

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    void renderPage() {
        long start = System.currentTimeMillis();

        final List<String> imageInfos = Arrays.asList("file1", "file2", "file3");

        renderText();

        for (String imageInfo : imageInfos) {
            renderImage(downloadImage(imageInfo));
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start) / 1000);
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
        new PageRender1().renderPage(); // 4 秒

    }
}
