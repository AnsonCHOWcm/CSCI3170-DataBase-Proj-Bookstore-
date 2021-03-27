package Main;

import java.util.*;
import java.text.*;

public class Date {
	Date t;
	
	public Date() {
	
		
	}
	
	public static void main(String args[]) {
	      Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy-MM-dd");

	      System.out.println("Current Date: " + ft.format(dNow));
	   }

}
