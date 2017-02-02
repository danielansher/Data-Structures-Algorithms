import java.util.*;
/**
 *  A class of bags whose entries are stored in a non-fixed size array.
 *  @author Dan Ansher
 */
public class ArrayListBag<T> implements BagInterface<T> {

    private ArrayList<T> bag;

    /** Creates an empty bag */
    public ArrayListBag() {
        bag = new ArrayList<T>();
    }

    /** Gets the current number of objects in this bag.
     * @return the integer number of objects currently in this bag 
     */
    public int getCurrentSize() {
        return bag.size();
    }

    /** Sees whether this bag is empty.
     * @return true if this bag is empty, or false if not 
     */
    public boolean isEmpty() {
        return bag.isEmpty();
    }

    /** Adds a new entry to the bag. Checks if object is null.
     * @param newEntry  the object to be added as a new entry
     * @return true if the addition is successful, or false if not 
     */
    public boolean add(T newEntry) {
        boolean result = true;

        if (newEntry == null) {
            result = false;
        }
        else {
            bag.add(newEntry);
        }

        return result;
    }

    /** Removes a random given entry from this bag.
     * @return the object that has been removed
     */
    public T remove() {
        Random r = new Random();
        int low = 0;
        int high = getCurrentSize();

        int randomElement = r.nextInt(high-low) + low;
        return bag.remove(randomElement);

    }

    /** Removes one occurrence of a given entry from this bag.
     * @param anEntry  the entry to be removed
     * @return true if the removal was successful, or false otherwise 
     */
    public boolean remove(T anEntry) {
        return bag.remove(anEntry);
    }

    /** Removes all entries from this bag */
    public void clear() {
        bag.clear();
    }

    /** Counts the number of times a given entry appears in a bag.
     * @param anEntry  the entry to be counted
     * @return the number of times anEntry appears in this bag 
     */
    public int getFrequencyOf(T anEntry) {
        int counter = 0;
        for (int i = 0; i < getCurrentSize(); i++) {
            if (anEntry.equals(bag.get(i))) {
                counter++;
            }
        }

        return counter;
    }

    /** Tests whether this bag contains a given entry.
     * @param anEntry  the entry to locate
     * @return true if this bag contains anEntry, or false otherwise 
     */
    public boolean contains(T anEntry) {
        return bag.contains(anEntry);
    }

    /** Retrieves all entries that are in this bag.
     * @return a newly allocated array of all the entries in this bag 
     */
    public T[] toArray() {
        return (T[])(bag.toArray());
    }



}