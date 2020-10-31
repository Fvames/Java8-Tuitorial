package dev.fvames.pattern.strategy;

/**
 * Created by James on 2018/11/23.
 */
public class Item {

    private String des;
    private int cents;

    public Item(String des, int cents) {
        this.des = des;
        this.cents = cents;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        this.cents = cents;
    }
}
