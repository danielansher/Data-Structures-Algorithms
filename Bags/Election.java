import java.util.Scanner;
/**
 * A class that demonstrates the BallotBox.
 * @author Daniel Ansher
 */
public class Election {

    /** Prints menu and controls user interaction for Elections.
     * @param args  Any desired command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        BallotBox myBox = new BallotBox();
        boolean loopAgain = true;
        do {
            displayMenu();
            int response = keyboard.nextInt();
            keyboard.nextLine();
            if (response == 1) {
                newVote(keyboard, myBox);
            }
            else if (response == 2) {
                countVotes(keyboard, myBox);
            }
            else if (response == 3) {
                removeVote(keyboard, myBox);
            }
            else if (response == 4) {
                System.out.println("Votes in the Box: " + myBox.getBallotVotes());
            }
            else if (response == 5) {
                myBox.emptyBallotBox();
                System.out.println("The box has been cleared.");
            }
            else if (response == 6) {
                myBox.printAllVotes();
            }
            else {
                loopAgain = false;
            }           
        } while (loopAgain);

    }

    private static void displayMenu() {
        System.out.println("Welcome to the election! What would you like to do?");
        System.out.println("\t1: Vote for a candidate");
        System.out.println("\t2: Count the number of votes for a candidate");
        System.out.println("\t3: Remove a vote");
        System.out.println("\t4: Get number of votes in the ballot box");
        System.out.println("\t5: Empty ballot box");
        System.out.println("\t6: Print all votes");
        System.out.println("\t7: Quit");
        System.out.print("Enter your choice (1-7) here: ");
    }

    private static void newVote(Scanner keyboard, BallotBox myBox) {
        System.out.print("Enter the name of the candidate you would like to vote for: ");
        String name = keyboard.nextLine();
        System.out.print("Enter bribe amount: ");
        double bribe = keyboard.nextDouble();
        myBox.addVote(new Ballot(name, bribe));
    }

    private static void countVotes(Scanner keyboard, BallotBox myBox) {
        System.out.print("Enter candidate name: ");
        String name = keyboard.nextLine();
        System.out.println("Counted Votes: " + myBox.getVotes(name));
    }

    private static void removeVote(Scanner keyboard, BallotBox myBox) {
        myBox.printAllVotes();
        System.out.print("If you want to remove a specific candidate, enter their name here (leave blank for random removal): ");
        String name = keyboard.nextLine();
        if (name.equals("")) {
            myBox.removeVote();
            System.out.println("Sucess! candidate has been removed.");
        }
        else {
            myBox.removeVote(new Ballot(name, 0.0));
            System.out.println("Success! candidate has been removed.");
        }        
    }
}