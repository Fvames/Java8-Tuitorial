package dev.fvames.pattern.delegate;

public class Person {

    public void action(String option, Ikitchen ikitchen) {
        ikitchen.makeFood(option);
    }

}
