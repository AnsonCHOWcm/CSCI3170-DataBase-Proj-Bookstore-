package Main;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db20";
    static String dbUsername = "db016";
    static String dbPassword = "tc7fwpit";
    static Connection con = null;
    static java.util.Date system_time;

    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR] Java MySQL DB Driver not found.");
            System.exit(0);
        }

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat ft = new SimpleDateFormat(pattern);
        system_time = ft.parse("0000-00-00");

        System.out.println("Connection Success\n\n");
        int choice = -1;

        StringIntegerChecker checker = new StringIntegerChecker();

        loop: while (true) {
            System.out.println("The System Date is now : " + system_time);
            System.out.println("<This is the Book Ordering System>");
            System.out.println("----------------------------------------");
            System.out.println("1. System interface.");
            System.out.println("2. Customer interface.");
            System.out.println("3. Bookstore interface.");
            System.out.println("4. Show System Date.");
            System.out.println("5. Quit the system.......");

            System.out.println("Please enter your choice??..");
            choice = checker.IntegerChecker(1, 5);

            switch (choice) {
            case 1:
                SystemInterface admin = new SystemInterface();
                admin.CommandLineInterface();
                break;
            case 2:
                Customer customer = new Customer();
                customer.CommandLineInterface();
                break;
            case 3:
                Bookstore bookstore = new Bookstore();
                bookstore.CommandLineInterface();
                break;
            case 4:
                System.out.println("The System Date is now : " + system_time);
                break;
            case 5:
                System.out.println("See you next time. Bye Bye.");
                break loop;
            }
        }

        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("[ERROR] SQL Exception happens when closing JDBC Connection");
        }
    }
}
