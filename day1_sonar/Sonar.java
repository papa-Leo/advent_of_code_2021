import java.util.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class Sonar {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		
		if (args.length == 0) {
			// CHALLENGE ONE
			int currentDepth = Integer.parseInt(scanner.nextLine());
			while (scanner.hasNextLine()) {
				int previousDepth = currentDepth;
				currentDepth = Integer.parseInt(scanner.nextLine());

				if (currentDepth > previousDepth) {
					count++;
				}
			}
			scanner.close();
		} 
		else {
			// CHALLENGE TWO
			int[] currentWindow = new int[3];
			int previousSum = 0;

			currentWindow[2] = Integer.parseInt(scanner.nextLine());
			currentWindow[1] = Integer.parseInt(scanner.nextLine());
			currentWindow[0] = Integer.parseInt(scanner.nextLine());
			
			while (scanner.hasNextLine()) {				
				previousSum = currentWindow[2] + currentWindow[1] + currentWindow[0];
				currentWindow[2] = currentWindow[1];
				currentWindow[1] = currentWindow[0];
				currentWindow[0] = Integer.parseInt(scanner.nextLine());

				if (currentWindow[2] + currentWindow[1] + currentWindow[0] > previousSum) {
					count++;
				}
			}
		}

		System.out.println("Total number of depth increases: " + count);

		StringSelection output = new StringSelection(String.valueOf(count));
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(output, null);
		System.out.println("Program ouput successfully copied to clipboard.");
	}
} 
