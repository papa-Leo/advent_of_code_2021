import java.util.*;

public class Origami {
	static boolean[][] paper = new boolean[1][1];
	static boolean foldHorizontal = false;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean firstFoldDone = false;

		while (scanner.hasNextLine()) {
			foldHorizontal = false;
			String input = scanner.nextLine();
			int x = 0, y = 0, foldLine = 0;

			if (input.length() >= 3 && input.indexOf(",") != -1) {
				x = Integer.parseInt(input.substring(0, input.indexOf(",")));
				y = Integer.parseInt(input.substring(input.indexOf(",") + 1, input.length()));
				addDot(x, y);
			} else if (input.length() >= 13) {
				if (input.charAt(11) == 'y') {
					foldHorizontal = true;
				}
				foldLine = Integer.parseInt(Character.toString(input.charAt(13)));
				foldPaper(foldLine);
				firstFoldDone = true;
			}
			// displayResults();
			// System.out.println();
			// System.out.println("-----------------------------------------------");
			// System.out.println();
		}
		scanner.close();
		displayResults();
	}

	public static void addDot(int x, int y) {
		if (y+1 >= paper.length) {
			boolean[][] temp = new boolean[paper.length][paper[0].length];
			for (int i = 0; i < paper.length; i++ ) {
				for (int j = 0; j < paper[0].length; j++ ) {
					temp[i][j] = paper[i][j];
				}
			}
			paper = new boolean[y+1][paper[0].length];
			for (int i = 0; i < temp.length; i++ ) {
				for (int j = 0; j < temp[0].length; j++ ) {
					paper[i][j] = temp[i][j];
				}
			}
		}

		if (x+1 >= paper[0].length) {
			boolean[][] temp = new boolean[paper.length][paper[0].length];
			for (int i = 0; i < paper.length; i++ ) {
				for (int j = 0; j < paper[0].length; j++ ) {
					temp[i][j] = paper[i][j];
				}
			}
			paper = new boolean[paper.length][x+1];
			for (int i = 0; i < temp.length; i++ ) {
				for (int j = 0; j < temp[0].length; j++ ) {
					paper[i][j] = temp[i][j];
				}
			}
		}

		paper[y][x] = true;
	}

	public static void foldPaper(int foldLine) {
		if (foldHorizontal) {
			//for horizontal paper folds
			boolean[][] temp = new boolean[paper.length][paper[0].length];
			for (int i = 0; i < paper.length; i++ ) {
				for (int j = 0; j < paper[0].length; j++ ) {
					temp[i][j] = paper[i][j];
				}
			}
			
			paper = new boolean[(int) Math.floor(paper.length/2)][paper[0].length];

			for (int i = 0; i < paper.length; i++ ) {
				for (int j = 0; j < paper[0].length; j++ ) {
					if (temp[temp.length-i-1][j] || temp[i][j]) paper[i][j] = true;
				}
			}
		}
		else {
			//for vertical paper folds
			boolean[][] temp = new boolean[paper.length][paper[0].length];
			for (int i = 0; i < paper.length; i++ ) {
				for (int j = 0; j < paper[0].length; j++ ) {
					temp[i][j] = paper[i][j];
				}
			}

			paper = new boolean[paper.length][(int) Math.floor(paper[0].length/2)];

			for (int i = 0; i < paper.length; i++ ) {
				for (int j = 0; j < paper[0].length; j++ ) {
					if (temp[i][temp[0].length-j-1] || temp[i][j]) paper[i][j] = true;
				}
			}
		}
	}

	public static void displayResults() {
		int numberOfDotsVisible = 0;
		for (int i = 0; i < paper.length; i++ ) {
			for (int j = 0; j < paper[0].length; j++ ) {
				if (paper[i][j]) {
					System.out.print("#");
					numberOfDotsVisible++;
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		System.out.println("\nNumber of Dots Visible on Page: " + numberOfDotsVisible);
	}
} 
