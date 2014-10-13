package Test;

import main.Book;
import org.junit.Test;
import main.Author;

import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class AuthorTest {
    @Test
    public void testRetrieveAll() throws Exception {
        List<Author> authors = Author.retrieveAll();
        for (Author author : authors) {
            System.out.print(author.getAuthorId() + " " + author.getName());
            for(Book book:author.getBooks()){
                System.out.println(" "+book.getTitle());
            }
        }
    }

    @Test
    public void testMatches() throws Exception {
        List<Author> authors = Author.matches("");
        for (Author author : authors) {
            System.out.println(author.getAuthorId() + " " + author.getName());
        }
    }
}
