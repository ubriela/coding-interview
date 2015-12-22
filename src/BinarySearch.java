import java.util.Vector;


public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = {7, 10, 4, 3, 4, 4, 20, 15};
		System.out.println(findKthMedian(arr, 3	));
	}
	
	// find kth median in an array
	// arr[] = {7, 10, 4, 3, 20, 15}
	// k = 3 --> output = 7
	// k = 4 --> output = 10
    // arr[] = {7, 10, 4, 3, 20, 15}
    // assumption: distinct values
    // 4,
    public static int findKthMedian(int[] arr, int k) {
        Vector<Integer> l = new Vector<Integer>();
        Vector<Integer> r = new Vector<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[0])
                l.add(arr[i]);
            else
                r.add(arr[i]);
        }
        
        // kth median is on left array
        if (l.size() < k - 1) {
            int[] rightArr = convert(r.toArray(new Integer[r.size()]));
            return findKthMedian(rightArr, k - l.size());
        } else if (l.size() > k - 1) {
            int[] leftArr = convert(l.toArray(new Integer[l.size()]));
            return findKthMedian(leftArr, k);
        } else {
            return arr[0];
        }
    }
    
    public static int[] convert(Integer[] arr) {
    	int[] a = new int[arr.length];
    	for (int i = 0; i < arr.length; i++)
    		a[i] = arr[i];
    	return a;
    }

}


