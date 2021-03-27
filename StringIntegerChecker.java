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

int IntegerChecker() {
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
}
