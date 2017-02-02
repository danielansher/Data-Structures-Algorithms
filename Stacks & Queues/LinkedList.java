
public class LinkedList<T> implements LinkedListInterface<T> {
	
	private Node head;
	private Node tail;
	
	@Override
	public void addToFront(T t) {
		// TODO Auto-generated method stub
		if (t != null) {
			if (head == null) {
				Node<T> toAdd = new Node(t, null);	
				head = toAdd;
				tail = toAdd;
			} else {
				Node<T> toAdd = new Node(t, head);	
				head = toAdd;
			}
		}

	}

	@Override
	public void addToBack(T t) {
		// TODO Auto-generated method stub
		if (head == null) {
			addToFront(t);
		} else {
			if (t != null) {
				Node<T> toAdd = new Node(t, null);
				tail.next = toAdd;
				tail = toAdd;
			}
		}
	}

	@Override
	public T removeFromFront() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return null;
		}
		
		T toReturn = (T) (head.data);		
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			head = head.next;			
		}
		return toReturn;
	}

	@Override
	public T removeFromBack() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return null;
		}
		
		T toReturn = (T) (tail.data);
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			Node<T> element = head;
			while (element.next != tail) {
				element = element.next;
			}
			tail = element;
			element.next = null;
			
		}
		return toReturn;
	}

	@Override
	public T[] toList() {
		// TODO Auto-generated method stub
		Node<T> element = head;
		T[] toReturn = (T[]) (new Object[size()]);
		int i = 0;
		while (element != null) {
			toReturn[i] = element.data;
			i++;
			element = element.next;
			
		}
		return toReturn;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (head == null);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		Node<T> element = head;
		int count = 0;
		
		while (element != null) {
			count++;
			element = element.next;
		}
		return count;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head = null;
		tail = null;
	}

	/**
	 * The node class. Remember that this is a singularly linked list.
	 * 
	 * @author Steven Wojcio
	 */
	private class Node<T> {
		private T data;
		private Node<T> next;
		
		private Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
	}

}
