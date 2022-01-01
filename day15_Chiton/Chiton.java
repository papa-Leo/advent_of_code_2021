import java.util.*;

public class Chiton {
	static int[][] map = new int[0][0];
	public static void main(String[] args) {
		boolean[][] visited = new boolean[0][0];
		Scanner scanner = new Scanner(System.in);

		//input
		while (scanner.hasNextLine()) {
			addToMap(true, 0);

			char[] line = scanner.nextLine().toCharArray();

			if (map[0].length < line.length) {
				addToMap(false, line.length);
			}
			
			for (int i = 0; i < line.length; i++) {
				map[map.length-1][i] = Integer.parseInt(Character.toString(line[i]));
			}
		}

		visited = new boolean[map.length][map[0].length];
		scanner.close();

		//perform uniform search
		System.out.println("Lowest path risk: " + search(0, 0, 0, visited));
	}

	public static int search(int pathScore, int i, int j, boolean[][] visited) {
		System.out.println(pathScore);
		if (i == map.length-1 && j == map[0].length-1) return pathScore += map[i][j];
		if (i < 0 || i >= map.length) return -1;
		if (j < 0 || j >= map[0].length) return -1;
		if (visited[i][j] == true) return -1;

		visited[i][j] = true;
		pathScore += map[i][j];

		return minPathScore(search(pathScore, i+1, j, visited), minPathScore(search(pathScore, i, j+1, visited), minPathScore(search(pathScore, i-1, j, visited), search(pathScore, i, j-1, visited))));
	}

	public static void addToMap(boolean addingLine, int length) {
		if (map.length > 0) {
			int[][] temp = new int[map.length][map[0].length];
			if (addingLine) {
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[0].length; j++) {
						temp[i][j] = map[i][j];
					}
				}
				map = new int[map.length+1][map[0].length];
				for (int i = 0; i < temp.length; i++) {
					for (int j = 0; j < temp[0].length; j++) {
						map[i][j] = temp[i][j];
					}
				}
			} else {
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[0].length; j++) {
						temp[i][j] = map[i][j];
					}
				}
				map = new int[map.length][length];
				for (int i = 0; i < temp.length; i++) {
					for (int j = 0; j < temp[0].length; j++) {
						map[i][j]= temp [i][j];
					}
				}
			}
		} else {
			map = new int[1][1];
		}
	}

	public static int minPathScore(int a, int b) {
		if (a > -1 && b > -1) {
			return Math.min(a, b);
		} else if (a > -1 && b <= -1) {
			return a;
		} else if (a <= -1 && b > -1) {
			return b;
		} else return -1;
	}

	public static void printMap() {
		int total = 0;
		for (int[] line : map) {
			for (int node : line) {
				System.out.print(node + " ");
				total += node;
			}
			System.out.println();
		}
		System.out.println(total);
	}
} 
