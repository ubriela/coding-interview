import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class RecursiveAlgorithms {

	public static void main(String[] args) {
		RecursiveAlgorithms r = new RecursiveAlgorithms();

		// test getAllSubsets
		// ArrayList<Integer> A = new ArrayList<Integer>();
		// A.add(1);
		// A.add(2);
		// A.add(3);
		// ArrayList<LinkedList<Integer>> subsets = r.getAllSubsets(A);
		// Iterator<LinkedList<Integer>> it = subsets.iterator();
		// while (it.hasNext()) {
		// LinkedList<Integer> ll = it.next();
		// Iterator<Integer> i = ll.iterator();
		// while (i.hasNext()) {
		// Integer value = i.next();
		// System.out.print(value + " ");
		// }
		// System.out.println();
		// }

		// test getPerms
		// String input = "abc";
		// ArrayList<String> perms = r.getPerms(input);
		// for (String s : perms) {
		// System.out.println(s);
		// }

		// test isPowerOfTwo
		// System.out.println(isPowerOfTwo(10));

//		int[] A = { 25, 10, 5, 1 };
//		int N = 25;
//		System.out.print(r.makeChange(N, A, 0));
		
//		int[] a = {0, 2, 3, 5, 8};
//		System.out.println(isFound(a, 16));
		
		int[] a = {-1, -1, 0, 2, 3, 5, 8};
		System.out.println(isSumUpTo(a, 17, 3));
	}

	// if an integer is a power of two
	public static boolean isPowerOfTwo(int x) {
		if (x == 2)
			return true;
		if (x % 2 == 0)
			return isPowerOfTwo(x / 2);
		return false;
	}

	// Given an infinite number of quarters (25 cents), dimes (10 cents),
	// nickels (5 cents) and pennies (1 cent), write code to calculate the
	// number of ways of representing n cents
	/**
	 * A is a list of money units, sorted in decreasing order N is the total
	 * amount of money
	 */
	public int makeChange(int N, int[] A, int idx) {
		int count = 0;
		if (N == 0 || idx == A.length - 1)
			return 1;
		int x = N / A[idx];
		for (int i = 0; i <= x; i++) {
			count += makeChange(N - i * A[idx], A, idx + 1);
		}
		return count;
	}

	// Write a method to compute all permutations of a string
	public ArrayList<String> getPerms(String s) {
		if (s == null)
			return null;
		ArrayList<String> perms = new ArrayList<String>();
		if (s.length() == 1) { // special case, size 1
			perms.add(s);
			return perms;
		}

		// s.length > 1
		String sub_s = s.substring(1);
		ArrayList<String> sub_perms = getPerms(sub_s);

		// iterate over all string in sub_perms
		for (String str : sub_perms) {
			for (int i = 0; i <= str.length(); i++)
				perms.add(insertStr(str, s.charAt(0), i));
		}
		return perms;
	}

	// insert a character into a string a specific position
	private String insertStr(String str, char c, int pos) {
		String start = str.substring(0, pos);
		String end = str.substring(pos);
		return start + c + end;
	}

	// Write a method that returns all subsets of a set
	public ArrayList<LinkedList<Integer>> getAllSubsets(ArrayList<Integer> A) {
		ArrayList<LinkedList<Integer>> subsets = new ArrayList<LinkedList<Integer>>();
		LinkedList<Integer> first = new LinkedList<Integer>();
		first.add(A.get(0));
		subsets.add(first);
		for (int i = 1; i < A.size(); i++) {
			ArrayList<LinkedList<Integer>> temp_subsets = (ArrayList<LinkedList<Integer>>) subsets
					.clone();
			Integer new_value = A.get(i);
			// add first array list
			LinkedList<Integer> one = new LinkedList<Integer>();
			one.add(new_value);
			subsets.add(one);

			// add the rest
			Iterator<LinkedList<Integer>> it = temp_subsets.iterator();
			while (it.hasNext()) {
				LinkedList<Integer> ll = it.next();
				LinkedList<Integer> new_ll = (LinkedList<Integer>) ll.clone();
				new_ll.add(new_value);
				subsets.add(new_ll);
			}
		}
		return subsets;
	}

	// Q1: Assume we have an unsorted integer array:
	// [2 4 -1 3 5 -1 8 0]
	//
	// Give k: for example, 10
	// Q: return true/false that three integers out of the array can sum up to
	// k.
	//
	// k=6
	
	/**
	 * 
	 * @param a : array of integers
	 * @param s : sum
	 * @param n : the number of values
	 * @return
	 */
	public static boolean isSumUpTo(int[] a, int s, int n) {
//		System.out.println(s + " " + n);
		if (n == 1) {
			if (isFound(a, s))
				return true;
			else
				return false;
		}
		for (int i = 0; i < a.length; i++) {
			// if a[i] is included
			int[] tmp = new int[a.length - 1];
			System.arraycopy(a, 0, tmp, 0, i);
			System.arraycopy(a, i + 1, tmp, i, a.length - i - 1);
			if (isSumUpTo(tmp, s - a[i], n - 1))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Binary search value n in array a
	 * @param a
	 * @param n
	 * @return
	 */
	public static boolean isFound(int[] a, int n) {
		if (a.length == 0)
			return false;
			
		int left = 0;
		int right = a.length - 1;
		while (right > left) {
			int mid = (left + right)/2;
			if (a[mid] == n)
				return true;
			else if (a[mid] > n)
				right = mid - 1;
			else
				left = mid + 1;
			if (right == left && n == a[right])
				return true;
		}
		return false;
	}
	
}
