/**
 * Assignment to teach dynamic programming using 3 simple example problems:
 * 1. Fibonacci numbers
 * 2. Longest common subsequence
 * 3. Edit distance
 * 
 * @author Daniel Ansher
 */
public class DynamicProgramming {
	
	/**
	 * Calculates the nth fibonacci number: fib(n) = fib(n-1) + fib(n-2).
	 * Remember that fib(0) = 0 and fib(1) = 1.
	 * 
	 * This should NOT be done recursively - instead, use a 1 dimensional
	 * array so that intermediate fibonacci values are not re-calculated.
	 * 
	 * The running time of this function should be O(n).
	 * 
	 * @param n A number 
	 * @return The nth fibonacci number
	 */
	public static int fib(int n) {
		// TODO Auto-generated method stub
		int[] array = new int[n + 1];
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			array[0] = 0;
			array[1] = 1;
			for (int i = 2; i < array.length; i++) {
				array[i] = (array[i-1] + array[i-2]);
			}
			return array[n];
		}
	}
	
	/**
	 * Calculates the length of the longest common subsequence between a and b.
	 * 
	 * @param a
	 * @param b
	 * @return The length of the longest common subsequence between a and b
	 */
	public static int lcs(String a, String b) {
		// TODO Auto-generated method stub
		int[][] array = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i < array.length - 1; i++) {
			array[i][0] = 0;
		}
		for (int j = 0; j < array[0].length - 1; j++) {
			array[0][j] = 0;
		}
		
		for (int j = 1; j < array[0].length; j++) {
			for (int i = 1; i < array.length; i++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					array[i][j] = array[i-1][j-1] + 1;
				} else {
					array[i][j] = Math.max(array[i - 1][j], array[i][j - 1]);
				}
			}
		}
		return array[a.length()][b.length()];
	}

	/**
	 * Calculates the edit distance between two strings.
	 * 
	 * @param a A string
	 * @param b A string
	 * @return The edit distance between a and b
	 */
	public static int edit(String a, String b) {
		// TODO Auto-generated method stub
		int[][] matrix = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i <= a.length(); i++) {
			matrix[i][0] = i;
		}
		for (int j = 0; j <= b.length(); j++) {
			matrix[0][j] = j;
		}
		for (int j = 1; j <= b.length(); j++) {
			for (int i = 1; i <= a.length(); i++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					matrix[i][j] = matrix[i - 1][j - 1];
				} else {
					matrix[i][j] = Math.min(Math.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1), matrix[i - 1][j - 1] + 1);
					
				}
			}
		}
		return matrix[a.length()][b.length()];
	}
}
