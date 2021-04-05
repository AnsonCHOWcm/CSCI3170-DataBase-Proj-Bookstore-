package Main;

public class BookRecord {
	String ISBN;
	int quantity;
	
	public BookRecord (String code , int q) {
		ISBN = code;
		quantity = q;
	}
	
	public String GetISBN() {
		return ISBN;
	}
	
	public int quantity() {
		return quantity;
	}

}
