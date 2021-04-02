package Main;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bookstore {
	StringIntegerChecker menuchecker;
    static Connection con = Main.con;
    
    public Bookstore() {
    	menuchecker = new StringIntegerChecker();
    }
    
    public void CommandLineInterface() {
    	int choice = -1;
        while (true) {
        System.out.println("<This is the bookstore interface.>");
        System.out.println("------------------------------------");
        System.out.println("1. Order Update.");
        System.out.println("2. Order Query.");
        System.out.println("3. N Most Popular Book Query.");
        System.out.println("4. Back to main menu.");

        choice = menuchecker.BookStoreIntegerChecker();
        switch(choice) {
            case 1: OrderUpdate();
                    break;
            case 2: OrderQuery();
                    break;
            case 3: NMostPopular();
                    break;
            case 4: System.out.println("Thank you. See you next time. Bye Bye.");
                    return;
            
        }
    }
    	
    }
    
    public void OrderUpdate() {
    	int OID = 0 ;
    	int number_of_books = 0;
    	String shipping_status = "";
    	String order_id = "" ;
    	
    	try {
    		OID = menuchecker.OrderIDchecker();
    		String psql = "SELECT shipping_status , order_id" + 
    		              "FROM orders" +
    				      "WHERE order_id = (CAST(? AS CHAR(8)))";
    		PreparedStatement pstmt = con.prepareStatement(psql);
    		pstmt.setInt(1, OID);
    		ResultSet rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			shipping_status = rs.getString("shipping_status");
    			order_id = rs.getString("order_id");
    		}
    		
    		if (shipping_status == "Y") {
    			System.out.println("The order has been shipped");
    		}else {
    			
    			String psql2 = "SELECT COUNT(*) as number" + 
    			               "FROM ordering" +
    					       "WHERE order_id = (CAST(? AS CHAR(8))) AND quantity > 0";
    			PreparedStatement pstmt2 = con.prepareStatement(psql2);
    			pstmt2.setInt(1, OID);
    			ResultSet rs2 = pstmt2.executeQuery();
    			
    			while(rs2.next()) {
    				number_of_books = rs2.getInt("number");
    			}
    			
    			if (number_of_books == 0) {
    				System.out.println("There is no any book in the order. Please send a reminder to the user");
    				return;
    			}
    			
    			System.out.println("the Shipping status of " + order_id + " is " + shipping_status + "and" + number_of_books + "books ordered");
    			String UserResponds = menuchecker.UserRespondchecker();
    			
    			if(UserResponds == "Y") {
    				String sql = "UPDATE orders" + 
    			                     "SET shipping_status = Y" +
    						         "WHERE order_id = (CAST(? AS CHAR(8)))" ;
    				Statement stmt = con.createStatement();
    				stmt.executeUpdate(sql);
    				
    				System.out.println("The Update has been made");
    				
    			}else {
    				return;
    			}
    			
    			
    		}
    		
    		
    	}catch(SQLException se) {
    		se.printStackTrace();
    		System.out.println("[Error] Order Update failed.");
    	}
    	
    	
    	
    }
    
    public void OrderQuery() throws ParseException {
    	java.util.Date month = new java.util.Date();
    	String pattern = "yyyy-MM";
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		int total_charge = 0;
		
		try {
			month = ft.parse(menuchecker.monthchecker());
			String psql = "SELECT SUM(charge)"+
			              "FROM orders" +
					      "WHERE shipping_status = 'Y' AND month(o_date) = ? AND year(o_date) = ?";
			PreparedStatement pstmt = con.prepareStatement(psql);
			
			pstmt.setDate(1,);
			pstmt.setDate(2,);
			
			
			
			
		}catch (SQLException se) {
			se.printStackTrace();
    		System.out.println("[Error] Order Update failed.");
		}
    	
    	
    	
    }
    
    public void NMostPopular() {
    	
    }

}
