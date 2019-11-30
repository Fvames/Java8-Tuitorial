package dev.fvames.pattern.delegate;

public class PersonTest {

    public static void main(String[] args) {
        new Person().action(Kitchen.RICECOOK, new Kitchen());
    }
}
