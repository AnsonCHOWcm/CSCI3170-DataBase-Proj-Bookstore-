package Main;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Date;
import java.text.*;

public class Customer {
	StringIntegerChecker menuchecker;
    static Connection con = Main.con;
    
    public Customer() throws ParseException {
		
		menuchecker = new StringIntegerChecker();
	}
    
    public void BookSearch() {
	    int choice = -1;
        while (true) {
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
		     int number = 1;
		    	while (resultSet2.next()) {
		    		System.out.println(number + " :" + resultSet2.getString("author_name"));
		    		number++;
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
		    	
		    	int number = 1;
		    	System.out.println("Author Name : ");
		    	ISBN = resultSet.getString("ISBN");
		    	String psql2 = "SELECT author_name FROM book_author  WHERE ISBN = ? ORDER BY author_name ASC";
		    	pstmt = con.prepareStatement(psql2);
		    	pstmt.setString(1, ISBN);
		    	ResultSet resultSet2 = pstmt.executeQuery();
		    	while(resultSet2.next()) {
		    		System.out.println(number + " :" + resultSet2.getString("author_name"));
		    		number++;
		    	}
	    		
	    	}
	    	
	    	
	    }catch(SQLException se) {
	    	se.printStackTrace();
	    	System.out.println("[Error] Book Title Search failed.");
	    }
    	
    }
    
    public void SearchAuthorName() {
    	String AuthorName = "";
    	
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
    	String CID = "";
    	int OID = 0;
    	Date OrderDate = Main.system_time;
    	char shipping_status = 'N' ;
    	int charge = 10 ;
    	String ISBN = "";
    	int quantity = 0 ; 
    	int quantity_avaliable = 0;
    	int price = 0;
    	String exit = "" ; 
    	
    	try {
    		CID = menuchecker.CustomerIDchecker();
    		
    		Statement stmt = con.createStatement();
    		String sql = "SELECT Max(CAST(order_id AS int)) as latest_date FROM orders";
    		ResultSet rs = stmt.executeQuery(sql);
    		while(rs.next()) {
    			OID = rs.getInt("latest_date") + 1;
    		}
    		
    		String psql = "Insert " + 
    		              "into orders (order_id , o_date , shipping_status , charge , customer_id)" +
    				      "Values (CAST(? AS CHAR(8)) , ? , ? , ? ,? )";
    		PreparedStatement pstmt = con.prepareStatement(psql) ; 
    		
    		pstmt.setInt(1, OID);
    		pstmt.setDate(2,(java.sql.Date)OrderDate);
    		pstmt.setString(3,String.valueOf(shipping_status));
    		pstmt.setInt(4,charge);
    		pstmt.setString(5, CID);
    		pstmt.executeUpdate();
    		
    		
    		System.out.println("Please enter ISBN and then the quantity.");
    		System.out.println("You can press \"L\" to see ordered list, or \"F\" to finish ordering");
    		while (true) {
    			ISBN = menuchecker.ISBNchecker();
    			quantity = menuchecker.Quantitychecker();
    			
    			psql = "SELECT unit_price , no_of_copies" +
    			       "FROM book" + 
    				   "WHERE ISBN = ?";
    			pstmt = con.prepareStatement(psql) ;
    			pstmt.setString(1,ISBN);
    			ResultSet rs2 = pstmt.executeQuery();
    			while (rs2.next()) {
    				price = rs2.getInt("unit_price");
    				quantity_avaliable = rs2.getInt("no_of_copies");
    			}
    			
    			if (quantity > quantity_avaliable) {
    				System.out.println("There is not enough books.");
    				continue;
    			}
    			
    			
    	            psql = "Insert " + 
    		               "into ordering (order_id , ISBN ,  quantity)" +
    				       "Values (CAST(? AS CHAR(8)) , ? , ?)";
    		        pstmt = con.prepareStatement(psql) ;
    		        pstmt.setInt(1, OID);
    		        pstmt.setString(2, ISBN);
    		        pstmt.setInt(3, quantity);
    		        pstmt.executeUpdate();
    		        
    		        charge = charge + quantity * 10 + quantity * price;
    		        
    		        psql = "UPDATE book" + 
    		               "SET no_of_copies = no_of_copies - ?" +
    		        	   "WHERE ISBN = ?";
    		        pstmt = con.prepareStatement(psql);
    		        pstmt.setInt(1, quantity);
    		        pstmt.setString(2, ISBN);
    		        pstmt.executeUpdate();
    		        
    		        exit = menuchecker.Exitchecker();
    		        
    		        if(exit == "L") {
    		        	int number = 1 ;
    		        	psql = "SELECT ISBN , quantity" + 
    		                   "FROM ordering" +
    		        		   "WHERE order_id = (CAST(? AS CHAR(8))" ;
    		        	pstmt = con.prepareStatement(psql);
    		        	pstmt.setInt(1, OID);
    		        	rs = pstmt.executeQuery();
    		        	while(rs.next()) {
    		        		System.out.println("Record" + number + " : ");
    		        		System.out.println("ISBN : " + rs.getString("ISBN") + " " + "quantity : " + rs.getInt("quantity") );
    		        		number++ ;
    		        	}
    		        	
    		        	
    		        }else if(exit == "F"){
    		        	break;
    		        }
    			
    		}
    		
    		String psql2 = "UPDATE orders"+
    		               "SET charge = ?" +
    				       "WHERE order_id = (CAST(? AS CHAR(8)))";
    		PreparedStatement pstmt2 = con.prepareStatement(psql2);
    		
    		pstmt2.setInt(1, charge);
    		pstmt2.setInt(2 , OID);
    		pstmt2.executeUpdate();
    		
    		System.out.println("The Order has been created.");
    		
    	}catch( SQLException se ) {
    		se.printStackTrace();
    		System.out.println("[Error] Order Creation failed.");
    	}
    	
    	
    	
    	
    }
    
    public void OrderAltering() {
    	int OID = 0;
    	int quantity_change = 0;
    	String ISBN = "";
    	String action = "" ;
    	String shipping_status = "" ;
    	List<String> BookList = new ArrayList<String>() ;
    	int quantity_remaining = 0;
    	int price = 0 ;
    	int choice = -1 ;
    	
    	try {
    		
    		OID = menuchecker.OrderIDchecker();
    		
    		String psql = "SELECT order_id , shipping_status , charge , customer_id" +
    		              "FROM orders" +
    				      "WHERE order_id = (CAST( ? AS CHAR(8)))";
    		
    		PreparedStatement pstmt = con.prepareStatement(psql);
    		
    		pstmt.setInt(1, OID);
    		
    		ResultSet rs = pstmt.executeQuery();
    		
    		while (rs.next()) {
    			System.out.println("order_id :" + rs.getString("order_id") + " " + "shipping : " + " " + rs.getString("shipping_status") + " " + "charge : " + rs.getInt("charge") + "customerID : " + rs.getString("customer_id") );
    		}
    		
    		psql = "SELECT ISBN , quantity"+
    		              "FROM ordering"+
    				      "where order_id = (CAST(? AS CHAR(8)))";
    		pstmt = con.prepareStatement(psql);
    		
    		pstmt.setInt(1, OID);
    		
    		rs = pstmt.executeQuery();
    		
    		int number = 0 ; 
    		ISBN = "" ;
    		while(rs.next()) {
    			number++;
    			ISBN = rs.getString("ISBN");
    			BookList.add(ISBN);
    			System.out.println("book no:" + number + "ISBN : " + ISBN + " " + "Quantity : " + rs.getInt("quantity"));
    		}
    		
    		choice = menuchecker.choicechecker(number);
    		
    		ISBN = BookList.get(choice);
    		
    		action = menuchecker.Actionchecker();
    		
    		if (action == "A") {
    			
    			quantity_change = menuchecker.QuantityAddchecker();
    			
    			psql = "SELECT shipping_status" + 
    			       "FROM orders" +
    				   "WHERE order_id = (CAST(? AS CHAR(8)))";
    			
    			pstmt = con.prepareStatement(psql);
    			
    			pstmt.setInt(1, OID);
    			
    			rs = pstmt.executeQuery();
    			
    			while(rs.next()) {
    				shipping_status = rs.getString("shipping_status");
    			}
    			
    			psql = "SELECT no_of_copies" + 
    			       "FROM book" +
    				   "WHERE ISBN = ? ";
    			
    			pstmt = con.prepareStatement(psql);
    			
    			pstmt.setString(1, ISBN);
    			
                rs = pstmt.executeQuery();
    			
    			while(rs.next()) {
    				quantity_remaining = rs.getInt("no_of_copies");
    			}
    			
    			
    			if (shipping_status == "Y" ) {
    				System.out.println("The books in the order are shipped.");
    			}else if(quantity_remaining < quantity_change) {
    				System.out.println("There is not enough books.");
    			}else {
    				psql = "UPDATE book" + 
    			           "SET no_of_copies = ?" +
    					   "WHERE ISBN = ?" ;
    				
    				pstmt = con.prepareStatement(psql);
    				
    				pstmt.setInt(1, quantity_remaining - quantity_change);
    				pstmt.setString(2, ISBN);
    				
    				pstmt.executeUpdate();
    				
    				psql = "UPDATE ordering" + 
     			           "SET quantity = quantity + ?" +
     					   "WHERE ISBN = ?" ;
     				
     				pstmt = con.prepareStatement(psql);
     				
     				pstmt.setInt(1, quantity_change);
     				pstmt.setString(2, ISBN);
     				
     				pstmt.executeUpdate();
     				
     				psql = "SELECT unit_price"+
  				           "FROM book" +
  				    	   "WHERE ISBN = ?" ;
  				           
  				    PreparedStatement pstmt4 = con.prepareStatement(psql);
  				    
  				    pstmt4.setString(1, ISBN);
  				    
  				    ResultSet rs4 = pstmt4.executeQuery();
  				    
  				    while(rs4.next()) {
  				    	price = rs4.getInt("unit_price");
  				    }
  				    
  				    
  				    psql = "UPDATE orders" + 
  				           "SET o_date = ? , charge = charge + ? " + 
  				    	   "WHERE order_id = (CAST(? AS CHAR(8)))" ;
  				    
  				    PreparedStatement pstmt3 = con.prepareStatement(psql);
  				    
  				    pstmt3.setDate(1,(java.sql.Date) Main.system_time);
  				    pstmt3.setInt(2, quantity_change*(10+price));
  				    pstmt3.setInt(3, OID);
    				
  		    		System.out.println("The Update has been made.");
    				
    			}
    			
    			System.out.println("Updated order infomation :  ");
    			
    			psql = "SELECT order_id , shipping_status , charge , customer_id" +
  		              "FROM orders" +
  				      "WHERE order_id = (CAST( ? AS CHAR(8)))";
  		
  		        PreparedStatement pstmt5 = con.prepareStatement(psql);
  		
  		        pstmt5.setInt(1, OID);
  		
  		        ResultSet rs5 = pstmt.executeQuery();
  		
  		        while (rs5.next()) {
  			           System.out.println("order_id :" + rs5.getString("order_id") + " " + "shipping : " + " " + rs5.getString("shipping_status") + " " + "charge : " + rs5.getInt("charge") + "customerID : " + rs5.getString("customer_id") );
  		        }
  		
  		         psql = "SELECT ISBN , quantity"+
  		                "FROM ordering"+
  				        "where order_id = (CAST(? AS CHAR(8))) , ISBN = ?";
  		         pstmt5 = con.prepareStatement(psql);
  		
  		         pstmt5.setInt(1, OID);
  		         pstmt5.setString(2, ISBN);
  		
  		         rs5 = pstmt.executeQuery();
  		         
  		         while (rs5.next()) {
  		        	 System.out.println("book no :" + choice + "ISBN :" + rs5.getString("ISBN") + "quantity : " + rs5.getInt("quantity"));
  		         }
    			
    		     }else if (action == "D"){
    			
                  quantity_change = menuchecker.QuantityDelchecker();
    			
    			  psql = "SELECT shipping_status" + 
    			           "FROM orders" +
    				       "WHERE order_id = (CAST(? AS CHAR(8)))";
    			
    			  PreparedStatement pstmt2 = con.prepareStatement(psql);
    			
    			  pstmt2.setInt(1, OID);
    			
    			  ResultSet rs2 = pstmt.executeQuery();
    			
    			  while(rs2.next()) {
    				    shipping_status = rs2.getString("shipping_status");
    			    }
    			
    			  psql = "SELECT quantity" + 
    			            "FROM ordering" +
    				        "WHERE ISBN = ? ";
    			
    			  pstmt2 = con.prepareStatement(psql);
    			
    			  pstmt2.setString(1, ISBN);
    			
                  rs2 = pstmt2.executeQuery();
    			
    			  while(rs2.next()) {
    				       quantity_remaining = rs2.getInt("quantity");
    			    }
    			
    			
    			  if (shipping_status == "Y" ) {
    				    System.out.println("The books in the order are shipped.");
    			  }else if(quantity_remaining < quantity_change) {
    				    System.out.println("Too many to be deleted.");
    			  }else {
    				    psql = "UPDATE book" + 
    			               "SET no_of_copies = no_of_copies + ?" +
    					       "WHERE ISBN = ?" ;
    				
    				    PreparedStatement pstmt3 = con.prepareStatement(psql);
    				
    				    pstmt3.setInt(1, quantity_change);
    				    pstmt3.setString(2, ISBN);
    				
    				    pstmt3.executeUpdate();
    				
    				    psql = "UPDATE ordering" + 
     			               "SET quantity = quantity - ?" +
     					       "WHERE ISBN = ?" ;
     				
     				    pstmt3 = con.prepareStatement(psql);
     				
     				    pstmt3.setInt(1, quantity_change);
     				    pstmt3.setString(2, ISBN);
     				
     				    pstmt3.executeUpdate();
     				    
     				    psql = "SELECT unit_price"+
     				           "FROM book" +
     				    	   "WHERE ISBN = ?" ;
     				           
     				    PreparedStatement pstmt4 = con.prepareStatement(psql);
     				    
     				    pstmt4.setString(1, ISBN);
     				    
     				    ResultSet rs4 = pstmt4.executeQuery();
     				    
     				    while(rs4.next()) {
     				    	price = rs4.getInt("unit_price");
     				    }
     				    
     				    
     				    psql = "UPDATE orders" + 
     				           "SET o_date = ? , charge = charge - ? " + 
     				    	   "WHERE order_id = (CAST(? AS CHAR(8)))" ;
     				    
     				    pstmt3 = con.prepareStatement(psql);
     				    
     				    pstmt3.setDate(1,(java.sql.Date) Main.system_time);
     				    pstmt3.setInt(2, quantity_change*(10+price));
     				    
     				    pstmt3.executeUpdate();

     		    		System.out.println("The Update has been made.");
     				    
     				
    			}
    		
    			  System.out.println("Updated order infomation :  ");
      			
      			   psql = "SELECT order_id , shipping_status , charge , customer_id" +
    		              "FROM orders" +
    				      "WHERE order_id = (CAST( ? AS CHAR(8)))";
    		
    		        PreparedStatement pstmt5 = con.prepareStatement(psql);
    		
    		        pstmt5.setInt(1, OID);
    		
    		        ResultSet rs5 = pstmt.executeQuery();
    		
    		        while (rs5.next()) {
    			           System.out.println("order_id :" + rs5.getString("order_id") + " " + "shipping : " + " " + rs5.getString("shipping_status") + " " + "charge : " + rs5.getInt("charge") + "customerID : " + rs5.getString("customer_id") );
    		        }
    		
    		         psql = "SELECT ISBN , quantity"+
    		                "FROM ordering"+
    				        "where order_id = (CAST(? AS CHAR(8))) AND ISBN = ?";
    		         pstmt5 = con.prepareStatement(psql);
    		
    		         pstmt5.setInt(1, OID);
    		         pstmt5.setString(2, ISBN);
    		
    		         rs5 = pstmt.executeQuery();
    		         
    		         while (rs5.next()) {
    		        	 System.out.println("book no :" + choice + "ISBN :" + rs5.getString("ISBN") + "quantity : " + rs5.getInt("quantity"));
    		         }  
    			  
    		}
    		

    		
    	}catch(SQLException se) {
    		se.printStackTrace();
    		System.out.println("[Error] Order Altering failed");
    	}	
    }
    
    public void OrderQuery() throws ParseException {
    	String customer_id = "";
    	String order_id = "";
    	Date year = new Date() ;
        String pattern = "yyyy";
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		int number = 0 ;
    	
    	try {
    		customer_id = menuchecker.CustomerIDchecker();
    		year = ft.parse(menuchecker.yearchecker());
    		String psql = "SELECT order_id , o_date , charge , shipping_address FROM orders WHERE customer_id = ? AND year(o_date) = ? ORDER BY order_id ASC ";
	    	PreparedStatement pstmt = con.prepareStatement(psql);
	    	pstmt.setString(1, customer_id);
	    	pstmt.setDate(2, (java.sql.Date) year);
	    	ResultSet resultSet = pstmt.executeQuery();
	    	while(resultSet.next()) {
	    	number++;
	    	System.out.println("Record : " + number);
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
	    	
	    	System.out.println("All the orders have been printed.");
	    	
	    	}
    	}catch (SQLException se) {
    		se.printStackTrace();
    		System.out.println("[Error] Order Query failed.");
    	}
    	
    	
    	
    }
    
    public void CommandLineInterface() throws ParseException {
	    int choice = -1;
        while (true) {
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
