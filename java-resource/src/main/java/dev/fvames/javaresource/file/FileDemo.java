package dev.fvames.javaresource.file;

import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * @version 2019/6/4 16:52
 */

public class FileDemo {

    public static void main(String[] args) throws Exception {
        // java-resource 模块
        File file = new File("java-resource/src/main/resources/application.properties");

        URL fileURL = file.toURI().toURL();

        //URL url = new URL("https://www.baidu.com"); // https 协议
        //URL ftpURL = new URL("ftp://ftp.baidu.com"); // ftp 协议
        //URL jar = new URL("jar://jar.baidu.com"); // jar 协议
        //URL dubboURL = new URL("dubbo://");       // dubbo协议
        //URL classpathURL = new URL("classpath:/");       // classpath

        // file URLStreamHandler   = sun.net.www.protocol.file.Handler
        // http URLStreamHandler  =  sun.net.www.protocol.http.Handler
        // https URLStreamHandler  = sun.net.www.protocol.https.Handler
        // jar URLStreamHandler  = sun.net.www.protocol.jar.Handler
        // ftp URLStreamHandler  = sun.net.www.protocol.ftp.Handler
        // 模式 URLStreamHandler =  sun.net.www.protocol.${protocol}.Handler

        URLConnection urlConnection = fileURL.openConnection();

        InputStream inputStreamFromURL = urlConnection.getInputStream();

        String content = StreamUtils.copyToString(inputStreamFromURL, Charset.forName("UTF-8"));

        System.out.println(content);

    }
}
