/**
 * A class that represents the ballot box.
 * @author Daniel Ansher
 */
public class BallotBox {
    private BagInterface <Ballot> ballots;

    /** Creates an empty ballot box */
    public BallotBox() {
        ballots = new ArrayListBag<Ballot> ();
    }

    /** Adds a vote to the ballot box.
     * @param aBallot  the ballot to add
     * @return true if the addition was success, or false if not
     */
    public boolean addVote(Ballot aBallot) {
        return ballots.add(aBallot);
    }

    /** Removes a random vote from the ballot box.
     * @return the ballot entry that was removed
     */
    public Ballot removeVote() {
        return ballots.remove();
    }

    /** Removes a specific vote from the ballot box.
     * @param aBallot  the ballot to remove
     * @return true if the removal was success, or false if not
     */
    public boolean removeVote(Ballot aBallot) {
        return ballots.remove(aBallot);
    }

    /** Clears the ballot box of all candidates. */
    public void emptyBallotBox() {
        ballots.clear();
    }

    /** Obtains the number of votes for a specific candidate.
     * @param candName  the name of the candidate to look up
     * @return the number of votes for the candidate
     */
    public int getVotes(String candName) {
        return ballots.getFrequencyOf(new Ballot(candName, 0.0));
    }

    /** Retrieves the number of votes in the box
     * @return the number of votes in the box
     */
    public int getBallotVotes() {
        return ballots.getCurrentSize();
    }

    /** Prints an organized list of all the votes in the ballot box. */
    public void printAllVotes() {
        Object[] ballotsArray = ballots.toArray();
        for (int i = 0; i < ballotsArray.length; i++) {
            System.out.print(i+1 + ": " + ballotsArray[i]);
        }
    }
}