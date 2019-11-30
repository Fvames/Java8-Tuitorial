package dev.fvames.pattern.delegate;

/**
 * 电饭煲
 */
public class RiceCook implements Ikitchen {

    public void makeFood(String option) {
        System.out.println(option + "做食物： 米饭");
    }
}
