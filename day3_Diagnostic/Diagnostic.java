import java.util.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*;

public class Diagnostic {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		StringSelection output;
		String[][] binNumbers = new String[2][1]; // first index: 0 is most common values, 1 least
		String line = scanner.nextLine();
		int[][] data = new int[line.length()][2];
		binNumbers[0][0] = line;
		binNumbers[1][0] = line;
		
		if (args.length == 0) {
			// CHALLENGE ONE
			while (scanner.hasNextLine()) {
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '0') {
						data[i][0]++;
					} else {
						data[i][1]++;
					}
				}
				line = scanner.nextLine();
			}
			scanner.close();

			String gamma = new String();
			String epsilon = new String();
			for (int i = 0; i < data.length; i++) {
				if (data[i][0] > data[i][1]) {
					gamma += "0";
					epsilon += "1";
				} else {
					gamma += "1";
					epsilon += "0";
				}
			}

			int gammaDecimal = binToDec(gamma);
			int epsilonDecimal = binToDec(epsilon);

			System.out.println("The power consumption of the submarine is: " + gammaDecimal * epsilonDecimal);

			output = new StringSelection(String.valueOf(gammaDecimal * epsilonDecimal));
		}
		else {
			// CHALLENGE TWO
			while (scanner.hasNextLine()) {
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '0') {
						data[i][0]++;
					} else {
						data[i][1]++;
					}
				}
				line = scanner.nextLine();
				binNumbers[0] = addToStringArray(binNumbers[0], line);
				binNumbers[1] = addToStringArray(binNumbers[1], line);
			}
			scanner.close();

			// OXYGEN RATING
			printArray(binNumbers[0]); //remove
			for (int i = 0; i < data.length; i++) {
				if (data[i][0] > data[i][1]) {
					// if 0 more common than 1
					int index = 0;
					for (String binNo : binNumbers[0]) {
						if (binNo.charAt(i) == '1') binNumbers[0] = removeFromStringArray(binNumbers[0], index);
						index++;
						if (binNumbers[0].length <= 1) break;
					}
				} else {
					// if 1 more common than 0 or count(0) == count(1)
					int index = 0;
					for (String binNo : binNumbers[0]) {
						if (binNo.charAt(i) == '0') binNumbers[0] = removeFromStringArray(binNumbers[0], index);
						index++;
						if (binNumbers[0].length <= 1) break;
					}
				}
				if (binNumbers[0].length <= 1) break;
				printArray(binNumbers[0]); //remove
			}

			System.out.println(); //remove
			printArray(binNumbers[1]); //remove
			// CO2_RATING
			for (int i = 0; i < data.length; i++) {
				if (data[i][0] < data[i][1]) {
					// if 1 more common than 0
					int index = 0;
					for (String binNo : binNumbers[1]) {
						if (binNo.charAt(i) == '0') binNumbers[1] = removeFromStringArray(binNumbers[1], index);
						index++;
						if (binNumbers[0].length <= 1) break;
					}
				} else {
					// if 0 more common than 1 or count(0) == count(1)
					int index = 0;
					for (String binNo : binNumbers[1]) {
						if (binNo.charAt(i) == '1') binNumbers[1] = removeFromStringArray(binNumbers[1], index);
						index++;
						if (binNumbers[1].length <= 1) break;
					}
				}
				if (binNumbers[0].length <= 1) break;
				printArray(binNumbers[1]); //remove
			}

			int oxRating = binToDec(binNumbers[0][0]);
			int CO2Rating = binToDec(binNumbers[1][0]);
			System.out.println(binNumbers[0][0].length() + " " + binNumbers[1][0].length());
			System.out.println("O2 rating: " + oxRating + " | CO2 Rating: " + CO2Rating);
			System.out.println("Product of oxygen and CO2: " + oxRating * CO2Rating);
			output = new StringSelection(String.valueOf(oxRating * CO2Rating));
		}

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(output, null);
	}

	public static String[] addToStringArray(String[] array, String newElement) {
		String[] newArr = new String[array.length+1];

		for (int i = 0; i < array.length; i++) {
			newArr[i] = array[i];
		}

		newArr[newArr.length-1] = newElement;
		return newArr;
	}

	public static String[] removeFromStringArray(String[] array, int indexToRemove) {
		String[] newArr = new String[array.length-1];
		int oldArrIndex = 0;

		for (int i = 0; i < newArr.length; i++) {
			if (i >= indexToRemove) oldArrIndex = i+1;
			else oldArrIndex = i;
			newArr[i] = array[oldArrIndex];
		}
		return newArr;
	}

	public static void printArray(String[] array) {
		System.out.println("-------------------------------------------");
		for (String element : array) {
			System.out.println(element);
		}
	}

	public static int binToDec(String binary) {
		int decimal = 0;
		for (int i = binary.length()-1; i >= 0; i--) {
			decimal += Integer.parseInt(binary.substring(binary.length()-1-i, binary.length()-i)) * Math.pow(2, i);
		}
		return decimal;
	}
} 
