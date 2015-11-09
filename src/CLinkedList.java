import java.util.Hashtable;

public class CLinkedList {
	public int size;
	Node first;
	Node last;

	public CLinkedList() {
		size = 0;
		first = null;
		last = null;
	}

	public static void main(String[] args) {
		CLinkedList ll = new CLinkedList();
		ll.add(new Integer(1));
		ll.add(new Integer(2));
		ll.add(new Integer(3));
		ll.add(new Integer(4));
		ll.add(new Integer(5));
		// CLinkedList ll2 = ll1.reverse(ll1);
		// Node n = ll2.first;
		// while (n != null) {
		// System.out.println(n.value);
		// n = n.next;
		// }
		System.out.println(ll.findNthLast(2, ll.first));
	}


	// reverse a linked list (create new)
	public CLinkedList reverse(CLinkedList ll) {
		if (ll == null || ll.first == null)
			return null;
		CLinkedList result = new CLinkedList();
		Node node = ll.first;
		while (node != null) {
			Integer value = node.value;
			Node n = new Node(value);
			n.next = result.first;
			result.first = n;
			node = node.next;
		}
		return result;
	}

	// compute sum of two numbers represented by two linked lists
	public static CLinkedList sum(CLinkedList L1, CLinkedList L2) {
		CLinkedList L = new CLinkedList();
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

	// Input: single LinkedList 1 -> 2 -> 3 -> 4 ->5
	// Output: nth node to the end
	// For example, n= 2 output 4
	// O(N), N is the size of the linkedlist
	public int findNthLast(int n, Node f) {
		if (n < 1)
			return -Integer.MAX_VALUE;
		Node first = f;
		Node second = null;
		int i = 0;
		while (first != null && i < n) {
			first = first.next;
			i++;
		}
		if (i < n)
			return -Integer.MAX_VALUE;
		second = first;
		first = f;
		while (second != null) {
			first = first.next;
			second = second.next;
		}
		return first.value;
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

	// Write code to remove duplicates from an unsorted linked list
	// this method uses extra storage hashtable
	// O(N), N is the size of the list
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
	// O(N^2), N is the size of the list
	public void removeDups2() {
		if (first == null)
			return;
		Node current = first.next;
		while (current != null) { // first iteration
			if (first.value.equals(current.value)) { // if the first element is
														// duplicated, remove
														// the first
				first = first.next;
				continue;
			}
			Node n = first.next;
			Node previous = first;
			while (n != current && n != null) { // iterate to check
												// duplicate
				if (n.value.equals(current.value)) { // remove n and exit
					previous.next = n.next;
					break;
				}
				previous = n;
				n = n.next;
			} // end second iteration
			current = current.next;
		} // end first iteration
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
