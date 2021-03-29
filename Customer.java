package Main;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Date;
import java.text.*;

public class Customer {
	StringIntegerChecker menuchecker;
    static Connection con = Main.con;
    
    public void BookSearch() {
	    int choice = -1;
        loop: while (true) {
        System.out.println("What do u want to search??");
        System.out.println("1. ISBN");
        System.out.println("2. Book Title");
        System.out.println("3. Author Name");
        System.out.println("4. Exit");

        choice = menuchecker.CustomerIntegerChecker();
        switch(choice) {
            case 1: SearchISBN();
                    break;
            case 2: SearchBookTitle();
                    break;
            case 3: SearchAuthorName();
                    break;
            case 4: System.out.println("Back to Main Page.");
                    return;
            
        }
    }
    	
    }
    
    public void SearchISBN(){
	    String ISBN = "";    
	    
	    try {
	    	ISBN = menuchecker.ISBNchecker();
	    	String psql1 = "SELECT * FROM book  WHERE ISBN = ? ";
	    	PreparedStatement pstmt = con.prepareStatement(psql1);
	    	pstmt.setString(1, ISBN);
	    	ResultSet resultSet1 = pstmt.executeQuery();	    	
	    	
	    	String psql2 = "SELECT author_name FROM book_author  WHERE ISBN = ? ORDER BY author_name ASC";
	    	pstmt = con.prepareStatement(psql2);
	    	pstmt.setString(1, ISBN);
	    	ResultSet resultSet2 = pstmt.executeQuery();

	    	
		     System.out.println("Here are the Search Result.");
		     System.out.println("Book Title : " + resultSet1.getString("title"));
		     System.out.println("ISBN : " + ISBN);
		     System.out.println("Unit Price : " + resultSet1.getInt("unit_price"));
		     System.out.println("No of Copies Available : " + resultSet1.getInt("no_of_copies"));
		     System.out.println("Author Name : ");
		    	while (resultSet2.next()) {
		    		System.out.println(resultSet2.getString("author_name"));
		    	}
	    	
	    	
	    } catch (SQLException se) {
	    	se.printStackTrace();
			System.out.println("[Error]: ISBN Searching failed.");
	    }
	     
   }
    
    public void SearchBookTitle() {
	    String BookTitle = "";
	    String ISBN = "";
	    
	    try {
	    	BookTitle = menuchecker.BookTitlechecker();
	    	String psql = "SELECT ISBN , title , unit_price , no_of_copies FROM book  WHERE title = ? ORDER BY title ASC , ISBN ASC ";
	    	PreparedStatement pstmt = con.prepareStatement(psql);
	    	pstmt.setString(1, BookTitle);
	    	ResultSet resultSet = pstmt.executeQuery();
	    	System.out.println("Here are the searching result:");
	    	while (resultSet.next()) {
	    		System.out.println("Book Title : " + BookTitle);
		    	System.out.println("ISBN : " + resultSet.getString("ISBN"));
		    	System.out.println("Unit Price : "+resultSet.getInt("unit_price"));
		    	System.out.println("No of Copies Available : " + resultSet.getInt("no_of_copies"));
		    	
		    	System.out.println("Author Name : ");
		    	ISBN = resultSet.getString("ISBN");
		    	String psql2 = "SELECT author_name FROM book_author  WHERE ISBN = ? ORDER BY author_name ASC";
		    	pstmt = con.prepareStatement(psql2);
		    	pstmt.setString(1, ISBN);
		    	ResultSet resultSet2 = pstmt.executeQuery();
		    	while(resultSet2.next()) {
		    		System.out.println(resultSet2.getString("author_name"));
		    	}
	    		
	    	}
	    	
	    	
	    }catch(SQLException se) {
	    	se.printStackTrace();
	    	System.out.println("[Error] Book Title Search failed.");
	    }
    	
    }
    
    public void SearchAuthorName() {
    	String AuthorName = null;
    	
    	try {
    		AuthorName = menuchecker.AuthorNamechecker();
	    	String psql = "SELECT * FROM book, book_author  WHERE title = ? ORDER BY title ASC , ISBN ASC , author_name ASC ";
	    	PreparedStatement pstmt = con.prepareStatement(psql);
	    	pstmt.setString(1, AuthorName);
	    	ResultSet resultSet = pstmt.executeQuery();
	    	System.out.println("Here are the searching result:");
	    	while (resultSet.next()) {
	    		System.out.println("Book Title : " + resultSet.getString("title"));
		    	System.out.println("ISBN : " + resultSet.getString("ISBN"));
		    	System.out.println("Unit Price : "+resultSet.getInt("unit_price"));
		    	System.out.println("No of Copies Available : " + resultSet.getInt("no_of_copies"));
		    	System.out.println("Author Name : " + AuthorName);
	    	}
    		
    		
    		
    		
    		
    	}catch(SQLException se) {
    		se.printStackTrace();
    		System.out.println("[Error] Author Name Search failed.");
    	}
    	
    }
    
    public void OrderCreation() {
    	
    }
    
    public void OrderAltering() {
    	
    }
    
    public void OrderQuery() throws ParseException {
    	String customer_id = "";
    	String order_id = "";
    	Date year = new Date() ;
        String pattern = "yyyy";
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
    	
    	try {
    		customer_id = menuchecker.CustomerIDchecker();
    		year = ft.parse(menuchecker.yearchecker());
    		String psql = "SELECT order_id , o_date , charge , shipping_address FROM orders WHERE customer_id = ? AND year(o_date) = ? ORDER BY order_id ASC ";
	    	PreparedStatement pstmt = con.prepareStatement(psql);
	    	pstmt.setString(1, customer_id);
	    	pstmt.setDate(2, (java.sql.Date) year);
	    	ResultSet resultSet = pstmt.executeQuery();
	    	while(resultSet.next()) {
	    	System.out.println("Here are the searching result:");
	    	System.out.println("Order ID : " + resultSet.getString("order_id"));
	    	System.out.println("Order Date : " + resultSet.getDate("o_date"));
	    	
	    	order_id = resultSet.getString("order_id");
    		String psql2 = "SELECT title  FROM  ordering, book WHERE order_id = ? ORDER BY order_id ASC ";
	        pstmt = con.prepareStatement(psql2);
	    	pstmt.setString(1, order_id);
	    	ResultSet resultSet2 = pstmt.executeQuery();
	    	System.out.println("Books Ordered : " );
	    	while (resultSet2.next()){
	    		System.out.println(resultSet2.getString("title"));
	    	}
	    	
	    	System.out.println("Charge : " + resultSet.getInt("charge"));
	    	System.out.println("Shipping Status : " + resultSet.getString("shipping_address"));
	    	
	    	}
    	}catch (SQLException se) {
    		se.printStackTrace();
    		System.out.println("[Error] Order Query failed.");
    	}
    	
    	
    	
    }
    
    public void CommandLineInterface() throws ParseException {
	    int choice = -1;
        loop: while (true) {
        System.out.println("<This is the customer interface.>");
        System.out.println("-------------------------------");
        System.out.println("1. Book Search.");
        System.out.println("2. Order Creation.");
        System.out.println("3. Order Altering.");
        System.out.println("4. Order Query.");
        System.out.println("5. Back to main menu.");

        choice = menuchecker.CustomerIntegerChecker();
        switch(choice) {
            case 1: BookSearch();
                    break;
            case 2: OrderCreation();
                    break;
            case 3: OrderAltering();
                    break;
            case 4: OrderQuery();
                    break;
            case 5: System.out.println("Thank you. See you next time. Bye Bye.");
                    return;
            
        }
    }
	
}

}
