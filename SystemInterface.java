package Main;

import java.sql.*;
import java.util.*;
import java.text.*;

public class SystemInterface {
	java.util.Date t;
	StringIntegerChecker menuchecker;
    static Connection con = Main.con;
	
	public SystemInterface() throws ParseException {
        String pattern = "yyyy-MM-dd";
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		t = ft.parse("0000-00-00");
		
		menuchecker = new StringIntegerChecker(1,5);
	}
	
	public void CreateTable() {
		try {
			Statement stmt = con.createStatement();
			String book = "Create table book" +
			              "ISBN CHAR(13)" +
					      "title CHAR(100)"+
					      "unit_price integer"+
					      "no_of_copies integer"+
		}
	}
	
	
	
    public void CommandLineInterface() {
	    int choice = -1;
        loop: while (true) {
        System.out.println("<This is the system interface.>");
        System.out.println("-------------------------------");
        System.out.println("1. Create Table.");
        System.out.println("2. Delete Table.");
        System.out.println("3. Insert Data.");
        System.out.println("4. Set System Date.");
        System.out.println("5. Back to main menu.");

        choice = menuchecker.IntegerChecker();
        switch(choice) {
            case 1: CreateTable();
                    break;
            case 2: DeleteTable();
                    break;
            case 3: InsertData();
                    break;
            case 4: SetSystemDate();
                    break;
            case 5: System.out.println("See you next time. Bye Bye.");
                    return;
            
        }
    }
	
}
}
