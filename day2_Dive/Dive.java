import java.util.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*;

public class Dive {
	public static void main(String[] args) {
		int hozPos = 0, depth = 0;
		Scanner scanner = new Scanner(System.in);

		if (args.length == 0) {
			// CHALLENGE ONE

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.charAt(0) == 'f') {
					hozPos += Integer.parseInt(line.substring(8));
				} else if (line.charAt(0) == 'd') {
					depth += Integer.parseInt(line.substring(5));
				} else if (line.charAt(0) == 'u') {
					depth -= Integer.parseInt(line.substring(3));
				}
			}
			scanner.close();
		}
		else {
			// CHALLENGE TWO
			int aim = 0;

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.charAt(0) == 'f') {
					hozPos += Integer.parseInt(line.substring(8));
					depth += aim * Integer.parseInt(line.substring(8));
				} else if (line.charAt(0) == 'd') {
					aim += Integer.parseInt(line.substring(5));
				} else if (line.charAt(0) == 'u') {
					aim -= Integer.parseInt(line.substring(3));
				}
			}
		}

		int multiplication = depth * hozPos;
		System.out.println("Final multiplication of depth and horizontal distance: " + multiplication);
		
		//Copy program output to system clipboard:
		StringSelection output = new StringSelection(String.valueOf(multiplication));
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(output, null);
		System.out.println("Program output succesfully copied to clipboard.");
	}
} 
