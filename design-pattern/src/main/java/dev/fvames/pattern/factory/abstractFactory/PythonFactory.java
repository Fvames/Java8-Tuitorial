package dev.fvames.pattern.factory.abstractFactory;

public class PythonFactory implements LanguageFactory {
    public MethodFactory createMethod() {
        return new PythonMethod();
    }

    public ClassFactory createClass() {
        return new PythonClass();
    }
}
