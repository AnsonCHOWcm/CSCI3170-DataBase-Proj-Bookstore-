import java.util.Scanner;

public class Checker {
	Scanner reader;

	private static Checker instance = new Checker();

	public static Checker getInstance() {
		return instance;
	}

	private Checker() {}

	public int IntegerChecker(int min, int max) {
		Scanner in = new Scanner(System.in);

		while (true) {
			try {
				int cmd = in.nextInt();

				if (cmd >= min && cmd <= max) {
					in.close();
					return cmd;
				} else {
					System.out.println("[Error] Please type between " + min + "-" + max + ".");
				}
			} catch (InputMismatchException e) {
				System.out.println("[Error] Invalid Input");
			}
		}
	}

	public String ISBNchecker() {
		String input = "";
		while (true) {
			reader = new Scanner(System.in);
			input = reader.nextLine().replace("\n", "");
			try {
				if (input.matches("\\d{1}-\\d{4}-\\d{4}-\\d{1}")) {
					System.out.println("[Error] Input does not follow the format of ISBN. Please try again.");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invalid Input");
			}
		}

		return input;
	}

	public String BookTitlechecker() {
		String input = "";
		while (true) {
			System.out.println("Please enter The Book Title : ");
			reader = new Scanner(System.in);
			input = reader.nextLine().replace("\n", "");
			try {
				if (input.length() == 0 || input.length() > 100) {
					System.out.println("[Error] Invaild Input : Title out of range!. Please try again.");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return input;
	}

	public String AuthorNamechecker() {
		String input = "";
		while (true) {
			System.out.println("Please enter The Author Name : ");
			reader = new Scanner(System.in);
			input = reader.nextLine().replace("\n", "");
			try {
				if (input.length() == 0 || input.length() > 50) {
					System.out.println("[Error] Invaild Input : Author Name  out of range. Please try again. ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return input;
	}

	public String CustomerIDchecker() {
		String input = "";
		while (true) {
			System.out.println("Please enter Customer ID : ");
			reader = new Scanner(System.in);
			input = reader.nextLine().replace("\n", "");
			try {
				if (input.length() == 0 || input.length() > 10) {
					System.out.println("[Error] Invaild Input : ID  out of range. Please try again. ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return input;

	}

	public String yearchecker() {
		String input = "";
		while (true) {
			System.out.println("Please enter the target year : ");
			reader = new Scanner(System.in);
			input = reader.nextLine().replace("\n", "");
			try {
				if (input.length() != 4) {
					System.out.println("[Error] Invaild Input : Formatting Error. Please try again. ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return input;
	}

	public String monthchecker() {
		String input = "";
		while (true) {
			System.out.println("Please input the Month for Order Query (e.g.2005-09) : ");
			reader = new Scanner(System.in);
			input = reader.nextLine().replace("\n", "");
			try {
				if (!(input.matches("\\d{4}-\\d{2}"))) {
					System.out.println("[Error] Input does not follow the format of month. Please try again.");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invalid Input");
			}
		}

		return input;
	}

	public int Quantitychecker() {
		int quantity = 0;
		while (true) {
			System.out.println("Please enter the Quantity you want : ");
			reader = new Scanner(System.in);
			quantity = reader.nextInt();
			try {
				if (quantity < 1) {
					System.out.println("[Error] Invaild Input : Zero Quantity is entered. Please try again. ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return quantity;

	}

	public int QuantityAddchecker() {
		int input = 0;

		while (true) {
			System.out.println("How many numbers you want to add ? : ");
			reader = new Scanner(System.in);
			input = reader.nextInt();
			try {
				if (input < 1) {
					System.out.println("[Error] Invaild Input : Zero Quantity is entered. Please try again. ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return input;
	}

	public int QuantityDelchecker() {
		int input = 0;

		while (true) {
			System.out.println("How many numbers you want to delete ? : ");
			reader = new Scanner(System.in);
			input = reader.nextInt();
			try {
				if (input < 1) {
					System.out.println("[Error] Invaild Input : Zero Quantity is entered. Please try again. ");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return input;
	}

	public String Exitchecker() {
		Scanner reader = new Scanner(System.in);
		String input;

		System.out.println("Please press (L/F) : ");

		while (true) {
			input = reader.nextLine().replace("\n", "");
			try {
				if (input.equals("L") || input.equals("F")) {
					reader.close();
					return input;
				} else {
					System.out.println(
							"[Error] You should only enter 'L' for Looking the ordered list / 'F' for Finishing ordering. Please try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}
	}

	public int OrderIDchecker() {
		int input = 0;

		while (true) {
			System.out.println("What is the Order ID ? : ");
			reader = new Scanner(System.in);
			input = reader.nextInt();
			try {
				if (input < 0) {
					System.out.println("[Error] Invaild Order ID. Please try again.");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return input;
	}

	public String Actionchecker() {
		String input = "";

		while (true) {
			System.out.println("What kind of action you want to make ? (A/D) : ");
			reader = new Scanner(System.in);
			input = reader.nextLine().replace("\n", "");
			try {
				if (input != "A" & input != "D") {
					System.out.println("[Error] Only A or D can be entered (A = Add / D = Delete)");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return input;
	}

	public String UserRespondchecker() {
		String input = "";

		while (true) {
			System.out.println("Are you sure to update the shipping status ? (Y/N) : ");
			reader = new Scanner(System.in);
			input = reader.nextLine().replace("\n", "");
			try {
				if (input != "Y" & input != "N") {
					System.out.println("[Error] Only Y or N can be entered (A = Yes / D = No)");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return input;
	}

	public int choicechecker(int number) {
		int input = -1;

		while (true) {
			System.out.println("Which book you want to alter (input book no.) : ");
			reader = new Scanner(System.in);
			input = reader.nextInt();
			try {
				if (input < 1 || input > number) {
					System.out.println("[Error] Out of Range. Please select a number range from 1 to " + number);
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("[Error] Invaild Input");
			}
		}

		return input;
	}

}
