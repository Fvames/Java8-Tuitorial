package dev.fvames.pattern.prototype;

import java.io.*;

/**
 * 浅克隆
 */

public class DeepClone implements Cloneable, Serializable {

    private Long id;
    private DeepUser user;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //DeepClone deepClone = new DeepClone();
        //deepClone.id=id;
        //deepClone.user = (DeepUser) user.clone();
        //return deepClone;

        return deepClone();
    }

    private Object deepClone() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            DeepClone deepClone = (DeepClone) ois.readObject();

            return deepClone;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeepUser getUser() {
        return user;
    }

    public void setUser(DeepUser user) {
        this.user = user;
    }


}
