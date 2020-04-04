package dev.fvames.design.design015.myself;

/**
 * Created by James on 2018/8/13.
 */
public class Task {

    private String id;
    private String name;
    private int price;

    public Task() {
    }

    public Task(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
