import java.util.Scanner;

/**
 * Program that manipulates various functions of implementing
 * a DoublyLinkedList.
 * 
 * @author Dan Ansher
 */

public class Driver {
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		boolean continueLoop = true;
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		System.out.println("The list currently has " + list.size() + " elements.");
		String cmd = null;
		do {
			System.out.print("Enter a command: ");
			cmd = keyboard.nextLine();
			if (cmd.toLowerCase().equals("add")) {
				System.out.print("What number would you like to add? ");
				int number = keyboard.nextInt();
				keyboard.nextLine();
				System.out.print("Where would you like to add? ");
				String loc = keyboard.nextLine();
				if (loc.toLowerCase().equals("back")) {
					list.add(list.size(), number);
				} else if (loc.toLowerCase().equals("front")) {
					list.add(0, number);
				}
			} else if (cmd.toLowerCase().equals("remove")) {
				System.out.print("Where would you like to remove? ");
				String loc = keyboard.nextLine();
				if (loc.toLowerCase().equals("back")) {
					list.remove(list.size() - 1);
				} else if (loc.toLowerCase().equals("front")) {
					list.remove(0);
				}
			} else if (cmd.toLowerCase().equals("reverse")) {
				list.reverseList();
			} else if (cmd.toLowerCase().equals("exit")) {
				continueLoop = false;
			} else if (cmd.toLowerCase().equals("list")) {
				System.out.print("List: [ ");
				for (Integer i : list) {
				System.out.print(i + " ");
				}
				System.out.println("] ");				
			} else {
				System.out.println("Invalid Input");
			}
		} while(continueLoop);
	}

}
