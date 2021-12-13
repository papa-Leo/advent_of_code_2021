import java.util.ArrayList;
import java.util.Scanner;

public class PathFinder {
	static Cave start;
	static Cave currentCave;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("-\n");

		String caveName = scanner.next();
		start = new Cave(caveName, hasSmallChar(caveName));

		String childName = scanner.next();
		Cave newChild = new Cave(childName, hasSmallChar(childName));
		start.addToChildren(newChild);

		while (scanner.hasNextLine()) {
			caveName = scanner.next();

		}
		scanner.close();
	}

	public static boolean hasSmallChar(String caveName) {
		char[] cave = caveName.toCharArray();
		for (char c : cave) {
			if (Character.isUpperCase(c)) return false;
		}
		return true;
	}

	public static void findCave(String cave) {
		currentCave = start;
		ArrayList<Cave> DFS = new ArrayList<Cave>();

		while (currentCave.getCaveName() != cave) {
			DFS.addAll(currentCave.getChildren());
			currentCave = DFS.get(DFS.size()-1);
		}
	}

	public static boolean findCave(String cave) {
		currentCave = start;
		ArrayList<Cave> DFS = new ArrayList<Cave>();

		while (currentCave.getCaveName() != cave && currentCave.getChildren().size() > 0) {
			DFS.addAll(currentCave.getChildren());
			currentCave = DFS.get(DFS.size()-1);
		}

	}
}
