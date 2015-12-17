import java.util.HashMap;
import java.util.Random;

// LINKEDIN
// Design and implement a data structure that allows for add(T e), remove(T e) and removeRand(), all in O(1) time.


public class O1DataStructure {

	static Random rand = new Random(1000);
	
	static int[] arr = {};
	static int length = 0;
	
	static HashMap<Integer, Integer> valueToIndex = new HashMap<>();
	
	public static void main(String[] args) {
		arr = add(arr, 1);
		arr = add(arr, 2);
		arr = add(arr, 3);
		arr = add(arr, 4);
		arr = add(arr, 5);
		arr = add(arr, 6);
		
		for (int j = 0; j < arr.length; j++)
			System.out.print(arr[j] + "\t");
		System.out.print("\n");
		
//		Iterator<Integer> it = valueToIndex.keySet().iterator();
//		while (it.hasNext()) {
//			int key = it.next();
//			int val = valueToIndex.get(key);
//			System.out.println(key + "\t" + val);
//		}
		arr = removeValue(arr, 2);
		arr = removeValue(arr, 6);
		
		for (int j = 0; j < arr.length; j++)
			System.out.print(arr[j] + "\t");
		System.out.print("\n");
		
		arr = removeRand(arr);
		arr = removeRand(arr);
		
		for (int j = 0; j < arr.length; j++)
			System.out.print(arr[j] + "\t");
		System.out.print("\n");
	}

	
	/**
	 * Add a value into array a
	 * @param a
	 * @param val
	 * @return
	 */
	public static int[] add(int[] a, int val) {
		int[] ret = new int[a.length + 1];
	    System.arraycopy(arr, 0, ret, 0, a.length);
	    ret[ret.length - 1] = val;
	    
	    // update index
	    valueToIndex.put(val, ret.length - 1);
		return ret;
	}
	
	/**
	 * 
	 * @param a : array to be removed a random element
	 * @return
	 */
	public static int[] removeRand(int[] a) {
		rand.setSeed(System.nanoTime());
		int r = rand.nextInt(a.length); // random index bwn 0 and length - 1
		
		return removeAtIndex(a, r);
	}
	
	private static int[] removeAtIndex(int[] a, int i) {
		// swap two values
		a[i] = a[a.length - 1];
		
		// update index
		valueToIndex.put(a[i], i);
		return removeAtLast(a); // remove the last element
	}
	
	/**
	 * 
	 * @param a: array to be removed a particular element
	 * @param val
	 * @return
	 */
	public static int[] removeValue(int[] a, int val) {
		int index = 0;
		if (valueToIndex.containsKey(val))
			index = valueToIndex.get(val);
		else
			System.out.println("key does not exist");
		return removeAtIndex(a, index);
	}

	static int[] removeAtLast(int[] a) {
	    final int L = arr.length;
	    int[] ret = new int[L - 1];
	    System.arraycopy(arr, 0, ret, 0, a.length - 1);
	    //System.arraycopy(arr, a.length, ret, a.length - 1, L - a.length);
	    return ret;
	}
}
