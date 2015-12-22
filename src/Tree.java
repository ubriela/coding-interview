import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.plaf.basic.BasicTreeUI.TreeIncrementAction;

/**
 * 
 * @author ubriela
 * 
 */
public class Tree {

	public static void main(String[] args) {
		// Tree t = new Tree();
		// int[] boss = { 1, 3, 3, -1 };
		// System.out.println(t.lowestCommonBoss2(boss, 0, 1));

		int[] tree = { 0, 0, 0, 1, 3, 5, 5, 2, 3, 4, 8, 9, 6, 1, 2};
		Vector<Integer> v = removeNode2(tree, 3);
		System.out.println(v.toString());
	}

	/**
	 * 0 reports to 1, 1 reports to 3, 2 reports to 3, 3 reports to -1 (highest
	 * boss)
	 * 
	 * For example: bigBoss(0,2) is 3; bigBoss(0,3) is 3
	 * 
	 * @param boss
	 * @param x
	 * @param y
	 * @return
	 */

	// using a hashset to store path-to-ancestor of x
	// check first appearance of boss of y in the hashset
	// O(logN) in average, with a hashset
	public int lowestCommonBoss(int[] boss, int x, int y) {
		HashSet<Integer> ancestors_x = getAncestors(boss, x);
		int cur_y = y;
		while (boss[cur_y] != -1) {
			// check cur_y in ancestors_x
			if (ancestors_x.contains(cur_y)) {
				return cur_y;
			}
			cur_y = boss[cur_y];
		}
		return cur_y;
	}

	// find a path to its ancestor O(logN), assuming balance tree.
	// Worst case O(N)
	public HashSet<Integer> getAncestors(int[] boss, int x) {
		HashSet<Integer> ancestors_x = new HashSet<Integer>();
		int cur = x;
		while (boss[cur] != -1) {
			ancestors_x.add(cur);
			cur = boss[cur];
		}
		ancestors_x.add(cur);
		return ancestors_x;
	}

	// this code is much better, no extra storage.
	// the average complexity is the same
	public int lowestCommonBoss2(int[] boss, int x, int y) {
		int hx = findHeight(boss, x);
		int hy = findHeight(boss, y);

		int tmp_x = x;
		int tmp_y = y;
		if (hx > hy) {
			// find (hx - hy)th boss of x
			int i = hx - hy;
			while (i > 0) {
				tmp_x = boss[tmp_x];
				i--;
			}
		} else if (hx < hy) {
			int i = hy - hx;
			while (i > 0) {
				tmp_y = boss[tmp_y];
				i--;
			}
		}

		// find common boss when x and y in the save height
		while (tmp_x != tmp_y) {
			tmp_x = boss[tmp_x];
			tmp_y = boss[tmp_y];
		}
		return tmp_x;
	}

	public int findHeight(int[] boss, int x) {
		int h = 0;
		int cur = x;
		while (boss[cur] != -1) {
			cur = boss[cur];
			h++;
		}
		return h;
	}

	// Given a set of trees
	// [Node(0), Node(0), Node(0), Node(1), Node(3), Node(5), Node(5), Node(2)]
	// 0
	// |\
	// 1 2
	// | |
	// 3 7
	// |
	// 4
	//
	// 5
	// |
	// 6
	// Write a function to remove a particular index
	// del id[3]
	// output
	// 0
	// |\
	// 1 2
	//
	// [Node(0), Node(0), Node(0), Node(3), Node(3)]
	public static Vector<Integer> removeNode(int[] treeIdx, int id) {
		Vector<Integer> ret = new Vector<>();
		int removedCount = 0;
		for (int i = 0; i < treeIdx.length; i++) {
			if (!isAncestor(treeIdx, id, i)) {
				if (treeIdx[i] < id)
					// the current node is not affected by the removed nodes
					ret.add(treeIdx[i]);
				else
					ret.add(treeIdx[i] - removedCount);
			} else
				// remove idx
				removedCount++;
		}
		return ret;
	}
	
	public static Vector<Integer> removeNode2(int[] treeIdx, int id) {
		Vector<Integer> ret = new Vector<>();
		HashSet<Integer> removedNodes = new HashSet<Integer>();
		removedNodes.add(id);
		int removedCount = 0;
		for (int i = 0; i < treeIdx.length; i++) {
			if (!removedNodes.contains(treeIdx[i]) && !removedNodes.contains(i) ) {
				if (treeIdx[i] < id)
					// the current node is not affected by the removed nodes
					ret.add(treeIdx[i]);
				else
					ret.add(treeIdx[i] - removedCount);
			} else {
				removedNodes.add(i);
				// remove idx
				removedCount++;
			}
		}
		return ret;
	}

	// check if
	static boolean isAncestor(int[] treeIdx, int ancestorIdx, int i) {
		boolean found = false;
		if (treeIdx.length < i || treeIdx.length < ancestorIdx)
			return false;
		if (i == ancestorIdx)
			return true;

		while (!found) {
			if (treeIdx[i] == ancestorIdx)
				found = true;
			if (i == treeIdx[i]) // i is the root node
				return false;
			i = treeIdx[i];
		}
		return found;
	}
}