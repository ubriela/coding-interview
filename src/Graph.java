import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	LinkedList<Node> nodes = new LinkedList<Node>();

	public static void main(String[] args) {
		Graph g = new Graph();
	}

	public Graph() {
		// init nodes
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);

		// create directed edges
		n1.adjacentNodes.add(n2);
		n1.adjacentNodes.add(n3);
		n2.adjacentNodes.add(n3);
		n3.adjacentNodes.add(n5);
		n4.adjacentNodes.add(n5);

		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
		nodes.add(n4);
		nodes.add(n5);

		System.out.println(search(n1, n3));
	}

	// if there is a path between two node
	// O(V*E)
	public boolean search(Node start, Node end) {
		LinkedList<Node> q = new LinkedList<Node>(); // use as a stack (DFS)
		start.state = State.Visiting;
		q.add(start);
		Node u;
		while (!q.isEmpty()) {
			u = q.removeLast(); // pop()
			if (u != null) {
				Iterator it = u.adjacentNodes.iterator();
				while (it.hasNext()) {
					Node v = (Node) it.next();
					if (v.state == State.Unvisited) {
						if (v.equals(end))
							return true;
						else {
							v.state = State.Visiting;
							q.add(v);
						}
					}
				}
			}
			u.state = State.Visited;
		}
		return false;
	}

	class Node {
		Integer value;
		LinkedList adjacentNodes;
		State state;

		public Node(int value) {
			value = value;
			adjacentNodes = new LinkedList<Node>();
			state = State.Unvisited;
		}
	}

	public enum State {
		Unvisited, Visited, Visiting;
	}
}
