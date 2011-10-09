package problem;

import eightPuzzle.EightPuzzleNode;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface Node {


    public void setParent(Node parent);
    public int getGValue();

    public String getMoveFromParent();
    public void setMoveFromParent(String moveFromParent);
    public void copyState(Object stateToCopy);
    public int getHeuristicValue(Map<Integer, String> goalState);
    public ArrayList<Node> getChildren();
    public String getPath();
    Node getParent();
}
