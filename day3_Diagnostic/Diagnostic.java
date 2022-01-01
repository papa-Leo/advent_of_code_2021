import java.util.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*;

public class Diagnostic {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String line = scanner.nextLine();
		int[][] data = new int[line.length()][2];
		
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

		int gammaDecimal = 0;
		for (int i = gamma.length()-1; i >= 0; i--) {
			gammaDecimal += Integer.parseInt(gamma.substring(gamma.length()-1-i, gamma.length()-i)) * Math.pow(2, i);
		}

		int epsilonDecimal = 0;
		for (int i = epsilon.length()-1; i >= 0; i--) {
			epsilonDecimal += Integer.parseInt(epsilon.substring(epsilon.length()-1-i, gamma.length()-i)) * Math.pow(2, i);
		}

		System.out.println("The power consumption of the submarine is: " + gammaDecimal * epsilonDecimal);

		StringSelection output = new StringSelection(String.valueOf(gammaDecimal * epsilonDecimal));
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(output, null);
	}
} 
