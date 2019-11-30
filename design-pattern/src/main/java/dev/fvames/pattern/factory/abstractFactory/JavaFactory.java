package dev.fvames.pattern.factory.abstractFactory;

public class JavaFactory implements LanguageFactory {
    public MethodFactory createMethod() {
        return new JavaMethod();
    }

    public ClassFactory createClass() {
        return new JavaClass();
    }
}
