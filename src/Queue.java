// An implemtation of a queue
public class Queue {
	public Node head, tail;

	public Queue() {
		head = null;
		tail = null;
	};

	public static void main(String args[]) {
		Queue queue = new Queue();
		System.out.println(queue.poll());
		queue.offer(1);
		queue.offer(2);
		System.out.println(queue.poll());
		queue.offer(3);
		System.out.println(queue.poll());
		System.out.println(queue.poll());

	}

	// Retrieves, but does not remove, the head of this queue. Returning null if
	// this queue is empty
	public int peek() {
		if (head == null)
			return -Integer.MAX_VALUE;
		return head.value;
	}

	// Inserts the specified element into this queue
	public void offer(int value) {
		Node node = new Node(value);
		if (head != null) {
			head.next = node;
			head = node;
		} else {
			head = node;
			head.next = null;
			tail = node;
		}
	}

	// Retrieves and removes the head of this queue, or null if this queue is
	// empty.
	public int poll() {
		int value = 0;
		if (tail != null) {
			value = tail.value;
			tail = tail.next;
			return value;
		}
		return -Integer.MAX_VALUE; // the queue is empty
	}

	public class Node {
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
		}
	}
}
