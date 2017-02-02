/**
 * Your queue implementation. Don't add any new public methods.
 * 
 * @author Steven Wojcio
 *
 */
public class Queue<T> implements QueueInterface<T> {

	private LinkedListInterface<T> queue = new LinkedList<T>();

	@Override
	public void enqueue(T t) {
		// TODO Auto-generated method stub
		queue.addToBack(t);
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		return queue.removeFromFront();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}
	
}
