package Main;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db20";
    static String dbUsername = "Group20";
    static String dbPassword = "weareg20";
    static Connection con = null;

    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR] Java MySQL DB Driver not found.");
            System.exit(0);
        }

        System.out.println("Connection Success\n\n");
        int choice = -1;
        StringIntegerChecker checker = new StringIntegerChecker(1, 5);

        SystemInterface admin = new SystemInterface();
        Customer customer = new Customer();
        Bookstore bookstore = new Bookstore();

        loop: while (true) {
            System.out.println("The System Date is now : " + admin.t);
            System.out.println("<This is the Book Ordering System>");
            System.out.println("----------------------------------------");
            System.out.println("1. System interface.");
            System.out.println("2. Customer interface.");
            System.out.println("3. Bookstore interface.");
            System.out.println("4. Show System Date.");
            System.out.println("5. Quit the system.......");

            choice = checker.IntegerChecker();
            switch(choice) {
                case 1: admin.CommandLineInterface();
                        break;
                case 2: customer.CommandLineInterface();
                        break;
                case 3: bookstore.CommandLineInterface();
                        break;
                case 4: System.out.println("The System Date is now : " + admin.t);
                        break;
                case 5: System.out.println("See you next time. Bye Bye.");
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
