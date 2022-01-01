import java.util.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class Sonar {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int currentDepth = Integer.parseInt(scanner.nextLine());
		int count = 0;

		while (scanner.hasNextLine()) {
			int previousDepth = currentDepth;
			currentDepth = Integer.parseInt(scanner.nextLine());

			if (currentDepth > previousDepth) {
				count++;
			}
		}
		scanner.close();

		System.out.println("Total number of depth increases: " + count);

		StringSelection output = new StringSelection(String.valueOf(count));
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(output, null);
		System.out.println("Program ouput successfully copied to clipboard.");
	}
} 
