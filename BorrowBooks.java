package Library;

import java.util.Scanner;

public class BorrowBooks implements IOOperation {

    @Override
    public void oper(Database database, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Book Name: ");
        String bookName = scanner.nextLine().trim();
        scanner.close(); // Closing the Scanner to release system resources

        // Validate book name input
        if (bookName.isEmpty()) {
            System.out.println("Invalid book name.");
            user.menu(database, user);
            return;
        }

        int bookIndex = database.getBookIndex(bookName);

        if (bookIndex == -1) {
            System.out.println("Book not found in the library.");
            user.menu(database, user);
            return;
        }

        Book book = database.getBook();

        // Check if the user has borrowed this book before
        if (database.hasUserBorrowedBook(user, book)) {
            System.out.println("You have borrowed this book before!");
        } else {
            if (book.getBrwcopies() > 0) {
                Borrowing borrowing = new Borrowing(book, user);
                book.setBrwcopies(book.getBrwcopies() - 1);
                database.addBorrowing(borrowing);
                System.out.println("You must return the book within 14 days from now.");
                System.out.println("Expiry Date: " + borrowing.getFinish());
                System.out.println("Enjoy!");
            } else {
                System.out.println("This book isn't available for borrowing.");
            }
        }

        user.menu(database, user);
    }
}
