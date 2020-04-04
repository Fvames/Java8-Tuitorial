package dev.fvames.base.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by James on 2018/11/23.
 */
public class SerializableDemo {

    public static void main(String[] args) throws Exception {
        User user = new User("james", 25);

        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileOutputStream baos = new FileOutputStream("user");
        ObjectOutputStream oops = new ObjectOutputStream(baos);
        oops.writeObject(user);
        //byte[] rs = baos.toByteArray();

        //ByteArrayInputStream bais = new ByteArrayInputStream(rs);
        FileInputStream bais = new FileInputStream("user");
        ObjectInputStream ois = new ObjectInputStream(bais);
        User user1 = (User) ois.readObject();

        System.out.println(user1.toString());
    }
}
