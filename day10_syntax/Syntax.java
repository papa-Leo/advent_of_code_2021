import java.util.*;

public class Syntax {
	static Stack<Character> charStack = new Stack<Character>();
	static Long[] syntaxPoints = {0l};
	public static void main(String[] args) {
		int corruptionPoints = 0;
		long currentSyntaxPoint = 0;
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			char[] lineChars = line.toCharArray();
			boolean isCorrupted = false;

			for (char c : lineChars) {
				if (c == ')' || c == '}' || c == ']' || c == '>') {
					if (isValidBracket(c)) {
						charStack.pop();
						continue;
					} else {
						isCorrupted = true;
						if (c == ')') corruptionPoints += 3;
						else if (c == ']') corruptionPoints += 57;
						else if (c == '}') corruptionPoints += 1197;
						else if (c == '>') corruptionPoints += 25137;
						charStack.clear();
						break;
					}
				}
				charStack.push(c);

			}
			System.out.println();
			while (charStack.size() > 0) {
				System.out.println(charStack.peek());
				currentSyntaxPoint *= 5;
				
				if (charStack.peek() == '(') currentSyntaxPoint++;
				else if (charStack.peek() == '[') currentSyntaxPoint += 2;
				else if (charStack.peek() == '{') currentSyntaxPoint += 3;
				else if (charStack.peek() == '<') currentSyntaxPoint += 4;

				charStack.pop();
			}
			if (!isCorrupted) arrayAdd(currentSyntaxPoint);
			currentSyntaxPoint = 0;
			outputArray();
			System.out.println(syntaxPoints[syntaxPoints.length-1]);
		}
		Arrays.sort(syntaxPoints);
		outputArray();

		scanner.close();
		System.out.println("Total points of corruption errors in this file: " + corruptionPoints);
		System.out.println("Total points of incomplete lines in this file: " + syntaxPoints[(int) Math.ceil((syntaxPoints.length)/2)]);
	}

	public static boolean isValidBracket(char bracket) {
		if (bracket == ')' && (charStack.peek() == '{' || charStack.peek() == '[' || charStack.peek() == '<')) return false;
		if (bracket == '}' && (charStack.peek() == '(' || charStack.peek() == '[' || charStack.peek() == '<')) return false;
		if (bracket == ']' && (charStack.peek() == '{' || charStack.peek() == '(' || charStack.peek() == '<')) return false;
		if (bracket == '>' && (charStack.peek() == '{' || charStack.peek() == '[' || charStack.peek() == '(')) return false;
		return true;
	}

	public static void arrayAdd(Long newInt) {
		// Integer[] temp = new Integer[syntaxPoints.length];
		// temp = syntaxPoints;
		// syntaxPoints = new Integer[temp.length+1];
		// syntaxPoints = temp;
		// syntaxPoints[syntaxPoints.length-1] = newInt;
		Long[] temp = new Long[syntaxPoints.length];
		for (int i = 0; i < syntaxPoints.length; i++) {
			temp[i] = syntaxPoints[i];
		}
		syntaxPoints = new Long[temp.length + 1];
		for (int i = 0; i < temp.length; i++) {
			syntaxPoints[i] = temp[i];
		}
		syntaxPoints[syntaxPoints.length-1] = newInt;
	}

	public static void outputArray() {
		System.out.print("Array: ");
		for (Long i : syntaxPoints) {
			System.out.print(", " + i);
		}
		System.out.println();
	}
}
