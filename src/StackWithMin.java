//How would you design a stack which, in addition to push and pop, also has a function min which returns the minimum element? Push, pop and min should all operate in O(1) time
public class StackWithMin {
	Node top;
	
	public static void main(String[] args) {
		StackWithMin s = new StackWithMin();
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(1);
		System.out.println(s.pop());
		System.out.println(s.min());
	}
	
	public StackWithMin() {
		top = null;
	}
	
	public void push(Integer value) {
		if (value == null)
			return;
		int min = value;
		if (top != null) {
			if (top.min <= value)
				min = top.min;
		}
		Node n = new Node(value, min);
		n.next = top;
		top = n;
	}
	
	public Integer pop() {
		if (top != null) {
			Integer value = top.value;
			top = top.next;
			return value;
		}
		return null;
	}
	
	public int min() {
		if (top != null)
			return top.min;
		return Integer.MAX_VALUE;
	}
	
	public class Node {
		public int value;
		public Node next;
		public int min;

		public Node(int value, int min) {
			this.value = value;
			this.min = min;
		}
	}
}
