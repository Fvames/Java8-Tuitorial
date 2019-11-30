package dev.fvames.pattern.delegate;

/**
 * 烤箱
 */
public class Roaster implements Ikitchen {


    public void makeFood(String option) {
        System.out.println(option + "做食物： 烤串、鸡翅");
    }
}
