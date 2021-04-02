package Main;

import java.util.*;

public class StringIntegerChecker {
	Scanner reader;

public StringIntegerChecker() {
}

public int SystemIntegerChecker() {
	String num;
	int input;
	while(true) {
	System.out.println("Please enter your choice??..");
	reader = new Scanner(System.in);
	num = reader.nextLine().replace("\n", "");
	input = Integer.parseInt(num);
	try{
		if (input < 1 || input > 5) {
			System.out.println("[Error] Invaild Input");
		}else {
			break;
		}
	 }catch (NumberFormatException e){
		 System.out.println("[Error] Invalid Input");
	}
	}
	
	
	return input;
}

public int CustomerIntegerChecker() {
	String num;
	int input;
	while(true) {
	System.out.println("Your choice?...");
	reader = new Scanner(System.in);
	num = reader.nextLine().replace("\n", "");
	input = Integer.parseInt(num);
	try{
		if (input < 1 || input > 5) {
			System.out.println("[Error] Invaild Input");
		}else {
			break;
		}
	 }catch (NumberFormatException e){
		 System.out.println("[Error] Invalid Input");
	}
	}
	
	
	return input;
}

public int BookStoreIntegerChecker() {
	int input ;
	while(true) {
		System.out.println("Your choice?...");
		reader = new Scanner(System.in);
		input = reader.nextInt();
		try{
			if (input < 1 || input > 4) {
				System.out.println("[Error] Invaild Input");
			}else {
				break;
			}
		 }catch (NumberFormatException e){
			 System.out.println("[Error] Invalid Input");
				
		
	}
}
	
	return input ; 
}

public String ISBNchecker() {
	String input ="" ;
	while(true) {
	System.out.println("Please enter ISBN : ");
	reader = new Scanner(System.in);
	input = reader.nextLine().replace("\n", "");
	try{
		if (input.matches("\\d{1}-\\d{4}-\\d{4}-\\d{1}")) {
			System.out.println("[Error] Input does not follow the format of ISBN. Please try again.");
		}else {
			break;
		}
	 }catch (NumberFormatException e){
		 System.out.println("[Error] Invalid Input");
	}
	}
	
	
	
	return input;
}

public String ISBNAlterchecker() {
	String input ="" ;
	while(true) {
	System.out.println("Please enter the ISBN of book that you want to adjust: ");
	reader = new Scanner(System.in);
	input = reader.nextLine().replace("\n", "");
	try{
		if (!(input.matches("\\d{1}-\\d{4}-\\d{4}-\\d{1}"))) {
			System.out.println("[Error] Input does not follow the format of ISBN. Please try again.");
		}else {
			break;
		}
	 }catch (NumberFormatException e){
		 System.out.println("[Error] Invalid Input");
	}
	}
	
	
	
	return input;
}

public String BookTitlechecker() {
	String input = "" ; 
	while(true) {
	System.out.println("Please enter The Book Title : ");
	reader = new Scanner(System.in);
	input = reader.nextLine().replace("\n", "");
	try{
		if (input.length()==0||input.length()>100) {
			System.out.println("[Error] Invaild Input : Title out of range!. Please try again.");
		}else {
			break;
		}
	 }catch (NumberFormatException e){
		 System.out.println("[Error] Invaild Input");
	}
	}
	
	return input;
}

public String AuthorNamechecker() {
	String input ="";
	while(true) {
	System.out.println("Please enter The Author Name : ");
	reader = new Scanner(System.in);
	input = reader.nextLine().replace("\n", "");
	try{
		if (input.length()==0||input.length()>50) {
			System.out.println("[Error] Invaild Input : Author Name  out of range. Please try again. ");
		}else {
			break;
		}
	 }catch (NumberFormatException e){
		 System.out.println("[Error] Invaild Input");
	}
	}

	return input ;
}

public String CustomerIDchecker() {
	String input = "";
	while(true) {
	System.out.println("Please enter Customer ID : ");
	reader = new Scanner(System.in);
	input = reader.nextLine().replace("\n", "");
	try{
		if (input.length()==0||input.length()>10) {
			System.out.println("[Error] Invaild Input : ID  out of range. Please try again. ");
		}else {
			break;
		}
	 }catch (NumberFormatException e){
		 System.out.println("[Error] Invaild Input");
	}
	}
	
	
	
	return input;
	
}

public String yearchecker() {
	String input = "";
	while(true) {
	System.out.println("Please enter the target year : ");
	reader = new Scanner(System.in);
	input = reader.nextLine().replace("\n", "");
	try{
		if (input.length()!=4) {
			System.out.println("[Error] Invaild Input : Formatting Error. Please try again. ");
		}else {
			break;
		}
	 }catch (NumberFormatException e){
		 System.out.println("[Error] Invaild Input");
	}
	}
	
	
	return input;
}

public String monthchecker() {
	String input = "";
	while(true) {
		System.out.println("Please input the Month for Order Query (e.g.2005-09) : ");
		reader = new Scanner(System.in);
		input = reader.nextLine().replace("\n", "");
		try{
			if (!(input.matches("\\d{4}-\\d{2}"))) {
				System.out.println("[Error] Input does not follow the format of month. Please try again.");
			}else {
				break;
			}
		 }catch (NumberFormatException e){
			 System.out.println("[Error] Invalid Input");
		}
		}
	
	
	return input;
}

public int Quantitychecker() {
	int quantity = 0;
	while(true) {
		System.out.println("Please enter the Quantity you want : ");
		reader = new Scanner(System.in);
		quantity = reader.nextInt();
		try{
			if (quantity < 1) {
				System.out.println("[Error] Invaild Input : Zero Quantity is entered. Please try again. ");
			}else {
				break;
			}
		 }catch (NumberFormatException e){
			 System.out.println("[Error] Invaild Input");
		}
		}
	
	return quantity;
	
}

public int QuantityAddchecker() {
	int input = 0 ; 
	
	while(true) {
		System.out.println("How many numbers you want to add ? : ");
		reader = new Scanner(System.in);
		input = reader.nextInt();
		try {
			if (input < 1) {
				System.out.println("[Error] Invaild Input : Zero Quantity is entered. Please try again. ");
			}else {
				break;
			}
		}catch(NumberFormatException e) {
			System.out.println("[Error] Invaild Input");
		}
	}
	
	return input;
}

public int QuantityDelchecker() {
	int input = 0 ; 
	
	while(true) {
		System.out.println("How many numbers you want to delete ? : ");
		reader = new Scanner(System.in);
		input = reader.nextInt();
		try {
			if (input < 1) {
				System.out.println("[Error] Invaild Input : Zero Quantity is entered. Please try again. ");
			}else {
				break;
			}
		}catch(NumberFormatException e) {
			System.out.println("[Error] Invaild Input");
		}
	}
	
	return input;
}

public String Exitchecker() {
	String input = "";
	
	while(true) {
		System.out.println("Please press (L/F) : ");
		reader = new Scanner(System.in);
		input = reader.nextLine().replace("\n", "");
		try{
			if (input != "L" || input != "F"){
				System.out.println("[Error] You should only enter 'L' for Looking the ordered list / 'F' for Finishing ordering. Please try again.");
			}
		 }catch (NumberFormatException e){
			 System.out.println("[Error] Invaild Input");
		}
		}
}

public int OrderIDchecker() {
	int input = 0;
	
	while(true) {
		System.out.println("What is the Order ID ? : ");
		reader = new Scanner(System.in);
		input = reader.nextInt();
		try{
			if (input < 0) {
				System.out.println("[Error] Invaild Order ID. Please try again.");
			}else {
				break;
			}
		 }catch (NumberFormatException e){
			 System.out.println("[Error] Invaild Input");
		}
		}
	
	
	
	return input;
}

public String Actionchecker() {
	String input = "";
	
	while(true) {
		System.out.println("What kind of action you want to make ? (A/D) : ");
		reader = new Scanner(System.in);
		input = reader.nextLine().replace("\n", "");
		try{
			if (input != "A" & input != "D") {
				System.out.println("[Error] Only A or D can be entered (A = Add / D = Delete)");
			}else {
				break;
			}
		 }catch (NumberFormatException e){
			 System.out.println("[Error] Invaild Input");
		}
		}
	
	
	
	return input;
}

public String UserRespondchecker() {
	String input = "" ;
	
	while(true) {
		System.out.println("Are you sure to update the shipping status ? (Y/N) : ");
		reader = new Scanner(System.in);
		input = reader.nextLine().replace("\n", "");
		try{
			if (input != "Y" & input != "N") {
				System.out.println("[Error] Only Y or N can be entered (A = Yes / D = No)");
			}else {
				break;
			}
		 }catch (NumberFormatException e){
			 System.out.println("[Error] Invaild Input");
		}
		}

	
	
	
	return input;
}

public int choicechecker(int number) {
	int input = -1 ;
	
	while(true) {
		System.out.println("Which book you want to alter (input book no.) : ");
		reader = new Scanner(System.in);
		input = reader.nextInt() ;
		try{
			if (input < 1 ||  input > number) {
				System.out.println("[Error] Out of Range. Please select a number range from 1 to " + number);
			}else {
				break;
			}
		 }catch (NumberFormatException e){
			 System.out.println("[Error] Invaild Input");
		}
		}
	
	
	
	return input;
}


}
