import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DynamicProgramming {

	public static void main(String[] args) {
		// test LCS
		// System.out.println(LCSubstr("abcaccb", "abbcacb"));

		// test getIntersectedValues
		// int[] A = {1, 3, 4, 5, 5, 5, 5, 9};
		// int[] B = {5, 6, 6, 7};
		// ArrayList<Integer> result = getIntersectedValues(A, B);
		// for (int i : result)
		// System.out.println(i);

		// int[] a = { 2, -8, 3, -2, 4, 10 };
		// System.out.println(getMaxSequenceSum(a));

		// int[] a = { 7, 9, 13, 2, 5, 9, 2, 6, 8, 0, 15, 8 };
		// // output: 2,5,6,8,15
		// int[] max = maxIncSubArr(a);
		// for (int i : max)
		// System.out.println(i);

		HashSet<String> dict = new HashSet<String>();
		dict.add("apple");
		dict.add("pie");
		dict.add("app");

		System.out.println(segmentString2("applepie", dict));
	}

	// Given an input string and a dictionary of words,
	// segment the input string into a space-separated
	// sequence of dictionary words if possible. For
	// example, if the input string is "applepie" and
	// dictionary contains a standard set of English words,
	// then we would return the string "apple pie" as output

	// O(2^N), N is the length of the input string (Bad solution)
	public static String segmentString(String input, HashSet<String> dict) {
		if (dict.contains(input))
			return input;
		int len = input.length();
		for (int i = 1; i < len; i++) {
			String prefix = input.substring(0, i);
			if (dict.contains(prefix)) {
				String suffix = input.substring(i, len);
				String segSuffix = segmentString(suffix, dict);
				if (segSuffix != null) {
					return prefix + " " + segSuffix;
				}
			}
		}
		return null;
	}

	// worse case O(N^2), N is the length of the input string
	// memorize the temporary result <string, break words> in a hashmap
	static HashMap<String, String> memoized = new HashMap();

	public static String segmentString2(String input, HashSet<String> dict) {
		if (dict.contains(input))
			return input;
		if (memoized.containsKey(input))
			return memoized.get(input);
		int len = input.length();
		for (int i = 1; i < len; i++) {
			String prefix = input.substring(0, i);
			if (dict.contains(prefix)) {
				String suffix = input.substring(i, len);
				String segSuffix = segmentString2(suffix, dict);
				if (segSuffix != null) {
					memoized.put(input, prefix + " " + segSuffix);
					return prefix + " " + segSuffix;
				}
			}
		}
		memoized.put(input, null);
		return null;
	}

	// Output: sub_arr which has the same order as arrValue and all of the
	// values in sub_arr should be increasing and has the maximum size.
	// for example,
	// input: 7 9 13 2 5 9 2 6 8 0 15
	// output: 2 5 6 8 15
	//
	// Recursive function:
	// result[i]==max(result[j]) + 1 where 0<=j<i and arr[i] > arr[j]; otherwise
	// result[i] = 1
	// result[i] is the max increasing array ending by arr[i]
	// O(N^2)
	// exist O(NlogN in literature)
	public static int[] maxIncSubArr(int[] arr) {
		if (arr == null)
			return null;
		int[] result = new int[arr.length]; // max length so far
		for (int i = 0; i < result.length; i++)
			result[i] = 1;
		for (int i = 1; i < arr.length; i++) {
			int max = -Integer.MAX_VALUE;
			boolean found = false;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && max < result[j] + 1) {
					max = result[j] + 1;
					found = true;
				}
			}
			if (found)
				result[i] = max;
			else
				result[i] = 1;
		}
		return result;
	}

	// You are given an array of integers (both positive and negative)
	// Find the continuous sequence with the largest sum
	// Return the sum
	// for example
	// Input: {2, -8, 3, -2, 4, -10}
	// Output: 5 ({3, -2, 4})
	// O(N)
	public static int getMaxSequenceSum(int[] a) {
		int maxSum = -Integer.MAX_VALUE;
		int curSum = 0;
		for (int i = 0; i < a.length; i++) {
			curSum = a[i] + curSum;
			if (0 < curSum) {
				if (curSum > maxSum)
					maxSum = curSum;
			} else
				curSum = 0;
		}
		return maxSum;
	}

	// find intersected values of two array (not common values)
	public static ArrayList<Integer> getIntersectedValues(int[] A, int[] B) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (A[0] <= B[0]) {
			if (B[0] > A[A.length - 1])
				return null;
			int start = BinSearchLower(A, B[0]);
			if (B[B.length - 1] <= A[A.length - 1]) {
				int end = BinSearchUpper(A, B[B.length - 1]);
				for (int i = start; i <= end; i++)
					result.add(A[i]);
				for (int b : B)
					result.add(b);
			} else {
				int end = BinSearchUpper(B, A[A.length - 1]);
				for (int i = start; i < A.length; i++)
					result.add(A[i]);
				for (int i = 0; i <= end; i++)
					result.add(B[i]);
			}
			return result;
		} else {
			return getIntersectedValues(B, A);
		}
	}

	public static int BinSearchUpper(int[] A, int b) {
		int start = 0;
		int end = A.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (A[mid] == b)
				break;
			if (A[mid] > b)
				end = mid - 1;
			else
				start = mid + 1;
		}
		while (mid >= 0 && mid < A.length && A[mid] <= b) {
			mid++;
		}
		return mid - 1;
	}

	public static int BinSearchLower(int[] A, int b) {
		int start = 0;
		int end = A.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (A[mid] == b)
				break;
			if (A[mid] > b)
				end = mid - 1;
			else
				start = mid + 1;
		}
		while (mid >= 0 && mid < A.length && A[mid] >= b) {
			mid--;
		}
		return mid + 1;
	}
	
	// http://rosettacode.org/wiki/Longest_common_subsequence#Dynamic_Programming_2
	// Dynamic programming
	public static String longestCommonSubstr(String a, String b) {
	    int[][] lengths = new int[a.length()+1][b.length()+1];
	 
	    // row 0 and column 0 are initialized to 0 already
	 
	    for (int i = 0; i < a.length(); i++)
	        for (int j = 0; j < b.length(); j++)
	            if (a.charAt(i) == b.charAt(j))
	                lengths[i+1][j+1] = lengths[i][j] + 1;
	            else
	                lengths[i+1][j+1] =
	                    Math.max(lengths[i+1][j], lengths[i][j+1]);
	 
	    // read the substring out from the matrix
	    StringBuffer sb = new StringBuffer();
	    for (int x = a.length(), y = b.length();
	         x != 0 && y != 0; ) {
	        if (lengths[x][y] == lengths[x-1][y])
	            x--;
	        else if (lengths[x][y] == lengths[x][y-1])
	            y--;
	        else {
	            assert a.charAt(x-1) == b.charAt(y-1);
	            sb.append(a.charAt(x-1));
	            x--;
	            y--;
	        }
	    }
	 
	    return sb.reverse().toString();
	}
	
	// http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Longest_common_substring
	// dynamic programming
	public static int longestSubstr(String first, String second) {
	    if (first == null || second == null || first.length() == 0 || second.length() == 0) {
	        return 0;
	    }
	 
	    int maxLen = 0;
	    int fl = first.length();
	    int sl = second.length();
	    int[][] table = new int[fl][sl];
	 
	    for (int i = 0; i < fl; i++) {
	        for (int j = 0; j < sl; j++) {
	            if (first.charAt(i) == second.charAt(j)) {
	                if (i == 0 || j == 0) {
	                    table[i][j] = 1;
	                }
	                else {
	                    table[i][j] = table[i - 1][j - 1] + 1;
	                }
	                if (table[i][j] > maxLen) {
	                    maxLen = table[i][j];
	                }
	            }
	        }
	    }
	    return maxLen;
	}

	// using recursive algorithm
	public static String LCS(String A, String B) {
		if (A.length() == 0 || B.length() == 0)
			return "";
		if (A.charAt(0) == B.charAt(0))
			return A.charAt(0) + LCS(A.substring(1), B.substring(1));
		String first = LCS(A, B.substring(1));
		String second = LCS(A.substring(1), B);
		if (first.length() >= second.length())
			return first;
		return second;
	}

	// find longest common substring O(N^3)
	public static String LCSubstr(String A, String B) {
		int max = 0;
		String maxStr = new String();
		for (int i = 0; i < A.length(); i++)
			for (int j = 0; j < B.length(); j++) {
				String str = LCSuffix(A.substring(0, i + 1),
						B.substring(0, j + 1));
				if (max < str.length()) {
					max = str.length();
					maxStr = str;
				}
			}
		return maxStr;
	}

	// find longest common suffix (O(N))
	private static String LCSuffix(String A, String B) {
		if (A.length() == 0 || B.length() == 0)
			return "";
		if (A.charAt(A.length() - 1) == B.charAt(B.length() - 1))
			return LCSuffix(A.substring(0, A.length() - 1),
					B.substring(0, B.length() - 1))
					+ A.charAt(A.length() - 1);
		return "";
	}
}