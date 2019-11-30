package dev.fvames.pattern.single;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 枚举单例测试
 */

public class EnumSingletonTest {

    public static void main(String[] args) throws Exception {

        EnumSingleton e1 = null;
        EnumSingleton e2 = EnumSingleton.INSTANCE;
        e2.setData(new Object());

        String fileName = "EnumSingleton.obj";
        FileOutputStream outputStream = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(e2);
        oos.flush();
        oos.close();

        FileInputStream inputStream = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(inputStream);
        e1 = (EnumSingleton) ois.readObject();
        ois.close();

        System.out.println(e1.getData());
        System.out.println(e2.getData());
        System.out.println(e1.getData() == e2.getData());
    }
}
