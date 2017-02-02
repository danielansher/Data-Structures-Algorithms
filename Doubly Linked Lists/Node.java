/**
 * Node class for the Doubly Linked List
 */
public class Node<E> {
	
	private E data;
	private Node<E> next;
	private Node<E> prev;
	
	/**
	 * Initializes a node with data.
	 */
	public Node(E data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		//TODO
		return data + " ";
	}

	
	/**
	 * Retrieves specific node data.
	 * 
	 * @return the data of the node
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * Retrieves the next specific node.
	 * 
	 * @return the next node
	 */
	public Node<E> getNext() {
		return next;
	}
	
	/**
	 * Retrieves the previous specific node.
	 * 
	 * @return the previous node
	 */
	public Node<E> getPrev() {
		return prev;
	}

	/**
	 * Sets the data of a specific node.
	 * 
	 * @param e The new data replacement
	 */
	public void setData(E e) {
		data = e;
	}

	/**
	 * Sets the next reference to a node.
	 * 
	 * @param e The new node reference
	 */
	public void setNext(Node<E> e) {
		next = e;
	}
	
	/**
	 * Sets the previous reference to a node.
	 * 
	 * @param e The new node reference
	 */
	public void setPrev(Node<E> e) {
		prev = e;
	}
	
}