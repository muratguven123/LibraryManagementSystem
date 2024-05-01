package Library;

import java.util.NoSuchElementException;
import java.util.Scanner;


import static Library.User.database;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static Database database = new Database();


    public static void main(String[] args) {

        Database database = new Database();

        int num = 0;

            System.out.println("Welcome to Library Management System!\n" +
                    "0. Exit\n" + "1.login\n2. New User");

            // Read user input using the existing scanner object
            if(scanner.hasNextInt()){
                num = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter an integer");
                // Consume the invalid input to prevent an infinite loop
                scanner.next();
            }

            switch (num) {
                case 1:
                    login();
                    break;

                case 2:
                    newUser();
                    break;
            }
        }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Phone Number: ");
        String phoneNumber = scanner.next();
        System.out.println("Enter Mail: ");
        String email = scanner.next();
        int n = database.login(phoneNumber, email);
        System.out.println("n"+ n);
        User user;
        if (n !=-1) {
             user = database.getUser(n);
             user.menu(database,user);
        } else {
            System.out.println("User does not exist!");
        }

    }

    private static void newUser() {
        try {
            System.out.println("Enter Name: ");
            String name = scanner.next();
            if(database.userExist(name)) {
                System.out.println("User exists!");
                newUser();
            }
            System.out.println("Enter Phone Number: ");
            String phoneNumber = scanner.next();
            System.out.println("Enter email: ");
            String email = scanner.next();
            System.out.println("1. Admin \n2. Normal User");
            int n2 = scanner.nextInt();
            User user;
            if (n2 == 1) {
                user = new Admin(name, email, phoneNumber);

            } else {
                user = new NormalUser(name, email, phoneNumber);
            }
            database.AddUser(user);
            user.menu(database,user);
        }catch (NoSuchElementException e){
            System.err.println("Input Error: "+e.getMessage());
        }
//        System.out.println("Enter Name: ");
//        String name = scanner.next();
//        System.out.println("Enter Phone Number: ");
//        String phoneNumber = scanner.next();
//        System.out.println("Enter email: ");
//        String email = scanner.next();
//        System.out.println("1. Admin \n2. Normal User");
//        int n2 = scanner.nextInt();
//        User user;
//        if (n2 == 1) {
//           user = new Admin(name, email, phoneNumber);
//
//        } else {
//            user = new NormalUser(name, email, phoneNumber);
//        }
//        database.AddUser(user);
//        user.menu(database,user);
    }
}

