package src;

import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class SearchByAuthor implements SearchBy {
    @Override
    public List<Book> matches(String criteria) {

        List<Author> matchedAuthors = Author.matches(criteria);
        List<AuthorBookAssociation> authorBookAssociations = AuthorBookAssociation.matches(matchedAuthors);
        List<Book> matchedBooks = Book.retrieveAll(authorBookAssociations);

        return null;
    }
}
