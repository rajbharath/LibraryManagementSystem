package Test;

import org.junit.Test;
import src.Author;
import src.AuthorBookAssociation;
import src.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class BookTest {
    @Test
    public void testRetrieveAll() throws Exception {
        List<Book> books = new ArrayList<Book>();
        books.addAll(Book.retrieveAll(AuthorBookAssociation.matches(Author.matches("E"))));
        for(Book book:books){
            System.out.println(book.getTitle());
        }
    }
}
