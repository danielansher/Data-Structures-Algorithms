import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Hashtable implementation.
 * 
 * @author Daniel Ansher
 * @author Julia Ting
 * 
 */
public class HashTable<K, V> implements HashTableInterface<K, V> {

	/**
	 * DO NOT CHANGE THIS NUMBER.
	 * 
	 * This is the constant determining max load factor, or when you will
	 * have to regrow the table.
	 */
	private static final double MAX_LOAD_FACTOR = .71;
	
	/**
	 * DO NOT CHANGE THIS NUMBER.
	 * 
	 * This is the constant determining what size you will initialize your
	 * table array to.
	 */
	private static final int INITIAL_CAPACITY = 11;
	
	/**
	 * The number of entries in the table.
	 */
	private int size;
	
	/**
	 * The backing array of your hash table.
	 */
	private MapEntry<K, V>[] table;
	
	/**
	 * Initialize the backing array to the initial capacity and the size
	 * to the appropriate starting size.
	 */
	public HashTable() {
		table = (MapEntry<K, V>[]) (new MapEntry[INITIAL_CAPACITY]);
		size = 0;
	}
	
	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		if (value == null || key == null) {
			throw new IllegalArgumentException();
		}
		V toReturn = null;
		double loadFactor = (double) (size) / table.length;
		if (loadFactor > MAX_LOAD_FACTOR) {
			regrow();
		}
		for (int i = getHashIndex(key); i <= table.length; i++) {
			if (i == table.length) {
				i = 0;
			}
			if (table[i] == null || table[i].isRemoved()) {
				table[i] = new MapEntry<K, V>(key, value);
				size++;
				i = table.length + 1; //terminates for loop.
			} else if (key.equals(table[i].getKey())) {
				toReturn = table[i].getValue();	
				table[i] = new MapEntry<K, V>(key, value);
				i = table.length + 1;
			}

		}
		return toReturn;
	}

	/**
	 * Adjusts table size to double current size plus 1. Rehashes
	 * every value for new table.
	 */
	private void regrow() {
		MapEntry<K, V>[] newTable = new MapEntry[(2 * table.length) + 1];
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				K curKey = table[i].getKey();
				V curVal = table[i].getValue();
				int index = Math.abs(curKey.hashCode() % newTable.length);
				newTable[index] = new MapEntry<K, V>(curKey, curVal);				
			}
		}
		table = newTable;
	}

	/**
	 * Gets the index for entry in hash table.
	 * 
	 * @param key The key to convert into hash index
	 * @return the index of placement for the value
	 */
	private int getHashIndex(K key) {
		return Math.abs(key.hashCode() % table.length);
	}
	
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		int count = 0;
		for (int i = getHashIndex(key); i <= table.length; i++) {
			if (i == table.length) {
				i = 0;
			}
			if (table[i] != null && !table[i].isRemoved()) {
				count++;
				if (table[i].getKey().equals(key)) {
					return table[i].getValue();
				}

			}
			if (count == size) {
				i = table.length + 1;
			}

		}
		return null;
	}
	


	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		V toReturn = null;

		if (key == null) {
			throw new IllegalArgumentException();
		}
		
		int count = 0;		
		for (int i = getHashIndex(key); i <= table.length; i++) {
			if (i == table.length) {
				i = 0;
			}
			if (table[i] != null && !table[i].isRemoved()) {
				count++;
				if (key.equals(table[i].getKey())) {
					toReturn = table[i].getValue();
					table[i].setRemoved(true);
					size--;
					return toReturn;
				}
			}
			if (count == size) {
				i = table.length + 1;
			}

		}

		return toReturn;
	}

	@Override
	public boolean contains(V value) {
		// TODO Auto-generated method stub
		if (value == null) {
			throw new IllegalArgumentException();
		}
		for (MapEntry<K, V> element : table) {
			if (element != null && !(element.isRemoved())) {
				if (element.getValue().equals(value)) {
					return true;
				}				
			}
		}
		return false;
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalArgumentException();
		}
		return (get(key) != null);
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		HashSet<K> toReturn = new HashSet<K>(); 
		for (MapEntry<K, V> element : table) {
			if (element != null && !(element.isRemoved())) {
				toReturn.add(element.getKey());
			}
		}
		return toReturn;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		ArrayList<V> toReturn = new ArrayList<V>();
		for (MapEntry<K, V> element : table) {
			if (element != null && !(element.isRemoved())) {
				toReturn.add(element.getValue());
			}
		}
		return toReturn;
	}

	@Override
	public Set<MapEntry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		HashSet<MapEntry<K, V>> set = new HashSet<MapEntry<K, V>>();
		for (MapEntry<K, V> element : table) {
			if (element != null && !(element.isRemoved())) {
				set.add(element);
			}
		}
		return set;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		table = (MapEntry<K, V>[]) (new MapEntry[INITIAL_CAPACITY]);
		size = 0;
	}

}
