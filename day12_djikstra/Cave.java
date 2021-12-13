import java.util.ArrayList;

public class Cave {
    private String caveName;
    private boolean smallCave;
    private ArrayList<Cave> children = new ArrayList<Cave>();

    Cave(String cave, boolean isSmallCave) {
        caveName = cave;
        smallCave = isSmallCave;
    }

    public String getCaveName() {
        return caveName;
    }

    public void addToChildren(Cave newChild) {
        children.add(newChild);
    }

    public ArrayList<Cave> getChildren() {
        return children;
    }

    public boolean isSmall() {
        return smallCave;
    }
}
