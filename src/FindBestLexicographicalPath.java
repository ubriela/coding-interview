public final class FindBestLexicographicalPath {

	static int[][] m = { { 3, 1, 4 }, { 5, 9, 2 }, { 6, 8, 7 } };

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] path = getBestPath(m, 3, 3);
		for (int i = 0; i < path.length; i++)
			System.out.println(path[i] + "\t");

	}

	public static int[] getBestPath(int[][] m, int r, int c) {
		boolean[][] invalidMap = new boolean[r][c];
		int[] path = new int[r + c - 1];
		int index = 0;

		// search for all candidates in path (C+R-1)
		for (int p = 0; p < r + c - 1; p++) {
			// search for the next candidate
			int c_i = 0;
			int c_j = 0;
			int c_val = Integer.MAX_VALUE;
			// for (int i = 0; i < r; i++)
			int i = 0;
			int j = 0;
			for (i = 0; i < r; i++) {
				for (j = 0; j < r; j++) {
					if (m[i][j] < c_val && !invalidMap[i][j]) {
						c_val = m[i][j];
						c_i = i;
						c_j = j;
					}
				}
			}

			// update path
			System.out.println("Candidate: " + c_val);
			path[index++] = c_val;
			invalidMap[c_i][c_j] = true;

			// found c_i, c_j --> update invalidMap
			for (i = 0; i < r; i++)
				for (j = 0; j < c; j++) {
					if (!invalidMap[i][j]
							&& ((c_i < i && c_j > j) || c_i > i && c_j < j))
						invalidMap[i][j] = true;
				}

			// print out invalidMap
			for (i = 0; i < r; i++) {
				for (j = 0; j < c; j++) {
					System.out.print(invalidMap[i][j] + "\t");
				}
				System.out.println("");
			}
		}

		return path;
	}
}
