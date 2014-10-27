package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ganeswari on 10/12/14.
 */
public class SearchByAuthor implements SearchBy {
    @Override
    public List<Book> matches(String criteria) {
        Set<Book> matchedBooks = new HashSet<>();
        List<Author> matchedAuthors = Author.matches(criteria);
        for (Author author : matchedAuthors) {
            matchedBooks.addAll(author.getBooks());
        }
        return new ArrayList<>(matchedBooks);
    }
}
