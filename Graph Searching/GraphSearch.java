/**
 * This class provides a general graph search using Breadth-first Search and
 * depth-first search. Also, implements Djikstra's Shortest Path Algorithm.
 * 
 * @author Daniel Ansher
 */
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class GraphSearch {

	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using General Graph Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be added to the Structure.
	 *
	 * The structure(struct) passed in is an empty structure may behave as a Stack or Queue and the
	 * correspondingly search function should execute DFS(Stack) or BFS(Queue) on the graph.
	 *
	 * @param start
	 * @param struct
	 * @param adjList
	 * @param goal
	 * @return true if path exists false otherwise
	 */
	public static <T> boolean generalGraphSearch(T start, Structure<T> struct, Map<T, List<T>> adjList, T goal) {
		// TODO Implement General Graph Search here
		if (start == null || struct == null || adjList == null || goal == null) {
			throw new IllegalArgumentException();
		}
		if (!adjList.containsKey(start)) {
			return false;
		}
		Set<T> visited = new HashSet<T>();
		struct.add(start);
		while (!struct.isEmpty()) {
			T cur = struct.remove();
			if (!visited.contains(cur)) {
				visited.add(cur);
				if (cur.equals(goal)) {
					return true;
				} else {
					for (T neighbor : adjList.get(cur)) {
						struct.add(neighbor);
					}
				}
			}
		}
		return false;
	}

	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using Bredth First Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be added to the Structure.
	 *
	 * @param start
	 * @param adjList
	 * @param goal
	 * @return true if path exists false otherwise
	 */
	public static <T> boolean breadthFirstSearch(T start, Map<T, List<T>> adjList, T goal) {
		// TODO Implement Breadth First Search here
		return generalGraphSearch(start, new StructureQueue<T>(), adjList, goal);
	}

	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using Depth First Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be added to the Structure.
	 *
	 * @param start
	 * @param adjList
	 * @param goal
	 * @return true if path exists false otherwise
	 */
	public static <T> boolean depthFirstSearch(T start, Map<T, List<T>> adjList, T goal) {
		// TODO Implement Depth First Search here
		return generalGraphSearch(start, new StructureStack<T>(), adjList, goal);
	}

	/**
	 * Find the shortest distance between the start node and the goal node in the given a weighted graph
	 * in the form of an adjacency list where the edges only have positive weights
	 * Return the aforementioned shortest distance if there exists a path between the start and goal,-1
	 * otherwise.
	 *
	 * There are no negative edge weights in the graph.
	 *
	 * @param start
	 * @param adjList
	 * @param goal
	 * @return the shortest distance between the start and the goal node
	 */
	public static <T> int djikstraShortestPathAlgorithm(T start, Map<T, List<Pair<T, Integer>>> adjList, T goal) {
		// TODO Implement Djikstra's Shortest Path Algorithm here
		if (start == null || adjList == null || goal == null) {
			throw new IllegalArgumentException();
		}
		if (!adjList.containsKey(start)) {
			return -1;
		}
		Comparator<Pair<T, Integer>> comparator = new Comparator<Pair<T, Integer>>() {
			public int compare(Pair<T, Integer> sg1, Pair<T, Integer> sg2) {
				return sg1.b - sg2.b;
			}
		};
		Set<T> visited = new HashSet<T>();
		PriorityQueue<Pair<T, Integer>> manager = new PriorityQueue<Pair<T, Integer>>(adjList.size(), comparator);
		boolean done = false;
		List<Pair<T, Integer>> neighbors;
		Pair<T, Integer> removed = new Pair<T, Integer>(start, 0);
		manager.add(removed);
		while (!manager.isEmpty() && !done) {
			removed = manager.remove();
			if (!visited.contains(removed.a)) {
				neighbors = adjList.get(removed.a);
				visited.add(removed.a);				
				if (removed.a.equals(goal)) {
					done = true;
				} else {
					if (!neighbors.isEmpty()) {
						Pair<T, Integer> neighbor;
						for (int i = 0; i < neighbors.size(); i++) {
							neighbor = neighbors.get(i);
							if (!visited.contains(neighbor.a)) {
								int newWeight = removed.b + neighbor.b;
								manager.add(new Pair<T, Integer>(neighbor.a, newWeight));
							}
						}				
					}				
				}
			}
		}

		if (done) {
			return removed.b;
		} else {
			return -1;
		}
	}
}