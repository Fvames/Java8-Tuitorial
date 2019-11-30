package dev.fvames.pattern.factory;

import dev.fvames.pattern.factory.factoryMethod.JavaFactory;
import dev.fvames.pattern.factory.factoryMethod.PythonFactory;
import dev.fvames.pattern.factory.simple.IProgramLanguage;

public class FactoryMethodTest {

    public static void main(String[] args) {
        IProgramLanguage java = new JavaFactory().create();
        IProgramLanguage python = new PythonFactory().create();

        java.sayHello();
        python.sayHello();
    }

}
