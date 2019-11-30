package dev.fvames.pattern.factory.factoryMethod;

import dev.fvames.pattern.factory.model.Python;
import dev.fvames.pattern.factory.simple.IProgramLanguage;

public class PythonFactory implements IFactoryMethod {

    public IProgramLanguage create() {
        return new Python();
    }
}
