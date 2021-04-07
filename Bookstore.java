import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bookstore {
	Checker menuchecker;

	public Bookstore() {
		menuchecker = new Checker();
	}

	public void CommandLineInterface() throws ParseException {
		int choice = -1;
		while (true) {
			System.out.println("<This is the bookstore interface.>");
			System.out.println("------------------------------------");
			System.out.println("1. Order Update.");
			System.out.println("2. Order Query.");
			System.out.println("3. N Most Popular Book Query.");
			System.out.println("4. Back to main menu.");

			System.out.println("Your choice?...");
			choice = Checker.IntegerChecker(1, 4);

			if (choice == 1) {
				OrderUpdate();
			} else if (choice == 2) {
				OrderQuery();
			} else if (choice == 3) {
				NMostPopular();
			} else {
				System.out.println("Thank you. See you next time. Bye Bye.");
				return;
			}
		}

	}

	public void OrderUpdate() {
		int oid;
		int number_of_books;
		String shipping_status;
		String order_id;
		Connection con = DataBaseController.connectToSQL();

		try {
			oid = Checker.OrderIDchecker();
			String sql = "SELECT shipping_status , order_id FROM orders WHERE order_id = ?";
			ArrayList<String> sqlParms = new ArrayList<String>();
    		sqlParms.add(String.valueOf(oid));

			ResultSet rs = DataBaseController.runSQL(sql, sqlParms);
			rs.next();
			shipping_status = rs.getString("shipping_status");
			order_id = rs.getString("order_id");

			if (shipping_status.equals("Y")) {
				System.out.println("The order has been shipped");
			} else {
				sql = "SELECT COUNT(*) as number FROM ordering WHERE order_id = ? AND quantity > 0";
				ResultSet rs2 = DataBaseController.runSQL(sql, sqlParms);
				rs2.next();
				number_of_books = rs2.getInt("number");

				if (number_of_books == 0) {
					System.out.println("There is no any book in the order. Please send a reminder to the user");
					return;
				}

				System.out.println("the Shipping status of " + order_id + " is " + shipping_status + "and"
						+ number_of_books + "books ordered");

				String UserResponds = Checker.UserRespondchecker();

				if (UserResponds.equals("Y")) {
					sql = "UPDATE orders SET shipping_status = Y WHERE order_id = ?";
					DataBaseController.runSQL(sql, sqlParms);

					System.out.println("The Update has been made");
				}
			}
		} catch (SQLException e) {
            System.out.println(e);
        }
	}

	public void OrderQuery() throws ParseException {
		java.util.Date Date = new java.util.Date();
		String pattern = "yyyy-MM";
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		int total_charge = 0;
		Connection con = DataBaseController.connectToSQL();

		try {
			Date = ft.parse(menuchecker.monthchecker());
			int month = Date.getMonth() + 1;
			int year = Date.getYear() + 1900;
			String psql = "SELECT SUM(charge) sum" + "FROM orders"
					+ "WHERE shipping_status = 'Y' AND month(o_date) = ? AND year(o_date) = ?";

			PreparedStatement pstmt = con.prepareStatement(psql);

			pstmt.setInt(1, month);
			pstmt.setInt(2, year);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				total_charge = rs.getInt("sum");
			}

			String psql2 = "SELECT order_id, customer_id,o_date,charge" + "FROM orders"
					+ "WHERE shipping_status = 'Y' AND month(o_date) = ? AND year(o_date) = ?";

			PreparedStatement pstmt2 = con.prepareStatement(psql2);

			pstmt.setInt(1, month);
			pstmt.setInt(2, year);

			ResultSet rs2 = pstmt2.executeQuery();
			int number = 0;
			while (rs2.next()) {
				number++;
				System.out.println("Record : " + number);
				System.out.println("order_id : " + rs2.getString("order_id"));
				System.out.println("customer_id : " + rs2.getString("customer_id"));
				System.out.println("date : " + rs2.getDate("o_date"));
				System.out.println("charge : " + rs2.getInt("charge"));
			}

			System.out.println("Total charge of the month is " + total_charge);
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("[Error] Order Query failed.");
		}

	}

	public void NMostPopular() {
		int N = -1;
		Scanner reader = new Scanner(System.in);
		Connection con = DataBaseController.connectToSQL();

		try {
			System.out.println("Please input the N popular book number : ");
			N = reader.nextInt();

			String psql = "SELECT ISBN , title , SUM(quantity) AS total" + "FROM book , ordering"
					+ "GROUP BY ISBN , title" + "ORDER BY total DESC , ISBN , title ASC LIMIT ?";

			PreparedStatement pstmt = con.prepareStatement(psql);

			pstmt.setInt(1, N);

			ResultSet rs = pstmt.executeQuery();

			System.out.println("ISBN             Title             Copies");

			while (rs.next()) {
				System.out.print(rs.getString("ISBN"));
				System.out.print("    ");
				System.out.print(rs.getString("title"));
				System.out.print("    ");
				System.out.println(rs.getInt("total"));
			}
			reader.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("[Error] Order Update failed.");
		}

	}

}
