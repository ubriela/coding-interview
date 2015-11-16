import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class BinarySearchTree {

	Node root;
	static ArrayList<LinkedList<Node>> ll = new ArrayList<LinkedList<Node>>();

	public BinarySearchTree() {
		root = null;
	}

	public static void main(String[] args) {
		 BinarySearchTree tree = new BinarySearchTree();
		 tree.root = tree.insert(tree.root, tree.root, 3);
		 tree.root = tree.insert(tree.root, tree.root, 2);
		 tree.root = tree.insert(tree.root, tree.root, 4);
		 tree.root = tree.insert(tree.root, tree.root, 1);
		 tree.root = tree.insert(tree.root, tree.root, 5);
		 tree.root = tree.insert(tree.root, tree.root, 6);
//		 System.out.println(tree.isBalanceLessMemory());

		// Test createBinTreeWithMinHeight
		// int[] N = {1,2,3,4,5,6};
		// tree.root = tree.createBinTreeWithMinHeight(N, 0, N.length - 1);
		// tree.pre_order_traverse(tree.root);
		// System.out.println();
		// tree.in_order_traverse(tree.root);
		// System.out.println();
		// tree.post_order_traverse(tree.root);

		// Test createLL
		// LinkedList<Node> parents = new LinkedList<Node>();
		// ll.add(parents);
		// parents.add(tree.root);
		// tree.createLinkedListAtEachDepth(parents);
		// for (LinkedList<Node> l : ll) {
		// for (Node n : l) {
		// System.out.print(n.value + " ");
		// }
		// System.out.println();
		// }

		// Test firstCommonAncestor
		// BinarySearchTree tree = new BinarySearchTree();
		// tree.root = tree.insert(tree.root, 6);
		// tree.root = tree.insert(tree.root, 3);
		// tree.root = tree.insert(tree.root, 8);
		// tree.root = tree.insert(tree.root, 2);
		// tree.root = tree.insert(tree.root, 1);
		// tree.root = tree.insert(tree.root, 4);
		// tree.root = tree.insert(tree.root, 7);
		// tree.root = tree.insert(tree.root, 9);
		// tree.root = tree.insert(tree.root, 2);
		// tree.root = tree.insert(tree.root, 5);
		// System.out.println(tree.commonAncestor2(tree.root, 1, 9).value);

		// Test isSubTree
//		BinarySearchTree tree = new BinarySearchTree();
//		tree.root = tree.insert(tree.root, 6);
//		tree.root = tree.insert(tree.root, 3);
//		tree.root = tree.insert(tree.root, 8);
//		tree.root = tree.insert(tree.root, 1);
//		tree.root = tree.insert(tree.root, 4);
//		tree.root = tree.insert(tree.root, 7);
//		tree.root = tree.insert(tree.root, 9);
//		tree.root = tree.insert(tree.root, 2);
//		tree.root = tree.insert(tree.root, 5);
//		BinarySearchTree tree2 = new BinarySearchTree();
//		tree2.root = tree.insert(tree2.root, 1);
//		tree2.root = tree.insert(tree2.root, 2);
		//
		// System.out.println(tree.isSubTree(tree2.root, tree.root));

		// test findPairs()
		// BinarySearchTree tree = new BinarySearchTree();
		// tree.root = tree.insert(tree.root, 6);
		// tree.root = tree.insert(tree.root, 3);
		// tree.root = tree.insert(tree.root, 8);
		// tree.root = tree.insert(tree.root, 1);
		// tree.root = tree.insert(tree.root, 6);
		// tree.root = tree.insert(tree.root, 7);
		// tree.root = tree.insert(tree.root, 2);
		//
		// Pair p = tree.findPairs(tree.root, 12);
		// if (p != null) {
		// System.out.println(p.n1.value);
		// System.out.println(p.n2.value);
		// }

//		System.out.println(tree.isBinSearchTree(tree.root));
//		tree.preorderTraversal(tree.root);
		 tree.in_order_traverse_less_memory(tree.root);
	}

	// if a binary tree is a binary search tree
	// a binary tree: node.value >= node.left.value and node.value <
	// node.right.value
	// recursive thinking: check current node, then check both its left and
	// right branches
	// O(N), N is the size of the tree
	public boolean isBinSearchTree(Node root) {
		if (root != null) {
			if (root.left != null && root.value < root.left.value) {
				return false;
			}
			if (root.right != null && root.value >= root.right.value) {
				return false;
			}

			// both the left and the right branches are bin search tree
			return (isBinSearchTree(root.left) && isBinSearchTree(root.right));
		}
		return true;
	}

	private class Pair {
		Node n1;
		Node n2;
	}

	// find two nodes in a binary tree summing up to a value
	// return those two nodes
	public Pair findSumPairs(Node node, int sum) {
		if (node == null)
			return null;
		Pair pair;

		// current node is a candidate
		// --> find the other node in the tree
		Node other_node = binSearch(root, node, sum - node.value);
		if (other_node != null) {
			pair = new Pair();
			pair.n1 = node;
			pair.n2 = other_node;
			return pair;
		}

		// otherwise search the left and the right branches
		pair = findSumPairs(node.left, sum);
		if (pair != null)
			return pair;
		return findSumPairs(node.right, sum);
	}

	// return a node in a tree whose value is equal to a value
	// otherwise return null;
	private Node binSearch(Node root, Node node, int value) {
		if (root == null || node == null)
			return null;
		if (root.value == value && !root.equals(node))
			return root;
		Node n = binSearch(root.left, node, value);
		if (n != null)
			return n;
		return binSearch(root.right, node, value);
	}

	// Create an algorithm to decide if T2 is a subtree of T1
	// recursive thinking: T2 is subtree of T1 if either two tree are equal or
	// T2 is the subtree of T1.left or T1.right
	// Worse case: m*n where m and n are the size of T2 and T1 correspondingly
	public boolean isSubTree(Node T2, Node T1) {
		if (T2 == null) // a null tree is a subtree of any tree
			return true;
		if (T1 == null) // a null tree does not contain any tree
			return false;
		if (T2.value == T1.value)
			return matchTree(T2, T1);
		return isSubTree(T2, T1.left) || isSubTree(T2, T1.right);
	}

	// check if T2 = T1
	private boolean matchTree(Node T2, Node T1) {
		if (T2 == null && T1 == null) // return true if both are null
			return true;
		if (T1 == null || T2 == null) // otherwise, return false if null
			return false;
		if (T2.value != T1.value) // return false if data doesn't match
			return false;
		return matchTree(T2.left, T1.left) && isSubTree(T2.right, T1.right);
	}

	// Design an algorithm and write code to find the first common ancestor of
	// two nodes in a binary tree Avoid storing additional nodes in a data
	// structure
	// recursive thinking: the root is definitely the common ancestor,
	// recursively check isAncestor condition in the top-down manner
	public Node commonAncestor(Node node, Integer A, Integer B) {
		if (isAncestor(node.left, A) && isAncestor(node.left, B))
			return node.left;
		if (isAncestor(node.right, A) && isAncestor(node.right, B))
			return node.right;
		return root;
	}

	// return true if the node is the ancestor of another node with value
	private boolean isAncestor(Node node, Integer value) {
		if (node == null)
			return false;
		if (node.value == value)
			return true;
		return (isAncestor(node.left, value) || isAncestor(node.right, value));
	}

	public Node commonAncestor2(Node root, Integer A, Integer B) {
		while (root != null) {
			if (root.value > A && root.value > B)
				root = root.left;
			else if (root.value < A && root.value < B)
				root = root.right;
			else
				return root;
		}
		return null; // if empty tree
	}

	// Given a binary search tree, design an algorithm which creates a linked
	// list of all the nodes at each depth (i e , if you have a tree with depth
	// D, you'll have D linked lists)
	// The idea is to use a global variable to store all list of node at each
	// depth
	public void createLinkedListAtEachDepth(LinkedList<Node> parents) {
		LinkedList<Node> children = new LinkedList<Node>();
		Iterator<Node> it = parents.iterator();
		while (it.hasNext()) {
			Node n = (Node) it.next();
			if (n != null) {
				if (n.left != null)
					children.add(n.left);
				if (n.right != null)
					children.add(n.right);
			}
		}

		// ll is a global variable to store all linkedlist<node> at each depth
		ll.add(children);
		if (children.size() == 0)
			return;
		createLinkedListAtEachDepth(children);
	}

	// create a binary tree from a sorted array with min height
	public Node createBinTreeWithMinHeight(int[] N, int from, int to) {
		if (from > to)
			return null;
		int mid = (from + to) / 2;
		Node n = new Node(N[mid]);
		n.left = createBinTreeWithMinHeight(N, from, mid - 1);
		n.right = createBinTreeWithMinHeight(N, mid + 1, to);
		return n;
	}

	// if a tree is balance or not
	// memory = O(n), n is the number of nodes
	public boolean isBalance() {
		if (maxDepth(root) - minDepth(root) > 1)
			return false;
		else
			return true;
	}

	private int minDepth(Node node) {
		if (node == null)
			return 0;
		return 1 + Math.min(minDepth(node.left), minDepth(node.right));
	}

	private int maxDepth(Node node) {
		if (node == null)
			return 0;
		return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
	}
	
	// memory = O(h), h is the height of the tree
	public boolean isBalanceLessMemory() {
		return getHeight(root) != -2;
	}
	
	public int getHeight(Node node) {
		if (node == null)
			return -1;
		
		int l_height = getHeight(node.left);
		if (l_height == -2)
			return -2;	// left tree is not balanced
		int r_height = getHeight(node.right);
		if (r_height == -2)
			return -2; // right tree is not balanced
		
		if (Math.abs(l_height - r_height) > 1)
			return -2;	// current node is not balance
		return Math.max(l_height, r_height) + 1;
	}

	// insert a node into a bin tree
	// if there is already a value in the tree --> replace
	public Node insert(Node parent, Node node, Integer value) {
		if (node == null)
			return new Node(value);
		if (node.value == value)
			node.value = value; // replace value
		else {
			if (node.value > value)
				node.left = insert(node, node.left, value);
			else
				node.right = insert(node, node.right, value);
		}
		node.parent = parent;
		return node;
	}

	// in order traverse
	// memory = O(n)
	public void in_order_traverse(Node node) {
		if (node != null) {
			in_order_traverse(node.left);
			System.out.print(node.value + " ");
			in_order_traverse(node.right);
		}
	}
	
	// not done yet
	public void in_order_traverse_less_memory(Node node) {
		Node curr = node;
		Node prev = null;	// point to parent so that we can backtrack
		Node next = null;
		while (curr != null) {
			if (prev != null || (prev != null && prev.left == curr) || (prev != null && prev.right == curr)) {
				if (curr.left != null)
					next = curr.left;
				else {
					System.out.print(curr.value + " ");
					if (curr.right != null)
						next = curr.right;
					else
						next = curr.parent;
				}
			} else if (curr.left == prev) {
				System.out.print(curr.value + " ");
				if (curr.right != null)
					next = curr.right;
				else
					next = curr.parent;
			} else { // curr.right == prev
				next = curr.parent;
			}
			
			// right
			
			prev = curr;
			curr = next;
		}
	}

	// pre-order traverse
	public void pre_order_traverse(Node node) {
		if (node != null) {
			System.out.print(node.value + " ");
			pre_order_traverse(node.left);
			pre_order_traverse(node.right);
		}
	}

	// preorder traverse without using recursion --> avoid too many function
	// calls but use extra stack
	public void preorderTraversal(Node root) {
		java.util.Stack<Node> s = new Stack<>();
		s.push(root);
		while (true && !s.isEmpty()) {
			Node curr = s.pop();
			if (curr == null)
				break;
			System.out.print(curr.value + " ");
			if (curr.right != null)
				s.push(curr.right);
			if (curr.left != null)
				s.push(curr.left);
		}
	}

	// post-order traverse
	public void post_order_traverse(Node node) {
		if (node != null) {
			post_order_traverse(node.left);
			post_order_traverse(node.right);
			System.out.print(node.value + " ");
		}
	}

	class Node {
		Integer value;
		Node left;
		Node right;
		Node parent;

		public Node(Integer value) {
			this.value = value;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
	}
}
