import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    static String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db1";
    static String dbUsername = "Group1";
    static String dbPassword = "CSCI3170";
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

        Checker checker = Checker.getInstance();

        while (true) {
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

            if (choice == 1) {
                SystemInterface admin = new SystemInterface();
                admin.CommandLineInterface();
            } else if (choice == 2) {
                Customer customer = new Customer();
                customer.CommandLineInterface();
            } else if (choice == 3) {
                Bookstore bookstore = new Bookstore();
                bookstore.CommandLineInterface();
            } else if (choice == 4) {
                System.out.println("The System Date is now : " + system_time);
            } else {
                System.out.println("See you next time. Bye Bye.");
                break;
            }
        }

        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("[ERROR] SQL Exception happens when closing JDBC Connection");
        }
    }
}
