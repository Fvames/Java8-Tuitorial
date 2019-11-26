package dev.fvames.apache.commons;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class IOUtilsDemo {

    public static void main(String[] args) throws Exception {

        jdkReadLine();

        ioReadline();

    }

    private static void ioReadline() throws IOException {
        try (InputStream in = new URL("http://commons.apache.org").openStream();) {
            String s = IOUtils.toString(in, "UTF-8");
            System.out.println(s);
        }
    }

    private static void jdkReadLine() throws IOException {
        try (InputStream in = new URL("http://commons.apache.org").openStream()) {

            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(inR);
            String line;
            while (null != (line = buf.readLine())) {
                System.out.println(line);
            }
        }
    }
}
