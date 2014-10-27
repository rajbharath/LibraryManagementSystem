package main.screen;


import main.util.IOUtils;

/**
 * Created by ganeswari on 10/25/14.
 */
public class MainMenuScreen implements Navigator, Screen {
    Screen previousScreen;
    private boolean completed;
    private int option = -1;

    public MainMenuScreen() {
        this.previousScreen = previousScreen;
    }

    @Override
    public void goNextScreen() {
        while (!completed) {
            showMainMenu();
            option = IOUtils.read();
            completed = processOption(option);
            IOUtils.clearScreen();
        }
    }

    @Override
    public void goPreviousScreen() {
        previousScreen.show();
    }

    public void show() {
        IOUtils.print("Library Management");


        goNextScreen();

    }

    private boolean processOption(int option) {
        switch (option) {
            case 1:
                new SearchByTitleScreen().show();
                break;
            case 2:
                break;
            case 5:
                IOUtils.print("Exiting ....");
                return true;
            default:
                IOUtils.print("Invalid Option " + option);
                break;
        }
        return false;
    }

    private void showMainMenu() {
        IOUtils.print("1. Search By Title");
        IOUtils.print("2. Search By Author Name");
        IOUtils.print("5. Exit");
    }
}
