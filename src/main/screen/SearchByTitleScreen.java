package main.screen;

import main.Book;
import main.SearchBy;
import main.SearchByType;
import main.Shelf;
import main.util.IOUtils;

import java.util.List;

/**
 * Created by ganeswari on 10/23/14.
 */
public class SearchByTitleScreen implements Navigator, Screen {
    private String searchCriteria;
    private SearchBy searchBy;
    private boolean completed = false;
    private Shelf shelf;

    public SearchByTitleScreen() {
        shelf = Shelf.getInstance();
    }

    public void show() {

        goNextScreen();
    }

    private boolean process(String searchCriteria) {
        if (searchCriteria.equals("back")) {
            return true;
        } else {
            List<Book> books = shelf.search(SearchByType.TITLE, searchCriteria);
            if (books != null)
                new DisplayScreen(books).show();
        }
        return false;
    }

    @Override
    public void goNextScreen() {
        while (!completed) {
            IOUtils.print("Enter Book Title. To go back type back");
            searchCriteria = IOUtils.readString();
            completed = process(searchCriteria);
        }
    }

    @Override
    public void goPreviousScreen() {
        completed = true;
    }
}
