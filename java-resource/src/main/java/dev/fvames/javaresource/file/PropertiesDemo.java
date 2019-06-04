package dev.fvames.javaresource.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @version 2019/6/4 17:05
 */

public class PropertiesDemo {

    public static void main(String[] args) throws IOException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("application.properties");

        Properties properties = new Properties();
        properties.load(inputStream);

        String appName = properties.getProperty("spring.application.name");
        System.out.println(appName);

    }

}
