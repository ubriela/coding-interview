import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;

public class SortNSearch {

	static Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();

	public static void main(String[] args) {
		// test AnagramComparator
		// ArrayList<String> al = new ArrayList<String>();
		// al.add("hien");
		// al.add("neih");
		// al.add("kien");
		// Collections.sort(al, new AnagramComparator());
		// for (String s : al)
		// System.out.println(s);

		// test sort

		// int[] A = { 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
		// 11,
		// 12, 13, 14 }; // rotated array
		// System.out.println(searchRotatedArray(A, 6, 0, 19));

		// char[] dict = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
		// 'a',
		// 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v',
		// 'b', 'n', 'm' };
		// for (int i = 0; i < dict.length; i++)
		// ht.put(new Character(dict[i]), i);
		//
		// ArrayList<String> al = new ArrayList<String>();
		// al.add("ab");
		// al.add("abm");
		// al.add("qa");
		// al.add("ba");
		// Collections.sort(al, new DictComparator());
		// for (String s : al)
		// System.out.println(s);

		// Test KNN points
		// SortNSearch s = new SortNSearch();
		// Point[] points = {new Point(1,1), new Point(1,3), new Point(4,1), new
		// Point(2,2)};
		// PriorityQueue<Point> knnPoints = s.getKNNPoints(points, 3);
		// for (Point p : knnPoints) {
		// System.out.println(p.x + "," + p.y);
		// }

		// Test isValidSchedule
		Meeting[] meetings = { new Meeting(0, 1), new Meeting(1, 7),
				new Meeting(11, 20) };
		System.out.println(isValidSchdule(meetings));
	}

	// Input: a list meeting. Each meeting has a start time and end time
	// Output
	// True if no overlap
	// False if other wise
	public static boolean isValidSchdule(Meeting[] meetings) {
		if (meetings == null)
			return false;
		Arrays.sort(meetings, new MeetingComparator());
		Meeting last = meetings[0];
		for (int i = 1; i < meetings.length; i++) {
			Meeting cur = meetings[i];
			if (cur.start < last.end)
				return false;
			last = cur;
		}
		return true;
	}

	// check valid meeting schedule
	private static class MeetingComparator implements Comparator<Meeting> {

		@Override
		public int compare(Meeting m1, Meeting m2) {
			if (m1.start == m2.start)
				return 0;
			if (m1.start > m2.start)
				return 1;
			return -1;
		}

	}

	private static class Meeting {

		public Meeting(double start, double end) {
			this.start = start;
			this.end = end;
		}

		double start;
		double end;
	}

	// find K closest points to the origin
	public static PriorityQueue<Point> getKNNPoints(Point[] points, int k) {
		if (points == null || k > points.length)
			return null;

		// heap
		Comparator c = new PointComparator();
		PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(k, c);
		for (int i = 0; i < k; i++) {
			maxHeap.add(points[i]);
		}

		for (int i = k; i < points.length; i++) {
			maxHeap.add(points[i]);
			maxHeap.remove(); // remove max value
		}

		return maxHeap;
	}

	// sort the point in decreasing order of distance to the origin
	private static class PointComparator implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			double d1 = Math.pow(p1.x * p1.x + p1.y * p1.y, .5);
			double d2 = Math.pow(p2.x * p2.x + p2.y * p2.y, .5);
			if (d1 == d2)
				return 0;
			else if (d1 < d2)
				return 1;
			return -1;
		}
	}

	private static class Point {
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		double x;
		double y;
	}

	// sort strings with abnormal ordered dictionary
	public static class DictComparator implements Comparator<String> {
		public int compare(String s1, String s2) {
			for (int i = 0; i < s1.length(); i++) {
				if (s2.length() <= i)
					return 1;
				int v = compareChar(s1.charAt(i), s2.charAt(i));
				if (v == 1)
					return 1;
				else if (v == -1)
					return -1;
			}
			return 0;// equal string
		}

		private int compareChar(char c1, char c2) {
			if (c1 == c2)
				return 0;
			if (ht.get(c1) > ht.get(c2))
				return 1;
			return -1;
		}
	}

	// Given a sorted array of n integers that has been rotated an unknown
	// number of times,giveanO(logn) algorithm that finds an element in the
	// array. You may assume that the array was originally sorted in increasing
	// order
	public static int searchRotatedArray(int[] A, int value, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;
			if (A[mid] == value)
				return (mid + 1);

			if (A[start] <= A[mid]) {
				if (value > A[mid])
					start = mid + 1;
				else if (value >= A[start])
					end = mid - 1;
				else
					start = mid + 1;
			} else if (value <= A[start])
				start = mid + 1;
			else
				end = mid - 1;
		}
		return -1;
	}

	// Write a method to sort an array of strings so that all the anagrams are
	// next to each other
	public static class AnagramComparator implements Comparator<String> {

		private String sortChars(String s) {
			char[] content = s.toCharArray();
			Arrays.sort(content);
			return new String(content);
		}

		@Override
		public int compare(String s1, String s2) {
			return sortChars(s1).compareTo(sortChars(s2));
		}
	}

}