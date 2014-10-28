LibraryManagementSystem - 28 / 10 / 2014
=======================

Functionalities :
================
Below are the basic functionalities included in the solution.

1. Add Book to the library
2. Remove Book to the library
3. Search Books By Title
4. Search Books By Author
5. Return Book
6. Issue Book
7. Fetch Readers History

DB:
==
Tables:
-------
1. Admin
    - Admin_ID
    - Admin_Name
    - Password
2. Author
    - Author_ID
    - Author_Name
3. Book
    - Book_ID
    - Name
    - ISBN
    - Edition
    - Publisher_ID
    - Price
    - Copies
4. Publisher
    - Publisher_ID
    - Publisher_Name
5. AuthorBookAssociation
    - Author_Book_Association_ID
    - Book_ID
    - Author_ID
6. Reader
    - Reader_ID
    - Reader_Name
7. Transaction
    - Transaction_ID
    - Book_ID
    - Reader_ID
    - Start_Date
    - Estimated_Due_Date
    - Returned_Date