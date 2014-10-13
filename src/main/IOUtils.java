package main;

import java.io.IOException;

/**
 * Created by ganeswari on 10/13/14.
 */
public class IOUtils {
    public static void print(String s) {
        System.out.println(s);
    }

    public static int read() {
        int input = -1;
        try {
            input = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
