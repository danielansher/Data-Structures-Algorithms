/**
 * Implementation for a AVL Binary Search Tree.
 * It utilizes the property that everything
 * will be balanced after addition and removal.
 *
 * @author Daniel Ansher 
 */
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class AVL<T extends Comparable<? super T>> implements BSTInterface<T> {
	private Node<T> root;


	@Override
	public void add(T data) {
		if (data == null) {
			throw new IllegalArgumentException();
		}
		root = addHelper(root, data);
	}

	/**
	 * Helper method for adding data to the AVL.
	 * All data to the left of a node must be less than it, 
	 * and all data to the right must be greater than it.
	 * Does not add duplicate data to the tree.
	 * 
	 * @param toManip The node to evaluate
	 * @param data Data to be added to the tree
	 * 
	 * @return the node to be added
	 *            
	 */
	private Node<T> addHelper(Node<T> toManip, T data) {
		if (toManip == null) {
			Node<T> toReturn = new Node<T>(data);
			toReturn.setHeight(1);
			toReturn.setBalanceFactor(0);
			return toReturn;
		} else if (toManip.getData().compareTo(data) == 0) {
			return toManip;    
		} else if (data.compareTo(toManip.getData()) < 0) {
			toManip.setLeft(addHelper(toManip.getLeft(), data));
			toManip.setHeight(1 + toManip.getLeft().getHeight());
			if (toManip.getRight() != null) {
				toManip.setBalanceFactor(toManip.getLeft().getHeight() - toManip.getRight().getHeight());
			} else {
				toManip.setBalanceFactor(toManip.getLeft().getHeight());
			}
			toManip = properBalance(toManip);
			return toManip;
		} else {
			toManip.setRight(addHelper(toManip.getRight(), data));
			toManip.setHeight(1 + toManip.getRight().getHeight());
			if (toManip.getLeft() != null) {
				toManip.setBalanceFactor(toManip.getLeft().getHeight() - toManip.getRight().getHeight());
			} else {
				toManip.setBalanceFactor(0 - toManip.getRight().getHeight());
			}
			toManip = properBalance(toManip);
			return toManip;
		}
	}

	/**
	 * Helper method for balancing data in the AVL.
	 * 
	 * @param toManip The unbalanced tree
	 * 
	 * @return the balanced tree
	 *            
	 */	
	private Node<T> properBalance(Node<T> toManip) {
		if (toManip.getBalanceFactor() < -1) {
			if (toManip.getRight().getBalanceFactor() < 0) {
				toManip = rotateL(toManip);
			} else {
				toManip = rotateRL(toManip);
			}
		} else if (toManip.getBalanceFactor() > 1) {			
			if (toManip.getLeft().getBalanceFactor() > 0) {
				toManip = rotateR(toManip);
			} else {
				toManip = rotateLR(toManip);
			}
		}
		return toManip;
	}

	/**
	 * Helper method for rotating data in the AVL left.
	 * 
	 * @param toManip The node to rotate
	 * 
	 * @return the balanced node
	 *            
	 */	
	private Node<T> rotateL(Node<T> toManip) {  
		Node<T> right = toManip.getRight();
		toManip.setRight(right.getLeft());
		right.setLeft(toManip);
		if (toManip.getLeft() != null) {
			toManip.setHeight(toManip.getLeft().getHeight() + 1);
		} else {
			toManip.setHeight(1);
		}
		right.setHeight(toManip.getHeight() + 1);
		if (right.getRight() != null) {
			right.setBalanceFactor(right.getLeft().getHeight() - right.getRight().getHeight());
		} else {
			right.setBalanceFactor(right.getLeft().getHeight());
		}
		if (toManip.getLeft() != null && toManip.getRight() != null) {
			toManip.setBalanceFactor(toManip.getLeft().getHeight() - toManip.getRight().getHeight());
		} else if (toManip.getLeft() != null) {
			toManip.setBalanceFactor(toManip.getLeft().getHeight());
		} else if (toManip.getRight() != null) {
			toManip.setBalanceFactor(0 - toManip.getRight().getHeight());
		} else {
			toManip.setBalanceFactor(0);
		}
		return right;
	}

	/**
	 * Helper method for rotating data in the AVL right.
	 * 
	 * @param toManip The node to rotate
	 * 
	 * @return the balanced node
	 *            
	 */	
	private Node<T> rotateR(Node<T> toManip) {
		Node<T> leftNode = toManip.getLeft();
		toManip.setLeft(toManip.getLeft().getRight());
		leftNode.setRight(toManip);
		if (toManip.getRight() != null) {
			toManip.setHeight(toManip.getRight().getHeight() + 1);
		} else {
			toManip.setHeight(1);
		}
		leftNode.setHeight(toManip.getHeight() + 1);
		if (leftNode.getLeft() != null) {
			leftNode.setBalanceFactor(leftNode.getLeft().getHeight() - leftNode.getRight().getHeight());
		} else {
			leftNode.setBalanceFactor(0 - leftNode.getRight().getHeight());
		}
		//*************************
		if (toManip.getLeft() != null && toManip.getRight() != null) {
			toManip.setBalanceFactor(toManip.getLeft().getHeight() - toManip.getRight().getHeight());
		} else if (toManip.getLeft() != null) {
			toManip.setBalanceFactor(toManip.getLeft().getHeight());
		} else if (toManip.getRight() != null) {
			toManip.setBalanceFactor(0 - toManip.getRight().getHeight());
		} else {
			toManip.setBalanceFactor(0);
		}
		return leftNode;
	}

	/**
	 * Helper method for double rotation of data in the AVL.
	 * Moves nodes in order and then does standard
	 * right rotation.
	 * 
	 * @param toManip The node to rotate
	 * 
	 * @return the balanced node
	 *            
	 */	
	private Node<T> rotateLR(Node<T> toManip) {
		toManip.setLeft(rotateL(toManip.getLeft()));
		return rotateR(toManip);
	}

	/**
	 * Helper method for double rotation of data in the AVL.
	 * Moves nodes in order and then does standard
	 * left rotation.
	 * 
	 * @param toManip The node to rotate
	 * 
	 * @return the balanced node
	 *            
	 */	
	private Node<T> rotateRL(Node<T> toManip) {
		toManip.setRight(rotateR(toManip.getRight()));
		return rotateL(toManip);
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
		Node<T> toReturn = new Node<T>(null);
		if (root == null) {
			return null;
		} else if (root.getLeft() == null && root.getRight() == null) {
			toReturn.setData(root.getData());
			root = null;
			return toReturn.getData();
		} else {
			root = removeHelper(root, data, toReturn);    	
		}
		return toReturn.getData();
	}


	/**
	 * Remove the data element from the tree and rebalances elements.
	 * for 0 children, the node is pointed to null. When the node
	 * has one child, the parent pointer is changed to the left child's left child
	 * or the right child's right child. When the node has 2 children, the node removes
	 * its successor 
	 * If the data is null throw IllegalArgumentException.
	 * 
	 * @param root The node that is examined.
	 * @param data The data to be removed
	 *            
	 * @return The data that was removed from the tree. Return null if
	 *         the data doesn't exist.
	 */
	private Node<T> removeHelper(Node<T> root, T data, Node<T> temp) {
		if (root != null) {
			if (data.compareTo(root.getData()) == 0) {
				temp.setData(root.getData());
				root = removeRoot(root);
				if (root == null) {
					return root;
				} else if (root.getLeft() == null) {
					root.setHeight(root.getHeight() - 1);
				} else if (root.getLeft() != null && root.getRight() != null) {
					root.setBalanceFactor(root.getLeft().getHeight() - root.getRight().getHeight());
				} else if (root.getLeft() != null) {
					root.setBalanceFactor(root.getLeft().getHeight());
				} else if (root.getRight() != null) {
					root.setBalanceFactor(0 - root.getRight().getHeight());
				} else {
					root.setBalanceFactor(0);
				}
			} else if (data.compareTo(root.getData()) < 0) {
				Node<T> left = root.getLeft();
				root.setLeft(removeHelper(root.getLeft(), data, temp));
				if (left == null) {
					return root;
				} else if (root.getRight() == null) {
					root.setHeight(root.getHeight() - 1);
				} else if (root.getLeft() != null && root.getRight() != null) {
					root.setBalanceFactor(root.getLeft().getHeight() - root.getRight().getHeight());
				} else if (root.getLeft() != null) {
					root.setBalanceFactor(root.getLeft().getHeight());
				} else if (root.getRight() != null) {
					root.setBalanceFactor(0 - root.getRight().getHeight());
				} else {
					root.setBalanceFactor(0);
				}
				root = properBalance(root);
			} else {
				Node<T> right = root.getRight();
				root.setRight(removeHelper(right, data, temp));
				if (right == null) {
					return root;
				} else if (root.getLeft() == null) {
					root.setHeight(root.getHeight() - 1);
				} else if (root.getLeft() != null && root.getRight() != null) {
					root.setBalanceFactor(root.getLeft().getHeight() - root.getRight().getHeight());
				} else if (root.getLeft() != null) {
					root.setBalanceFactor(root.getLeft().getHeight());
				} else if (root.getRight() != null) {
					root.setBalanceFactor(0 - root.getRight().getHeight());
				} else {
					root.setBalanceFactor(0);
				}
				root = properBalance(root);
			}
		}
		return root;
	}

	/**
	 * Helper method that removes from the root node.
	 * 
	 * @param root The node to be examined
	 * 
	 * @return The new root with adjusted values
	 */
	private Node<T> removeRoot(Node<T> root) {
		if (root.getRight() != null) {
			Node<T> smallestNode = findMin(root.getRight());
			root.setData(smallestNode.getData());
			root.setRight(removeMin(root.getRight()));
		} else if (root.getLeft() != null) {
			root.setData(root.getLeft().getData());
			root.setRight(root.getLeft().getRight());
			root.setLeft(root.getLeft().getLeft());
		} else {
			root = root.getRight();
		}
		return root;
	}

	/**
	 * Helper method that finds the min values of
	 * the tree.
	 * 
	 * @param node The node to be examined
	 * 
	 * @return the min value node
	 */
	private Node<T> findMin(Node<T> node) {
		if (node.getLeft() != null) {
			node = findMin(node.getLeft());
		}
		return node;
	}

	/**
	 * Helper method that removes the min value from 
	 * the tree.
	 * 
	 * @param node The node to be examined
	 * 
	 * @return The node with new tree values
	 */
	private Node<T> removeMin(Node<T> node) {
		if (node.getLeft() != null) {
			node.setLeft(removeMin(node.getLeft()));
		} else {
			node = node.getRight();
		}
		return node;
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

	/**
	 * See if the tree contains the data.
	 * If the data is null throw IllegalArgumentException. 
	 * 
	 * @param data The data to search for in the tree.
	 *            
	 * @return Return true if the data is in the tree, false otherwise.
	 */
	public boolean contains(T data) {
		return (get(data) != null);
	}

	@Override
	public List<T> preOrder() {
		List<T> linkedlist = new LinkedList<T>();
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
		List<T> linkedlist = new LinkedList<T>();

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
		List<T> linkedlist = new LinkedList<T>();
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
		List<T> list = new ArrayList<T>();
		levelOrderHelper(list, root);
		return list;
	}

	/**
	 * Helper method for creating a list of elements of the
	 * BST in level-order linearization (iterative).
	 * 
	 * @return A list that contains every element in level-order.
	 *
	 */
	private void levelOrderHelper(List<T> list, Node<T> node) {
		if (node != null) {
			LinkedList<Node<T>> q = new LinkedList<Node<T>>();
			q.addLast(node);
			while (!q.isEmpty()) {
				Node<T> curr = q.poll();
				if (curr.getLeft() != null) {
					q.addLast(curr.getRight());
				}
				if (curr.getRight() != null) {
					q.addLast(curr.getLeft());
				}
				list.add(curr.getData());
			}
		}
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


	/**
	 * A suggested private helper method
	 * You do not have to use this if you don't want to
	 * @param unbalanced Node of subtree that is unbalanced
	 * @return the balanced subtree
	 */
	private Node<T> rotate(Node<T> unbalanced) {
		return null;
	}


	//DO NOT MODIFY OR USE ANY OF THE METHODS BELOW IN YOUR IMPLEMENTATION
	//These methods are for testing purposes only
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
}
