package Test;

import org.junit.Test;
import src.Author;

import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class AuthorTest {
    @Test
    public void testRetrieveAll() throws Exception {
        List<Author> authors = Author.retrieveAll();
        for (Author author : authors) {
            System.out.println(author.getAuthorId() + " " + author.getName());
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
