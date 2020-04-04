package dev.fvames.pattern.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 2018/11/23.
 */
public class Bill {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public int getSumCents() {
        return items.stream().mapToInt(item -> item.getCents()).sum();
    }

    public void pay(PayMethod payMethod) {
        payMethod.pay(getSumCents());
    }


}
