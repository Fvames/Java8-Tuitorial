package dev.fvames.pattern.factory;

import dev.fvames.pattern.factory.simple.IProgramLanguage;
import dev.fvames.pattern.factory.simple.SimpleFactory;

public class SimpleFactoryTest {

    public static void main(String[] args) throws Exception {
        SimpleFactory simpleFactory = new SimpleFactory();
        //byName(simpleFactory);

        byClassName(simpleFactory);

    }

    private static void byClassName(SimpleFactory simpleFactory) throws Exception {
        IProgramLanguage Java = simpleFactory.getByClassName(SimpleFactory.JAVA_CLASS_NAME);
        IProgramLanguage python = simpleFactory.getByClassName(SimpleFactory.PYTHON_CLASS_NAME);

        Java.sayHello();
        python.sayHello();
    }

    private static void byName(SimpleFactory simpleFactory) {
        IProgramLanguage Java = simpleFactory.getByName(SimpleFactory.JAVA);
        IProgramLanguage python = simpleFactory.getByName(SimpleFactory.PYTHON);

        Java.sayHello();
        python.sayHello();
    }

}
