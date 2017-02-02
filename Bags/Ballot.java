/**
 * A class that represents an individual ballot.
 * @author Daniel Ansher
 */
public class Ballot {
    private String name;
    private double bribe;

    /** Creates a ballot.
     * @param name  the name of the candidate
     * @param bribe  the amount of bribe towards the candidate
     */
    public Ballot(String name, double bribe) {
        this.name = name;
        this.bribe = bribe;
    }

    /** Compares two different ballots to see if their equal.
     * @param obj  the ballot to compare with
     * @return true if both ballots have same candidate name, or false if not
     */
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Ballot)) return false;
        Ballot objConverted = (Ballot)(obj);
        return (this.getName().equals(objConverted.getName()));
    }

    /** Retrieves the name of a candidate on a ballot.
     * @return the name of the candidate
     */
    public String getName() {
        return name;
    }

    /** String representation of a ballot.
     * @return the name of the candidate, and its corresponding bribe amount
     */
    public String toString() {
        return "Candidate Name: " + name + ", Bribe Amt: " + bribe + "\n";
    }
}