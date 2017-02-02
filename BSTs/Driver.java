import java.util.Scanner;

/**
 * This class represents a social networking startup's new system
 * for how their user data is organized.
 *
 * 
 * @author Daniel Ansher
 */
public class Driver {
	
	public static void main(String[] args) {
		String cmd;
		BST<User> usersDB = new BST<User>();
		do {
			Scanner keyboard = new Scanner(System.in);
			System.out.print("Please enter a command: ");
			cmd = keyboard.nextLine();
			if (cmd.toLowerCase().equals("add")) {
				System.out.print("Please enter a username: ");
				String userName = keyboard.nextLine();
				System.out.print("Please enter a name: ");
				String name = keyboard.nextLine();
				usersDB.add(new User(userName, name));
			} else if (cmd.toLowerCase().equals("remove")) {
				System.out.println("Please enter a username you would like to remove: ");
				String userName = keyboard.nextLine();
				if (usersDB.contains(new User(userName, ""))) {
					usersDB.remove(new User(userName, ""));
					System.out.println("Remove was successful!");
				} else {
					System.out.println("User does not exist!");
				}
			} else if (cmd.toLowerCase().equals("find")) {
				System.out.print("Please enter a username you would like to find: ");
				String userName = keyboard.nextLine();
				if (usersDB.contains(new User(userName, ""))) {
					System.out.println(usersDB.get(new User(userName, "")));
				} else {
					System.out.println("User does not exist!");
				}
			} else if (cmd.toLowerCase().equals("list")) {
				System.out.println("List of current users: ");
				System.out.println(usersDB.inOrder());
			} else if (cmd.toLowerCase().equals("debug")) {
				System.out.println("String representation of tree: ");
				System.out.println(usersDB.toString());
			}
		} while(!cmd.toLowerCase().equals("exit"));
		System.out.println("Goodbye!");

	}
		
		

}
