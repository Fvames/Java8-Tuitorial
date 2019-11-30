package dev.fvames.pattern.factory.model;

import dev.fvames.pattern.factory.simple.IProgramLanguage;

public class Python implements IProgramLanguage {

    public void sayHello() {
        System.out.println("Python say hello ");
    }
}
