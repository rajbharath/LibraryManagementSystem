package main.screen;

import main.Reader;
import main.util.IOUtils;

/**
 * Created by ganeswari on 10/25/14.
 */
public class CheckOutScreen implements Navigator, Screen {
    @Override
    public void goNextScreen() {

    }

    private void bookIssueMenu() {
        IOUtils.print("1. Check Out ");
        IOUtils.print("B - Back to Main Menu");
        int bookIssueOption = IOUtils.read();
        processBookIssueMenu(bookIssueOption);

    }

    private boolean processBookIssueMenu(int option) {
        switch (option) {
            case 1:
                IOUtils.print("Enter the reader id");
                long readerId = IOUtils.read();
                Reader reader = Reader.retrieve(readerId);

                if (reader != null) {
//                    admin.issue(reader, selectedBook);
                } else {
                    IOUtils.print("Invalid reader");
                }
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void goPreviousScreen() {

    }

    @Override
    public void show() {

    }
}
