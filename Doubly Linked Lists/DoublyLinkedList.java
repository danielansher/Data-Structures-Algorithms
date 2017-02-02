import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is a Doubly LinkedList that has both a head an tail pointer.
 */

public class DoublyLinkedList<T> implements LinkedListInterface<T>, Iterable<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	@Override
	public boolean add(int index, T data) {
		// TODO
		boolean toReturn = false;
		if (data == null || index < 0) return false;
		if (index == 0) {
			insertFirst(data);
			toReturn = true;
		} else if (index >= 0 && index <= size() - 1) {
			insertMiddle(index, data);
			toReturn = true;
		} else {
			insertLast(data);
			toReturn = true;
		}
		size++;
		return toReturn;
	}
	
	/**
	 * Inserts a node in the middle of the list
	 * 
	 * @param index Location of insertion
	 * @param data Content of Node to add
	 */	
	private void insertMiddle(int index, T data) {
		Node<T> toAdd = new Node<T>(data);
		if (isEmpty()) {
			head = toAdd;
			tail = toAdd;
			head.setPrev(null);
			head.setNext(null);
		} else {
			getNodeAt(index - 1).setPrev(toAdd);
			toAdd.setNext(getNodeAt(index));
			getNodeAt(index - 1).setNext(toAdd);
		}

		
	}

	/**
	 * Inserts a node at the end of the list
	 * 
	 * @param data Content of Node to add
	 */	
	private void insertLast(T data) {
		Node<T> toAdd = new Node<T>(data);
		if (isEmpty()) {
			head = toAdd;
			tail = toAdd;
			head.setPrev(null);
			head.setNext(null);
		} else {
			tail.setNext(toAdd);
			toAdd.setPrev(tail);
		}
		tail = toAdd;
	}

	/**
	 * Inserts a node in the beginning of the list
	 * 
	 * @param data Content of Node to add
	 */	
	private void insertFirst(T data) {
		Node<T> toAdd = new Node<T>(data);
		if (isEmpty()) {
			head = toAdd;
			tail = toAdd;
			head.setPrev(null);
			head.setNext(null);
		} else {
			head.setPrev(toAdd);
			toAdd.setNext(head);
			head = toAdd;
		}

	}
	
	@Override
	public void clear() {
		// TODO
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	public boolean contains(Object o) {
		// TODO
		Node<T> element = head;
		while (element != null) {
			if (element.getData().equals(o)) {
				return true;
			} else {
				element = element.getNext();				
			}
		}
		return false;
	}
	
	
	@Override
	public T get(int index) {
		// TODO
		int count = 0;
		Node<T> element = head;
		while (element !=  null) {
			if (count == index) {
				return element.getData();
			} else {
				count++;
				element = element.getNext();
			}
		}
		return null;
	}
	
	/**
	 * Gets node at specified index
	 * 
	 * @param index Index of node
	 * @return node at index, invalid index and not found element returns null.
	 */
	private Node<T> getNodeAt(int index) {
		int count = 0;
		Node<T> element = head;
		while (element !=  null) {
			if (count == index) {
				return element;
			} else {
				count++;
				element = element.getNext();
			}
		}
		return null;
	}
	
	@Override
	public int indexOf(T data) {
		// TODO
		int count = 0;
		Node<T> element = head;
		while (element != null) {
			if (element.getData().equals(data)) {
				return count;
			}
			count++;
			element = element.getNext();
		}
		return -1;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO
		return (size == 0);
	}
	
	@Override
	public T remove(Object o) {
		// TODO
		if (contains(o) && !isEmpty()) {
			Node<T> element = head;
			int index = 0;
			while (element != null) {
				if (element.getData().equals(o)) {
					return remove(index);
				}
				element = element.getNext();
				index++;
			}
		}
		return null;
	}
	
	@Override
	public T remove(int index) {
		// TODO
		if (isEmpty() || index < 0 || index > size() - 1) {
			return null;
		} else if (index == 0) {
			if (size() == 1) {
				T toReturn = head.getData();
				head = null;
				tail = null;
				size--;
				return toReturn;
			} else {
				Node<T> toManip = getNodeAt(index);
				head = head.getNext();	
				toManip.setNext(null);
				head.setPrev(null);
				size--;
				return toManip.getData();
			}			
		} else if (index > 0 && index < size() - 1) {
			Node<T> toManip = getNodeAt(index);
			toManip.getNext().setPrev(toManip.getPrev());
			toManip.getPrev().setNext(toManip.getNext());
			toManip.setNext(null);
			toManip.setPrev(null);
			size--;
			return toManip.getData();
		} else {
			Node<T> toManip = getNodeAt(index);
			tail = tail.getPrev();
			tail.setNext(null);
			toManip.setPrev(null);
			size--;
			return toManip.getData();
		}
	}
	
	@Override
	public T replace(int index, T data) {
		// TODO
		if (data == null || getNodeAt(index) == null) {
			return null;
		}
		T oldElement = get(index);
		getNodeAt(index).setData(data);
		return oldElement;
	}
	
	@Override
	public int size() {
		// TODO
		return size;
	}
	
	@Override
	public Node<T> getHead() {
		// TODO
		return head;
	}
	
	@Override
	public void reverseList() {
		// TODO
		Node<T> temp = head;
		head = tail;
		tail = temp;
		
		Node<T> element = head;
		while (element != null) {
			temp = element.getNext();
			element.setNext(element.getPrev());
			element.setPrev(temp);
			element = element.getNext();
		}
		
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator<T>();
	}
	
	private class LinkedListIterator<E> implements java.util.Iterator<E> {
		
		private Node<E> probe;
		
		/**
		 * Initializes temp variable to head for scan.
		 */
		public LinkedListIterator() {
			probe = (Node<E>) (head);
		}
		
		@Override
		public boolean hasNext() {
			return (probe != null);
		}
		
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("There is no element.");
			}
			E temp = probe.getData();
			probe = probe.getNext();
			return temp;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("We don't support this function.");
		}
		
	}
	
}