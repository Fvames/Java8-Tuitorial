package dev.fvames.pattern.factory.simple;

import dev.fvames.pattern.factory.model.Java;
import dev.fvames.pattern.factory.model.Python;

public class SimpleFactory {

    public static final String JAVA = "JAVA";
    public static final String PYTHON = "PYTHON";
    public static final String JAVA_CLASS_NAME = "dev.fvames.pattern.factory.model.Java";
    public static final String PYTHON_CLASS_NAME = "dev.fvames.pattern.factory.model.Python";

    /**
     * 违反开闭原则
     *
     * @param name
     * @return
     */
    public IProgramLanguage getByName(String name) {
        if (JAVA.equals(name)) {
            return new Java();
        }
        if (PYTHON.equals(name)) {
            return new Python();
        }
        return null;
    }

    public IProgramLanguage getByClassName(String className) throws Exception {
        return (IProgramLanguage) Class.forName(className).newInstance();
    }

}
