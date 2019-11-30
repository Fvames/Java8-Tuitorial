package dev.fvames.pattern.single;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化破坏
 */

public class SerializableSingletonTest {

    public static void main(String[] args) throws Exception {
        SerializableSingleton s1 = null;
        SerializableSingleton s2 = SerializableSingleton.getInstance();


        // 输出文件
        String fileName = "SerializableSingleton.obj";
        FileOutputStream outputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(s2);
        objectOutputStream.flush();
        objectOutputStream.close();

        // 读取
        FileInputStream inputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        s1 = (SerializableSingleton) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println(s1 == s2);

    }
}
