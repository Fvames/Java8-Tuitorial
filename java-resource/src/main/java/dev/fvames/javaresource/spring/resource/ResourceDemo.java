package dev.fvames.javaresource.spring.resource;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @version 2019/6/4 17:19
 */

public class ResourceDemo {

    public static void main(String[] args) throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        resourceLoader.addProtocolResolver(new ProtocolResolver() {

            public static final String PROTOCOL_PREFIX = "cp:/";

            @Override
            public Resource resolve(String location, ResourceLoader resourceLoader) {
                // cp:/application.properties -> classpath:/application.properties
                if (location.startsWith(PROTOCOL_PREFIX)) {
                    String classPath = ResourceLoader.CLASSPATH_URL_PREFIX +
                            location.substring(PROTOCOL_PREFIX.length());
                    return resourceLoader.getResource(classPath);
                }

                return null;
            }
        });

        Resource resource = resourceLoader.getResource("cp:/application.properties");
        InputStream inputStream = resource.getInputStream();

        String content = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        System.out.println(content);
    }

}
