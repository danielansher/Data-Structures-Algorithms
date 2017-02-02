import java.util.Random;
/**
 * This class implements six different sorting
 * algorithms.
 * 
 * @author Daniel Ansher
 * 
 */
public class Sorting implements SortingInterface {

	@Override
	public <T extends Comparable<T>> void bubblesort(T[] arr) {
		boolean swapped = false;
		do {
			swapped = false;
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i].compareTo(arr[i + 1]) > 0) {
					T toReplace = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = toReplace;
					swapped = true;
				}
			}

		} while (swapped);
	}

	@Override
	public <T extends Comparable<T>> void insertionsort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			T toCompare = arr[i];
			int j = 0;
			for (j = i - 1; j >= 0 && (toCompare.compareTo(arr[j]) < 0); j--) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = toCompare;
		}
	}

	@Override
	public <T extends Comparable<T>> void selectionsort(T[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			T foundSmaller = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i] = foundSmaller;
		}
	}

	@Override
	public <T extends Comparable<T>> void quicksort(T[] arr, Random r) {
		quicksortHelper(arr, r, 0, arr.length - 1);
	}

	/**
	 * Helper method for QuickSort. Finds the pivot and then
	 * rearranges elements less than the pivot left and elements
	 * more than the pivot right, and recursively does the same with
	 * two left and right sub-arrays until fully sorted.
	 * 
	 * @param arr The array to be sorted
	 * @param lowIndex The lower bound
	 * @param highIndex The upper bound
	 * @param r The random number generator for pivot generation
	 */
	private static <T extends Comparable<T>> void quicksortHelper(T[] arr, Random r, int lowIndex, int highIndex) {
		if (lowIndex >= highIndex) {
			return;
		}
		int pivotIndex = r.nextInt(highIndex - lowIndex) + lowIndex;
		int newPivotIndex = partition(arr, lowIndex, highIndex, pivotIndex);
		quicksortHelper(arr, r, lowIndex, newPivotIndex - 1);
		quicksortHelper(arr, r, newPivotIndex, highIndex);
	}

	/**
	 * Helper method for QuickSort algorithm. Finds the partition index to
	 * aid in fully sorting the array.
	 * 
	 * @param arr The array to be sorted
	 * @param lowIndex The lower bound
	 * @param highIndex The upper bound
	 * @param pivotIndex The index of the pivot number
	 */
	private static <T extends Comparable<T>> int partition(T[] arr, int lowIndex, int highIndex, int pivotIndex) {
		T pivotValue = arr[pivotIndex];
		while (lowIndex <= highIndex) {
			while (arr[lowIndex].compareTo(pivotValue) < 0) {
				lowIndex++;
			}
			while (arr[highIndex].compareTo(pivotValue) > 0) {
				highIndex--;
			}
			if (lowIndex <= highIndex) {
				T toReplace = arr[lowIndex];
				arr[lowIndex] = arr[highIndex];
				arr[highIndex] = toReplace;
				highIndex--;
				lowIndex++;
			}
		}
		return lowIndex;
	}


	@Override
	public <T extends Comparable<T>> T[] mergesort(T[] arr) {
		if (arr.length > 1) {
			int firstHalf = arr.length / 2;
			int secondHalf = firstHalf;
			if ((arr.length % 2) == 1) {
				secondHalf = secondHalf + 1;
			}
			T[] arr1 = (T[]) (new Comparable[firstHalf]);
			T[] arr2 = (T[]) (new Comparable[secondHalf]);
			for (int i = 0; i < firstHalf; i++) {
				arr1[i] = arr[i];
			}
			for (int i = firstHalf; i < firstHalf + secondHalf; i++) {
				arr2[i - firstHalf] = arr[i];
			}
			arr1 = mergesort(arr1);
			arr2 = mergesort(arr2);

			int i = 0, j = 0, k = 0;
			while (firstHalf != j && secondHalf != k) {
				if (arr1[j].compareTo(arr2[k]) < 0) {
					arr[i] = arr1[j];
					i++;
					j++;
				}
				else {
					arr[i] = arr2[k];
					i++;
					k++;
				}
			}
			while (arr1.length != j) {
				arr[i] = arr1[j];
				i++;
				j++;
			}
			while (arr2.length != k) {
				arr[i] = arr2[k];
				i++;
				k++;
			}
		}
		return arr;
	}

	@Override
	public int[] radixsort(int[] arr) {		
		int index = arr[0];
		int initial = arr[0];
		int arrayLength = arr.length;
		for (index = 1; index < arrayLength; index++) {
			if (arr[index] > initial) {
				initial = arr[index];
			}
		}
		int exponent = 1;
		int[] arrayAdjust = new int[10];
		while ((initial / exponent) > 0) {
			int[] buckets = new int[10];
			for (index = 0; index < arrayLength; index++) {
				int toManip = arr[index] / exponent;
			}
			for (index = 1; index < 10; index++) {
				buckets[index] = buckets[index] + buckets[index - 1];
			}
			for (index = arrayLength - 1; index >= 0; index--) {
				int toManip = arr[index] / exponent;
				arrayAdjust[--buckets[toManip % 10]] = arr[index];
			}
			for (index = 0; index < arrayLength; index++) {
				arr[index] = arrayAdjust[index];
			}
			exponent = exponent * 10;
		}

		return arr;
	}

}
