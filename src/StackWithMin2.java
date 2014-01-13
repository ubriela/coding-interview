// This implementation is to save extra storage for each node
// The idea is to use an extra stack instead of extra storage for each node. 
// This idea will help if large values are put after small values
public class StackWithMin2 {
	Node top;
	Stack s = new Stack();

	public static void main(String[] args) {
		StackWithMin2 s = new StackWithMin2();
		s.push(4);
		s.push(3);
		s.push(2);
		s.push(1);
		s.pop();
		System.out.println(s.min());
		s.pop();
		System.out.println(s.min());
	}

	public StackWithMin2() {
		top = null;
	}

	public void push(Integer value) {
		if (value == null)
			return;
		Node n = new Node(value);
		n.next = top;
		top = n;

		// push value into s
		if (s.isEmpty() || s.peek() >= value)
			s.push(value);
	}

	public Integer pop() {
		if (top != null) {
			Integer value = top.value;
			top = top.next;
			if (value == s.peek())
				s.pop();
			return value;
		}
		return null;
	}

	public int min() {
		if (!s.isEmpty())
			return s.peek();
		return Integer.MAX_VALUE;
	}

	public class Node {
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
		}
	}
}
