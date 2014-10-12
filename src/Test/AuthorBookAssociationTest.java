package Test;

import org.junit.Test;
import src.Author;
import src.AuthorBookAssociation;

import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class AuthorBookAssociationTest {
    @Test
    public void testMatches() throws Exception {
        List<AuthorBookAssociation> authorBookAssociations = AuthorBookAssociation.matches(Author.matches("E"));
        for (AuthorBookAssociation association:authorBookAssociations) {
            System.out.println(association.getAuthorId() + " " + association.getBookId());
        }
    }
}
