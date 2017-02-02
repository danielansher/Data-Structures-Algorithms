import java.util.Random;
import java.util.Scanner;
/**
 * This class calculates comparable sorting algorithm times
 * based off a random array with a size generated
 * by the client.
 * 
 * @author Daniel Ansher
 * 
 */
public class Driver {

	/**
	 * Driver method, asks the user for a size of array.
	 * Randomly generates an array of ints based on inputted size
	 * and calculates speeds for each sorting algorithm.
	 */	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int response = 0;
		do {
			System.out.print("Please enter the size of the array you would like to sort or enter 0 to exit: ");			
			response = keyboard.nextInt();
			if (response != 0) {
				Random r = new Random();
				Sorting toSort = new Sorting();
				Integer[] randomArr = new Integer[response];
				for (int i = 0; i < response; i++) {
					int random = r.nextInt(19999) - 10000;
					randomArr[i] = new Integer(random);
				}
				Integer[] randomArrCpy1 = randomArr;
				Integer[] randomArrCpy2 = randomArr;
				Integer[] randomArrCpy3 = randomArr;
				Integer[] randomArrCpy4 = randomArr;

				System.out.print("\nThe unsorted array is [ ");
				for (int i = 0; i < randomArr.length; i++) {
					System.out.print(randomArr[i] + " ");
				}
				System.out.println("]");

				long now = 0;
				long later = 0;
				double finalDuration = 0.0;

				now = System.nanoTime();
				toSort.bubblesort(randomArr);
				later = System.nanoTime();
				finalDuration = (later - now) / 1000000000.0;

				System.out.print("The sorted array is [ ");
				for (int i = 0; i < randomArr.length; i++) {
					System.out.print(randomArr[i] + " ");
				}
				System.out.println("]\n");


				System.out.println("It took bubble sort " + finalDuration + " seconds.");

				now = 0;
				later = 0;
				finalDuration = 0.0;				

				now = System.nanoTime();
				toSort.insertionsort(randomArrCpy1);
				later = System.nanoTime();
				finalDuration = (later - now) / 1000000000.0;
				System.out.println("It took insertion sort " + finalDuration + " seconds.");

				now = 0;
				later = 0;
				finalDuration = 0.0;				

				now = System.nanoTime();
				toSort.selectionsort(randomArrCpy2);
				later = System.nanoTime();
				finalDuration = (later - now) / 1000000000.0;
				System.out.println("It took selection sort " + finalDuration + " seconds.");

				now = 0;
				later = 0;
				finalDuration = 0.0;				

				now = System.nanoTime();
				toSort.quicksort(randomArrCpy3, r);
				later = System.nanoTime();
				finalDuration = (later - now) / 1000000000.0;
				System.out.println("It took quick sort " + finalDuration + " seconds.");

				now = 0;
				later = 0;
				finalDuration = 0.0;				

				now = System.nanoTime();
				toSort.mergesort(randomArrCpy4);
				later = System.nanoTime();
				finalDuration = (later - now) / 1000000000.0;
				System.out.println("It took merge sort " + finalDuration + " seconds.");

				int[] randomArrRadix = new int[response];
				for (int j = 0; j < response; j++) {
					int random = r.nextInt();
					randomArr[j] = random;
				}
				now = 0;
				later = 0;
				finalDuration = 0.0;
				now = System.nanoTime();
				toSort.radixsort(randomArrRadix);
				later = System.nanoTime();
				finalDuration = (later - now) / 1000000000.0;
				System.out.println("It took radix sort " + finalDuration + " seconds.\n");				
			}

		} while(response != 0);
	}

}
