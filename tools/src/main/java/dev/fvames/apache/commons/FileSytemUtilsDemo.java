package dev.fvames.apache.commons;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 对文件系统操作的封装
 */
public class FileSytemUtilsDemo {

    public static void main(String[] args) throws IOException {
        FileStore fileStore = Files.getFileStore(Paths.get("/home"));
        long usableSpace = fileStore.getTotalSpace();
        System.out.println("/home usableSpace : " + usableSpace);

        Iterable<FileStore> fileStores = FileSystems.getDefault().getFileStores();
        fileStores.forEach(fileStore1 -> {
            try {
                long totalSpace = fileStore1.getTotalSpace();
                System.out.println(fileStore1 + " :" + totalSpace);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
