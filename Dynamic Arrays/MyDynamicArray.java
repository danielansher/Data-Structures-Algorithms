import java.util.Collection;

/**
 * A class that represents a dynamic array object.
 * 
 * @author Daniel Ansher
 *
 */
public class MyDynamicArray<T> implements DynamicArrayInterface<T> {
	
	//Add instance variables here, they must be private
	private T[] array;
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	private int numOfEntries;

	/**
	 * Creates the initial array with a default capacity of 10.
	 */
	public MyDynamicArray() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * Creates the initial array.
	 * @param capacity custom size for the array
	 */
	public MyDynamicArray(int capacity) {
		numOfEntries = 0;
		array = (T[]) (new Object[capacity]);
	}
	
	@Override
	public void add(T toAdd) {
		// TODO Auto-generated method stub
		if (toAdd != null) {
            if (!isFull()) {
                array[numOfEntries] = toAdd;
                numOfEntries++;
            } else {
            	resizeArray(toAdd);
            }
		}
	}

	@Override
	public void addAll(Collection<T> collection) {
		// TODO Auto-generated method stub
        for (T element : collection) {
        	if (element != null) {
            	add(element);  		
        	}
        }
	}

	@Override
	public T remove(T toRemove) {
		// TODO Auto-generated method stub

		T toReturn =  null;
		boolean continueToRemove = false;
		if (toRemove != null) {
			for (int i = 0; i < numOfEntries; i++) {
				if (array[i].equals(toRemove)) {
					toReturn = array[i];
					array[i] = array[i + 1];
					continueToRemove = true;
				}
				if (continueToRemove) {
					array[i] = array[i + 1];
				}
			}
			numOfEntries--;
		}
		return toReturn;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		T obj = get(index);
		return remove(obj);	
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		if (index >= 0 && index < numOfEntries) {
			return array[index];
		}
		return null;
	}

	@Override
	public boolean contains(T obj) {
		// TODO Auto-generated method stub
		boolean toReturn = false;
		if (obj != null) {
			for (int i = 0; i < numOfEntries; i++) {
				if (array[i] != null) {
					if (array[i].equals(obj)) {
						toReturn = true;
						i = numOfEntries + 1;
					}					
				}

			}
		}
		return toReturn;
	}
	
	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		return array;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (numOfEntries == 0) {
			return true;
		}
		return false; 
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		numOfEntries = 0;
		T[] arrayCleared = (T[]) (new Object[DEFAULT_INITIAL_CAPACITY]);
		array = arrayCleared;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numOfEntries + 1;
	}
	
	/**
	 * Resizes the array by doubling copying elements
	 * from old array to new array with double the size
	 * and setting the old array to the copy.
	 * @param toAdd the new element that will be added
	 *              after resize.
	 */
	private void resizeArray(T toAdd) {
		T[] newArray = (T[]) (new Object[array.length * 2]);
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];	
		}
		newArray[array.length] = toAdd;
		array = newArray;
	}
	/**
	 * Checks to see if the array is full by comparing
	 * seeing if all indexes of the array are filled with
	 * elements.
	 * @return true if the array is full, false otherwise
	 */
	private boolean isFull() {
        return numOfEntries == array.length;
	}
	

}