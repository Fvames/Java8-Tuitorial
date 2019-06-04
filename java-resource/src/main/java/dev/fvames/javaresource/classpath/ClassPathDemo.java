package dev.fvames.javaresource.classpath;

import org.springframework.util.StreamUtils;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.nio.charset.Charset;

/**
 * 需要创建 Handler 并继承 {@link URLStreamHandler} 实现 openConnection，定义自定义协议
 *
 * @version 2019/6/4 14:33
 */

public class ClassPathDemo {

    public static void main(String[] args) throws Exception {

        //URLConnection connection = Thread.currentThread().getContextClassLoader()
        //        .getResource("application.properties").openConnection();

        URL url = new URL("classpath:/application.properties");
        URLConnection connection = url.openConnection();

        String content = StreamUtils.copyToString(connection.getInputStream(), Charset.forName("UTF-8"));
        System.out.printf("content：" + content);
    }

}
