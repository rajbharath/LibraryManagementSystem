package main;

import main.DAO.AuthorDAO;

import java.util.List;

/**
 * Created by ganeswari on 10/8/14.
 */
public class Author {
    private static AuthorDAO authorDAO = new AuthorDAO();
    long authorId;
    String name;
    List<Book> books;
    boolean isPersistent;

    public Author(String authorName) {
        this(0, authorName, null, false);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Author(long authorId, String name, List<Book> books, boolean isPersistent) {
        this.authorId = authorId;
        this.name = name;
        this.books = books;
        this.isPersistent = isPersistent;
    }


    public long getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public static List<Author> retrieveAll() {
        return authorDAO.retrieveAll();
    }

    public static List<Author> matches(String criteria) {
        return authorDAO.retrieveMatchesByName(criteria);
    }


    public static Author retrieve(Long authorID) {
        return authorDAO.retrieve(authorID);
    }

    public void save() {
        if (isPersistent)
            authorDAO.update(this);
        else
            authorDAO.create(this);
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setIsPersistent(boolean isPersistent) {
        this.isPersistent = isPersistent;
    }
}
