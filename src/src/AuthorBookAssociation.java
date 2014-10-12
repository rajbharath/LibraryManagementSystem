package src;

import DAO.AuthorBookAssociationDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeswari on 10/8/14.
 */
public class AuthorBookAssociation {
    long authorBookAssociationId;
    long authorId;
    long bookId;
    private static AuthorBookAssociationDAO authorBookAssociationDAO = new AuthorBookAssociationDAO();

    public long getAuthorId() {
        return authorId;
    }

    public long getBookId() {
        return bookId;
    }

    public AuthorBookAssociation(long authorBookAssociationId, long authorId, long bookId) {

        this.authorBookAssociationId = authorBookAssociationId;
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public static List<AuthorBookAssociation> matches(List<Author> authors) {
        List<AuthorBookAssociation> authorBookAssociations = new ArrayList<AuthorBookAssociation>();
         for(Author author:authors)
         {
               authorBookAssociations.addAll(authorBookAssociationDAO.retrieveFor(author.getAuthorId()));


         }
        return authorBookAssociations;

    }
}



