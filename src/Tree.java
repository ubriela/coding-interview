import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * @author ubriela
 * 
 */
public class Tree {

	public static void main(String[] args) {
		Tree t = new Tree();
		int[] boss = { 1, 3, 3, -1 };
		System.out.println(t.lowestCommonBoss2(boss, 0, 1));
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
}