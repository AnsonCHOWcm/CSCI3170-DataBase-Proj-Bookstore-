import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static java.util.Date system_time;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        SystemDate.createTheInstance();
        SystemDate sdate = SystemDate.getInstance();

        while (true) {
            System.out.println("The System Date is now : " + sdate);
            System.out.println("<This is the Book Ordering System>");
            System.out.println("----------------------------------------");
            System.out.println("1. System interface.");
            System.out.println("2. Customer interface.");
            System.out.println("3. Bookstore interface.");
            System.out.println("4. Show System Date.");
            System.out.println("5. Quit the system.......");

            System.out.print("Please enter your choice??..");

            try {
                if (in.hasNext()) {
                    int choice = in.nextInt();

                    if (choice == 1) {
                        SystemInterface admin = new SystemInterface();
                        admin.CommandLineInterface(in);
                    } else if (choice == 2) {
                        Customer customer = new Customer();
                        customer.CommandLineInterface();
                    } else if (choice == 3) {
                        Bookstore bookstore = new Bookstore();
                        bookstore.CommandLineInterface(in);
                    } else if (choice == 4) {
                        System.out.println("The System Date is now : " + sdate);
                    } else if (choice == 5){
                        System.out.println("See you next time. Bye Bye.");
                        break;
                    } else {
                        System.out.println("[Error] Please type between 1-5.");
                    }
                }
            } catch (InputMismatchException e) {
				System.out.println("[Error] Invalid Input");
			} catch (Exception e) {
                System.out.println(e);
            }

        }
        in.close();
    }
}
