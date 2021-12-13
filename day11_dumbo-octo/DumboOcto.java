import java.util.Scanner;

public class DumboOcto {
	static int numberColumns = 10;
	static int numberLines = 10;
	static int[][] dumbos = new int[numberLines][numberColumns];
	static int stepsToSim = 195;
	static int flashes = 0;
	static int stepsUntilSynced = 0;
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
			
		int currentLine = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int c = 0; c < line.length(); c++) {
				dumbos[currentLine][c] = Integer.parseInt(line.substring(c, c+1));
			}
			currentLine++;
		}
		scanner.close();

		if (args.length == 0) { //Iterative simulation mode (text)

			for (int iterations = 0; iterations < stepsToSim; iterations++) {
				simulateOctos();
				displayGrid();
			}
			System.out.println("\nTotal number of flashes: " + flashes);
		}
		else { //Sync finder mode (text)

			boolean isSynced = false;
			while (!isSynced) {
				simulateOctos();
				displayGrid();

				isSynced = true;
				for (int[] line : dumbos) {
					for (int octo : line) {
						if (octo != 0) {
							isSynced = false;
							break;
						}
					}
					if (!isSynced) break;
				}
				stepsUntilSynced++;
			}

			System.out.println("All the octopuses synchronised for the first time on step " + stepsUntilSynced);
		}
		//Stop program
		System.exit(0);
	}

	public static void simulateOctos() {
		for (int i = 0; i < dumbos.length; i++) {
			for (int j = 0; j < dumbos[i].length; j++) {
				dumbos[i][j]++;
			}
		}

		boolean stepDone = true;
		while (stepDone) {
			stepDone = false;
			for (int i = 0; i < dumbos.length; i++) {
				for (int j = 0; j < dumbos[i].length; j++) {
					if (dumbos[i][j] > 9) {
						stepDone = true;
						flashes++;
						dumbos[i][j] = 0;

						for (int m = -1; m <= 1; m++) {
							for (int n = -1; n <= 1; n++) {
								int x = i + m;
								int y = j + n;

								if (x < 0 || x > dumbos.length-1 || y < 0 || y > dumbos[i].length-1) continue;

								if (dumbos[x][y] != 0) {
									dumbos[x][y]++;
								}
							}
						}
					}
				}
			}
		}
	}

	public static void displayGrid() {
		System.out.println();
		for (int[] line : dumbos) {
			for (int column : line) {
				System.out.print(column);
			}
			System.out.println();
		}
	}
}
