 import java.util.HashSet;
import java.util.Hashtable;

public class ArrayNString {

	public static void main(String[] args) {
		ArrayNString a = new ArrayNString();
		// char[] str = "to trong hien       ".toCharArray();
		// int[][] M = new int[2][3];
		// M[0][0] = 1;
		// M[0][1] = 2;
		// M[0][2] = 3;
		// M[1][0] = 4;
		// M[1][1] = 0;
		// M[1][2] = 6;
		// for (int i = 0; i < 2; i++) {
		// for (int j = 0; j < 3; j++)
		// System.out.print(M[i][j] + " ");
		// System.out.println();
		// }
		// a.rewriteMatrix(M);
		// for (int i = 0; i < 2; i++) {
		// for (int j = 0; j < 3; j++)
		// System.out.print(M[i][j] + " ");
		// System.out.println();
		// }

		// String[] str = { "to", "trong", "hien", "to" };
		// a.distinctWords2(str);
		// for (String s : str) {
		// System.out.println(s);
		// }

		int[] arr = { 1, 2, 2, 4, 5 };
		int sum = 6;
		a.findPairs(arr, sum);
	}

	/**
	 * Design an algorithm to find all pairs of integers within an array which
	 * sum to a specified value.
	 * 
	 * For example: 1,2,3,4,5, sum = 5 return 2,3
	 */

	// a pairs cannot be in the same value
	// no duplicated values
	// complexity: O(N) --> sorting is not good
	// without an extra storage, using in place sorting (quicksort) and iterate
	// all all the element and use binary search
	public void findPairs(int[] list, int sum) {
		if (list == null)
			return;
		Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
		for (int i = 0; i < list.length; i++) {
			ht.put(list[i], i);
		}

		for (int i = 0; i < list.length; i++) {
			int remainder = sum - list[i];
			if (ht.containsKey(remainder)) {
				if (ht.get(remainder) != i)
					System.out.println(list[i] + " " + remainder);
			}
		}
	}

	// return distinct words of a string, using hashset, O(N)
	public HashSet<String> distinctWords(String str) {
		if (str == null)
			return null;
		HashSet<String> hs = new HashSet<String>();
		String[] strs = str.split(" ");

		for (String s : strs) {
			if (!hs.contains(s)) {
				hs.add(s);
			}
		}
		return hs;
	}

	// distinct words without using additional storage, O(N^2)
	public void distinctWords2(String[] str) {
		if (str == null)
			return;
		int len = str.length;
		if (len < 2)
			return;

		int tail = 1;
		for (int i = 1; i < len; i++) {
			int j;
			for (j = 0; j < tail; j++) {
				if (str[i].equals(str[j]))
					break;
			}

			// if no duplication, replace tail by the current string
			if (j == tail) {
				str[tail] = str[i];
				tail++;
			}
		}
		str[tail] = "\0";
	}

	// use extra storage, O(N), N is the number of characters
	// use a boolean array to check duplication
	// iterate all character in the array, check the boolean array
	public boolean isUniqueCharacters(String str) {
		boolean[] char_set = new boolean[256];
		for (int i = 0; i < str.length(); i++) {
			int code = str.charAt(i);
			if (char_set[code] == true)
				return false;
			else
				char_set[code] = true;
		}
		return true;
	}

	// without using extra storage, complexity is N^2, N is the number of
	// characters
	// need two loops
	public boolean isUniqueCharacters2(String str) {
		for (int i = 0; i < str.length() - 1; i++) {
			int code = str.charAt(i);
			for (int j = i + 1; j < str.length(); j++)
				if (code == str.charAt(j))
					return false;
		}
		return true;
	}

	// remove dup characters in a string (this code need three four loop which
	// is not good)
	public void removeDuplicates(char[] str) {
		if (str == null)
			return;
		int length = str.length;
		for (int i = 0; i < length - 1; i++) {
			char c = str[i]; // for every character,
			for (int j = length - 1; j > i; j--)
				if (c == str[j]) {
					for (int k = j; k < length; k++) {
						if (k + 1 < length - 1)
							str[k] = str[k + 1];
						str[length - 1] = 0;
					}
					length--;
				}
		}
	}

	// this method is better (N^2)
	// for each character check if it is a duplicate of already found characters
	// skip the duplicate characters and update the non duplicate characters
	public void removeDuplicates2(char[] str) {
		if (str == null)
			return;
		int len = str.length;
		if (len < 2)
			return;
		int tail = 1; // last position of already found characters

		for (int i = 1; i < len; i++) {
			int j;
			for (j = 0; j < tail; ++j) {
				if (str[i] == str[j])
					break;
			}
			if (j == tail) { // no duplicate with found characters
				str[tail] = str[i]; // replace tail position by current
									// non-duplicate characters
				tail++;
			}
		}
		str[tail] = 0;
	}

	// replace space in a string by "02%"
	public void replaceSpace(char[] str, int length) {
		int count = 0;
		for (int i = 0; i < length; i++)
			if (str[i] == ' ')
				count++;
		int new_length = length + 2 * count;
		str[new_length - 1] = '\0';
		for (int i = length - 1; i >= 0; i--) {
			char c = str[i];
			if (c == ' ') {
				str[new_length - 1] = '0';
				str[new_length - 2] = '2';
				str[new_length - 3] = '%';
				new_length = new_length - 3;
			} else {
				str[new_length - 1] = str[i];
				new_length = new_length - 1;
			}
		}
	}

	// Write a method to decide if two strings are anagrams or not
	public boolean isAnagram(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() != str2.length())
			return false;
		int[] char_freq1 = new int[256];
		int[] char_freq2 = new int[256];

		// count the frequency of each character
		for (int i = 0; i < str1.length(); i++) {
			char_freq1[str1.charAt(i)]++;
			char_freq2[str2.charAt(i)]++;
		}

		// check two arrays are equal
		for (int i = 0; i < 256; i++)
			if (char_freq1[i] != char_freq2[i])
				return false;

		// otherwise
		return true;
	}

	// rotate the matrix 90 degree (can use additional matrix)
	public int[][] rotate(int[][] M, int n) {
		int[][] N = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				N[j][n - 1 - i] = M[i][j];
		return N;
	}

	// for all zero values, assign its row and column to zero
	public void rewriteMatrix(int[][] A) {
		int row = A.length;
		int col = A[0].length;
		boolean[] row_check = new boolean[row];
		boolean[] col_check = new boolean[col];

		for (int i = 0; (i < row); i++)
			for (int j = 0; (j < col); j++)
				if (A[i][j] == 0 && (!row_check[i] && (!col_check[j]))) {// set
																			// row/col
																			// sets
																			// =
																			// 0
					row_check[i] = true;
					col_check[j] = true;
					for (int k = 0; (k < col); k++)
						// set row set = 0
						A[i][k] = 0;
					for (int k = 0; (k < row); k++)
						// set col set = 0
						A[k][j] = 0;
				}
	}
}