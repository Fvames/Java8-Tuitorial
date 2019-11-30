package dev.fvames.pattern.factory.factoryMethod;

import dev.fvames.pattern.factory.model.Java;
import dev.fvames.pattern.factory.simple.IProgramLanguage;

public class JavaFactory implements IFactoryMethod {

    public IProgramLanguage create() {
        return new Java();
    }
}
