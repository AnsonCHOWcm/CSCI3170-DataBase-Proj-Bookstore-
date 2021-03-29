package Main;

import java.util.*;

public class StringIntegerChecker {
	int lowerlim;
	int upperlim;
	Scanner reader;

public StringIntegerChecker(int l,int u) {
	lowerlim = l;
	upperlim = u;
}

String input(String sentence) {
	System.out.println(sentence);
	reader = new Scanner(System.in);
	String input = reader.nextLine().replace("\n","");
	return input ;
}

int SystemIntegerChecker() {
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

int CustomerIntegerChecker() {
	String num;
	int input;
	while(true) {
	System.out.println("Your choice?...");
	reader = new Scanner(System.in);
	num = reader.nextLine().replace("\n", "");
	input = Integer.parseInt(num);
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
	
	
	return input;
}

String ISBNchecker() {
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

String BookTitlechecker() {
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

String AuthorNamechecker() {
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

String CustomerIDchecker() {
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

String yearchecker() {
	String input;
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


}
