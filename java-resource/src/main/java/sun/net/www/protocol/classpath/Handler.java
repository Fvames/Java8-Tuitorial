package sun.net.www.protocol.classpath;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * @version 2019/6/4 15:14
 */

public class Handler extends URLStreamHandler {
    public static final String PROTOCOL_PREFIX = "classpath:/";

    @Override
    protected URLConnection openConnection(URL u) throws IOException {

        // classpath:/application.properties
        // 移除 classpath:/ 前缀
        String path = u.toString().substring(PROTOCOL_PREFIX.length());
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL classPathURL = classLoader.getResource(path);
        return classPathURL.openConnection();
    }
}
