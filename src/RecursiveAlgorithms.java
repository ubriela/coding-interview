import java.util.ArrayList;
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

		int[] A = { 25, 10, 5, 1 };
		int N = 25;
		System.out.print(r.makeChange(N, A, 0));
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
	 * A is a list of money units, sorted in decreasing order
	 * N is the total amount of money
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
}
