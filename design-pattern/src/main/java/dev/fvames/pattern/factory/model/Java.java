package dev.fvames.pattern.factory.model;

import dev.fvames.pattern.factory.simple.IProgramLanguage;

public class Java implements IProgramLanguage {
    public void sayHello() {
        System.out.println("Java say hello");
    }
}
