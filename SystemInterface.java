import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

public class SystemInterface {
	Checker checker;
	static Connection con = Main.con;

	public SystemInterface() throws ParseException {
		checker = Checker.getInstance();
	}

	public void CreateTable() {
		try {
			Statement stmt = con.createStatement();
			String bookSql = "Create table book" + "(ISBN CHAR(13)," + "title VARCHAR(100) NOT NULL,"
					+ "unit_price INTEGER," + "no_of_copies INTEGER," + "CONSTRAINT PRIMARY KEY (ISBN),"
					+ "CHECK (unit_price >=0)," + "CHECK (no_of_copies >=0))";

			String customerSql = "Create table customer" + "(customer_id VARCHAR(10) NOT NULL,"
					+ "name VARCHAR(50) NOT NULL," + "shipping_address VARCHAR(200) NOT NULL,"
					+ "credit_card_no CHAR(19)," + "CONSTRAINT PRIMARY KEY (customer_id))";

			String ordersSql = "Create table orders" + "(order_id CHAR(8)," + "o_date DATE," + "shipping_status CHAR,"
					+ "charge INTEGER," + "customer_id VARCHAR(10) NOT NULL," + "CONSTRAINT PRIMARY KEY (order_id),"
					+ "FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE NO ACTION,"
					+ "CHECK (charge >=0) , CHECK (shipping_status = 'Y' || shipping_status = 'N'))";

			String orderingSql = "Create table ordering" + "(order_id CHAR(8) NOT NULL," + "ISBN CHAR(13),"
					+ "quantity INTEGER," + "CONSTRAINT PRIMARY KEY (order_id , ISBN),"
					+ "FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE NO ACTION,"
					+ "FOREIGN KEY (ISBN) REFERENCES book(ISBN)," + "CHECK (quantity>=0))";

			String bookauthorSql = "Create table book_author" + "(ISBN CHAR(13) NOT NULL,"
					+ "author_name VARCHAR(50) NOT NULL," + "CONSTRAINT PRIMARY KEY (ISBN , author_name),"
					+ "FOREIGN KEY (ISBN) REFERENCES book(ISBN) ON DELETE NO ACTION)";

			stmt.executeUpdate(bookSql);
			stmt.executeUpdate(customerSql);
			stmt.executeUpdate(ordersSql);
			stmt.executeUpdate(orderingSql);
			stmt.executeUpdate(bookauthorSql);

			System.out.println("Done! All Tables are created!");

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("[Error]: All tables have been created. Please try deleting all the tables first.");
		}
	}

	public void DeleteTable() {
		try {
			Statement stmt = con.createStatement();
			String delBook = "drop table if exists book";
			String delCustomer = "drop table if exists customer";
			String delOrders = "drop table if exists orders";
			String delOrdering = "drop table if exists ordering";
			String delBookAuthor = "drop table if exists book_author";
			
			stmt.executeUpdate(delOrdering);
			stmt.executeUpdate(delBookAuthor);
			stmt.executeUpdate(delOrders);
			stmt.executeUpdate(delBook);
			stmt.executeUpdate(delCustomer);

			System.out.println("Done! All Tables are deleted.");

		} catch (SQLException se) {
			System.out.println("[Error]: Delete tables error.");
			se.printStackTrace();
		}
	}

	public void InsertData() {
		Scanner reader = new Scanner(System.in);
		try {
			System.out.println("Please the Path of folder containing the data");
			String path = reader.nextLine().replace("\n", "");
			Statement stmt = con.createStatement();

			String bookpath = path + "/book.txt";
			String customerpath = path + "/customer.txt";
			String orderspath = path + "/orders.txt";
			String orderingpath = path + "/ordering.txt";
			String bookauthorpath = path + "book_author.txt";

			String insertBookSql = "LOAD DATA LOCAL INFILE \'" + bookpath + "\'" + " INTO TABLE book"
					+ " FIELDS TERMINATED BY \',\'" + " LINES TERMINATED BY \'\\n\'"
					+ " (ISBN , title , unit_price , no_of_copies)";

			String insertCustomerSql = "LOAD DATA LOCAL INFILE \'" + customerpath + "\'" + " INTO TABLE customer"
					+ " FIELDS TERMINATED BY \',\'" + " LINES TERMINATED BY \'\\n\'"
					+ " (custiomer_id , name , shipping_address , credit_card_no)";

			String insertOrdersSql = "LOAD DATA LOCAL INFILE \'" + orderspath + "\'" + " INTO TABLE orders"
					+ " FIELDS TERMINATED BY \',\'" + " LINES TERMINATED BY \'\\n\'"
					+ " (order_id , o_date , shipping_status , charge , customer_id)";

			String insertOrderingSql = "LOAD DATA LOCAL INFILE \'" + orderingpath + "\'" + " INTO TABLE ordering"
					+ " FIELDS TERMINATED BY \',\'" + " LINES TERMINATED BY \'\\n\'" + " (order_id , ISBN , quantity)";

			String insertBookAuthorSql = "LOAD DATA LOCAL INFILE \'" + bookauthorpath + "\'" + " INTO TABLE book_author"
					+ " FIELDS TERMINATED BY \',\'" + " LINES TERMINATED BY \'\\n\'" + " (ISBN , author_name)";

			stmt.executeUpdate(insertBookSql);
			stmt.executeUpdate(insertCustomerSql);
			stmt.executeUpdate(insertOrdersSql);
			stmt.executeUpdate(insertOrderingSql);
			stmt.executeUpdate(insertBookAuthorSql);

			System.out.println("The Data is loaded Succussfully");
			reader.close();

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("[Error] Insert Data failed! Please try again.");
		}
	}

	public void SetSystemDate() throws ParseException {
		Date OrderDate = new Date();
		Date inputDate = new Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		boolean flag = false;
		try {
			Statement stmt = con.createStatement();

			String query = "SELECT max(o_date) order_date," + "FROM orders";

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				OrderDate = rs.getDate("order_date");

			}

		} catch (Exception e) {
			System.out.println("Record reading failed. Please try again.");
		}
		do {
			flag = false;
			System.out.print("Please Input the date (YYYYMMDD) : ");
			try {
				Scanner s = new Scanner(System.in);
				inputDate = ft.parse(s.nextLine());
				s.close();
			} catch (Exception e) {
				System.out.println("Invaild Format! Please Try again!");
				flag = true;
			}

			if (inputDate.before(Main.system_time)) {
				System.out.println("Invaild input : Input Date eariler than the Original Date");
				flag = true;
			} else if (OrderDate.after(inputDate)) {

				System.out.println("Invaild input : Input Date eariler than the Latest Order Date");
				flag = true;

			}
		} while (flag);

		Main.system_time = inputDate;

		System.out.println("Today is " + Main.system_time);

	}

	public void CommandLineInterface() throws ParseException {
		int choice = -1;
		while (true) {
			System.out.println("<This is the system interface.>");
			System.out.println("-------------------------------");
			System.out.println("1. Create Table.");
			System.out.println("2. Delete Table.");
			System.out.println("3. Insert Data.");
			System.out.println("4. Set System Date.");
			System.out.println("5. Back to main menu.");

			System.out.println("Please enter your choice??..");
			choice = checker.IntegerChecker(1, 5);

			if (choice == 1) {
				CreateTable();
			} else if (choice == 2) {
				DeleteTable();
			} else if (choice == 3) {
				InsertData();
			} else if (choice == 4) {
				SetSystemDate();
			} else {
				System.out.println("See you next time. Bye Bye.");
				return;
			}
		}

	}
}
