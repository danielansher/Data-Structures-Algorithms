import java.util.ArrayList;
import java.util.List;
/**
 * Implementation of String Search using two algorithms: Boyer-Moore
 * and Rabin Karpe methods. 
 *
 * @author Daniel Ansher
 */
public class StringSearch implements StringSearchInterface {
	private int nthBase;

	@Override
	public List<Integer> boyerMoore(String needle, String haystack) {
		List<Integer> toReturn = new ArrayList<Integer>();
		if (needle == null || haystack == null || needle.length() == 0 || haystack.length() == 0) {
			return toReturn;
		}
		int hayLength = haystack.length();
		int needleLength = needle.length();
		int[] array = buildLastTable(needle);
		int index = 0;
		while (index + (needleLength - 1) < hayLength) {
			for (int needleIndex = needleLength - 1; needleIndex >= 0; needleIndex--) {
				int hayIndex = index + needleIndex;
				if (hayIndex >= hayLength) {
					break;
				}
				char haystackChar = haystack.charAt(hayIndex);
				char needleChar = needle.charAt(needleIndex);
				if (haystackChar != needleChar) {
					if (!charMatch(needle, haystackChar)) {
						index += array[haystackChar];
						index -= ((needleLength - 1) - needleIndex);
					} else {
						index += array[haystackChar];
					}
					break;
				} else if (needleIndex == 0) {
					toReturn.add(index);
					index++;
				}
			}
		}

		return toReturn;
	}
	
	/**
	 * Checks if letter matches any character in needle.
	 *
	 * @param needle The string to iterate with
	 * @param letter The letter to check
	 * 
	 * @return whether there a match or not
	 */
	private boolean charMatch(String needle, char character) {
		   for (int i = 0; i <= needle.length() - 1; i++) {
		     if (needle.charAt(i) == character) {
		       return true;
		     }
		   }
		   return false;
		 }

	@Override
	public int[] buildLastTable(String needle) {
		if (needle == null) {
			throw new IllegalArgumentException();
		}
		int[] map = new int[Character.MAX_VALUE + 1];
		for (int i = 0; i < map.length; i++) {
			map[i] = needle.length();
		}
		for (int i = 0; i < needle.length(); i++) {
			int value = Math.max(needle.length() - needle.lastIndexOf(needle.charAt(i)) - 1,  1);
			map[needle.charAt(i)] = value;
		}
		return map;
	}

	@Override
	public int generateHash(String current) {
		if (current == null) {
			throw new IllegalArgumentException();
		}
		nthBase = pow(BASE, current.length() - 1);
		int hash = 0;
		for (int i = 0; i < current.length(); i++) {
			char x = current.charAt(i);
			hash += pow(BASE, current.length() - 1 - i) * x;
		}
		return hash;
	}

	/**
	 * Exponential number generation.
	 *
	 * @param base The base number calculation
	 * @param exp The power of the calculation
	 * 
	 * @return the exponential number
	 */
	private static int pow(int base, int exp) {
		int val = 1;
		for (int i = exp; i > 0; i--) {
			val *= base;
		}
		return val;
	}

	@Override
	public int updateHash(int oldHash, int length, char oldChar, char newChar) {
		return (oldHash - (oldChar * nthBase)) * BASE + newChar;
	}

	/**
	 * Exponential number generation specifically with the base.
	 *
	 * @param exp The power of the calculation
	 * 
	 * @return the exponential number
	 */
	private static int basePow(int exp) {
		return pow(BASE, exp);
	}

	/**
	 * Checks to see if each letter of the strings match.
	 *
	 * @param one First string
	 * @param two Second string
	 * 
	 * @return whether they match or not
	 */
	private static boolean check(String a, String b) {
		if (a == null || b == null || a.length() != b.length()) {
			return false;
		}

		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public List<Integer> rabinKarp(String needle, String haystack) {
		List<Integer> toReturn = new ArrayList<Integer>();
		if (needle == null || haystack == null || needle.length() == 0 || haystack.length() == 0) {
			return toReturn;
		}
		int hayLength = haystack.length();
		int needleLength = needle.length();
		int basePow = basePow(needleLength - 1);
		int hashN = generateHash(needle);
		if (needleLength > hayLength) {
			return toReturn;
		}
		int hashSubstring = generateHash(haystack.substring(0, needleLength));
		for (int i = 0; i < hayLength - needleLength + 1; i++) {
			String substring = haystack.substring(i, i + needleLength);
			if (hashN == hashSubstring && check(needle, substring)) {
				toReturn.add(i);
			}
			int boyermoore = i + needleLength;
			if (boyermoore < hayLength) {
				hashSubstring = updateHash(hashSubstring, basePow, haystack.charAt(i), haystack.charAt(boyermoore));
			}
		}
		return toReturn;
	}	
}
