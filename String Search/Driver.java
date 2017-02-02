import java.util.Scanner;
/**
 * This class uses various algorithms to determine matches
 * between text in patterns.
 * 
 * @author Daniel Ansher
 * 
 */
public class Driver {
	
	/**
	 * Driver method that allows user to enter a haystack
	 * and needle. Outputs matches and then shows speed
	 * of rabin karpe vs boyer moore algorithms.
	 */	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		StringSearch component = new StringSearch();
		boolean loopAgain = false;
		do {
			System.out.print("Enter a haystack: ");
			String haystack = keyboard.nextLine();
			System.out.print("Enter a needle: ");
			String needle = keyboard.nextLine();

			long now = 0;
			long later = 0;
			double finalDuration = 0.0;

			System.out.print("Choose an algorithm: (ENTER rk or bm) ");
			String option = keyboard.nextLine();
			if (option.toLowerCase().equals("bm")) {
				System.out.println("You chose boyer-moore!");
				now = System.nanoTime();
				System.out.println("Matches: " + component.boyerMoore(needle, haystack));
				later = System.nanoTime();
				finalDuration = (later - now) / 1000000000.0;
				System.out.println("It took boyer moore " + finalDuration + " seconds.");
				
				now = 0;
				later = 0;
				finalDuration = 0.0;

				now = System.nanoTime();
				component.rabinKarp(needle, haystack);
				later = System.nanoTime();
				finalDuration = (later - now) / 1000000000.0;
				System.out.println("It took rabin karp " + finalDuration + " seconds.");
				
			} else {
				System.out.println("You chose rabin karp!");
				now = System.nanoTime();
				System.out.println("Matches: " + component.rabinKarp(needle, haystack));
				later = System.nanoTime();
				finalDuration = (later - now) / 1000000000.0;
				System.out.println("It took rabin karp " + finalDuration + " seconds.");	
				
				now = 0;
				later = 0;
				finalDuration = 0.0;
				
				now = System.nanoTime();
				component.boyerMoore(needle, haystack);
				later = System.nanoTime();
				finalDuration = (later - now) / 1000000000.0;
				System.out.println("It took boyer moore " + finalDuration + " seconds.");
			}



			System.out.print("Continue (Y/N)? ");
			String cont = keyboard.nextLine();
			if (cont.toUpperCase().equals("Y")) {
				loopAgain = true;
			} else {
				loopAgain = false;
			}

		} while (loopAgain);
	}


}
