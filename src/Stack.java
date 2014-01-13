public class Stack {
	Node top;
	int size;

	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(4);
		s.push(2);
		s.push(6);
		s.push(1);
		s.sort();
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}

	public Stack() {
		top = null;
		size = 0;
	}

	public void sort() { // n is the size of the stack
		if (isEmpty())
			return;
		Stack s = new Stack(); // temp stack
		for (int n = size; n >= 2; n--) {
			int min = Integer.MAX_VALUE;
			for (int i = 1; i <= n; i++) {
				int value = pop();
				s.push(value);
				if (min > value)
					min = value;
			} // the stack is now empty
				// put the min into stack first
			push(min);

			// put back all value in temp stack, except the min
			boolean occur = false;
			while (!s.isEmpty()) {
				int value = s.pop();
				if (occur == false && value == min) {
					occur = true;
					continue;
				}
				push(value);
			}
		}
	}

	// Removes the object at the top of this stack and returns that object as
	// the value of this function.
	public Integer pop() {
		if (top != null) {
			Integer value = top.value;
			top = top.next;
			size--;
			return value;
		}
		return null;
	}

	// Looks at the object at the top of this stack without removing it from the
	// stack.
	public Integer peek() {
		if (top != null) {
			return top.value;
		}
		return null;
	}

	public boolean isEmpty() {
		return (top == null);
	}

	// Pushes an item onto the top of this stack
	public void push(Integer value) {
		if (value == null)
			return;
		Node n = new Node(value);
		n.next = top;
		top = n;
		size++;
	}

	public class Node {
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
		}
	}
}