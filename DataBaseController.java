import java.sql.*;

public class DataBaseController {
    static private String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db1";
    static private String dbUsername = "Group1";
    static private String dbPassword = "CSCI3170";

    static public Connection connectToSQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
            System.out.println("Connection Success\n\n");
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR] Java MySQL DB Driver not found.");
            System.exit(0);
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(0);
        }
        return null;
    }

    // static public void CreateTable() {
    //     Connection con = connectToSQL();
    //     try {
    //         Statement stmt = con.createStatement();
    //         String bookSql = "Create table book" + "(ISBN CHAR(13)," + "title VARCHAR(100) NOT NULL,"
    //                 + "unit_price INTEGER," + "no_of_copies INTEGER," + "CONSTRAINT PRIMARY KEY (ISBN),"
    //                 + "CHECK (unit_price >=0)," + "CHECK (no_of_copies >=0))";

    //         String customerSql = "Create table customer" + "(customer_id VARCHAR(10) NOT NULL,"
    //                 + "name VARCHAR(50) NOT NULL," + "shipping_address VARCHAR(200) NOT NULL,"
    //                 + "credit_card_no CHAR(19)," + "CONSTRAINT PRIMARY KEY (customer_id))";

    //         String ordersSql = "Create table orders" + "(order_id CHAR(8)," + "o_date DATE," + "shipping_status CHAR,"
    //                 + "charge INTEGER," + "customer_id VARCHAR(10) NOT NULL," + "CONSTRAINT PRIMARY KEY (order_id),"
    //                 + "FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE NO ACTION,"
    //                 + "CHECK (charge >=0) , CHECK (shipping_status = 'Y' || shipping_status = 'N'))";

    //         String orderingSql = "Create table ordering" + "(order_id CHAR(8) NOT NULL," + "ISBN CHAR(13),"
    //                 + "quantity INTEGER," + "CONSTRAINT PRIMARY KEY (order_id , ISBN),"
    //                 + "FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE NO ACTION,"
    //                 + "FOREIGN KEY (ISBN) REFERENCES book(ISBN)," + "CHECK (quantity>=0))";

    //         String bookauthorSql = "Create table book_author" + "(ISBN CHAR(13) NOT NULL,"
    //                 + "author_name VARCHAR(50) NOT NULL," + "CONSTRAINT PRIMARY KEY (ISBN , author_name),"
    //                 + "FOREIGN KEY (ISBN) REFERENCES book(ISBN) ON DELETE NO ACTION)";

    //         stmt.executeUpdate(bookSql);
    //         stmt.executeUpdate(customerSql);
    //         stmt.executeUpdate(ordersSql);
    //         stmt.executeUpdate(orderingSql);
    //         stmt.executeUpdate(bookauthorSql);

    //         System.out.println("Done! All Tables are created!");
    //         con.close();
    //     } catch (SQLException se) {
    //         se.printStackTrace();
    //         System.out.println("[Error]: All tables have been created. Please try deleting all the tables first.");
    //     }
    // }
}
