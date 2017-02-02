import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is where you will put your main method. For
 * instructions on how to create it, refer to the PDF for
 * this homework assignment.
 * 
 * @author Steven Wojcio
 *
 */
public class AmusementPark {
	
	//Leave this variable alone. It is necessary for compilation.
	public static int ticketNumber = 1000;
	
	public static void main(String[] args) {
		Queue<Patron> line = new Queue<Patron>();
		Stack<Ticket> pile = new Stack<Ticket>();
		ArrayList<Patron> records = new ArrayList<Patron>();
		Scanner keyboard = new Scanner(System.in);
		
		int choice = 0;
		do {
			System.out.println("Welcome to the Park! Select an option: ");
			System.out.println("\t1: Add patron to the back of the line.");
			System.out.println("\t2: Put a ticket in the pile.");
			System.out.println("\t3: Get the number of patrons in line.");
			System.out.println("\t4: Get the number of available tickets.");
			System.out.println("\t5: Administer a ticket.");
			System.out.println("\t6: Show patrons with tickets.");
			System.out.println("\t7: Quit.");
			System.out.print("Enter Choice Number: ");
			choice = keyboard.nextInt();
			if (choice == 1) {
				keyboard.nextLine();
				System.out.print("Enter Patron Name: ");
				String name = keyboard.nextLine();
				Patron toAdd = new Patron(name);
				records.add(toAdd);
				line.enqueue(toAdd);
				System.out.println("Successfully Added.");
			} else if (choice == 2) {
				Ticket toAdd = new Ticket();
				pile.push(toAdd);
				System.out.println("Ticket added to the pile.");
			} else if (choice == 3) {
				System.out.println("There are currently " + line.size() + " people in line.");
			} else if (choice == 4) {
				System.out.println("There are " + pile.size() + " available tickets.");
			} else if (choice == 5) {
				if (!pile.isEmpty() && line.size() != 0) {
					Patron toReceiveTicket = line.dequeue();
					toReceiveTicket.giveTicket(pile.pop());
					System.out.println("Ticket successfully administered for " + toReceiveTicket.getName());
				} else {
					System.out.println("There are no tickets left OR There is nobody to give tickets to. Sorry!");
				}
			} else if (choice == 6) {
				int count = 0;
				System.out.println("Patrons with Tickets: ");
				for (int i = 0; i < records.size(); i++) {
					if (records.get(i).getTicket() != null) {
						count++;
						System.out.println(records.get(i).toString());						
					}
				}
				if (count == 0) {
					System.out.println("None");
				}
				
			}
		} while (choice != 7);
	}
}
