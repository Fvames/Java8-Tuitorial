package dev.fvames.pattern.factory.abstractFactory;

public interface LanguageFactory {

    MethodFactory createMethod();

    ClassFactory createClass();
}
