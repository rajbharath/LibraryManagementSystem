package main.screen;

import main.Admin;
import main.util.IOUtils;

/**
 * Created by ganeswari on 10/18/14.
 */
public class LoginScreen implements Navigator, Screen {


    private Admin admin;
    private boolean completed = false;

    public LoginScreen() {

    }

    private boolean loggedIn() {
        boolean isLoggedIn = false;
        int remainingTrial = 3;
        while (admin == null && remainingTrial != 0) {
            IOUtils.print("Login");
            IOUtils.print("Enter Username");
            String username = IOUtils.readString();
            IOUtils.print("Enter Password");
            String password = IOUtils.readString();
//            admin = Admin.create(username, password, false);
            if (admin != null) {
                isLoggedIn = true;
            } else {
                remainingTrial--;
                IOUtils.print("Invalid Username and/or password! You have " + remainingTrial + " more trials");
            }
        }
        return isLoggedIn;
    }

    @Override
    public void goNextScreen() {
        new MainMenuScreen().show();
    }

    @Override
    public void goPreviousScreen() {
        completed = true;
    }

    @Override
    public void show() {
        if (loggedIn()) {
            goNextScreen();
        } else {
            goPreviousScreen();
        }

    }
}
