import java.util.Hashtable;

public class LinkedList {
	public int size;
	Node first;
	Node last;

	public LinkedList() {
		size = 0;
		first = null;
		last = null;
	}

	public static void main(String[] args) {
		LinkedList ll1 = new LinkedList();
		ll1.add(new Integer(9));
		ll1.add(new Integer(9));
		ll1.add(new Integer(9));
		LinkedList ll2 = new LinkedList();
		ll2.add(new Integer(1));
		LinkedList L = sum(ll1, ll2);
		L.print();
	}

	public static LinkedList sum(LinkedList L1, LinkedList L2) {
		LinkedList L = new LinkedList();
		Node n1 = L1.first;
		Node n2 = L2.first;
		boolean check = false;

		while (n1 != null || n2 != null) {
			if (n1 != null && n2 != null) {
				int sum = n1.value + n2.value;
				if (check)
					sum++;
				if (sum < 10) {
					check = false;
					L.add(sum);
				} else {
					if (n1.next == null && n2.next == null) {
						L.add(sum - 10);
						L.add(1);
						break; // end
					} else {
						check = true;
						L.add(sum - 10);
					}
				}
			} else if (n1 == null && n2 != null) {
				int sum = n2.value;
				if (check)
					sum++;
				if (sum < 10) {
					check = false;
					L.add(sum);
				} else {
					if (n2.next == null) {
						L.add(1);
						break;
					} else {
						check = true;
						L.add(sum - 10);
					}
				}
			} else if (n1 != null && n2 == null) {
				int sum = n1.value;
				if (check)
					sum++;
				if (sum < 10) {
					check = false;
					L.add(sum);
				} else {
					if (n1.next == null) {
						L.add(1);
						break;
					} else {
						check = true;
						L.add(sum - 10);
					}
				}
			}
			if (n1 != null)
				n1 = n1.next;
			if (n2 != null)
				n2 = n2.next;
		}
		return L;
	}

	// Implement an algorithm to find the nth to last element of a singly linked
	// list
	public Node findLastNthElem(int n) {
		if (first == null)
			return null;
		Node current = first;
		int index = 0;
		while (current != null && index < n) {
			current = current.next;
			index++;
		}
		if (index != n)
			return null;
		Node last = first; // nth last element of current node
		while (current != null) {
			current = current.next;
			last = last.next;
		}
		return last;
	}

	// Write code to remove duplicates from an unsorted linked list.
	public void removeDups() {
		if (first == null)
			return;
		Hashtable<Integer, Boolean> ht = new Hashtable<Integer, Boolean>();
		Node previous = first;
		Node n = previous;
		while (n != null) {
			Integer value = n.value;
			if (ht.containsKey(value)) // remove n from linked list
				previous.next = n.next;
			else {
				ht.put(value, Boolean.TRUE);
				previous = n;
			}
			n = n.next;
		}
	}

	// Write code to remove duplicates from an unsorted linked list, without
	// using additional structure
	public void removeDups2() {
		if (first == null)
			return;
		Node current = first.next;
		while (current != null) { // first iteration
			Integer value = current.value;
			if (first.value.equals(value)) // if the first element is
											// duplicated, remove the first
				first = first.next;
			else {
				Node n = first.next;
				Node previous = first;
				while (n != current && n != null) { // iterate to check
													// duplicate
					if (n.value.equals(value)) { // remove n and exit
						previous.next = n.next;
						break;
					}
					previous = n;
					n = n.next;
				}
			}
			current = current.next;
		}
	}

	// add to the end
	public void add(Integer value) {
		if (value == null)
			return;
		Node node = new Node(value);
		if (last != null) {
			last.next = node;
			last = node;
		} else {
			last = node;
			first = node;
		}
		size++;
	}

	// add to a specific position; if not, add to the end
	public void add(int index, Integer value) {
		if (value == null)
			return;
		if (index >= size)
			add(value);
		else {
			Node node = new Node(value);
			if (index == 0) {
				node.next = first;
				first = node;
			} else {
				Node last = first;
				Node iter = first;

				for (int i = 0; i < index; i++) {
					last = iter;
					iter = iter.next;
				}

				node.next = iter;
				last.next = node;
			}
			size++;
		}
	}

	// get a specific position
	public Integer get(int index) {
		if (index >= size || index < 0 || first == null) {
			return null;
		} else {
			if (index == 0)
				return first.value;
			else {
				Node iter = first;
				for (int i = 0; i < index; i++)
					iter = iter.next;
				return iter.value;
			}
		}
	}

	// remove a specific position
	public Integer remove(int index) {
		if (index >= size || index < 0 || first == null)
			return null;
		else {
			size--;
			if (index == 0) {
				Node node = first;
				first = first.next;
				return node.value;
			} else {
				Node iter = first;
				Node last = first;
				for (int i = 0; i < index; i++) {
					last = iter;
					iter = iter.next;
				}
				last.next = iter.next;
				return iter.value;
			}
		}
	}

	// print linked list
	public void print() {
		Node n = first;
		while (n != null) {
			System.out.print(n.value + " ");
			n = n.next;
		}
	}

	class Node {
		public Integer value;
		Node next;

		public Node(Integer value) {
			this.value = value;
			this.next = null;
		}
	}
}
