package main;

import DAO.AuthorDAO;

import java.util.ArrayList;
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

    public List<Book> getBooks() {
        return books;
    }

    public Author(long authorId, String name, List<Book> books) {
        this.authorId = authorId;
        this.name = name;
        this.books = books;

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

        List<Author> authors = authorDAO.retrieveAll();

        List<Author> matchedAuthors = new ArrayList<Author>();
        for (Author author : authors) {
            if (author.getName().contains(criteria)) {
                matchedAuthors.add(author);
            }
        }
        return matchedAuthors;
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
}
