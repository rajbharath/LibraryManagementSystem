package main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by ganeswari on 10/13/14.
 */
public class IOUtils {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Scanner scanner = new Scanner(System.in);

    public static void print(String s) {
        System.out.println(s);
    }

    public static int read() {
        int input = -1;
        try {
            input = Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            IOUtils.print("Please enter a numeric");
        }
        return input;
    }

    public static String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void clearScreen() {
        System.out.flush();
    }
}
