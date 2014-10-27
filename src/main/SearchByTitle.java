package main;

import java.util.List;

/**
 * Created by ganeswari on 10/14/14.
 */
public class SearchByTitle implements SearchBy {
    @Override
    public List<Book> matches(String criteria) {
        return Book.matches(criteria);
    }
}
