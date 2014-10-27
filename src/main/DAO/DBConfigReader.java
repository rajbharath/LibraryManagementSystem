package main.DAO;

import java.io.*;

/**
 * Created by ganeswari on 10/11/14.
 */
public class DBConfigReader {
    private static String[] configurationDetails = new String[4];

    static {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("DBConfig"))));
            for (int i = 0; i < 4; i++) {
                configurationDetails[i] = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getDriverName() {
        return configurationDetails[0];
    }

    public static String getDBName() {
        return configurationDetails[1];
    }

    public static String getUsername() {
        return configurationDetails[2];
    }

    public static String getPassword() {
        return configurationDetails[3];
    }
}
