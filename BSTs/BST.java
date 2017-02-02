import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation for a Binary Search Tree.
 * It utilizes the property that everything
 * to the left of a given node will be less than it, 
 * and all nodes to the right greater than it.
 *
 * 
 * @author Daniel Ansher
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
	private Node<T> root;
	
	@Override
	public void add(T data) {
		if (data == null) {
			throw new IllegalArgumentException();
		}
		root = addHelper(data, root);
	}

    /**
     * Helper method for adding data to the binary search tree.
     * All data to the left of a node must be less than it, 
     * and all data to the right must be greater than it.
     * Does not add duplicate data to the tree.
     * 
     * @param data Data to be added to the tree
     * @param node The node to evaluate
     * 
     * @return the node to be added
     *            
     */
	private Node<T> addHelper(T data, Node<T> node) {		
		if (node == null) {
			Node<T> toAdd = new Node<T>(data);
			return toAdd;
		} else if (data.compareTo(node.getData()) < 0) {
			node.setLeft(addHelper(data, node.getLeft()));
			return node;
		} else if (data.compareTo(node.getData()) > 0) {
			node.setRight(addHelper(data, node.getRight()));
			return node;
		}
		return node;
	}
	
	@Override
    public void addAll(Collection<T> c) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		for (T element : c) {
    		add(element);
    	}
    }
	
    @Override
	public T remove(T data) {
		if (data == null) {
			throw new IllegalArgumentException();
		}
		return removeHelper(data, root, null);
	}
	
    /**
     * Helper method for removing data from the binary search tree.
     * 
     * @param data Data to be removed from the tree
     * @param node The node to evaluate
     * 
     * @return the data that has been removed. Returns null if it 
     *         doesn't exist
     *            
     */
	public T removeHelper(T data, Node<T> node, Node<T> parent) {		
		if (node == null) {
			return null;
		} else if (data.compareTo(node.getData()) < 0) {
			return removeHelper(data, node.getLeft(), node);
		} else if (data.compareTo(node.getData()) > 0) {
			return removeHelper(data, node.getRight(), node);
		} else {
			T toReturn = node.getData();
			if (node.getLeft() != null && node.getRight() != null) { //node has 2 children
				T temp = remove(minFromRight(node.getRight()).getData());
				node.setData(temp);
			} else if (node.getLeft() != null) { // node has left child
				if (parent == null) {
					root = node.getLeft();
				} else if (parent.getLeft() == node) {
					parent.setLeft(node.getLeft());
				} else {
					parent.setRight(node.getLeft());
				}	
			} else if (node.getRight() != null) { //node has right child
				if (parent == null) {
					root = node.getRight();
				} else if (parent.getLeft() == node) {
					parent.setLeft(node.getRight());
				} else {
					parent.setRight(node.getRight());
				}
			} else { // node has no children
				if (parent == null) {
					root = null;
				} else if (parent.getLeft() == node) {
					parent.setLeft(null);
				} else {
					parent.setRight(null);
				}
			}
			return toReturn;
		}
	}
	
    /**
     * Helper method for finding a successor of the tree
     * for the purpose of removing data from the binary 
     * search tree.
     * 
     * @param toTraverse Node to be evaluated for successor
     * 
     * @return the successor node
     *            
     */
	private Node<T> minFromRight(Node<T> toTraverse) {
		if (toTraverse.getLeft() == null) {
			return toTraverse;
		} else if (toTraverse.getLeft() != null) {
			minFromRight(toTraverse.getLeft());
		}
		return null;
	}
	
	@Override
    public T get(T data) {
    	if (data == null) {
    		throw new IllegalArgumentException();
    	}
    	return getHelper(data, root);

    }
    
    /**
     * Helper method for retrieving data from the binary search tree.
     * 
     * @param data Data to be removed from the tree
     * @param node The node to evaluate
     * 
     * @return the data that was found in the tree. Null if never found
     *            
     */	
    private T getHelper(T data, Node<T> node) {
    	if (node == null) {
    		return null;
    	} else if (node.getData().compareTo(data) == 0) {
    		return node.getData();
    	} else if (node.getData().compareTo(data) > 0) {
    		return getHelper(data, node.getLeft());
    	} else {
    		return getHelper(data, node.getRight());
    	}
    }
	
    @Override
	public boolean contains(T data) {
		return (get(data) != null);
	}
	
	@Override
    public List<T> preOrder() {
    	List<T> linkedlist = new LinkedList<>();
    	preOrderHelper(root, linkedlist);
    	return linkedlist;
    }
    
    /**
     * Helper method for creating a list of elements of the
     * BST in pre-order linearization.
     * 
     * @param curr Data the current element
     * @param list The list that elements are added to
     *            
     */
    private void preOrderHelper(Node<T> curr, List<T> list) {
    	if (curr == null) {
    		return;
    	}
    	list.add(curr.getData());
    	preOrderHelper(curr.getLeft(), list);
    	preOrderHelper(curr.getRight(), list);
    	
    }
    
    @Override
    public List<T> inOrder() {
    	List<T> linkedlist = new LinkedList<>();

    	inOrderHelper(root, linkedlist);
    	return linkedlist;
    }

    /**
     * Helper method for creating a list of elements of the
     * BST in in-order linearization.
     * 
     * @param curr Data the current element
     * @param list The list that elements are added to
     *            
     */
    private void inOrderHelper(Node<T> curr, List<T> list) {

    	if (curr == null) {
    		return;
    	}

    	inOrderHelper(curr.getLeft(), list);
    	list.add(curr.getData());
    	inOrderHelper(curr.getRight(), list);   	
    }
    
    @Override
    public List<T> postOrder() {
    	List<T> linkedlist = new LinkedList<>();
    	postOrderHelper(root, linkedlist);
    	return linkedlist;
    }
   
    /**
     * Helper method for creating a list of elements of the
     * BST in post-order linearization.
     * 
     * @param curr Data the current element
     * @param list The list that elements are added to
     *            
     */
    private void postOrderHelper(Node<T> curr, List<T> list) {
    	if (curr == null) {
    		return;
    	}
    	postOrderHelper(curr.getLeft(), list);
    	postOrderHelper(curr.getRight(), list);
    	list.add(curr.getData());
    }  
    
    @Override
    public List<T> levelOrder() {
    	return null;
    }
    
    @Override
    public boolean isEmpty() {
    	return (root == null);
    }
    
    @Override
    public int size() {
    	return inOrder().size();
    }
    
    @Override
    public void clear() {
    	root = null;
    }
	
    @Override
	public String toString() {
		if (root == null) {
		return "()";
		}
		return root.toString();
	}
	

}
