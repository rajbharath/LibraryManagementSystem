package main;

import main.util.IOUtils;

import java.util.List;

/**
 * Created by ganeswari on 10/11/14.
 */
public class Client {
    Admin admin = null;
    boolean completed = false;
    List<Book> books;
    Book selectedBook;

    public static void main(String[] args) {
        new Client();
    }

    Client() {

        if (loggedIn())
            start();
    }

    private boolean loggedIn() {
        int remainingTrial = 3;
        while (admin == null && remainingTrial != 0) {
            IOUtils.print("Login");
            IOUtils.print("Enter Username");
            String username = IOUtils.readString();
            IOUtils.print("Enter Password");
            String password = IOUtils.readString();
            admin = Admin.retrieve(username, password);
            if (admin == null) {
                remainingTrial--;
                IOUtils.print("Invalid Username and/or password! You have " + remainingTrial + " more trials");
            } else {
                return true;

            }

        }
        return false;
    }

    private void start() {
        IOUtils.print("Library Management");
        while (!completed) {
            showMainMenu();
            int option = IOUtils.read();
            completed = processOption(option);

        }
    }

    private void showMainMenu() {
        IOUtils.print("1. Search By Title");
        IOUtils.print("2. Search By Author Name");
        IOUtils.print("3. Add Book");
        IOUtils.print("4. Remove Book");
        IOUtils.print("5. Reader History");
        IOUtils.print("6. Return Book");
        IOUtils.print("7. Log off");
    }

    private boolean processOption(int option) {
        switch (option) {
            case 1:
                searchBooksByTitle();
                display();
                bookSelectionMenu();

                break;
            case 2:
                searchBooksByAuthor();
                display();
                bookSelectionMenu();
                break;
            case 3:
                addBook();
                break;
            case 4:
                removeBook();
                break;
            case 5:
                getReaderHistory();
                break;
            case 6:
                returnBook();
                break;
            case 7:
                return true;
            default:
                IOUtils.print("Invalid Option " + option);
                break;
        }
        return false;
    }

    private void returnBook() {
        IOUtils.print("Enter Reader ID");
        long readerId = IOUtils.read();
        IOUtils.print("Enter the Book ID");
        long bookId = IOUtils.read();
        admin.takeBack(Reader.retrieve(readerId), Book.retrieve(bookId));

    }

    private void getReaderHistory() {
        IOUtils.print("Enter Reader ID to see the transaction history");
        long readerId = IOUtils.read();
        books = admin.getHistory(Reader.retrieve(readerId));
        display();
    }

    private void removeBook() {
        IOUtils.print("Enter Book ID to be deleted");
        long bookId = IOUtils.read();
        admin.removeBook(Book.retrieve(bookId));
    }

    private void addBook() {
        IOUtils.print("Enter Book Title");
        String title = IOUtils.readString();
        IOUtils.print("Enter Book ISBN");
        String ISBN = IOUtils.readString();
        IOUtils.print("Enter Book Edition");
        String edition = IOUtils.readString();
        IOUtils.print("Enter Book Publisher");
        String publisherName = IOUtils.readString();
        IOUtils.print("Enter Book Authors (if more than one author give names separated by comma)");
        String authorNamesWithComma = IOUtils.readString();
        String[] authorNames = authorNamesWithComma.split(",");
        IOUtils.print("Enter No Of copies to be added");
        int copies = IOUtils.read();
        IOUtils.print("Enter cost of one book");
        double price = Double.parseDouble(IOUtils.readString());

        admin.addBook(title, ISBN, edition, publisherName, authorNames, copies, price);
    }

    private void searchBooksByAuthor() {
        IOUtils.print("Enter the author name");
        String authorName = IOUtils.readString();
        books = admin.searchBookByAuthorName(authorName);

    }

    private void searchBooksByTitle() {
        IOUtils.print("Enter the book title");
        String title = IOUtils.readString();
        books = admin.searchBooksByTitle(title);
    }

    private void display() {
        int i = 1;

        for (Book book : books) {
            IOUtils.print("S No : " + i);
            IOUtils.print(book.display());
            i++;
        }

    }


    private void bookSelectionMenu() {
        IOUtils.print("Select the book by entering the S No ");
        IOUtils.print("0 - Back to Main Menu");
        int option = IOUtils.read();
        processBookSelectionOption(option);
    }

    private void processBookSelectionOption(int option) {
        boolean completed = false;
        int selectedIndex = option - 1;
        while (!completed) {
            if (selectedIndex >= 0 && selectedIndex < books.size()) {
                selectedBook = books.get(selectedIndex);
                display(selectedBook);
                bookIssueMenu();
            } else if (option == 0) {
                IOUtils.print("Back");

            }
            completed = true;

        }

    }

    private void display(Book book) {
        IOUtils.print(book.display());
    }

    private void bookIssueMenu() {
        IOUtils.print("1. Check Out ");
        IOUtils.print("0 - Back to Main Menu");
        int bookIssueOption = IOUtils.read();
        processBookIssueMenu(bookIssueOption);

    }

    private boolean processBookIssueMenu(int option) {
        switch (option) {

            case 1:
                IOUtils.print("Enter the reader id");
                long readerId = IOUtils.read();
                Reader reader = Reader.retrieve(readerId);

                if (reader != null) {
                    admin.issue(reader, selectedBook);
                } else {
                    IOUtils.print("Invalid reader");
                }
                break;
            case 0:
            default:
                break;
        }
        return false;
    }
}
