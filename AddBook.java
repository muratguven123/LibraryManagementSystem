package Library;

import java.util.Scanner;

public class AddBook implements IOOperation {

    @Override
    public void oper(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter book name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Invalid book name.");
            // No need to close scanner here; continue with input process
            oper(database, user); // Restart the operation
            return;
        }

        if (database.getBookIndex(name) != -1) {
            System.out.println("A book with this name already exists.");
            // No need to close scanner here; continue with input process
            oper(database, user); // Restart the operation
            return;
        }

        Book book = new Book();
        book.setName(name);

        System.out.println("Enter Book author: ");
        book.setAuthor(scanner.nextLine().trim());

        System.out.println("Enter Book Publisher: ");
        book.setPublisher(scanner.nextLine().trim());

        System.out.println("Enter Book Collection Location: ");
        book.setAddress(scanner.nextLine().trim());

        int quantity = getIntegerInput(scanner, "Enter Book Quantity: ");
        if (quantity == -1) {
            oper(database, user); // Restart the operation
            return;
        }
        book.setQty(quantity);

        double price = getDoubleInput(scanner, "Enter Book Price: ");
        if (price == -1) {
            oper(database, user); // Restart the operation
            return;
        }
        book.setPrice(price);

        int borrowingCopies = getIntegerInput(scanner, "Enter Book borrowing copies: ");
        if (borrowingCopies == -1) {
            oper(database, user); // Restart the operation
            return;
        }
        book.setBrwcopies(borrowingCopies);

        scanner.close(); // Close scanner after all inputs are processed

        database.addBook(book);
        System.out.println("Book Added successfully.\n");
        user.menu(database, user);
    }

    // Helper method to get integer input with validation
    private int getIntegerInput(Scanner scanner, String message) {
        System.out.println(message);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next(); // Consume invalid input
            return -1; // Return -1 to indicate error
        }
    }

    // Helper method to get double input with validation
    private double getDoubleInput(Scanner scanner, String message) {
        System.out.println(message);
        if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        } else {
            System.out.println("Invalid input. Please enter a valid double value.");
            scanner.next(); // Consume invalid input
            return -1; // Return -1 to indicate error
        }
    }
}
