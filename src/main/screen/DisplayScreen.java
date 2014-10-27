package main.screen;

import main.Book;
import main.util.IOUtils;

import java.util.List;

/**
 * Created by ganeswari on 10/25/14.
 */
public class DisplayScreen implements Navigator, Screen {
    List<Book> books;
    private Book selectedBook;

    public DisplayScreen(List<Book> books) {
        this.books = books;
    }

    @Override
    public void goNextScreen() {

    }

    @Override
    public void goPreviousScreen() {

    }

    @Override
    public void show() {
        display();
        bookSelectionMenu();
    }

    private void display() {
        int i = 1;
        for (Book book : books) {
            IOUtils.print("S No : " + i);
            IOUtils.print(book.display());
            i++;
        }

    }

    private void bookSelectionMenu() {
        IOUtils.print("Select the book by entering the S No ");
        IOUtils.print("0 - Go Back ");
        int option = IOUtils.read();
        processBookSelectionOption(option);
    }

    private void processBookSelectionOption(int option) {
        boolean completed = false;
        int selectedIndex = option - 1;
        while (!completed) {
            if (selectedIndex < books.size()) {
                selectedBook = books.get(selectedIndex);
                completed = true;
            } else if (option == 0) {
                completed = true;
            }

        }

    }
}
