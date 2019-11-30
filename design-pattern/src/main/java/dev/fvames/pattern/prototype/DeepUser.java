package dev.fvames.pattern.prototype;


import java.io.Serializable;

public class DeepUser implements Cloneable, Serializable {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
}
